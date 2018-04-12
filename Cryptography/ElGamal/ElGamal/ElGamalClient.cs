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
        private BigInteger p;
        private BigInteger alpha;
        private BigInteger a;
        private int encryptionLevel;

        public ElGamalClient(int encryptionLevel)
        {
            this.encryptionLevel = encryptionLevel;
        }

        public BigInteger[] CreatePublicKey()
        {
            BigInteger[] safe_p = BigPrimes.GetSafePrime(BigInteger.Pow(2, encryptionLevel));
            p = safe_p[0];
            alpha = BigPrimes.GetPrimitiveFromSafePrime(safe_p);
            a = BigPrimes.RandomBigInteger(p);
            BigInteger alpha_a = BigInteger.ModPow(alpha, a, p);

            return new BigInteger[] { p, alpha, alpha_a };
        }

        public string Encrypt(string plainText, BigInteger p, BigInteger alpha, BigInteger alpha_a)
        {
            int blockSize = p.ToByteArray().Length - 1;

            List<byte> plainTextBytes = Encoding.UTF8.GetBytes(plainText).ToList();

            BigInteger b = BigPrimes.RandomBigInteger(p);
            BigInteger alpha_b = BigInteger.ModPow(alpha, b, p);

            string cipherText = alpha_b.ToString() + ",";
            BigInteger block;
            BigInteger alpha_ab = BigInteger.ModPow(alpha_a, b, p);

            int i = 0;
            while (i < plainTextBytes.Count - blockSize)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, blockSize).ToArray());

                i += blockSize;

                block = (block * alpha_ab) % p;

                cipherText += block.ToString() + " ";
            }

            if (i < plainTextBytes.Count - 1)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, plainTextBytes.Count - i).ToArray());

                block = (block * alpha_ab) % p;

                cipherText += block.ToString() + " ";
            }

            return cipherText;
        }

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
