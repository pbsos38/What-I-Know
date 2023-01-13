#include<iostream>
using namespace std;
int main()
{
    int n,i,wanted,count=0;
    cout<<"enter no. of students:";
    cin>>n;
    int m[n];
    for(i=1;i<=n;i++)
    {
        cout<<"enter marks of ";
        cout<<i;
        cout<<" students:";
        cin>>m[i];
    }
    cout<<"enter marks to be searched:";
    cin>>wanted;
    for(i=1;i<=n;i++)
    {
        if(wanted==m[i])
        {
            cout<<"found at";
            cout<<i;
            cout<<"location:";
            count++;
        }
    }
    if(count==0)
        cout<<"not found";
    else
        cout<<"found";
        cout<<count;
        cout<<"times";
}
