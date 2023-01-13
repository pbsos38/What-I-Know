#include<iostream>
using namespace std;
class vol
{
    int r,h,vco,vcy;
public:
    void setvo(int rr,int hh)
    {
        r=rr;
        h=hh;
    }
    void process()
    {
        vcy=3.14*r*r*h;
        vco=vcy/3;
    }
    int getcylinder()
    {
        return(vcy);
    }
    int getcone()
    {
        return(vco);
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
