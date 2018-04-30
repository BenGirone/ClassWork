import hashlib
import random
import string

def SHA1(s): return hashlib.sha1(s.encode()).hexdigest()

def clash(s1,s2):
  h1 = SHA1(s1)
  h2 = SHA1(s2)
  for (x,y) in zip(h1,h2):
    if x == y: return False
  return True

def mutate(s):
	s_list = list(s)
	s_list[random.randint(0, len(s_list) - 1)] = random.choice(string.letters)
	return ''.join(s_list)

s1 = input("Enter the base string: ")
s2 = s1


while (not clash(s1, s2)):
	s2 = mutate(s1)

print(s2,s1)
