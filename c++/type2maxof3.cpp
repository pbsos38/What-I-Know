#include<iostream>
using namespace std;
class greatest
{
    int a,b,c;
    public:
     void input()
     {
        cout<<"enter a,b,c:";
        cin>>a>>b>>c;
     }
     void process()
     {
        if(a>b)
        {
            if(b>c)
                cout<<"a";
                else
                    cout<<"c";
        }
        else
            if(b>c)
                cout<<"b";
            else
                cout<<"c";
        }
    };
    int main()
    {
        greatest obj;
        obj.input();
        obj.process();
    }

