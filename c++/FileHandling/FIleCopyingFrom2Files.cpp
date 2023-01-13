#include<fstream>
#include<iostream>
using namespace std;
class filecopy
{
    ofstream fout;
    ifstream fin1,fin2;
    char src[30],src2[30],des[30],ch1,ch2;
public:
    void docopy()
    {
        cout<<"enter file name to read:";
        cin>>src;
        cout<<"enter file name to read:";
        cin>>src2;
        cout<<"enter file name to write:";
        cin>>des;
        fin1.open(src,ios::in);
        if(fin1.fail())
        {
            cout<<"file not found:";
            return;
        }
        fin2.open(src2,ios::in);
        if(fin2.fail())
        {
            cout<<"file not found2:";
            return;
        }
        fout.open(des,ios::out);
        while(!fin1.eof())
        {
            ch1=fin1.get();
            cout<<ch1;
            fout<<ch1;
        }
        fout.open(des,ios::out);
        while(!fin2.eof())
        {
            ch2=fin2.get();
            cout<<ch2;
            fout<<ch2;
        }
        fin1.close();
        fin2.close();
        fout.close();
    }
};
int main()
{
    filecopy obj;
    obj.docopy();
}

