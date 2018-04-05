using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Numerics;

namespace ElGamalClient
{
    public class FakeTCPClient
    {
        private string ip = "10.0.0.";
        private BigInteger p;


        public FakeTCPClient()
        {
            this.ip += new Random().Next(2,999).ToString();
            p = BigPrimes.getPrime(512);
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/" + ip);
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/internet");
        }

        public void TransmitMessage(string remoteAddress, string plainText)
        {
            string encodedText = Encode(plainText);

        }

        private string encrypt(string encodedText)
        {

        }

        private string Encode(string plainText)
        {
            var plainTextBytes = Encoding.UTF8.GetBytes(plainText);
            return Convert.ToBase64String(plainTextBytes);
        }

        private string Decode(string base64EncodedData)
        {
            var base64EncodedBytes = Convert.FromBase64String(base64EncodedData);
            return Encoding.UTF8.GetString(base64EncodedBytes);
        }
    }
}
