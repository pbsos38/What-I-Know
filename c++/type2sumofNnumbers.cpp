#include<iostream>
using namespace std;
class num
{
    int sum=0,i,n;
public:
    void input()
    {
    cout<<"enter no. of numbers ";
    cin>>n;
    int s[n];
    for(i=0;i<n;i++)
    {
        cout<<"enter number:";
        cin>>s[i];
    }
        for(i=0;i<n;i++)
    {
        sum=sum+s[i];
    }
    }
    void show()
    {
    cout<<"total sum="<<sum;
    }
};
int main()
{
    num obj;
    obj.input();
    obj.show();
}
