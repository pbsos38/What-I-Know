                                                                    #EXCEPTION HANDLING
                                                                
#EXCEPTION -> an exception is an event, which occurs during the execution of a program,that disrupts the normal floe of the program's instructions
#HANDLING -> process of responding to the occurrence, during computation,of exceptional conditions requiring special processing - often changing the normal flow of progrsm execution

#rasing an exception
# x=10
# if x> 5:
    #raise Exception('x should not be greater than 5. the value of x was:{}'.format(x))

#assertion error exception
#instead of waiting for a program to crsh midway,you can also start by making an assertion in python
import sys
# assert('linus' in sys.platform),"this code runs on linux only."

#after assertion program will come to end and to continue the program use try and except blck

def linux_interaction():
    assert('linux' in sys.platform),"function can only run on linux systems."
    print("doing something....")

try :
    linux_interaction()
except:
    print('linux function was not executed')

#type2 
try :
    linux_interaction()
except AssertionError as error:
    print('linux function was not executed')
    print(error)

try:
    with open('file.log') as file:
        read_data = file.read()
except:
    print('could not open file.log')

#   or
#file not found
# raised when a file or director is requested but doesn't exist. corresponds to errno ENOENT

try:
    with open('file.log') as file:
        read_data = file.read()

except FileNotFoundError as fnf_error:
    print(fnf_error)
except AssertionError as error:
    print(error)
    print('linux linux_interaction() function was not excecuted')
    
#key points
#  a try clause is executed up until the point where the first exception is encountered
# inside the except clause, or the exception handler, you determine how the program responds to the exception
# you can anticipate multiple exceptions and differentite how program should respond
# avoid using bare except clauses.


try:
    linux_interaction()
except AssertionError as error:
    print(error)
else:
    print('Executing the else statement')    

#finally clause


try:
    linux_interaction()
except AssertionError as error:
    print(error)
else:
    try:
        with open('file.log') as file:
            read_data = file.read()
    except FileNotFoundError as fnf_error:
        print(fnf_error)
finally:
    print('cleaning up,irrespective of any exceptions.') 

#summary
#• raise allows you to throw an exception at any time. 
#• assert enables you to verify if a certain condition is met and throw an exception if it isn't.
#• In the try clause, all statements are executed until an exception is encountered. 
#• except is used to catch and handle the exception(s) that are encountered in the try clause. 
#• else lets you code sections that should run only when no exceptions are encountered in the try clause. 
#• finally enables you to execute sections of code that should always run, with or without any previously encountered exceptions.


# from programming with mosh

try:
    age = int(input('age: '))
    print(age)
except ValueError:
    print('Invalid value')