#include<iostream>
using namespace std;
class student
{
    int p,c,tot,per;
public:
    void input()
    {
        cout<<"enter marks in physics & chemistry:";
        cin>>p>>c;
    }
    void process()
    {
        tot=p+c;
        per=tot*100/200;
    }
    int show()
    {
        cout<<"total marks"<<tot<<endl;
        cout<<"percentage"<<per;
    }
};
int main()
{
    int n,i;
    cout<<"enter no of students:";
    cin>>n;
    student s[n];// creation of array
    for(i=1;i<=n;i++)
    {
        s[i].input();
        s[i].process();
    }
    for(i=1;i<=n;i++)
    {
        s[i].show();
    }
}
