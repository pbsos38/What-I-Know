#include<iostream>
using namespace std;
class greatest
{
    int p,ma,mi,s,i,n;
public:
    void input()
    {
        cout<<"enter data in array:";
        for(i=0;i<n;i++)
        cin>>s[i];
    }
    void process()
    {
        ma=s[i];
        for(i=0;i<n;i++)
        {
            if(ma<s[i])
            {
                ma=s[i];
            }
        }
        cout<<"max is:"<<ma;
    }
    void process2()
    {
        mi=s[i];
        for(i=0;i<n;i++)
        {
            if(mi>s[i])
            {
                mi=s[i];
            }
        }
        cout<<"min is"<<mi;
    }
};
int main()
{
    int n,i;
    cout<<"enter length of array";
    cin>>n;
    greatest s[n];// creation of array
    for(i=1;i<=n;i++)
    {
        s[i].input();
        s[i].process();
    }
}
