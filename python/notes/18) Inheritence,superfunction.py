#inheritence in python

#__init__() function s automatically called every time the class is used to create an object.

class parent:
    def __init__(self,fname,fage):
        self.name = fname
        self.age = fage
    def view(self):
        print(self.name ,self.age)

class child(parent):
    def __init__(self,fname,fage):
        parent.__init__(self,fname,fage)
        self.lastname = "Prince"

    def view(self):
        print(self.age,self.lastname,self.name)

ob = child(23 , 'python')
ob.view()

#types of inheritence
#1)single -> when the inheritance involves one child class and one parent class
#2)multiple ->it invoves more than one parent class
#3)multilevel -> the child class acts as a parent class for another child class
#4)Hierarchical -> more than one type of inheritance
#5)hybrid

#eg of single inheritence
class parent:
    def funcl(self):
        print("this is fun1")

class child(parent):
    def func2(self):
        print("this is fun2")
ob = child()
ob.funcl()

#eg of multiple inheritence
class parent:
    def funcl(self):
        print("this is fun1")

class parent2:
    def func3(self):
        print("this is function 3")

class child(parent,parent2):
    def func2(self):
        print("this is fun2")
ob = child()
ob.funcl()
ob.func3()


#eg of multilevel inheritence
class parent:
    def funcl(self):
        print("this is fun1")

class parent2(parent):
    def func3(self):
        print("this is function 3")

class child(parent2):
    def func2(self):
        print("this is fun2")
ob = child()
ob.funcl()
ob.func3()


#eg of heirarchical inheritence
class parent:
    def funcl(self):
        print("this is fun1")

class parent2(parent):
    def func3(self):
        print("this is function 3")

class child(parent):
    def func2(self):
        print("this is fun2")
ob = child()
ob.funcl()
ob1 =parent2()
ob1.funcl()

#eg of hybrid inheritence
class parent:
    def funcl(self):
        print("this is fun1")

class parent2(parent):
    def func3(self):
        print("this is function 3")

class parent3:
    def func4(self):
        print("this is func4")

class child(parent,parent3):
    def func2(self):
        print("this is fun2")
ob = child()
ob.funcl()
ob.func4()


#python super function
#super funtions directly calls the parent class methods

class parent:
    def func1(self):
        print('this is fun1')

class child(parent):
    def func2(self):
        super().func1()
        print("this is func2")

ob = child()
ob.func2()

