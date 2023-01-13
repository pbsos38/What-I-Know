#include<iostream>
using namespace std;
class student
{
    int p,c,tot,per;
public:
    void input()
    {
        cout<<"enter marks in physics and chemistry:";
        cin>>p>>c;
    }
    void process()
    {
        tot=p+c;
        per=tot*100/200;
    }
    void show()
    {
        cout<<tot<<""<<endl<<per<<endl;
    }
};
int main()
{
    student amn,rmn;
    amn.input();
    rmn.input();
    amn.process();
    rmn.process();
    amn.show();
    rmn.show();
}
