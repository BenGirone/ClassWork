def gcd(a,b):
    print("gcd(" + str(a) + ", " + str(b) + ") = ", end='')
    if b == 0: 
        return a
    else:
        r = a % b
        return gcd(b,r)

print(gcd(1428,969)) # Answer: 3288

def extended_gcd(a,b):
    s,t = 0,1
    old_s,old_t = 1,0
    r,old_r = b,a

    while (r != 0):
        q = old_r//r
        old_r,r = r,(old_r - q * r)
        old_s,s = s,(old_s - s * s)
        old_t,t = t,(old_t - q * t)

    print("old_s = " + str(old_s) + " old_t = " + str(old_t) + "\n"
          "old_r = " + str(old_r) + "\n"
          "t = " + str(t) + " s = " + str(s))

extended_gcd(1428,969)
