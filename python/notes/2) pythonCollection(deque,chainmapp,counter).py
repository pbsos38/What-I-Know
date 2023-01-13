                                                                                
                                                                            # python collections

from collections import namedtuple
# namedtuple is just like creating a table 
a=namedtuple('courses', 'pyhton, java')
b=a('started','Started')
c=a('completed','completed')
print(b,c)
# alternate mathod
# to represent this in a list
s=a._make( ['pending','pending'])
print(s)

                                                                            #deque
#pronounced:deck
#an optimised list which is used to perform insertion and deletion easily
from collections import deque
a=['p','r','i','n','c','e']
d=deque(a)
print(d)
print(d.append('python'))
print(d.appendleft('python'))

print(d.pop(),d)
print(d.popleft(),d)

                                                                            #chainmap

from collections import ChainMap
# chainmap is a dictionary like class for creating a single view of multiple classes
cou = {1:'python',2:'java' }
co = {1:'python',2:'java'}
print(ChainMap(cou,co))
                                                                            #counter
                                                                            
from collections import Counter
#counter is a dictionary subclass for cojnting hashable objects
#used to count hashable objects in a list

f=[2,4,4,4,6,6,6,6,6,8,8,8,8,8,8,8]
hj = Counter(f)
print(hj)
print(list(hj.elements()))
print(hj.most_common())
sub = {2:1,8:3 }
print(hj.subtract(sub))
print(hj.most_common())
