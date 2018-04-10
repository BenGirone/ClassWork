using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace ElGamal
{
    public class Controller
    {
        Form1 mainWindow;
        FakeTCPClient localClient;

        public Controller(Form1 mainWindow, int encryptionLevel)
        {
            this.mainWindow = mainWindow;
            this.localClient = new FakeTCPClient(encryptionLevel);

            this.mainWindow.ConsoleWrite("Generating public key...");
            Thread keyThread = new Thread(GeneratePublicKey);
            keyThread.Start();
        }

        public void SendMessage(string s, string remoteIP)
        {

        }

        //public string GetLocalAddress()
        //{

        //}

        private void GeneratePublicKey()
        {
            localClient.CreatePublicKey();
            mainWindow.ConsoleWrite("Public key has been generated\nReady");
        }
    }
}
