using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.Numerics;
using System.Security.Cryptography;

namespace ElGamalClient
{
    public partial class Form1 : Form
    {
        FakeTCPClient localClient;

        public Form1()
        {
            InitializeComponent();

            localClient = new FakeTCPClient(512);

            output.Text = "Generating public key...";
            output.Text += "\nKey generated. Ready to receive messages.";
        }
    }
}