#include<iostream>
using namespace std;
class facto
{
    int i,fact=1,n;
public:
    void setfac(int ii, int nn)
    {
        i=ii;
        n=nn;
    }
     void process()
     {
    for(i=1;i<=n;i++)
    fact=fact*i;
     }
     int getfact()
     {
         return(fact);
     }
};
int main()
{
    facto amn;
    int i,n;
    cout<<"enter no=";
    cin>>n;
    amn.setfac(i,n);
    amn.process();
    int fact=amn.getfact();
    cout<<"\nfactorial is"<<fact;

}
