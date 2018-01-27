from math import sqrt

def distance(p1, p2):
    x = 0
    for i in range(0,len(p1)):
        x += (p1[i] - p2[i])**2

    return sqrt(x)

point = [0,0,0]
obs1 = [0,3,0]
obs2 = [2,0,0]
obs3 = [0,1,3]
obs4 = [0,1,2]
obs5 = [-1,0,1]
obs6 = [1,1,1]

print("Obs 1: " + str(distance(point, obs1)))
print("Obs 2: " + str(distance(point, obs2)))
print("Obs 3: " + str(distance(point, obs3)))
print("Obs 4: " + str(distance(point, obs4)))
print("Obs 5: " + str(distance(point, obs5)))
print("Obs 6: " + str(distance(point, obs6)))
