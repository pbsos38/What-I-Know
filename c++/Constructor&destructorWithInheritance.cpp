#include<iostream>
using namespace std;
class b
{
public:
    b()
    {
        cout<<"base class constructor\n";
    }
    ~b()
    {
        cout<<"base class dectructor\n";
    }
};
class d:public b
{
public:
    d()
    {
        cout<<"derived class constructor\n";

    }
    ~d()
    {
        cout<<"derived class destructor\n";
    }
};
int main()
{
    d obj;
}
