#generators

#are functions that return traversable objects
#produce items one at a time and only when required
#are run along with "for" loops

#advantage
#easy to implemrnt (implemnets __iter__().__next__() automatically)
#better memoru management and utilization
#can be used to produce infinite item
#can also be used to pipeline a number of operations

        #diff. bw generators and normal functions
# make use of 'yield' keyword                           #make use of 'return' keyword
#run when 'next()' method is called                     #run when name of method is called
#produce items one at a time & only when required       #produce all items at once

#baisc
def new(dict):
    for x,y in dict.items():
        yield x,y
a={1:"hi",2:"welcome"}
b=new(a)
print(b)
print(next(b))
print(next(b))
#print(next(b))

def myfunc(i):
    while i<=3:
        yield i
        i+=1
j=myfunc(2)
print(next(j))
print(next(j))
#print(next(j))

def ex():
    n=3
    yield n
    n=n*n
    yield n
v=ex()
print(next(v))  #returns 1st n
print(next(v))  #returns 2nd n
#print(next(v))

#generators with loops

def ex():
    n=3
    yield n
    n=n*n
    yield n
v=ex()
for x in v:
    print(x)

#generator expressions

#resembles list comprehensiond and like lambda functions,
#generators expressions create anonymous generator functions.

#difference in print keyword 
f=range(6)
print("list comp",end=":")
q=[x+2 for x in f]
print(q)
print("gen exp",end=":")
r=(x+2 for x in f)
print(r)

#to print generator
for x in r:
    print(x)

#to find min val from genrstor
print("gen exp",end=":")
r=(x+2 for x in f)
print(r)
print(min(r))
