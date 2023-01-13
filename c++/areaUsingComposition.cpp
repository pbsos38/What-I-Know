#include<iostream>
using namespace std;
class volco
{
    int r,h,vco;
public:
    void vcon()
    {
        cout<<"enter r&h:";
        cin>>r>>h;
        vco=3.14*r*r*h*0.3;
        cout<<"vol. of cone:"<<vco<<endl;
    }
};
class volcy
{
    int r,h,vcy;
public:
    void vcyl()
    {
        cout<<"enter r&h:";
        cin>>r>>h;
        vcy=3.14*r*r*h;
        cout<<"vol. of cylinder:"<<vcy;
    }
};
class shapes
{
    volco coneobj;
    volcy cylinderobj;
public:
    void volumes()
    {
    coneobj.vcon();
    cylinderobj.vcyl();
    }
};
int main()
{
    shapes obj;
    obj.volumes();
}
