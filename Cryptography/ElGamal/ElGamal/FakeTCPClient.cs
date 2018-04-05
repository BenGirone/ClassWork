using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace ElGamalClient
{
    public class FakeTCPClient
    {
        string ip = "10.0.0.";

        public FakeTCPClient()
        {
            this.ip += new Random().Next(2,999).ToString();
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/" + ip);
            Directory.CreateDirectory(Directory.GetCurrentDirectory() + "/internet");
        }
    }
}
