#include<iostream>
using namespace std;
class gross
{
    int grossa;
public:
    void input()
    {
    cout<<"enter the gross amount";
    cin>>grossa;
    }
    friend void vol(gross obj,tax objt);
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
    friend void vol(gross obj,tax objt);
};
void vol(gross obj,tax objt)
{
    float volu=obj.grossa-objt.taxa;
    cout<<"vol of cylinder is :"<<volu;
}
int main()
{
    salary obj1;
    obj1.input();
    tax obj2;
    obj2.input();
    vol(obj1,obj2);
}
