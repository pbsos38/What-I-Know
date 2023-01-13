#include<iostream>
using namespace std;
class complex
{
    int r,i;
public:
    void input()
    {
        cout<<"enter the r&i:";
        cin>>r>>i;
    }
    void show()
    {
        cout<<r<<"  "<<i<<endl;
    }
    void operator++()
    {
        r++;
        i=i+1;
    }
    void operator++(int)
    {
        r++;
        i++;
    }
    void operator+=(complex c2)
    {
        r=r+c2.r;
        i=i+c2.i;
    }
    complex operator+(complex c1)
    {
        complex bhai;
        bhai.r=r+c1.r;
        bhai.i=i+c1.i;
        return(bhai);
    }
};
int main()
{
    complex c1,c2,c3;
    c1.input();
    ++c1;
    c1.show();
    c1++;
    c1.show();
    c2.input();
    c1.operator+=(c2);
    c1.show();
    c3=c2.operator+(c1);
    c3.show();
}
