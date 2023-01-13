#method overriding
#it can be achieved to change functionally of parent class function

class parent:
    def func1(self):
        print('this is fun1')

class child(parent):
    def func1(self):
        print("this is func2")#over ridden method

ob = child()
ob.func1()
