#time and date
#list of functions and discription
# time() returns the number of seconds
# ctime() returns the current date and time
#sleep() stops execution of a thread for given duration
# locsltime() returns the date and time in time.struct_time format
# gmtime() returns time.struct_time in UTC format
# mktime() returns the seconds passed since epoch pas output
# asctime() returns a string representing the same time

import time
print(time.time())
print(time.ctime(time.time()))
print(help(time.time))
print(time.localtime())

#attributes of struct_timeclass
# tm_year      0000,2019,9999
#tm_mon        1-12
#tm_mday       1-31
# tm_hour      0-23
# tm_min       0-59
#tm_sec        0-61
#tm_wday        0-6, monday as 0
#tm_yday        1-366
#tm_isdst       0,1,-1

a=time.localtime()
b=time.mktime(a)
print(b)

c=time.asctime(a)
print(c)
#print(help(time.strftime))
print(a)
x=time.strftime("%m/%d/%y")
print(x)

y="08 august 2019"
s=time.strptime(y,"%d %B %Y")
print(s)


#datetime module
#datetime()         datetime constuctor
#datetime.today()   returns the current date and date
# datetime.now()    returns the curret date and time
#date()             take year,month and day as parameter and creates the corresponding date
#time()             takes hour, min ,sec , microsecond and tzinfo as parameter and creates the correspnding date
#datetime.fromstamp() converts seconds to return the corresponding date and time
# timedelta()        it is the difference between different dates and times(durations)


import datetime
x=datetime.datetime(2019,6,7,4,30,54,678)
print(x)
print(datetime.datetime.now())
print(datetime.datetime.now().year)
print(datetime.datetime.now().month)
print(datetime.datetime.now().hour)
print(datetime.date(2019,7,5))
print(datetime.time(3,45,23))
d=datetime.timedelta(days=20)
f=datetime.timedelta(days=30)
g=f-d
print(g)
print(type(g))
