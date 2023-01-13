                                    #decorators

def fun1(name):
    return f"Hello{name}"

def fun2(name):
    return f"{name} , how are you doing?" 

def fun3(fun4):
    return fun4(' dear learner')

print(fun3(fun1))
print(fun3(fun2))

#inner functions

def fun():
    print("parent function")
    def fun1():
        print("1st child function")
    def fun2():
        print("2nd child function")
         
    fun2()
    fun1()
fun()

#return a function from funtion
def fun(n):
    def fun1():
        return "prince"
    def fun2():
        return "python"
    if n==1:
        return fun1
    else:
        return fun2
a = fun(1)
b = fun(2)
print(a())
print(b())

#deorators in python are ery powerful whch modify the behaviour of a function without modifying it permanently.
#it  basically wraps another fun and since both functions are callable, it returns callable


#example2
def fun1(function):
    def wrapper():
        print("hello")
        function()
        print("welcome")
    return wrapper
def fun2():
    print("quarantined!!")

fun2 = fun1(fun2)
fun2()

#diiferent method of example 2
def fun1(function):
    def wrapper():
        print("hello")
        function()
        print("welcome")
    return wrapper
@fun1
def fun2():
    print("quarntined")

fun2()

def fun(function):
    def wrapper(*args,**kwargs):
        print("hello")
        function(*args,**kwargs)
        print("welcome")
    return wrapper

@fun
def funtion2(name):
    print(f"{name}")
funtion2("prince")

#example 3
def function1(function):
    def wrapper(*args,**kwargs):
        print("it worked")
    return wrapper
@function1 
def function2(name):
    print(f"{name}")

function2("python")

#fancy decorators
class square:
    def __init__(self,side):
        self._side = side

    @property
    def side(self):
        return self._side
    @side.setter
    def side(self,value):
        if value >=0:
            self._side = value
        else:
            print("error")
        
    @property
    def area(self):
        return self._side **2
    @classmethod
    def unit_square(cls):
        return cls(1)
s=square(5)
print(s.side)
print(s.area)

#singleton class
import functools
def singleton(cls):
    @functools.wraps(cls)
    def wrapper(*args,**kwargs):
        if not wrapper.instance:
            wrapper.instance = cls(*args, **kwargs)
        return wrapper.instance
    wrapper.instance = None
    return wrapper

@singleton
class one:
    pass
first =one()
second = one()
print(first is second) 

#nesting decorators -- google it!

#argument in a decorators
 
import functools
def repeat(num):
    def decorator_repeat(func):
        @functools.wraps(func)
        def wrapper(*args,**kwargs):
            for _ in range(num):
                value = func(*args,**kwargs)
            return value 
        return wrapper
    return decorator_repeat

@repeat (num =5)
def function(name):
    print(f"{name}")
function("python")    

