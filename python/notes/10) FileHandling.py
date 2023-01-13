#file handling
# file.read()

import os
file = open("D:/What I Know/python/experimental.txt",'r')
# file.close()

print(file.read())
# file.close()

print(file.read(5))
# file.close()

#read lines

print(file.readline())
# file.close()

print(file.readline(2))
# file.close()

print(file.readlines())
# file.close()

#looping over a file object

for line in file:
    print(file.readlines())# one problem is that it skips the first line- no sol provided 
# file.close()

#writing in an existing file

import os
file = open("D:/What I Know/python/experimental.txt",'w')
file.write("just leave me alone\n")
file.write("you moron just get away")
# file.close()

#creation of file

# file = open("D:/What I Know/python/experimental-creting1.txt",'x')
# file.write("u r here again, but why")
# file.close()

#deleting a file
                    #not working
# if os.path.exists("D:/What I Know/python/experimental-creting1.txt"):
#     os.remove("D:/What I Know/python/exerimental-creating1.txt")
# else:
#     print("file does not exists")

#deleting a folder
#not working
#os.rmdir("new folder")
