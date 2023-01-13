#map function
#applies a givven function to all the iterables and return a new list

mylist=[1,2,3,4,5,6]
p=list(map(lambda a:(a/3!=2),mylist))
print(p)

#reduce function
# applies some other function to a list of elements that are passed as parameters
# to it and finally returns a single value

from functools import reduce
c=reduce(lambda a,b: a+b,[23,56,43,98,1])#it keeps on addding all the items in a list
print(c)

#solving algerbraic expressions using almbda

#linear equations
s=lambda a:a*a
print(s(4))
d=lambda x,y: 3*x+4*y
print(d(4,7))

#quadratic equations
x=lambda a,b:(a+b)**2
print(x(3,4))

#detail for map,filter,return

def new(a):
    return a*a
x=map(new,[1,2,3,4])
print(x)
print(list(x))
print(tuple(x)) 

def new(a,b):
    return a*b
x=map(new,[1,2,3,4],[2,3,4,5])
print(x)
print(tuple(x)) 

lst=[1,2,3,4,5]
y=list(map(lambda x: x+3,lst))
print(y)
#filter-details
#filter(function, iterables)
def new1(i):
    if i>=3:
        return i
j=filter(new1,(1,2,3,4,5,6,7))
print(j)
print(tuple(j))

#or

z=filter(lambda x: (x>=3),(1,2,3,4,5,6,7))
print(list(z))

#reduce-detail
#reduce(function,iterables)
from functools import reduce
def a(x,y):
    return x+y
s=reduce(a,[1,3,4,5,6,7,7,8,8])
print(s)

#or
f=reduce(lambda p,q: p*q,[1,2,3,4,5,6,7,7])
print(f)

#filter within map 
c=map(lambda x:x+x,filter(lambda x:(x>=4),[2,3,4,5]))
print(tuple(c))

#map within filter
d=filter(lambda x:(x>=4),map(lambda x:x+x,[2,3,4,5,6]))
print(set(d))
#map and filter within reduce
r=reduce(lambda x,y:x+y, map(lambda x:x+x,filter(lambda x:(x<=4),[1,2,3,4,5,6,7])))
print(r)

#using lambda functions within filter,map,refuce

#filters are used to filter the given iteralbes(lists,sets,etc) with help of another function passed
#as an argument to test all teh elements to be true or false.

newlist=list(filter(lambda a:(a/3==2),mylist))
print(newlist)