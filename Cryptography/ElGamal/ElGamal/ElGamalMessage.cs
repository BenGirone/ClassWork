using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace ElGamal
{
    public class ElGamalMessage
    {

        public string Text;
        public string RecipientIP;
        public string SenderIP;
        private BigInteger b;
        private BigInteger p;
        private BigInteger alpha;
        private BigInteger alpha_a;

        public ElGamalMessage(string plainText, string recipientIP, string senderIP, BigInteger b, BigInteger p, bool isPlainText = true)
        {
            Text = plainText;
            RecipientIP = recipientIP;
            SenderIP = senderIP;
            this.b = b;
            this.p = p;

            if (isPlainText)
            {
                Text = Encrypt(Text);
            }
        }

        //public static ElGamalMessage BuildFromFile(string fileLocation)
        //{

        //}

        //public string ToFile()
        //{

        //}

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
