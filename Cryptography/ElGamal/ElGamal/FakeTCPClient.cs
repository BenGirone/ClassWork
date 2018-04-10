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

        //https://stackoverflow.com/questions/1288718/how-to-delete-all-files-and-folders-in-a-directory?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        private void ClearFolder()
        {
            DirectoryInfo di = new DirectoryInfo(currentDirectory + "/" + ip);

            foreach (FileInfo file in di.GetFiles())
            {
                file.Delete();
            }
        }

        private void ExposeFile(string fileName, string fileText)
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
        }
    }
}
