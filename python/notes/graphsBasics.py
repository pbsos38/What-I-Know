from matplotlib import pyplot as plt 


plt.bar ( [0.25,1.25,2.25,3.25,4.25],[50,40,70,80,20],
label="bmw",width=.5)
plt.bar ( [.75,1.75,2.75,3.75,4.75],[80,20,20,50,60],
label="audi",color='r',width=.5)
plt.legend()
plt.xlabel('Days')
plt.ylabel('Distance (kms)')
plt.title('Information')
plt.show()

# piechart 
days=[1,2,3,4,5]
sleeping=[7,8,6,11,7]
eating =[2,3,4,3,2]
working=[7,8,6,11,7]
playing=[8,5,7,8,13]
slices =[7,2,2,13]
activities = ['sleeping', 'eating', 'working', 'playing']
cols = ['c','m','r','b']
plt.pie(slices,
        labels=activities,
        colors=cols,
        startangle=90,
        shadow=True,
        explode=(0,0.1,0,0),
        autopct='%1.1f %%'    )
plt.title('pie plot')
plt.show()
