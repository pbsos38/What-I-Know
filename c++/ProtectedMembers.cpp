#include<iostream>
using namespace std;
class college
{
protected:
    int cfee;
    void fun()
    {
        cout<<"enter the c fee:";
        cin>>cfee;
    }
};
class training:public college
{
protected:
    int tfee;
    void fun2()
    {
        fun();
        cout<<"enter the tution fee:";
        cin>>tfee;
    }
};
class pocket
{
protected:
    int pfee;
    void fun3()
    {
        cout<<"enter the pocket money:";
        cin>>pfee;
    }
};
class total:public pocket,public training
{
public:
    void show()
    {
        fun2();
        fun3();
        int tot=pfee-cfee-tfee;
        cout<<"rest over amount is:"<<tot;
    }
};
int main()
{
    total obj;
    obj.show();
}
