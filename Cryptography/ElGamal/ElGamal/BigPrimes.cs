using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;
using System.Security.Cryptography;

namespace ElGamal
{
    class BigPrimes
    {
        /// <summary>
        /// Simple test to see if a large random integer is prime. 
        /// The likelihood of a random large composite integer being a fermat pseudo-prime at {2, 3, 5, 7} is extremely low.   
        /// </summary>
        /// <param name="n">A randomly generated big integer.</param>
        /// <returns>True, if n is most likely prime.</returns>
        public static bool IsPrime(BigInteger n)
        {
            //bases to check
            int[] smallPrimes = { 2, 3, 5, 7 };

            //check if any of the bases indicate that n is composite with FLT
            foreach (int a in smallPrimes)
            {
                if (BigInteger.ModPow(a, n - 1, n) != 1)
                    return false;
            }

            return true;
        }

        /// <summary>
        /// Generates a random positive big integer bounded by a given value.
        /// </summary>
        /// <param name="max">The upper bound for the random integer to be generated.</param>
        /// <returns>A random big integer.</returns>
        public static BigInteger RandomBigInteger(BigInteger max)
        {
            //get the bit length of the integer
            int length = max.ToByteArray().Length * 8;

            //create the cryptogrphical number generator
            RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
            
            //create a byte array of the proper length
            byte[] bytes = new byte[(int)Math.Ceiling((double)length / 8.0)];

            //loop until a number (p) is generated that is less than max
            BigInteger p;
            do
            {
                rng.GetBytes(bytes);
                p = BigInteger.Abs(new BigInteger(bytes));
            } while (p >= max);

            return p;
        }

        /// <summary>
        /// Generates a random safe prime https://en.wikipedia.org/wiki/Safe_prime
        /// </summary>
        /// <param name="max">The upper bound for the random safe prime to be generated.</param>
        /// <returns>A random safe prime and a Sophie Germain prime (https://en.wikipedia.org/wiki/Sophie_Germain_prime)</returns>
        /// <credit>Dr. John Coleman Crypto 11.pptx</credit>
        public static BigInteger[] GetSafePrime(BigInteger max)
        {
            BigInteger n = max / 12;

            BigInteger p;
            BigInteger q;

            while (true)
            {
                //attempt to create the Sophie Germain prime
                q = 6 * RandomBigInteger(n) + 5;
                if (IsPrime(q))
                {
                    //attempt to create the safe prime 
                    p = 2 * q + 1;
                    if (IsPrime(p))
                        return new BigInteger[] { p, q };
                }
                    
            }
        }

        /// <summary>
        /// Creates a primitive root (https://en.wikipedia.org/wiki/Primitive_root_modulo_n) modulo a safe prime.
        /// </summary>
        /// <param name="p">A safe prime (https://en.wikipedia.org/wiki/Safe_prime)</param>
        /// <returns>A primitive root of p</returns>
        public static BigInteger GetPrimitiveFromSafePrime(BigInteger[] p)
        {
            BigInteger a;

            while (true)
            {
                //generate a random number less than p
                a = RandomBigInteger(p[0].ToByteArray().Length) % p[0];

                //check it with FLT
                if (BigInteger.ModPow(a, p[1], p[0]) != 1 && BigInteger.ModPow(a, 2, p[0]) != 1)
                    return a;
            }
        }
    }
}