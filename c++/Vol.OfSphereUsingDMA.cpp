 #include<iostream>
using namespace std;
int main()
{
    int *r=new int();
    cout<<"enter value of Radius:";
    cin>>*r;
    cout<<"*r="<<*r<<endl;
    delete r;
    float *f=new float();
    *f=1.33*3.14*(*r)*(*r)*(*r);
    cout<<"\n"<<"*f="<<*f;
}
