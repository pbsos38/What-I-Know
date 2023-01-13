#include<cmath>
#include<iostream>
using namespace std;
class clock
{
    int h,m,s;
public:
    void input()
    {
    cout<<"enter the time of clock in hours, min.& sec.";
    cin>>h>>m>>s;
    }
    clock operator-(clock num)
{
    clock bhai;
    bhai.h=abs(h-num.h);
    bhai.m=abs(m-num.m);
    bhai.s=abs(s-num.s);
    return(bhai);
}
    void show()
    {
        cout<<"diff b/w hours:"<<h<<endl;
        cout<<"diff b/w minutes:"<<m<<endl;
        cout<<"diff b/w sec:"<<s<<endl;
    }
};
int main()
{
    clock num,num2,num3;
    num.input();
    num2.input();
    num3=num2.operator-(num);
    num3.show();
}

