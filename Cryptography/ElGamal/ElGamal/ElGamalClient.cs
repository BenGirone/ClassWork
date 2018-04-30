using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ElGamal
{
    class ElGamalClient
    {
        //variable declaration
        private BigInteger p;
        private BigInteger alpha;
        private BigInteger a;
        private int encryptionLevel;

        public ElGamalClient(int encryptionLevel)
        {
            this.encryptionLevel = encryptionLevel;
        }

        /// <summary>
        /// Creates the values to be used in a public key
        /// </summary>
        /// <returns>An array of big integers that can be used as a public key</returns>
        public BigInteger[] CreatePublicKey()
        {
            BigInteger[] safe_p = BigPrimes.GetSafePrime(BigInteger.Pow(2, encryptionLevel));
            p = safe_p[0];
            alpha = BigPrimes.GetPrimitiveFromSafePrime(safe_p);
            a = BigPrimes.RandomBigInteger(p);
            BigInteger alpha_a = BigInteger.ModPow(alpha, a, p);

            return new BigInteger[] { p, alpha, alpha_a };
        }

        /// <summary>
        /// Uses Elgamal encryption to encrypt a message.
        /// </summary>
        /// <param name="plainText">The text to be encrypted.</param>
        /// <param name="p">Public prime for encryption.</param>
        /// <param name="alpha">Public primitive root for encryption.</param>
        /// <param name="alpha_a">The remote endpoint's half of the shared secret.</param>
        /// <returns>The cipher text corresponding to the provided plain text.</returns>
        public string Encrypt(string plainText, BigInteger p, BigInteger alpha, BigInteger alpha_a)
        {
            //the size of each block to be sent
            int blockSize = p.ToByteArray().Length - 1;

            //encodes the plain text
            List<byte> plainTextBytes = Encoding.UTF8.GetBytes(plainText).ToList();

            //create this end points half of the shared secret
            BigInteger b = BigPrimes.RandomBigInteger(p);
            BigInteger alpha_b = BigInteger.ModPow(alpha, b, p);

            string cipherText = alpha_b.ToString() + ",";
            BigInteger block;

            //generate the full shared secret
            BigInteger alpha_ab = BigInteger.ModPow(alpha_a, b, p);

            //encrypt all each block of plain text
            int i = 0;
            while (i < plainTextBytes.Count - blockSize)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, blockSize).ToArray());

                i += blockSize;

                block = (block * alpha_ab) % p;

                cipherText += block.ToString() + " ";
            }

            //get the final block if plain text if the loop missed it
            if (i < plainTextBytes.Count - 1)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, plainTextBytes.Count - i).ToArray());

                block = (block * alpha_ab) % p;

                cipherText += block.ToString() + " ";
            }

            return cipherText;
        }

        /// <summary>
        /// Decrypts cipher text to plain text.
        /// </summary>
        /// <param name="alpha_b">The remote endpoint's half of the shared secret.</param>
        /// <param name="cipherText">A string containing cipher text formatted into blocks.</param>
        /// <returns>A string containing plain text.</returns>
        public string Decrypt(BigInteger alpha_b, string cipherText)
        {
            string[] cipherBlocks = cipherText.Split(' ');

            BigInteger alpha_ab_inv = BigInteger.ModPow(alpha_b, (p - 1) - a, p);
            BigInteger block;

            string plainText = "";
            for (int i = 0; i < cipherBlocks.Length - 1; i++)
            {
                block = BigInteger.Parse(cipherBlocks[i]);

                block = (block * alpha_ab_inv) % p;

                plainText += Encoding.UTF8.GetString(block.ToByteArray());
            }

            return plainText;
        }
    }
}
