#odd or even
a=range(2,100,2)
b=(x for x in a)
print(b)
for y in b:
    print(y)

a=range(1,100,2)#excludes 100 in intervals of 2
b=(x for x in a)
print(b)
for y in b:
    print(y)
            
#Sine wave
                #not working
# import numpy as np
# from matplotlib import pyplot as plt
# import seaborn as sb
# def s(flip = 2):
#     x=np.linesplace(0,14,100)
#     for i in range(1,5):
#         plt.plot(x,np.sin(x + 1 * .5)* (7-i)*flip)
# sb.set()
# s()
# plt.show()

#type 2
import numpy as np
from matplotlib import pyplot as plt
import seaborn as sb
def s(flip = 2):
    x  =np.linspace(0,14,100)
    for i in range(1,10):
        yield(plt.plot(np.sin(x+i*.5)*(7-i)*flip))

print(sb.set())
s=s()
plt.show()
print(next(s))
print(next(s))
print(next(s))
