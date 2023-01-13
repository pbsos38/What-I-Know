#include<cmath>
#include<iostream>
using namespace std;
class height
{
    int f,i;
public:
    void input()
    {
     cout<<"enter the height in feet and inches:";
     cin>>f>>i;
    }
    height party(height obj)
    {
        height bhai;
        int df=(f*12+i)-(obj.f*12+obj.i);
        bhai.f=(df/12);
        bhai.i=(df%12);
        return(bhai);
    }
    void show()
    {
    cout<<"diff b/w of height in feet:"<<f<<endl;
    cout<<"diff b/w of height in inches:"<<i<<endl;
    }
};
int main()
{
    height obj,obj2,obj3;
    obj.input();
    obj2.input();
    obj3=obj2.party(obj);
    obj3.show();
}

