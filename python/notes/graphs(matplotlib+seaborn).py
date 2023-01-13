#matplotlib

#type1
from matplotlib import pyplot as plt
plt.plot([1,2,3],[4,5,1])

plt.title('info')
plt.ylabel('Y axis')
plt.xlabel('X axis')

plt.show()

#adding style to our graph
from matplotlib import pyplot as plt2
from matplotlib import style
style.use('ggplot')

x=[5,8,10]
y=[12,16,6]

x2=[6,9,11]
y2=[6,15,7]
plt2.plot(x,y,'g',label='line one',linewidth=5)
plt2.plot(x2,y2,'c',label='line two',linewidth=5)


plt2.title('epic info')
plt2.ylabel('Y axis')
plt2.xlabel('X axis')
plt2.legend()
plt2.grid(True,color='k')
plt2.show()

#bar graph

import matplotlib.pyplot as plt3
plt3.bar([1,3,5,7,9],[5,2,7,8,2],label="Example one")
plt3.bar([2,4,6,8,10],[8,6,2,5,6],label="example two", color='g')
plt3.legend()
plt3.xlabel('bar number')
plt3.ylabel('bar height')

plt3.title('my plot yo!')
plt3.show()

#histogram
import matplotlib.pyplot as plt4
population_ages=[22,55,62,45,21,22,34,42,42,4,99,102,110,120,121,122,130,111,1115,112,80,75,65,54,44,43,42,48]

bins=[0,10,20,30,40,50,60,70,80,90,100,110,120,130]
plt4.hist(population_ages,bins,histtype='bar',rwidth=0.8)

plt4.xlabel('x')
plt4.xlabel('x')
plt4.title('histogram')
plt4.legend()
plt4.show()


#scatter plot
import matplotlib.pyplot as plt5
x=[1,2,3,4,5,6,7,8]
y=[5,2,4,2,1,4,5,2]

plt5.scatter(x,y,label='skitscat',color='k',s=25,marker="o")
plt5.xlabel('x')
plt5.xlabel('x')
plt5.title('scatter plot')
plt5.legend()
plt5.show()

#stack plot
import matplotlib.pyplot as plt6
days=[1,2,3,4,5]

sleeping = [7,8,6,11,7]
eating = [2,3,4,3,2]
working = [7,8,7,2,2]
playing = [8,5,7,8,13]

plt6.plot([],[],color='m',label="sleeping",linewidth=5)
plt6.plot([],[],color='c',label="eating",linewidth=5)
plt6.plot([],[],color='r',label="working",linewidth=5)
plt6.plot([],[],color='k',label="playing",linewidth=5)

plt6.stackplot(days,sleeping,eating,working,playing,colors=['m','c','r','k'])
plt6.xlabel('x')
plt6.xlabel('x')
plt6.title('stack plot')
plt6.legend()
plt6.show()

#pie chart
import matplotlib.pyplot as plt7

slice = [7,2,2,13]
activities = ['sleeping','eating','working','playing']
cols = ['c','m','r','b']

plt7.pie(slice,labels=activities,colors=cols,startangle=90,shadow=True,explode=(0,0.1,0,0),
autopct='%1.1f%%')

plt7.title('pie plot')
plt7.show()

#mutilple plots
import numpy as np
import matplotlib.pyplot as plt8
def f(t):
    return np.exp(-t)*np.cos(2*np.pi*t)
t1 = np.arange(0.0,5.0,0.1)
t2 = np.arange(0.0,5.0,0.02)


plt8.subplot(211)#plt.subplot(221)
plt8.plot(t1,f(t1),'bo' ,t2 ,f(t2))

plt8.subplot(212)#plt.subplot(222)
plt8.plot(t2,np.cos(2*np.pi*t2))
plt8.show()

#seaborn

#used for data visualization & based on metplotlib
#seaborn llows the creation of statisticl graphic
#functions
#allows comparison bw multiple variables
#supports multi-plot grids
#available univarite and bivariate visualizations
#availibility of different colour plaettes
#estimates and plots linear regression automatically

#seaborn vs matplotlib
#seaborn fixes 2 shortcoming of matplotlib
# matplotlib settings are difficult to figure out. seaborn comes with nummerous customized themes and high-levl interfaces.
# matplotlib doesn't serve well when it comes to dealing with dataframes, while seaborn functions actually work on dtaframes


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
#inbuit examples files , online available at github.com/mwaskom/seaborn-data
a = sns.load_dataset("flights")
print(a)
print(sns.relplot(x="passengers",y="month",data=a))
print(sns.relplot(x="passengers",y="year",data=a))

b = sns.load_dataset("tips")
print(sns.relplot(x="time",y="tip",data=b,kind="line"))
print(sns.catplot(x="day",y="total_bill",data=b))
print(sns.catplot(x="day",y="total_bill",data=b,kind="violin"))

c=np.random.normal(loc = 5, size = 100,scale = 2)
print(sns.distplot(c))

#no code for multivariate
#multiplot grids
# a=sns.load_dataset("iris")
# b=sns.FacetGrid(a, col="species")
# print(b.map(plt.hist,"sepal_length"))
#pair grid
a=sns.load_dataset("flights")
b=sns.PairGrid(a)
b.map(plt.scatter)

#to change colour of pallets
sns.set(style="darkgrid")
a=sns.load_dataset("flights")
b=sns.PairGrid(a)
b.map(plt.scatter)

#box plots
sns.set(style="white",color_codes=True)
a=sns.load_dataset('tips')
print(sns.boxplot(x="day",y="total_bill",data=a))


#to remove any axis
sns.set(style="white",color_codes=True)
a=sns.load_dataset('tips')
print(sns.boxplot(x="day",y="total_bill",data=a))
sns.despine(offset=10,trim=True)#x-axis is removed

#available colour options
c=sns.color_palette()
print(sns.palplot(c))
