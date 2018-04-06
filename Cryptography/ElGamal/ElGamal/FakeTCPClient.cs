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
        private BigInteger alpha;
        private BigInteger a;


        public FakeTCPClient()
        {
            this.ip += new Random().Next(2,999).ToString();

            BigInteger[] safe_p = BigPrimes.GetSafePrime(128);
            p = safe_p[0];
            alpha = BigPrimes.GetPrimitiveFromSafePrime(safe_p);
            a = BigPrimes.RandomBigInteger(128) % p;

            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/" + ip);
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/internet");

            ExposePublicKey();
        }

        private void ExposePublicKey()
        {
            // Delete the file if it exists.
            if (File.Exists(Directory.GetCurrentDirectory() + "/" + ip + "/publickey"))
                File.Delete(Directory.GetCurrentDirectory() + "/" + ip + "/publickey");
            
            // Create the file.
            using (FileStream fs = File.Create(Directory.GetCurrentDirectory() + "/" + ip + "/publickey"))
            {
                Byte[] key = new UTF8Encoding(true).GetBytes(p.ToString() + "," + alpha.ToString() + "," + BigInteger.ModPow(alpha, a, p).ToString());
                
                // Add key.
                fs.Write(key, 0, key.Length);
            }
        }

        public BigInteger[] ReadRemoteKey(string remoteAddress)
        {
            // Open the stream and read
            using (StreamReader sr = File.OpenText(Directory.GetCurrentDirectory() + "/" + remoteAddress + "/publickey"))
            {
                string[] s = sr.ReadLine().Split(',');
                return new BigInteger[] { BigInteger.Parse(s[0]), BigInteger.Parse(s[1]), BigInteger.Parse(s[2]) }; 
            }
        }

        public void TransmitMessage(string remoteAddress, string plainText)
        {
            BigInteger[] remotePublicKey = ReadRemoteKey(remoteAddress);
            
            string cipherText = Encrypt(Encode(plainText));


        }

        private string Encrypt(string plainText)
        {
            
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
