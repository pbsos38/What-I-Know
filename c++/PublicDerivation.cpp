#include<iostream>
using namespace std;
class b
{
    void x()
    {
        cout<<"pvt\n";
    }
protected:
    void z()
    {
        cout<<"public\n";
    }
};
class d:public b
{
public:
    void all()
    {
        x();//not accessible
        y();//accessible
        z();//accessible
    }
};
class e:public d
{
public:
    void all2()
    {
        x();//not accessible
        y();//accessible
        z();//accessible
    }
};
int main()
{
    d obj;
    obj.all();
    e obj2;
    obj2.all2();
}
