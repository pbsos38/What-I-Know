from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def hi(request):
    #return HttpResponse('<h1>This is my home age</h1>')
    return render(request,'DemoApp/index.html')
# to create a connection add urls.py file in same directory
# and paste
# from django.urls import path
# from. import views #or the name of index file
# urlpatterns = [
#     path('', views.hi,name='home-page'),
# ]    

#in urls.py ile of django project 
#from django.urls import include
#and add path with app's folder name
#path('',include('DemoApp.urls'))


#now create new directory with name templates
#and inside templates create new directory with same name as of DemoApp's folder name
#create a html file inside demoapp and do minimal coding
#go to apps.py(app folder) and cop the class name
#go to settings.py(project folder) and in class INSTALLED_APPS paste is in form of     appFolderName.apps.classname
#in my case it looks like       'DemoApp.apps.DemoappConfig',

#now in views (app folder) change    return HttpResponse('<h1>This is my home age</h1>')
 #  to    return render(request,'appfolder name/index.html')
