def listComparer(list_i, list_j):
    i = 0
    j = 0
    newList = []

    while(i < len(list_i) and j < len(list_j)):
        print(str(list_i[i]) + " " + str(list_j[j]))
        
        if (list_i[i] < list_j[j]):
            i = i + 1
        else:
            if(list_i[i] > list_j[j]):
                j = j + 1
            else:
                print("^match")
                newList = newList + [list_i[i]]
                i = i + 1
                j = j + 1

    return newList

a1 = [2,5,5,5]
a2 = [2,2,3,5,5,7]

print(listComparer(a1, a2))
