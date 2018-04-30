# Ben Girone		MTH 330

import hashlib
import random
import string

# Dr. Coleman ---------------------------------------------
def SHA1(s): return hashlib.sha1(s.encode()).hexdigest()

def clash(s1,s2):
  h1 = SHA1(s1)
  h2 = SHA1(s2)
  for (x,y) in zip(h1,h2):
    if x == y: return False
  return True
# ---------------------------------------------------------

# Takes a string and returns the same string with one character randomly mutated.
def mutate(s):
	s_list = list(s)
	s_list[random.randint(0, len(s_list) - 1)] = random.choice(string.ascii_letters)
	return ''.join(s_list)
 
s1 = input("Enter the base string: ")
s2 = s1

# Keep mutating until we have strings with no clashes
while (not clash(s1, s2)):
	s2 = mutate(s1)

print(s2)
