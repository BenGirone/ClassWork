modulo = int(input("The inverses of numbers modulo: "))

for i in range (1,modulo):
    for j in range (i,modulo):
        if ((i*j)%modulo == 1):
            print(str(i) + " has inverse " + str(j))
