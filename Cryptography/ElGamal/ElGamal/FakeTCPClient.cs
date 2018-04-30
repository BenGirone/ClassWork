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

        public string LocalAddress { get { return ip; } }
        public string LocalDirectory { get { return currentDirectory + "\\" + ip; } }

        public FakeTCPClient()
        {
            ip += new Random().Next(2,999).ToString();
            currentDirectory = Directory.GetCurrentDirectory();

            Directory.CreateDirectory(currentDirectory + "/" + ip);
            ClearFolder();

            Directory.CreateDirectory(currentDirectory + "/internet");
        }

        /// <summary>
        /// Simulates the network operation of pinging a remote node over a network.
        /// </summary>
        /// <param name="remoteAddress">The IP address of the remote endpoint.</param>
        /// <returns>True if the remote endpoint is accessible.</returns>
        public bool Ping(string remoteAddress)
        {
            return remoteAddress.StartsWith("1") && Directory.Exists((Directory.GetCurrentDirectory() + "/" + remoteAddress));
        }

        /// <summary>
        /// Clears the folder used by this endpoint.
        /// </summary>
        /// <credit>https://stackoverflow.com/questions/1288718/</credit>
        private void ClearFolder()
        {
            DirectoryInfo di = new DirectoryInfo(currentDirectory + "/" + ip);

            foreach (FileInfo file in di.GetFiles())
            {
                file.Delete();
            }
        }

        /// <summary>
        /// Makes a file accessible to other endpoints.
        /// </summary>
        /// <param name="fileName">A name for the file being exposed.</param>
        /// <param name="fileText">The content of the file being exposed.</param>
        public void ExposeFile(string fileName, string fileText)
        {
            // Delete the file if it exists.
            if (File.Exists(currentDirectory + "/" + ip + "/"  + fileName))
                File.Delete(currentDirectory + "/" + ip + "/" + fileName);

            File.WriteAllText(currentDirectory + "/" + ip + "/" + fileName, fileText);
        }

        /// <summary>
        /// Retrieves the content of a file that has been made accessible by another endpoint.
        /// </summary>
        /// <param name="remoteAddress">The IP address of the remote endpoint.</param>
        /// <param name="fileName">The name of the file to be retrieved.</param>
        /// <returns></returns>
        public string RetrieveFile(string remoteAddress, string fileName)
        {
            return File.ReadAllText(currentDirectory + "/" + remoteAddress + "/" + fileName);
        }

        /// <summary>
        /// Sends a file to a remote endpoint 
        /// </summary>
        /// <param name="remoteAddress">The IP address of the remote endpoint.</param>
        /// <param name="fileName">The name of the file to be transmitted</param>
        /// <param name="fileText">The content of the file to be transmitted</param>
        public void Transmit(string remoteAddress, string fileName, string fileText)
        {
            ExposeFile(fileName, fileText);
            Thread t = new Thread(simulateTransmission);
            transmissionData data = new transmissionData { remoteAddress = remoteAddress, fileName = fileName };
            t.Start(data);
        }
        
        /// <summary>
        /// Simulates the transmission of a file over a network
        /// </summary>
        /// <param name="threadData"></param>
        private void simulateTransmission(object threadData)
        {
            transmissionData data = (transmissionData)threadData;

            //simulates the file moving through a public channel
            //this will be used later to test how quickly messages can be intercepted and decrypted by an attacker
            File.Move(currentDirectory + "/" + ip + "/" + data.fileName, currentDirectory + "/internet/" + data.fileName + " - in transmission");

            Thread.Sleep(1000); //latency

            //after the simulated latency the file arrives at the recieving endpoint
            File.Move(currentDirectory + "/internet/" + data.fileName + " - in transmission", currentDirectory + "/" + data.remoteAddress + "/" + data.fileName);
        }

        ~FakeTCPClient()
        {
            Directory.Delete(currentDirectory + "/" + ip, true);
        }
    }

    struct transmissionData
    {
        public string remoteAddress;
        public string fileName;
    }
}
