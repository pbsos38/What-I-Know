#include<fstream>
#include<iostream>
using namespace std;
class filecopy
{
    ofstream fout;
    ifstream fin;
    char src[30],des[30],ch;
public:
    void docopy()
    {
        cout<<"enter file name to read:";
        cin>>src;
        cout<<"enter file name to write:";
        cin>>des;
        fin.open(src,ios::in);
        if(fin.fail())
        {
            cout<<"file not found:";
            return;
        }
        fout.open(des,ios::out);
        while(!fin.eof())
        {
            ch=fin.get();
            cout<<ch;
            fout<<ch;
        }
        fin.close();
        fout.close();
    }
};
int main()
{
    filecopy obj;
    obj.docopy();
}
