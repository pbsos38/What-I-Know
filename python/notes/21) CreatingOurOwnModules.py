                                            #MODULE
#used to break down the code to separate parts

import egofmodule as mod
#from egofmodule import add
a=10
b=5

add = mod.add(a,b)
print(add)

sub = mod.sub(a,b)
print(sub)


#builtin modules

import egofmodule
print(dir(egofmodule)) 

import sys
a=sys.builtin_module_names
print(a)

# python modules search path
import sys
print(sys.path)

import math
print(math.factorial(5))

import random
print(random.randrange(0,50))

import datetime
print(datetime.date.today())
