#include<iostream>
using namespace std;
class bank
{
    float bal;
public:
    bank()
    {
        bal=5000;
    }
    void show()
    {
        cout<<"bal="<<bal<<endl;
    }
    bank(float b)
    {
        bal=b;
    }
    bank(bank &robj)
    {
        bal=robj.bal;
        robj.bal=0;
    }
    ~bank()
    {
        cout<<"bye\n";
    }
};
int main()
{
    bank amnobj;
    amnobj.show();
    bank rmnobj(81000.81);
    rmnobj.show();
    bank chmnobj(rmnobj);
    chmnobj.show();
}
