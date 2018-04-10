﻿using System;
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

        public void CreatePublicKey()
        {
            BigInteger[] safe_p = BigPrimes.GetSafePrime(BigInteger.Pow(2, encryptionLevel));
            p = safe_p[0];
            alpha = BigPrimes.GetPrimitiveFromSafePrime(safe_p);
            a = BigPrimes.RandomBigInteger(p);
        }

        private string Encrypt(string plainText)
        {
            int blockSize = p.ToByteArray().Length - 1;

            List<byte> plainTextBytes = Encoding.BigEndianUnicode.GetBytes(plainText).ToList();

            string cipherText = "";
            BigInteger block;
            BigInteger alpha_ab = BigInteger.ModPow(alpha_a, b, p);

            int i = 0;
            while (i < plainTextBytes.Count - blockSize)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, blockSize).ToArray());

                i += blockSize;

                block *= alpha_ab % p;

                cipherText += block.ToString() + " ";
            }

            if (i < plainTextBytes.Count - 1)
            {
                block = new BigInteger(plainTextBytes.GetRange(i, plainTextBytes.Count - i).ToArray());

                cipherText += block.ToString() + " ";
            }

            return cipherText;
        }

        private string Decrypt(BigInteger alpha_b, string cipherText)
        {
            string[] cipherBlocks = cipherText.Split(' ');

            BigInteger alpha_ab_inv = BigInteger.ModPow(alpha_b, p - 1 - a, p);
            BigInteger block;

            string plainText = "";
            for (int i = 0; i < cipherBlocks.Length - 1; i++)
            {
                block = BigInteger.Parse(cipherBlocks[i]);

                block *= alpha_ab_inv % p;

                plainText += Encoding.BigEndianUnicode.GetString(block.ToByteArray());
            }

            return plainText;
        }
    }
}
