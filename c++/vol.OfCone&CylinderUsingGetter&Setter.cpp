#include<iostream>
using namespace std;
class volume
{
    float r,h,co,cy;
public:
    void setvol(float rr,float hh)
    {
        r=rr;
        h=hh;
    }
    void process()
    {
        co=3.14*r*r*h;
        cy=0.3*3.14*r*r*h;
    }
    float getco()
    {
        return(co);
    }
    float getcy()
    {
        return(cy);
    }
};
int main()
{
    volume amn;
    float r,h;
    cout<<"enter r&h:";
    cin>>r>>h;
    amn.setvol(r,h);
    amn.process();
    float co=amn.getco();
    cout<<"\nvol. of cone="<<co;
    float cy=amn.getcy();
    cout<<"\nvol. of cylinder="<<cy;

}
