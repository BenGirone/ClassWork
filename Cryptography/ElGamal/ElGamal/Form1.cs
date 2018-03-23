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

namespace ElGamal
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            plainText.Text = "Number : Is Prime\n";
        }

        private void btn_encrypt_Click(object sender, EventArgs e)
        {
            while (true)
            {
                BigInteger candidate = BigPrimes.RandomPrimeCandidate(128 * 5);
                if (BigPrimes.IsProbablePrime(candidate, 20))
                {
                    plainText.Text += candidate.ToString() + " : " + BigPrimes.IsProbablePrime(candidate, 20).ToString() + "\n";
                    break;
                }
            }
        }

        public string listToString(List<BigInteger> L)
        {
            string s = "[";

            for (int i = 0; i < L.Count - 1; i++)
            {
                s += L[i].ToString() + ", ";
            }

            s += L[L.Count - 1].ToString() + "]";

            return s;
        }
    }
}