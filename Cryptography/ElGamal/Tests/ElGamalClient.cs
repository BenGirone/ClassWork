using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace ElGamalClient
{
    public class ElGamalClient
    {
        private BigInteger p;
        private BigInteger alpha;
        private BigInteger a;

        public List<BigInteger> PublicKey;

        public ElGamalClient(BigInteger p, BigInteger alpha)
        {
            this.p = p;
            this.alpha = alpha;

            a = BigPrimes.RandomBigInteger(p.ToByteArray().Length * 8) % p;

            PublicKey = new List<BigInteger>() { p, alpha, BigInteger.ModPow(alpha, a, p) };
        }
    }
}
