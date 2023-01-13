#include<iostream>
using namespace std;
int main()
{
    int *i=new int();
    *i=7;
    cout<<"*i="<<*i<<endl;
    delete i;
    float *f=new float();
    *f=8.2;
    cout<<"\n"<<"*f="<<*f;
}
