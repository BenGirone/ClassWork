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

        //https://stackoverflow.com/questions/10775367/cross-thread-operation-not-valid-control-textbox1-accessed-from-a-thread-othe?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
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

        private void sendMessage_Click(object sender, EventArgs e)
        {
            Program.controller.SendMessage(plainText.Text, remoteIP.Text);
        }

        //https://stackoverflow.com/questions/16136383/
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

        internal void DisplayMessage(string message)
        {
            this.plainText.Text = message;
        }
    }
}