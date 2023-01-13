
#data conversions

# #data munging  = converting one type of file to other
# import pandas as pd
# #print(help(pd))
# #not working just google code provided is for csv type
# country = pd.read_csv('D:\What I Know\WebDevelopment\node-js\project\ReriddenText.txt', index_col = 0)
# country.to_html('edu.html')


# import pandas as pd
# import matplotlib.pyplot as plt
# from matplotlib import style
# style.use('fivethirtyeight')
# country = pd.read_csv('D:\What I Know\WebDevelopment\node-js\project\ReriddenText.txt', index_col = 0)
# df = country.head(5)
# df = df.set_index(["country code"])
# sd = df.reindex(columns=['2011','2012'])
# db = sd.diff(axis=1)
# db.plot(kind = 'bar')
# plt.show()

from statistics import mean
print(mean([1,2,2,2,1,3,4,1,5]))


from statistics import median
print(median([1,1,1,2,2]))

from statistics import mode # mode = most repeateditem
print(mode([1,1,1,2,2]))

from statistics import variance
print(variance([1,1,1,2,2]))

#just info #pydoop is a python interface to haddop that allows youo to write mapreduce applications and interact with HDFS in pure python
