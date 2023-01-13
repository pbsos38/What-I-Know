
                                                    #NUMPY
#numpy is the core library for scientific computing in python
#it provoides a high profermence multidimensional array onject,and tools for working woth these arrays
import numpy as np
a= np.array([(1,2,3),(4,5,6)])#2-d arraay
print(a)
#numpy vs list
#less memory
#fast 
#convenient

import time
import sys
s = range(1000)
print(sys.getsizeof(5)*len(s))#memory occupied by list
d= np.arange(1000)
print(d.size*d.itemsize)#memory occupied by numpy array


#time difference bw numpy and list execution
size = 100000

l1 = range(size)
l2 = range(size)

a1 = np.arange(size)
a2 = np.arange(size)

start = time.time()

result = [(x,y) for x,y in zip(l1,l2)]

print((time.time()-start)*1000)

start = time.time()
result =a1+a2

print((time.time()-start)*1000)

#few operations
a= np.array([(1,2,3,4),(3,4,5,6)])
b= np.array([(1,2,3,4),(3,4,5,6)])
print(a.ndim)#type of array
print(a.itemsize)#byte size of each elements
print(a.dtype)##data type of elements
print(a.size)#total no of items in array
print(a.shape)#rows and coloumns
print(a.reshape(4,2))
print(a[0,2])#accessing specific item using the arra length
print(a[0:,3])#coloumn means all the rows including zero and in that row print index three  # slicing
a1= np.array([(1,2,3,4),(3,4,5,6),(7,8,9,11)])
print(a1)
print(a1[0:2,3])#coloumn means all the rows including zero  but less than index 2 and in that row print index three  # slicing
print(a1[0:,3])#coloumn means all the rows including zero and in that row print index three  # slicing

a2=np.linspace(1,3,5)
print(a2)#print five values which are equall placed between one and three

a3= np.array([1,2,3])
print(a3.max())
print(a3.min())
print(a3.sum())

#axis 0 is all rows
#axis 1 is all coloumn

print(a1.sum(axis=0))
print(a1.sum(axis=1))

print(np.sqrt(a1))
print(np.std(a1))#standard variation ie how much it varies from mean value

j=np.array([1,2,3])
k=np.array([1,2,3])
print(j+k)
print(j-k)
print(j*k)
print(j/k)

print(np.vstack((a,b)))#veritical stacking #appends array in veritcal mode
print(np.hstack((a,b)))#horizontal stacking #appends array in horizontal mode

a=np.array([(1,2,3),(4,5,6)])
print(a.ravel()) #it combines all the items in array