#include<iostream>
using namespace std;
class cylinder
{
    int r,h;
public:
    void input()
    {
    cout<<"enter the radius and height:";
    cin>>r>>h;
    }
    friend void vol(cylinder obj);
};
void vol(cylinder obj)
{
    float volu=3.14*obj.r*obj.r*obj.h;
    cout<<"vol of cylinder is :"<<volu;
}
int main()
{
    cylinder obj1;
    obj1.input();
    vol(obj1);
}
