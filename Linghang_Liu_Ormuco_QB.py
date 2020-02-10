#Linghang Liu
import math
import sys

def compare(v1, v2):
    if math.isclose(v1, v2):
        return "Version number 1 is equal to version number 2."
    elif v1 > v2:
        return "Version number 1 is greater than version number 2."
    else:
        return "Version number 1 is less than version number 2."

lines = sys.stdin.readlines()

for i in range(len(lines)):
    lines[i] = lines[i].strip("\n").split()

[v1] = lines[0]
[v2] = lines[1]

print(compare(float(v1), float(v2)))
