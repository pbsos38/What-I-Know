                                                #oops concept
#Class and Objects

#class -> A class is the blueprint from which specific objects are created
#class variable -> a variable that is shared by all instances of class
#instance variables -> instance varianble are unique to each instance
#data member -> a class variabe or instance variable that holds data associated with class and its objects

class car():
    pass

honda = car()# these are 2 objects that belongs to a specific class
tata = car()
honda.modelname='city'
honda.yearm = 2017
honda.price = 100000


tata.modelname='bolt'
tata.yearm = 2016
tata.price = 60000

print(honda.price)

#method 2
class car2():
    def __init__(self,modelname,yearm,price):
        self.modelname = modelname
        self.yearm = yearm
        self.price = price
    def price_inc(self):
        self.price = int(self.price*1.15)


honda = car2('city',2017,100000)
tota = car2('bolt',2016,60000)

honda.cc = 1500 # here i have created instance variable for specific object


print(honda.__dict__)#this will return the whole info about an object
print(honda.price)
honda.price_inc()
print(honda.price)

#OOPS in python
#inheritance -> a class can inherit attributes and behaviour methods from another class,called the superclass.
                # a class which inherits from a asuperclass is called a subclass, also called heir class or child class.
#eg parents are the base class and children are the derived class


#super class for method 2
class supercar(car2):
    pass
honda = supercar('supercity',2017,100000)
tata = car2('bolt',2016,600000)

honda.cc=1500
#print(help(honda))
print(honda.yearm)
honda.price_inc()
print(honda.price)

#superclass method 2
class supercar2(car2):
    def  __init__(self,modelname,yearm,price,cc):
        super.__init__(modelname,yearm,price)
        self.cc = cc  #inheritence

honda = supercar('supercity',2017,100000)
tata = car2('bolt',2016,600000)

honda.cc=1500
#print(help(honda))
print(honda.yearm)
honda.price_inc()
print(honda.price)
