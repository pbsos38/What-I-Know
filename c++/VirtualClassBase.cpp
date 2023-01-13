#include<iostream>
using namespace std;
class rect
{
public:
    void rarea()
    {
        cout<<"area of rect";
    }
};
class circle:virtual public rect
{

};
class square:public virtual rect{};
class shapes:public circle,public square{};
int main()
{
    shapes sobj;
    sobj.rarea();
}
