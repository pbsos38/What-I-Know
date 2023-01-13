#include<iostream>
using namespace std;
class cyc
{
    int r,h,vcy;
public:
    void setvo(int rr,int hh)
    {
        r=rr;
        h=hh;
    }
    void process()
    {
        vcy=3.14*r*r*h;
    }
    int getcylinder()
    {
        return(vcy);
    }
};
class con
{
    int r,h,vco;
public:
    void setvo(int rr,int hh)
    {
        r=rr;
        h=hh;
    }
    void process()
    {
        vco=vcy/3;
    }
    int getcone()
    {
        return(vco);
    }
};
class shapes
{
    cyc yobj;
    con nobj;
public:
    void vol()
    {

    }
};
int main()
{
    int r,h;
    cout<<"enter r&h";
    cin>>r>>h;
    vol obj;
    obj.setvo(r,h);
    obj.process();
    int vcy=obj.getcylinder();
    cout<<"vol of cylinder is "<<vcy;
    int vco=obj.getcone();
    cout<<"vol of cone is "<<vco;
}
