#include<iostream>
#include<cmath>
using namespace std;
class height
{
    int f,i;
public:
    void input()
    {
    cout<<"enter the time of height in feets and inches";
    cin>>f>>i;
    }
    height operator-(height obj)
    {
    height bhai;
    bhai.f=abs(f-obj.f);
    bhai.i=abs(i-obj.i);
    return(bhai);
    }
    void show()
    {
    cout<<"diff of height in feet:"<<f<<endl;
    cout<<"diff of height in inches:"<<i<<endl;
    }
};
int main()
{
    height obj,obj2,obj3;
    obj.input();
    obj2.input();
    obj3=obj2.operator-(obj);
    obj3.show();
}
