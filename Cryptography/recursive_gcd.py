def gcd(a,b):
    print("gcd(" + str(a) + ", " + str(b) + ") = ", end='')
    if b == 0: 
        return a
    else:
        r = a % b
        return gcd(b,r)

print(gcd(3127,7282))
#gcd(1428, 969) = gcd(969, 459) = gcd(459, 51) = gcd(51, 0) = 51

def extended_gcd(a,b):
    s,t = 1,0
    x,y = 0,1
    q,r = divmod(a,b)

    while (r != 0):
        a,b = b,r
        x_,y_ = x,y
        x,y = s - q*x, t - q*y
        s,t = x_,y_
        q,r = divmod(a,b)

    return [b, x, y]
    
answer = extended_gcd(3127,7282)
print("gcd(1428,969) = " + str(answer[0]) +
      " = 1428(" + str(answer[1]) +
      ") + 969(" + str(answer[2]) + ")")
#gcd(1428,969) = 51 = 1428(-2) + 969(3)
