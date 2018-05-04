using System;
using System.Windows.Forms;
using System.IO;

namespace ElGamal
{
    public partial class Form1 : Form
    {
        delegate void ConsoleWriteCallback(string s);

        public Form1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Writes to the console window.
        /// </summary>
        /// <param name="s">A string of text to be written to the console window</param>
        /// <credit>https://stackoverflow.com/questions/10775367/</credit>
        public void ConsoleWrite(string s)
        {
            // InvokeRequired required compares the thread ID of the
            // calling thread to the thread ID of the creating thread.
            // If these threads are different, it returns true.
            if (output.InvokeRequired)
            {
                ConsoleWriteCallback d = new ConsoleWriteCallback(ConsoleWrite);
                this.Invoke(d, new object[] { s });
            }
            else
            {
                output.Text += (s + "\n");
            }
        }

        /// <summary>
        /// Prompts the program controller to send the message to the provided IP.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sendMessage_Click(object sender, EventArgs e)
        {
            Program.controller.SendMessage(plainText.Text, remoteIP.Text);
        }

        /// <summary>
        /// Opens a message and prompts the program controller to process it.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        /// <credit>https://stackoverflow.com/questions/16136383/</credit>
        private void readMessage_Click(object sender, EventArgs e)
        {
            OpenFileDialog theDialog = new OpenFileDialog();
            theDialog.Title = "Open Encrypted File";
            theDialog.Filter = "Encrypted files|*.elgamal";
            theDialog.InitialDirectory = Program.controller.GetInbox();
            if (theDialog.ShowDialog() == DialogResult.OK)
            {
                string[] fullPath = theDialog.FileName.ToString().Split('\\');
                Program.controller.OpenMessage(fullPath[fullPath.Length - 1]);
            }
        }

        /// <summary>
        /// Writes a message to the main window.
        /// </summary>
        /// <param name="message"></param>
        internal void DisplayMessage(string message)
        {
            this.plainText.Text = message;
        }
    }
}