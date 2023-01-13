#include<iostream>
using namespace std;
class salary
{
    int gross;
public:
    void input()
    {
    cout<<"enter the gross amount";
    cin>>gross;
    }
    friend class net;
};
class tax
{
    int taxa;
public:
    void input()
    {
        cout<<"enter the tax amount:";
        cin>>taxa;
    }
    friend class net;
};
class net
{
public:
void vol(salary obj,tax objt)
{
    float nets=obj.gross-objt.taxa;
    cout<<"net amout is:"<<nets;
}
};
int main()
{
    salary obj1;
    obj1.input();
    tax obj2;
    obj2.input();
    net obj3;
    obj3.vol(obj1,obj2);
}
