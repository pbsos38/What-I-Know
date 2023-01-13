#include<iostream>
using namespace std;
class clock
{
    int h,m;
public:
    void input()
    {
    cout<<"enter the time of clock in hours and min.";
    cin>>h>>m;
    }
    clock party(clock num)
{
    clock bhai;
    bhai.h=h-num.h;
    bhai.m=m-num.m;
    return(bhai);
}
    void show()
    {
        cout<<"diff b/w hours:"<<h<<endl;
        cout<<"diff b/w minutes:"<<m;
    }
};
int main()
{
    clock num,num2,num3;
    num.input();
    num2.input();
    num3=num2.party(num);
    num3.show();
}
