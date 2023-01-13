                                                                            #array
import array
a=array.array('i',[1,2,3,4,5,6])
print(a)
print(a[2])
print(len(a))
a.append(7) #adding only 1 value at end of array
print(a)
a.insert(0,0) #adding only 1 value at start of array
print(a)
a.extend([8,9,10])#adding more than 1 value at end of array
print(a)

print("poping out a value",a.pop())
print('poping out a specific value of your choice', a.pop(2))# pop function returns a value
print(a.remove(8))# does not return value

                                                                        # array concatenation
bb=array.array('i',[1,2,3,4,5,6])
cc=array.array('i',[1,2,3,4,5,6])
dd=array.array('i')
dd=bb+cc
print(dd)
# can add array of same type only
bb1=array.array('i',[1,2,3,4,5,6])
cc1=array.array('i',[1,2,3,4,5,6])
dd1=array.array('i')
dd1=bb1+cc1
print(dd)


# slicing an array
# an array can be sliced using ':' symbol. this return the range of elements
#that we have specifed by their index no

bb2=array.array('i',[1,2,3,4,5,6])
print(bb2[0:5])
print(bb2[0:-4])

#reverse of array
print(bb2[::-1])

