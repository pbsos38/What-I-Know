from django.urls import path
from. import views
urlpatterns = [
    path('', views.indexView,name='home-page'),
    path('dashboard/',views.dashboardView,name='dashboard'),
    # path('login/',),
    path('register',views.registerView,name="register"),
    # path('logout',),
]