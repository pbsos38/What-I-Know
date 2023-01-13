#include<iostream>
using namespace std;
class employee
{
    int eid;
    float bs,da,hra,gs;
public:
    void input()
    {
        cout<<"enter basic salary and employ id:";
        cin>>bs>>eid;
    }
    void process()
    {
        da=bs*100/100;
        hra=bs*50/100;
        gs=bs+da+hra;
    }
    void show()
    {
        cout<<eid<<"get gross"<<gs<<endl;
    }
    float getgs()
    {
        return(gs);
    }
};

int main()
{
    int n,i;
    cout<<"enter no. of employees:";
    cin>>n;
    employee E[n];
    for(i=0;i<n;i++)
    {
        E[i].input();
        E[i].process();
    }
    cout<<"\n salary slip:";
    for(i=0;i<n;i++)
    {
        E[i].show();
    }
    float max=E[0].getgs();
    for(i=0;i<n;i++)
    {
        E[i].show();
        if(max<E[i].getgs())
            max=E[i].getgs();
    }
    cout<<"max is:"<<max<<endl;
        float min=E[0].getgs();
    for(i=0;i<n;i++)
    {
        E[i].show();
        if(min>E[i].getgs())
            min=E[i].getgs();
    }
    cout<<"min is:"<<min;
}
