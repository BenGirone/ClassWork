import math

def approximateNumberOfPrimesByDigits(n):
    return format(pow(10,n)/math.log(pow(10,n)) - pow(10,n - 1)/math.log(pow(10,n - 1)), '.2g')
    
print(approximateNumberOfPrimesByDigits(100))
print(approximateNumberOfPrimesByDigits(200))
