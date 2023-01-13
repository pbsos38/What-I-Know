
                                                # LOOPS
# while loop
# while loops are known as indefinite or conditional loops. they will keep iterating
#until certain contion are met. There is no guarantee ahead of time regarding how many time the loop will iterate.


count=0
while( count<9):
    print('while loop',count )
    count+=1

print("bye")


# example 2
import random
n=20
to_be_guessed = int(n* random.random())+1
guess=0
while guess != to_be_guessed:
    guess=int(input("new number:"))
    if guess > 0:
        if guess > to_be_guessed:
            print("number too large")
        elif guess< to_be_guessed:
            print("number too small")
    else:
        print("sorry that you're giving up!")
        break
else:
    print("congo. you made it!")
# basically this will automatically create an random no and we have guess it


#for loop

fruits = ['mango','grapes','apple']

for fruit in fruits:
    print("current fruit:",fruit)

print("good bye")

#example 2
import math
numm = int(input("Number:"))
factorial = 1

if numm<0:
    print("must be +ve")
elif numm==0:
    print("factorial=1")
else:
    for i in range(1,numm + 1):
        factorial = factorial*i

print(factorial)        

