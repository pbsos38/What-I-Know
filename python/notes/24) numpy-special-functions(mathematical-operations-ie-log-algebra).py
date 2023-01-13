#numpy special funtions
import matplotlib.pyplot as plt1
import numpy as np1

x= np1.arange(0,3*np1.pi , 0.1)
y= np1.sin(x)
plt1.plot(x,y)
plt1.show()

import matplotlib.pyplot as plt2
import numpy as np2

x= np2.arange(0,3*np2.pi , 0.1)
y= np2.cos(x)
plt2.plot(x,y)
plt2.show()

import matplotlib.pyplot as plt3
import numpy as np3


x= np3.arange(0,3*np3.pi , 0.1)
y= np3.tan(x)
plt3.plot(x,y)
plt3.show()

import matplotlib.pyplot as plt4
import numpy as np4

ar = np4.array([1,2,3])
print(np4.exp(ar))#e^x
print(np4.log(ar))#log x
print(np4.log10(ar))#log base 10


                                                #SciPY
#Scipy is python library used to solve scientific and mathematical problems
#built on numPy
#allows manipulation and visualizing

#numpy vs scipy
#numpy and scipy used for mathematical and numerical analysis
#numpy contains arraydata and basic operations
#scipy consists of all the numerical code
#scipy contains fully-featured versiond of mathemetical and scientific functions


#scipy packages(name - description)

#cluster    clustering algorithms
#constants  physical and mathematical constants
#fftpack    fast fourier transform routines
#integrate  integration and ordinary differential equation solvers
#interpolate  interpolation and smoothing splines
#io         input and output
#linalg     linear algebra
#ndimage    n-dimensional image processing
#odr        orthogonal distance regression
#optimize   optimization and root-finding routines
#signal     signal processing
#sparse     sparse matrices and associated routines
#spatial    spatial data structures and algorithms
#special    special functions
#stats      statical distribution and functions


#basic functions
#help()  returns info about any function,keywords,class etc
# info() returns info about any function, keyword, class etc
#source() returns the source code only for objects written in python

from scipy import cluster
#print(help(cluster))
import scipy
# scipy.info(cluster)
# scipy.source(cluster)

#special function
from scipy import special
a= special.exp10(2)
print(a)

b= special.exp2(3)
print(b)

c=special.sindg(90)
print(c)

d= special.cosdg(90)
print(d)

#integration functions
#1) general integrations    the quad fun calculatesvthe integral of a fnction which has one variale
#2) double integration      the dblquad function calculates doubles integral of a function which has 2 variables


from scipy import integrate
#print(help(integrate.quad))

i=scipy.integrate.quad(lambda x:special.exp10(x),0,1)
print(i)

e=lambda x,y:x*y**2
f=lambda x:1
g = lambda x:-1
print(integrate.dblquad(e,0,2,f,g))

#fourier transformations
#fourier analysis is a method that deals with expressing a function as a sum of periodic components and recoverinfng singnal fromthose components
#the fft and ifft functions can be used to return the discrete fourier tranform of a real or complex sequence.

from scipy.fftpack import fft,ifft
import numpy as np5
x=np5.array([1,2,3,4])
y=fft(x)#fourier transformed
y1=ifft(x)#inverse of fourier transform
print(y)
print(y1)

#Linear algebra
from scipy import linalg
import numpy as np6

a=np6.array([[1,2],[3,4]])
b=linalg.inv(a)#inverse of matrix
print(b)

#interpolation functions
#INTERPOLATION REFERS TO CONSTRUCTING new data pointswithin set of known data points. the scip.interpolate consists of spine functionsand classes, one-diimensional and multi-dimensional(univariate and multivariatr ) interpolation classes,etc.
import matplotlib.pyplot as plt4
from scipy import interpolate
import numpy as np7

x = np7.arange(5, 20)
y= np7.exp(x/3.0)
f=interpolate.interp1d(x,y)
x1 = np7.arange(6, 12)
y1 = f(x1) # use interpolation function returned by `interp1d`
plt4.plot(x, y, 'o', x1, y1,'--')
plt4.show()