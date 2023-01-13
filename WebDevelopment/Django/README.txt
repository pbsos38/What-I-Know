pyhton web-development django

#terminal = django-admin startproject django-project

#to run server = python manage.py runserver

# to return control to terminal press ctrl+c

# to start a new app  =  python manage.py startapp DemoApp

# to create a connection add urls.py file in same directory

# and paste
# from django.urls import path
# from. import views #or the name of index file
# urlpatterns = [
#     path('', views.hi,name='home-page'),
# ]    

#in urls.py file of django project 
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
