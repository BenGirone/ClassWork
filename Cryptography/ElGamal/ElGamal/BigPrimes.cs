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

        public static BigInteger RandomBigInteger(int length)
        {
            RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
            byte[] bytes = new byte[(int)Math.Ceiling((double)length / 8.0)];
            rng.GetBytes(bytes);

            BigInteger p = new BigInteger(bytes);

            return BigInteger.Abs(p);
        }

        public static BigInteger[] GetSafePrime(int length)
        {
            BigInteger p;

            while (true)
            {
                p = RandomBigInteger(length);
                if (p.IsEven)
                    p -= 1;

                if (IsPrime(p))
                    if (IsPrime(2 * p + 1))
                        return new BigInteger[] { 2 * p + 1, p };
                    
            }
        }

        public static BigInteger GetPrime(int length)
        {
            BigInteger p;

            while (true)
            {
                p = RandomBigInteger(length);
                if (p.IsEven)
                    p -= 1;

                if (IsPrime(p))
                        return p;

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