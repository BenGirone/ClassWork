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
        private int encryptionLevel;

        public FakeTCPClient(int encryptionLevel)
        {
            this.ip += new Random().Next(2,999).ToString();
            this.encryptionLevel = encryptionLevel;
        }

        public void CreatePublicKey()
        {
            BigInteger[] safe_p = BigPrimes.GetSafePrime(BigInteger.Pow(2, encryptionLevel));
            p = safe_p[0];
            alpha = BigPrimes.GetPrimitiveFromSafePrime(safe_p);
            a = BigPrimes.RandomBigInteger(p);

            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/" + ip);
            ClearFolder();

            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/internet");

            ExposePublicKey();
        }

        //https://stackoverflow.com/questions/1288718/how-to-delete-all-files-and-folders-in-a-directory?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        private void ClearFolder()
        {
            DirectoryInfo di = new DirectoryInfo(Directory.GetCurrentDirectory() + "/" + ip);

            foreach (FileInfo file in di.GetFiles())
            {
                file.Delete();
            }
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
            return plainText;
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
