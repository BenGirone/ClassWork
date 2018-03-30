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
        /// <summary>
        /// Generates a large random number with the specified amount of bits.
        /// </summary>
        /// <param name="length">Number of bits in the random number. Must be greater than 0.</param>
        /// <returns>A randomly generated large odd number that is at least one byte in size</returns>
        public static BigInteger RandomPrimeCandidate(int length)
        {
            BigInteger p = RandomBigInteger(length);

            if (p.IsEven)
                p += 1;

            return p;
        }

        public static bool IsProbablePrime(BigInteger n, int certainty)
        {
            uint s = 0;
            BigInteger d = n - 1;

            while (d % 2 == 0)
            {
                s++;
                d = d / 2;
            }

            int bitSize = n.ToByteArray().Length * 8;
            //List<BigInteger> squarings;
            BigInteger a;
            BigInteger p;
            int witnesses = 0;

            for (int i = 0; i < certainty; i++)
            {
                a = RandomBigInteger(bitSize) % n;
                p = BigInteger.ModPow(a, d, n);
                //squarings = new List<BigInteger> { p };

                for (int j = 0; j < s; j++)
                {
                    p = BigInteger.ModPow(p, 2, n);
                    //squarings.Add(p);
                }

                if (!p.IsOne)
                    witnesses++;
            }

            return witnesses/certainty < 0.75;
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
    }
}