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
        //variable declaration
        Form1 mainWindow;
        FakeTCPClient localHost;
        ElGamalClient localClient;

        public Controller(Form1 mainWindow, int encryptionLevel)
        {
            this.mainWindow = mainWindow;
            this.localClient = new ElGamalClient(encryptionLevel);
            this.localHost = new FakeTCPClient();

            this.mainWindow.ConsoleWrite("Starting TCP client on " + this.localHost.LocalAddress);
            this.mainWindow.ConsoleWrite("Generating public key...");
            Thread keyThread = new Thread(GeneratePublicKey);
            keyThread.Start();
        }

        /// <summary>
        /// Takes a message from the view and encrypts it before sending it to another client. 
        /// </summary>
        /// <param name="messageText">The content of the message to be sent.</param>
        /// <param name="remoteIP">The IP address of the remote endpoint.</param>
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

        /// <summary>
        /// Reads a message from a file and sends it to the view.
        /// </summary>
        /// <param name="fileName">The name of the file to be read.</param>
        public void OpenMessage(string fileName)
        {
            mainWindow.ConsoleWrite("Opening message");
            string rawMessage = localHost.RetrieveFile(localHost.LocalAddress, fileName);
            string[] messageContents = rawMessage.Split(',');

            string plainText = localClient.Decrypt(BigInteger.Parse(messageContents[0]), messageContents[1]);

            mainWindow.DisplayMessage(plainText);
            mainWindow.ConsoleWrite("done");

        }

        /// <summary>
        /// Gets the folder were inbound messages are stored
        /// </summary>
        /// <returns>A string that holds the address of the folder.</returns>
        public string GetInbox()
        {
            return localHost.LocalDirectory;
        }

        /// <summary>
        /// Creates a public key for this endpoint.
        /// </summary>
        private void GeneratePublicKey()
        {
            BigInteger[] key = localClient.CreatePublicKey();
            localHost.ExposeFile("publickey", key[0].ToString() + " " + key[1].ToString() + " " + key[2].ToString());

            mainWindow.ConsoleWrite("Key Ready");
        }
    }
}
