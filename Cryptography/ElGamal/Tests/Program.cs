using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace ElGamalClient
{
    class Program
    {
        static void Main(string[] args)
        {
            BigInteger p = BigPrimes.RandomPrimeCandidate(32);
            ElGamalClient localhost = new ElGamalClient(p, BigPrimes.RandomPrimeCandidate(32) % p);

            Console.WriteLine("[" + localhost.PublicKey[0].ToString() + ", " + localhost.PublicKey[1].ToString() + ", " + localhost.PublicKey[2].ToString() + "]");
            Console.ReadLine();
        }
    }
}
