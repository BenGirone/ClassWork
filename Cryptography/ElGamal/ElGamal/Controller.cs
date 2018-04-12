using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading;

namespace ElGamal
{
    public class Controller
    {
        Form1 mainWindow;
        FakeTCPClient localHost;
        ElGamalClient localClient;

        public Controller(Form1 mainWindow, int encryptionLevel)
        {
            this.mainWindow = mainWindow;
            this.localClient = new ElGamalClient(encryptionLevel);
            this.localHost = new FakeTCPClient();

            this.mainWindow.ConsoleWrite("Generating public key...");
            Thread keyThread = new Thread(GeneratePublicKey);
            keyThread.Start();
        }

        public void SendMessage(string messageText, string remoteIP)
        {
            mainWindow.ConsoleWrite("Attempting to connect to " + remoteIP);

            if (localHost.Ping(remoteIP))
            {
                mainWindow.ConsoleWrite("Retrieving public key");
                string[] publicKeyContents = localHost.RetrieveFile(remoteIP, "publickey").Split(' ');

                mainWindow.ConsoleWrite("Encrypting plain text");
                messageText = localClient.Encrypt(messageText, BigInteger.Parse(publicKeyContents[0]), BigInteger.Parse(publicKeyContents[1]), BigInteger.Parse(publicKeyContents[2]));

                mainWindow.ConsoleWrite("Transmitting message");
                localHost.Transmit(remoteIP, "From - " + localHost.LocalAddress + ".elgamal", messageText);

                mainWindow.ConsoleWrite("Done");
            }
            else
            {
                mainWindow.ConsoleWrite("Could not connect to " + remoteIP);
            }
        }

        public void OpenMessage(string fileName)
        {
            mainWindow.ConsoleWrite("Opening message");
            string rawMessage = localHost.RetrieveFile(localHost.LocalAddress, fileName);
            string[] messageContents = rawMessage.Split(',');

            string plainText = localClient.Decrypt(BigInteger.Parse(messageContents[0]), messageContents[1]);

            mainWindow.DisplayMessage(plainText);
            mainWindow.ConsoleWrite("done");

        }

        public string GetInbox()
        {
            return localHost.LocalDirectory;
        }

        private void GeneratePublicKey()
        {
            BigInteger[] key = localClient.CreatePublicKey();
            localHost.ExposeFile("publickey", key[0].ToString() + " " + key[1].ToString() + " " + key[2].ToString());

            mainWindow.ConsoleWrite("Key Ready");
        }
    }
}
