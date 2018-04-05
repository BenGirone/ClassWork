using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;
using System.Security.Cryptography;

namespace ElGamalClient
{
    class BigPrimes
    {
        public static bool isPrime(BigInteger n)
        {
            int[] smallPrimes = { 2, 3, 5, 7 };
            foreach (int a in smallPrimes)
            {
                if (BigInteger.ModPow(a, n - 1, n) != 1)
                    return false;
            }

            return true;
        }

        public static BigInteger RandomBigInteger(int length)
        {
            length = (int)Math.Ceiling((double)length / 8.0);

            RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
            byte[] bytes = new byte[length];
            rng.GetBytes(bytes);

            BigInteger p = new BigInteger(bytes);

            return BigInteger.Abs(p);
        }

        public static BigInteger getPrime(int length)
        {
            BigInteger p;

            while (true)
            {
                p = RandomBigInteger(length);
                if (p.IsEven)
                    p -= 1;

                if (isPrime(p))
                    return p;
            }
        }
    }
}