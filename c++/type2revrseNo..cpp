#include<iostream>
using namespace std;
class reve
{
    int n,rev=0,r;
public:
    void input()
    {
    cout<<"enter no:";
    cin>>n;
    }
    void process()
    {
    while(n!=0)
    {
        r=n%10;
        rev=rev*10+r;
        n=n/10;
    }
    }
    void show()
    {
    cout<<"\n reverse no is="<<rev;
    }
};
    int main()
    {
        reve obj;
        obj.input();
        obj.process();
        obj.show();
    }
