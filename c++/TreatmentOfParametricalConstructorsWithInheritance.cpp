#include<iostream>
using namespace std;
class b
{
public:
    b()
    {
        cout<<"base class constructor\n";
    }
    b(float y)
    {
        cout<<"y="<<y<<endl;
    }
};
class d:public b
{
public:
    d()
    {
        cout<<"derived class constructor\n";

    }
    d(int x,float t):b(t)
    {
        cout<<"x="<<x;
    }
};
int main()
{
    d obj(9,2.6);
}
