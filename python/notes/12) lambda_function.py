#lambda function

# anonymous ornameless functions
# lambda is not a name, but is a keyword


# used for one-time use
# i/o of other functions
# reduce code size

#lambda arguments:expression
x = lambda a: a*a
print(x(3))

#otherwise we have to follw traditional code i.e.
def new(a):
    return a*a
print(new(3))


# anonymous functions within user definef funcctions
def a(x):
    return(lambda y:x+y)
t=a(4)
print(t(9))
