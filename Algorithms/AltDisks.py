def generateDisks(n):
    disks = []
    for i in range(n):
        disks.append("b")
        disks.append("w")
    return disks

def arrangeDisks(disks):
    for i in range(len(disks)):
        for j in range(i,(len(disks) - i),2):
            disks[j],disks[j+1] = disks[j+1],disks[j]
    return disks

print(str(arrangeDisks(generateDisks(6))))
