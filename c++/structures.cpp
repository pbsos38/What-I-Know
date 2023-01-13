#include <iostream>
using namespace std;
struct complex
{
    int R,I;
};
int main()
{
    struct complex C1,C2,C3;
    cout<<"enter 1st complex no:";
    cin>>C1.R,C1.I;
    cout<<"enter 2nd complex no:";
    cin>>C2.R,C2.I;
    C3.R=C1.R+C2.R;
    C3.I=C2.I+C1.I;
    cout<<"sum real="<<C3.R;
    cout<<"sum imaginary="<<C3.I;
}
