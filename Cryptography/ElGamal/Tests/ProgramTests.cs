using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace Tests
{
    class ProgramTests
    {
        static void Main(string[] args)
        {
            Console.WriteLine(Decrypt(Encrypt("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pharetra, sem eget imperdiet pellentesque, sapien odio congue sapien, ut tincidunt erat urna vitae eros. Morbi convallis libero sed tellus mattis porttitor. Nullam id odio maximus purus pretium finibus non nec tortor. Nullam quis orci a leo iaculis iaculis. Ut non urna quis elit posuere feugiat suscipit id augue. Maecenas aliquet pellentesque ante sed faucibus. Aliquam non dignissim massa. Proin fringilla erat tortor, in vehicula odio blandit vitae. Nam sed sapien ullamcorper, ullamcorper orci at, rhoncus orci. Morbi sodales efficitur massa, vitae lacinia nisi eleifend vel. Sed ante leo, semper et luctus ac, tincidunt vel justo.")));
            Console.ReadLine();
        }

        private static string Encrypt(string plainText)
        {
            BigInteger p = BigInteger.Parse("1234567891011121314151617");
            int blockSize = p.ToByteArray().Length - 1;

            List<byte> plainTextBytes = Encoding.BigEndianUnicode.GetBytes(plainText).ToList();

            string cipherText = "";
            int i = 0;
            while (i < plainTextBytes.Count - blockSize)
            {
                BigInteger block = new BigInteger(plainTextBytes.GetRange(i, blockSize).ToArray());

                i += blockSize;

                //TODO: encrypt

                cipherText += block.ToString() + " ";
            }

            if (i < plainTextBytes.Count - 1)
            {
                BigInteger block = new BigInteger(plainTextBytes.GetRange(i, plainTextBytes.Count - i).ToArray());
                cipherText += block.ToString() + " ";
            }
            
            return cipherText;
        }

        private static string Decrypt(string cipherText)
        {
            string[] cipherBlocks = cipherText.Split(' ');

            string plainText = "";
            for (int i = 0; i < cipherBlocks.Length - 1; i++)
            {
                plainText += Encoding.BigEndianUnicode.GetString(BigInteger.Parse(cipherBlocks[i]).ToByteArray());
            }

            return plainText;
        }
    }
}
