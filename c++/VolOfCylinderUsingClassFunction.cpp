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
    friend class volume;
};
class volume
{
    public:
void vol(cylinder obj)
{
    float volu=3.14*obj.r*obj.r*obj.h;
    cout<<"vol of cylinder is :"<<volu;
}
};
int main()
{
    cylinder obj1;
    obj1.input();
    volume obj2;
    obj2.vol(obj1);
}
