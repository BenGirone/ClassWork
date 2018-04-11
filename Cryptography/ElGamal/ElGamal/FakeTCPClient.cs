using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.IO;
using System.Numerics;

namespace ElGamal
{
    public class FakeTCPClient
    {
        private string ip = "10.0.0.";
        private string currentDirectory = string.Empty;

        public FakeTCPClient()
        {
            ip += new Random().Next(2,999).ToString();
            currentDirectory = Directory.GetCurrentDirectory();

            Directory.CreateDirectory(currentDirectory + "/" + ip);
            ClearFolder();

            Directory.CreateDirectory(currentDirectory + "/internet");
        }

        public bool Ping(string remoteAddress)
        {
            return remoteAddress.StartsWith("1") && Directory.Exists((Directory.GetCurrentDirectory() + "/" + remoteAddress));
        }

        //https://stackoverflow.com/questions/1288718/
        private void ClearFolder()
        {
            DirectoryInfo di = new DirectoryInfo(currentDirectory + "/" + ip);

            foreach (FileInfo file in di.GetFiles())
            {
                file.Delete();
            }
        }

        public void ExposeFile(string fileName, string fileText)
        {
            // Delete the file if it exists.
            if (File.Exists(currentDirectory + "/" + ip + "/"  + fileName))
                File.Delete(currentDirectory + "/" + ip + "/" + fileName);

            File.WriteAllText(currentDirectory + "/" + ip + "/" + fileName, fileText);
        }

        public string RetrieveFile(string remoteAddress, string fileName)
        {
            return File.ReadAllText(currentDirectory + "/" + remoteAddress + "/" + fileName);
        }

        public void Transmit(string remoteAddress, string fileName, string fileText)
        {
            ExposeFile(fileName, fileText);
            Thread t = new Thread(simulateTransmission);
            transmissionData data = new transmissionData { remoteAddress = remoteAddress, fileName = fileName };
            t.Start(data);
        }
        
        private void simulateTransmission(object threadData)
        {
            transmissionData data = (transmissionData)threadData;
            File.Move(currentDirectory + "/" + ip + "/" + data.fileName, currentDirectory + "/internet/" + data.fileName + " - in transmission");
            Thread.Sleep(1000);
            File.Move(currentDirectory + "/internet/" + data.fileName + " - in transmission", currentDirectory + "/" + data.remoteAddress + "/" + "From " + ip + ":" + data.fileName);
        }
    }

    struct transmissionData
    {
        public string remoteAddress;
        public string fileName;
    }
}
