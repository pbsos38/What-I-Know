#include<iostream>
using namespace std;
class COMPLEX
{
    int r,i;
public:
    void input()
    {
    cout<<"enter real &imaginary no.";
    cin>>r>>i;
    }
    void show()
    {
        cout<<r<<"+"<<i<<endl;
    }
    COMPLEX party(COMPLEX num)
    {
        COMPLEX bhai;
    bhai.r=r+num.r;
    bhai.i=i+num.i;
    return(bhai);
    }
};
int main()
{
    COMPLEX num,num2,num3;
    num.input();
    num2.input();
    //c1.party(c2);
    //c1.show();
    //c2.show();
    num3=num2.party(num);
    num3.show();
}
