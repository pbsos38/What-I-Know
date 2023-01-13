#include<fstream>
#include<iostream>
using namespace std;
int main()
{
    char src[30];
    int c=0,w=0,l=1;
    ifstream fin(src);
    //ofstream fout;
    cout<<"enter file name to open:";
    cin>>src;
    //cout<<"enter file name to write:";
    //cin>>des;
    fin.open(src,ios::in);
        if(fin.fail())
        {
            cout<<"file not found:";
        }
        while(!fin.eof())
        {
           int ch=fin.get();
            cout<<ch;
            if(ch==' '||ch=='\n')
                w++;
            if(ch=='\n')
                l++;
            c++;
        }
        cout<<"words:"<<w<<" lines:  "<<l<<" total "<<c;
        fin.close();
}
