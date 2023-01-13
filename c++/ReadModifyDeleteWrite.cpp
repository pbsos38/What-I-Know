#include<iostream>
#include<fstream>
using namespace std;
class student
{
public:
    int roll;
    float e,m,tot;
};
class college
{
    student s;
public:
    void write1()
    {
        ofstream fout;
        fout.open("stud.txt",ios::app);
        cout<<"enter the roll no.:";
        cin>>s.roll;
        cout<<"enter the marks in maths:";
        cin>>s.m;
        cout<<"enter the marks in english:";
        cin>>s.e;
        s.tot=s.m+s.e;
        fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
        fout.close();
    }
    void read1()
    {
        ifstream fin;
        fin.open("stud.txt");
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;
            if(fin.eof())
                break;
            else
            {
                cout<<"\n roll no."<<s.roll;
                cout<<"\n marks in maths:"<<s.m;
                cout<<"\n marks in english:"<<s.e;
                cout<<"\n roll no."<<s.tot;
                cout<<"\n -------------------------\n";
            }
            fin.close();
    }
    }
    void search()
    {
        int wantedroll,found=0;
        cout<<"enter the roll no to be searched:";
        cin>>wantedroll;
        ifstream fin;
        fin.open("stud.txt");
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;
            if(fin.eof())
                break;
            else
                if(s.roll==wantedroll)
            {
                found=1;
                cout<<"\n rollno"<<s.roll<<"\t english:"<<s.e<<"\t maths"<<s.m<<"\t total:"<<s.tot<<endl;
                break;
            }
            if(found==0)
                cout<<"\n\n\nrecord not found";
            fin.close();
        }
    }
        void delete1()
        {
            int wantedroll;
            cout<<"enter the roll no to be deleted:";
        cin>>wantedroll;
        ifstream fin;
        fin.open("stud.txt");
        ofstream fout;
        fout.open("temp.txt");
        int jasus=0;
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;
            if(fin.eof())break;
            if(s.roll!=wantedroll)
                if(s.roll!=wantedroll)
            {
                fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
            }
            else
            {//143
             jasus=1;
            }
            fin.close();
            fout.close();
            if(jasus==0)
                cout<<"\n\n\n\tnot found";
            else
                cout<<"record deleted";
            remove("stud.txt");
            rename("temp.txt","stud.txt");
        }
        }

    void modify()//119-143
    {
        int wantedroll;
        cout<<"enter the roll no to be searched:";
        cin>>wantedroll;
        ifstream fin;
        fin.open("stud.txt");
        ofstream fout;
        fout.open("temp.txt");
        int jasus=0;
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;
            if(fin.eof())break;
            if(s.roll!=wantedroll)
            {
                fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
            }
            else
            {
                cout<<"\nenter new data";
                cout<<"\nenter new marks in maths";
                cin>>s.m;
                cout<<"\nenter new marks in english";
                cin>>s.e;
                s.tot=s.m+s.e;
                fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
                cout<<"\n\n\trecord modified";
                jasus=1;
            }
        }
        fin.close();
        fout.close();
        if(jasus==0)
            cout<<"\n\n\n\t not found";
        remove("stud.txt");
            rename("temp.txt","stud.txt");
    }
};
int main()
{
    char choice;
    college c;
    do
    {
        cout<<"\n\n--------------student record keeper-------------";
        cout<<"\npress w to write:";
        cout<<"\npress r to read:";
        cout<<"\npress s to search:";
        cout<<"\npress d to delete:";
        cout<<"\npress m to modify:";
        cout<<" \npress x to exit";
        cout<<"enter your choice: ";
        choice=getchar();
    switch(choice)
    {
    case 'w':
        c.write1();
        break;
    case 'r':
        c.read1();
        break;
    case 's':
        c.search();
        break;
    case 'd':
        c.delete1();
        break;
    case 'm':
        c.modify();
        break;
    }
    fflush(stdin);
}while(choice!='x');
cout<<"\n\tBye....";
}
