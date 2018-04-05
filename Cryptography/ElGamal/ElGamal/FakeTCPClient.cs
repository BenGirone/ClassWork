using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.IO;
using System.Numerics;

namespace ElGamalClient
{
    public class FakeTCPClient
    {
        private string ip = "10.0.0.";
        private BigInteger p;
        private BigInteger a;


        public FakeTCPClient()
        {
            this.ip += new Random().Next(2,999).ToString();

            BigInteger[] safe_p = BigPrimes.GetSafePrime(512);
            p = safe_p[0];
            a = BigPrimes.GetPrimitiveFromSafePrime(safe_p);

            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/" + ip);
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/internet");
        }

        public void TransmitMessage(string remoteAddress, string plainText)
        {
            string encodedText = Encode(plainText);
            string encryptedText = Encrypt(plainText);

        }

        private string Encrypt(string plainText)
        {
            string encodedText = Encode(plainText);



            return encodedText;
        }

        private string Encode(string plainText)
        {
            byte[] plainTextBytes = Encoding.Unicode.GetBytes(plainText);
            return Convert.ToBase64String(plainTextBytes);
        }

        private string Decode(string base64EncodedData)
        {
            var base64EncodedBytes = Convert.FromBase64String(base64EncodedData);
            return Encoding.Unicode.GetString(base64EncodedBytes);
        }
    }
}
