import sys

def check(x1,x2,x3,x4):
    if x3 <= x4:
        if (x1<x3 and x2<x3) or (x1>x4 and x2>x4):
           return False
    elif x3 > x4:
        if (x1>x3 and x2>x3) or (x1<x4 and x2<x4):
           return False
    return True

lines = sys.stdin.readlines()
for i in range(len(lines)):
    lines[i] = lines[i].strip("\n").split()

[x1, x2] =lines[0]
[x3, x4] =lines[1]

result = check(x1,x2,x3,x4)

if result == True:
    print("Overlap")
else:
    print("Not overlap")

