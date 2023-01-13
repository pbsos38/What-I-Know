#include<iostream>
using namespace std;
class student
{
    int p,c,tot,per;
public:
    void setmarks(int pp,int cc)
    {
        p=pp;
        c=cc;
    }
    void process()
    {
        tot=p+c;
        per=tot*100/200;
    }
    int gettotal()
    {
        return(tot);
    }
    int getper()
    {
        return(per);
    }
};
int main()
{
    student amn;
    int p,c;
    cout<<"enter marks";
    cin>>p>>c;
    amn.setmarks(p,c);
    amn.process();
    int tot=amn.gettotal();
    cout<<"\ntotal marks"<<tot;
    int per=amn.getper();
    cout<<"percentage="<<per;
    if(per>=50)
        cout<<"pass";
    else
        cout<<"fail";
}
