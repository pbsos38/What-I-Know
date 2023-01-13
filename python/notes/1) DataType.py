
                                                                        # data type           numbers
x=12
print(type(x))
y=12.5
print(type(y))
z=12j
print(type(z))
num=y>x
print(type(num))
print(num)

                                                                        #  data type       string
name="prince"
print(len(name))
 
print(name[2]) # tells the alphabet at location 2
print(name[-2]) # tells us the alphabet from back
print(name[2:5]) # tells us a series from so and so location
print(name.upper())

                                                                        # data types        lists
mylist = [1,2,3,'hi','hello']
print(mylist)
print(mylist[2:5])
#print(mylist[3] = 7 )
print(mylist.append(10))
print(mylist.insert(4,100))
print(mylist.reverse())

#example from programming with mosh
numbers = [1,2,3,5,6,8,9,1,2,2,2,3,5,6]
unique =[]
for number in numbers:
     if number not in unique:
         unique.append(number)

print(unique)

                                                                        # data type       Dictonary
courses = {1:'python',2:'java', 'third': 'c++' } 
print(courses)
print(courses['third'])
print(courses.get('third'))
#print(courses['third']=="hadoop")
#print(courses['four']=="machine learning")

                                                                            # data type             tuple 
# tuple is an unorder and unchangeable but we can have a duplicate value

animals=(10,10,20,'tiger','lion','tiger')
print(animals[2])
print(animals.count('tiger'))# for advance step have look  at python collections: counter

                                                                            # data type    sets
#1) set in unordered lists and has no duplicate entries

sets={10,10,20,'tiger','lion','tiger'}
print(sets)
#2) set does not have any indexes
#print(sets[2])

                                                                                #data type range 

print(range(10))
print(list(range(11))) 

                                                                                # data type Mislaneous

c=[mylist , sets]
print(c)
                                                                                # type conversion
g=10
gname="prince"
sum = str(g)+gname
print(sum)

x=2.9
print(round(x))

print(abs(-2.9))

import math
print(math.ceil(2.9))
print(math.floor(2.9))

# for other examples refer to pic in notes

