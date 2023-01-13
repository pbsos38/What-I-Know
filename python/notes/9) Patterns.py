                                                    # patterns
#pyramid

def pattern(n):
    k= 2*n-2
    for i in range(0,n):
        for j in range(0,k):
            print(end=" ")
        k = k - 1
        for j in range(0 ,i + 1):
            print("* ",end="")
        print("\r")
pattern(5)  


# inverse pattern
def pattern(n):
    k = n-2
    for i in range(n,-1,-1):
        for j in range(k,0,-1):
            print(end=" ")
        k=k+1
        for j in range(0,i+1):
            print("* ", end="")
        print("\n")
pattern(10)

#right start pattern

def pattern(n):
    for i in range(0,n):
        for j in range(0,i+1):
            print("* ",end=" ")
        print("\r")
    for i in range(n,-1,-1):    
        for j in range(0,i+1):
            print("* ", end=" ")
        print("\r")
pattern(10)

#left start pattern


def pattern(n):
    k = 2*n-2
    for i in range(0,n-1):
        for j in range(0,k):
            print(end=" ")
        k = k-2
        for j in range(0,i+1):
            print("* ", end="")
        print("\r")
    k=-1
    for i in range(n-1,-1,-1):
        for j in range(k,-1,-1):
            print(end=" ")
        k = k+2
        for j in range(0, i+1):
            print("* ",end="")
        print("\r")    
pattern(10)

# #hourglass 
def pattern(n):
        k = n-2
        for i in range(n,-1,-1):
            for j in range(k,0,-1):
                print(end=" ")
            k=k+1
            for j in range(0,i+1):
                print("* ", end="")
            print("\n")
        k= 2*n-2
        for i in range(0,n+1):
            for j in range(0,k):
                print(end=" ")
            k = k - 1
            for j in range(0 ,i + 1):
                print("* ",end="")
            print("\n")
pattern(5)


#right hand side triangle
def pattern(n):
    for i in range(0,n):
        for j in range(0,i+1):
            print("* ", end="")
        print("\r")
pattern(10)

#left handed triangle
def pattern(n):
    k=2*n-2
    for i in range(0,n):
        for j in range(0,k):
            print(end=" ")#here space in needed
        k=k-2
        for j in range(0,i+1):
            print("* ", end="")
        print("\r")
pattern(10)

#downward pattern
def pattern(n):
    for i in range(n,-1,-1):
        for j in range(0,i+1):
            print("* ",end="")
        print("\r")    
pattern(10)

#daimond pattern
def pattern(n):
    k=2*n-2
    for i in range(0,n):
        for j in range(0,k):
            print(end=" ")
        k=k-1
        for j in range(0,i+1):
            print("* ",end="")
        print("\r")
    k=n-2
    for i in range(n,-1,-1):
        for j in range(k,0,-1):
            print(end=" ")
        k=k+1
        for j in range(0,i+1):
            print("* ",end="")
        print("\r")

pattern(10)        

#diamond star pattern

for i in range(5):
    for j in range(5):
        if i+j==2 or i-j ==2 or i+j==6 or j-i ==2:
            print("*",end="")
        else:
            print(end=" ")
    print()


#number pattern
def paatern(n):
    x=0
    for i in range(0,n):
        x+=1
        for j in range(0,i+1):
            print(x,end=" ")
        print("\r")
paatern(7)

# #pascal's triangle
def pattern(n):
    for i in range(0,n):
        for j in range(0,i+1):
            print(function(i,j)," ", end="")
        print()

def function(n,k):
    res=1
    if(k> n -k):
        k=n-k
    for i in range(0,k):
        res = res*(n-1)
        res = res//(i+1)
    return res
pattern(7)

#half pyramid with numbers
def pattern(n):
    x=0
    for i in range(0,n):
        x=x+1
        for j in range(0,i+1):
            print(x, end="")
        print("\r")
pattern(10)

#diamond pattern with numbers
def pattern(n):
    k=2*n-2
    x=0
    for i in range(0,n):
        x=x+1
        for j in range(0,k):
            print(end=" ")
        k=k-1
        for j in range(0,i+1):
            print(x,end=" ")
        print("\r")
    k=n-2
    x=0
    for i in range(n,-1,-1):
        x=x+1
        for j in range(k,0,-1):
            print(end=" ")
        k=k+1
        for j in range(0,i+1):
            print(x,end=" ")
        print("\r")

pattern(5)        

#descending order
def pattern(n):
    for i in range(n,0,-1):
        for j in range(1,i+1):
            print(j,end=" ")
        print("\r")
pattern(5)


#bainary no pattern
def pattern(n):
    k = 2*n-2
    for i in range(0,n):
        for j in range(0,k):
            print(end=" ")
        k=k-1
        for j in range(0,i+1):
            print('10',end="")
        print("\r")
pattern(5)

#right alphbetical triangle

def pattern(n):
    x=65
    for i in range(0,n):
        ch=chr(x)
        x=x+1
        for j in range(0,i+1):
            print(ch,end=" ")
        print("\r")
pattern(10)

#type-2
def pattern(n):
    k=2*n-2
    x=65
    for i in range(0,n):
        for j in range(0,k):
            print(end=" ")
        k=k-1
        for j in range(0,i+1):
            ch =chr(x)
            print(ch,end=" ")
            x+=1
        print("\r")
pattern(10)

#in specific shape
for i in range(7):
    for j in range(7):
        if j ==0 or i-j ==3 or i+j ==3:
            print("A",end="")
        else:
            print(end=" ")
    print()
