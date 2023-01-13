                                                            #Hash table or HashMap
# it is a type of data structure ythat maps to iits value pairs
#It implements abstract array data type
#understand with help of dictionaries
# creating dictionaries

#method-1
my_dict={'dave':'001','ava':'002','joe':'003'}
print(my_dict)
type(my_dict)

#method 2
new_dict=dict()
print(new_dict)
type(new_dict)

new_dict2=dict(dave='001',ava='002')
print(new_dict2)


                                                                            # ordered ditionary

from collections import OrderedDict
#order dict is a dictionary subclass which rememers the order in which the entries were done                                                                            
d= OrderedDict()
d[1] ='p'
d[2] ='r'
d[3] ='i'
d[4] ='n'
d[5] ='c'
d[6] ='e'
print(d)
print(d.keys())
d[1]='e'
print(d)

                                                                            #Default dictionary

#it is a dictionary subclass which calls a fxactory function to supply missing values
from collections import defaultdict

d= defaultdict(int)
d[1]='python'
d[2]='java'
print(d[3])

#Some other collections
#userDict is a wrapper around dictionary objects for easier dictionary objects sub-classing.
#userDict is a wrapper around list objects for easier list sub-classing.
#userDict is a wrapper around string objects for easier string sub-classing.


                                                    #nested dictionries
emp_details={'employee':{'dave':{'id':'001','salary':'2000','designation':'team lead'},
'ava':{'id':'002','salary':'3000','designation':'leadar'}}}
print(emp_details)

                                        # performing operations on hash tables
print(my_dict['dave'])  #acessing data
print(my_dict.keys())   #all the keys in dict will be returned
print(my_dict.values()) #all the values in dict will be returned
print(my_dict.get('ava')) #all the speciic values in dict will be returned
for x in my_dict:         # getting content with help of loop
    print(x)
for x in my_dict.values():
    print(x)
for x,y in my_dict.items():
    print(x,y)
                                            #updating content in dictionary
                                            
my_dict['dave']='004'
my_dict['chirs']='003'
print(my_dict)
                                            #deleting content from dictionary
print(my_dict.pop('ava'))
print(my_dict.popitem())# removes last item
del my_dict['dave']
print(my_dict)
                                            #converting a dictionary in a dataframe
#dataFrame is a 2-d data structure that consists of columns of various types, ver similarly to python dictionary and can be converted to pandas data framework
#pandas in short help in organising content in the form of table
# to install pandas pls run command in terminal as " pip install pandas"
import pandas as pd
df = pd.DataFrame(emp_details['employee'])
print(df)


