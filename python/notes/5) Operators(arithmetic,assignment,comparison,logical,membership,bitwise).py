
                                                                #Arithmetic operators
#_ are used to perform arithmetic operations between variables
q=10
w=20
print(q+w)
print(q-w)
print(q*w)
print(q**w) # returns exponential value
print(q/w)
print(w//q) # returns floor division value # returns integer value after division
print(q//w)
print(q%w)


                                                                #Assingnment operators
a=15
a += 15
print( a)
a += 15
print(a)

a**=5
print(a)
a -= 5
print(a)
a*=2
print(a)
a%=5
print(a)
a//=5
print(a)
a|=2
print(a)
a^=3
print(a)
a&=4
print(a)

                                                                #comparison operators
val =50
numd =20
compare =val==numd
print(compare)

if val >  numd:
 print("val grater")
elif val<numd:
 print("val is smaller")

if val!=numd:
 print("val is not equal to num")
if val>= numd:
    print("val might be greater or equal to numd")
if val<=numd:
  print("val might be lesser or equal to numd")


                                                                        #Logical opertors
#logical opertors are used to combine conditional statements

fg=10
print(fg>10 and fg>5)

print(fg>8 and fg>5)
print(fg>10 or fg>5)
print(fg>8 or fg>5)

print(not(fg>10 and fg>5))
print(not(fg>8 and fg>5))


                                    #Identity Operators
#1) IS # returns true if both variables are same object
#2) IS NOT # returns true if both variables are not same object
list1=[10,20,30]
list2=[10,20,30]
x=list1
print(x)
print(x is list1)
print(list1 is list2)# in not same objects 
print(list1 is not list2)# although has same values, it doesn't depend


                                                    #MEMBERShIP OPERTAORS
#these are used to check if a sequence is present in an object or not
print(x in list1)
print(20 in list1)
print(x not in list1)
print(20 not in list1)
#in
#returns true if a sequence with the specified value is present in object
#not in
#returns true if a sequence with the specified value is not present in object

                                            #BITWISE OPERATORS
#types
#  &   Bitwise AND        #sets each bit to 1 if both bits are 1.
#  |   Bitwise OR         #sets each bit to 1 if one of the bits are 1.
#  ^   Bitwise XOR        #sets each bit to 1 if only one of the bits are 1.
#  ~   Bitwise NOT        #inverts all bits.
#  <<  bitwise left shift #shift left by pushing in zeros from the right and let the leftmost bits fell off
#  >>  bitwise right shift #shift right by pushing copies of the leftmost bit from the left and let the rightmost bits fell off

# concept not cleared 
print(10 & 12)
#he says 10 in binary is 1010 and 12 is 1100 and 1st digit matches(of 1010 & 1100) but not the second on so it becomes 1000 and if 1000 is converted into decimal it becomes 8
print(10 | 12)
print(10 << 12)
print(10 >> 12)
print(10 ^ 12)
# not wroking 
#print(10 ~ 12)