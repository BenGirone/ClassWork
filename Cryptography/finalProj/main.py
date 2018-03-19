'''
sudo apt install python3
sudo apt install python3-dev
download https://pypi.python.org/pypi/pycrypto
sudo python3 setup.py build
sudo python3 setup.py install
'''

from Crypto.Util import number

def prime_factors(n):
    i = 2
    factors = []
    while i * i <= n:
        if n % i:
            i += 1
        else:
            n //= i
            factors.append(i)
    if n > 1:
        factors.append(n)
    return factors

def primitiveRoot(p):
	
	#Eulers totient function
	phi_p = p - 1

	#Get the prime factorization of phi of p
	factors = prime_factors(phi_p)

	#Convert the prime factors into maximal divisors
	for i in range(0, len(factors)):
		factors[i] = phi_p//factors[i]


	#The primitive root candidate
	a = 1
	
	notPrimitive = True
	while (notPrimitive):
		a = a + 1

		for divisor in factors:
			if (pow(a,divisor,p) == 1):
				notPrimitive = True
				break
			else:
				notPrimitive = False

	return a


n_length = 32

primeNum = number.getPrime(n_length)

print("The smallest primitive root of " + str(primeNum) + " is "  + str(primitiveRoot(primeNum)))