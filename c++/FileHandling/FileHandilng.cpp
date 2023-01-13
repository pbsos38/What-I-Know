#include<fstream>
#include<iostream>
using namespace std;
int main()
{
    char fill[30];
    ofstream fout;
    cout<<"enter file name:";
    cin>>fill;
    fout.open(fill,ios::out);
    fout<<"real java:\n";
    fout<<"BCE:\n";
    fout.close();
    ifstream fin(fill);
    while(!fin.eof())
    {
        char ch=fin.get();
        cout<<ch;
    }
    fin.close();
}
