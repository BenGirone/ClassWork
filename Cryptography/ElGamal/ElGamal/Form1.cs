using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Numerics;
using System.Security.Cryptography;

namespace ElGamalClient
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btn_encrypt_Click(object sender, EventArgs e)
        {
            //plainText.Text += BigPrimes.GetSafePrime(Convert.ToInt16(bitSize.Text))[0] + "\n";
            plainText.Text += BigPrimes.GetPrime(Convert.ToInt16(bitSize.Text)) + "\n";
        }
    }
}