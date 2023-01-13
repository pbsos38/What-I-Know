#include<iostream>
using namespace std;
class cylinder
{
    float r,h,volcy;
public:
    void setInput(int rr,int hh)
    {
        r=rr;
        h=hh;
    }
    void process()
    {
        volcy=3.14*r*r*h;
    }
    float getOver()
    {
        return(volcy);
    }
};
class cone
{
    float volco,coneV;
public:
    void inputCone(float V)
    {
        volco=V;
    }
    void process()
    {
        coneV=volco/3;

    }
    float show()
    {
        return(coneV);
    }
};
int main()
{
    cylinder obj;
    obj.setInput(10,3);
    obj.process();
    float v=obj.getOver();
    cone cobj;
    cobj.inputCone(v);
    cobj.process();
    float c=cobj.show();
    cout<<"vol of cylinder:"<<v<<endl;
    cout<<"vol of cone:"<<c;

}
