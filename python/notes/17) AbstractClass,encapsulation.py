#encapsulation
# binding data and code togetther as a single unit.
#securing data by hiding the implementation details to user.




#abstraction
# hides the implementation details and only provides the functionality to users
#you can achieve abstraction using abstract class and interfaces

#abstract class cannot be instantiatd.
# it can only be inherited

            #notworking
# for abc import ABC, abstractmethod

# class car3(ABC):
#     @abstractmethod
#     def price_inc(self):
#         pass
# class supercar3(car3):
#     def __init__(self,modelname,yearm,price,cc):
#         super.__init__(modelname,yearm,price)
#         self.cc=cc
#     def price_inc(self):
#         self.price_inc= int(self.price*2.5)

# honda =supercar3('city',2017,100000)
# tata = car3('bolt',2016,600000)

# honda.cc=1500
# #print(help(honda))
# print(honda.yearm)
# honda.price_inc()
# print(honda.price)
