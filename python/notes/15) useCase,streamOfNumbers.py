                                            #use cases
#fibonacci series
#a sreries of number where in each number also called as the fibinacci number is the sum of the 2 preceding numbers.

def fib():
    f,s=0,1
    while True:
        yield f
        f,s=s,f+s
for x in fib():
    if x>50:
        break
    print(x,end= " ")


#generating a stream of numbers

a=range(100)
b=(x for x in a)
print(b)
for y in b:
    print(y)