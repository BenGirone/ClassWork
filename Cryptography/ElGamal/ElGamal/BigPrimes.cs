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
        public static bool IsPrime(BigInteger n)
        {
            int[] smallPrimes = { 2, 3, 5, 7 };
            foreach (int a in smallPrimes)
            {
                if (BigInteger.ModPow(a, n - 1, n) != 1)
                    return false;
            }

            return true;
        }

        public static BigInteger RandomBigInteger(BigInteger max)
        {
            int length = max.ToByteArray().Length * 8;

            RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
            byte[] bytes = new byte[(int)Math.Ceiling((double)length / 8.0)];

            BigInteger p;
            do
            {
                rng.GetBytes(bytes);
                p = BigInteger.Abs(new BigInteger(bytes));
            } while (p >= max);

            return p;
        }

        public static BigInteger[] GetSafePrime(BigInteger max)
        {
            BigInteger n = max / 12;

            BigInteger p;
            BigInteger q;

            while (true)
            {
                q = 6 * RandomBigInteger(n) + 5;
                if (IsPrime(q))
                {
                    p = 2 * q + 1;
                    if (IsPrime(p))
                        return new BigInteger[] { p, q };
                }
                    
            }
        }

        public static BigInteger GetPrimitiveFromSafePrime(BigInteger[] p)
        {
            BigInteger a;

            while (true)
            {
                a = RandomBigInteger(p[0].ToByteArray().Length) % p[0];

                if (BigInteger.ModPow(a, p[1], p[0]) != 1 && BigInteger.ModPow(a, 2, p[0]) != 1)
                    return a;
            }
        }
    }
}