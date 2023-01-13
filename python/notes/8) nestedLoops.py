# #nested loop
# #banking service
# print('welcome to my bank')
# restart=('y')
# chances= 3
# balance = 67.14
# while chances >=0:
#     pin = int(input('pls enter your 4 digit pin:'))
#     if pin==(1234):
#         print('u have entered the pin correctly\n')
#         while restart not in ('n','no','NO','N'):
#             print("press 1 for your balance\n")
#             print("press 2 to make a withdrawl \n")
#             print("press 3 for payin\n")
#             print("press 4 to return card\n")
#             option = int(input('what would u like to choose?'))
#             if option==1:
#                 print("your balance is",balance,'\n')
#                 restart = input("would u like to go back?")
#                 if restart in ('n','no','NO','N'):
#                     print("thank u")
#                     break
#             elif option == 2:
#                 option2 = ('y')
#                 withdrawl = float(input('how much would u like to withdraw? pls enter in 10,20,40,60,80,100'))
#                 if withdrawl in [10,20,40,60,80,100]:
#                     balance= balance -withdrawl
#                     print("\n your balance is ",balance)
#                     restart = input("would u like to go back?")
#                     if restart in ('n','no','NO','N'):
#                         print("thank u")
#                         break
#                 elif withdrawl != [10,20,40,60,80,100]:
#                     print("invalid amount pls retry\n")
#                     restart=('y')
#                 elif withdrawl ==1:
#                         withdrawl = float(input("pls enter the desired amount:"))
#             elif option==3:
#                 pay_in = float(input("how much u would like to pay in?"))
#                 balance=  balance + pay_in
#                 print("your balance is ", balance)
#                 restart = input("would u like to go back?")
#                 if restart in ('n','no','NO','N'):
#                     print("thank u")
#                     break
#             elif option==4:
#                 print("pls wait while your card is being returned...\n")
#                 print("thank u for your services")
#                 break 
#             else:
#                 print("pls enter the correct no. \n")
#     elif pin !=('1234'):
#         print('incorrect password')
#         chances = chances-1
#         if chances == 0:
#             print('\n no more tries')
#             break                


#example 2
#somewhat like pythgoros thm
from math import sqrt
n = int(input("max no?"))
for a in range(1,n+1):
    for b in range(a,n):
        c_square = a**2 + b**2
        c = int(sqrt(c_square))
        if((c_square - c**2) == 0):
            print(a,b,c)

#example 3

travelling = input("yes or no:")

while travelling == 'yes':

    num = int(input("number of people travelling:"))
    for num in range(1, num +1):
        name = input("name:")
        age = input("age:")
        sex = input("sex:")

        print(name)
        print(age)
        print(sex)


    travelling = input("oops! forgot someone")


# exampple 4 //from programming with mosh
for x in range(4):
    for y in range(3):
        print(f'({x},{y})')

