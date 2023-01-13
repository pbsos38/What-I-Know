#data anyalysis with python
#pandas
#pandas is a software libraary written for python programming language for data maniulationand analysis
#features
#tabular data with hetrogeneously-typed columns
#orderd and unorderd time series data.
#arbitary matrix adta with row and column labels 
#any other form of observational / statistical data sets. the data actually need not be loaded at all to be place into a pandas data structure

import pandas as pd
xyz_web = {'day':[1,2,3,4,5,6],"visitors":[1000,600,7000,1000,400,350],'bounce_rate':[10,20,23,15,10,34]}

df = pd.DataFrame(xyz_web)
print(df)
#other operations

#slicing dataframes
print(df.head(2))
print(df.tail(2))
#joining and merging
df1 = pd.DataFrame({"HPI":[80,90,70,60],"INt_rate":[2,1,2,3],"ind_gdp":[50,45,45,67]},
                    index = [2001,2002,2003,2004])

df2 = pd.DataFrame({"HPI":[80,90,70,60],"INt_rate":[2,1,2,3],"ind_gdp":[50,45,45,67]},
                    index = [2005,2006,2007,2008])

merge = pd.merge(df1,df2)
print(merge)
merge2 = pd.merge(df1,df2,on = "HPI")
print(merge2)

df3 = pd.DataFrame({"INt_rate":[2,1,2,3],"ind_gdp":[50,45,45,67]},
                    index = [2001,2002,2003,2004])

df4 = pd.DataFrame({"low_tier_HPI":[80,90,70,60,45],"Unemployment":[2,1,2,3,65]},
                    index = [2001,2002,2003,2004,2005])

merge = df4.join(df3)
print(merge)
#changing the coloumn headers
#changing the index
import matplotlib.pyplot as plt
from matplotlib import style
style.use("fivethirtyeight")
df5 = pd.DataFrame({"day":[1,2,3,4], "visitors":[200,100,230,300], "Bounce_rate":[20,45,60,10]}) 
df5.set_index("day",inplace=True)
#df5.plot()
#plt.show()
print(df5)

df6 = df.rename(columns={"visitors":"users"})
print(df6)

#concatenation adding a coloumn
df1 = pd.DataFrame({"HPI":[80,90,70,60],"INt_rate":[2,1,2,3],"ind_gdp":[50,45,45,67]},
                    index = [2001,2002,2003,2004])

df2 = pd.DataFrame({"HPI":[80,90,70,60],"INt_rate":[2,1,2,3],"ind_gdp":[50,45,45,67]},
                    index = [2005,2006,2007,2008])

concat = pd.concat([df1,df2])
print(concat)
