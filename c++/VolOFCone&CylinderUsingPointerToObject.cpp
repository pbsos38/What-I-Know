#include<iostream>
using namespace std;
class volume
{
    int r,h,vco,vcy;
public:
    void input()
    {
        cout<<"enter r&h:";
        cin>>r>>h;
    }
    void process()
    {
        vco=0.3*3.14*r*r*h;
        vcy=3.14*r*r*h;
        cout<<"vol of cone="<<vco<<endl;
        cout<<"vol of cylinder="<<vcy;
    }

};
int main()
{
    volume obj1,obj2,*p,*z;
    p=&obj1;
    z=&obj2;
    (*p).input();
    (*z).input();
    (*p).process();
    (*z).process();

}
