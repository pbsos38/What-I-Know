#include<iostream>
using namespace std;
int main()
{
    void swap(int a, int b);
    int x,y;
    cout<<"enter x";
    cin>>x;
    cout<<"enter y";
    cin>>y;
    swap(x,y);
}
void swap(int a,int b)
{
    int temp=a;
    a=b;
    b=temp;
    cout<<"new:x=";
    cout<<a<<endl;
    cout<<"new:y=";
    cout<<b;
}

