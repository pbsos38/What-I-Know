#include<iostream>
using namespace std;
class lia
{
public:
    void show1()
    {
        cout<<"lia"<<endl;
    }
};
class lib:public lia
{
public:
    void show2()
    {
        cout<<"lib"<<endl;
    }
};
class lic
{
public:
    void show3()
    {
        cout<<"lic"<<endl;
    }
};
class liz: public lic,public lib
{
public:
    void show4()
    {
        show1();
        show2();
        show3();
        cout<<"liz"<<endl;
    }
};
int main()
{
    liz obj;
    obj.show4();
}
