//programs to write and records from a text file
#include<fstream>
#include<iostream>
using namespace std;
class student   //size:16 bytes
    {
       public:
         int roll;
         float e,m,tot;
    };
class college //container class
{
    student s;
public:
    void write1()
    {
        ofstream fout;
        fout.open("stud.txt",ios::app);
        cout<<"enter the roll no.: ";
        cin>>s.roll;
        cout<<"enter the marks in english: ";
        cin>>s.e;
        cout<<"enter the marks in maths: ";
        cin>>s.m;
        s.tot=s.e+s.m;
        fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"/t"<<s.tot<<endl; //text mode
        //fout.write((char*)&s,sizeof(s));//binary mode
        fout.close();  //file closed
    }

    void read1()
    {
        ifstream fin;
        fin.open("stud.txt");
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;//text mode
            //fin.read((char*)&s,sizeof(s));//binary mode
            if(fin.eof())
                break;
            else

                cout<<"\nRoll no="<<s.roll;
                cout<<"\nEng="<<s.e;
                cout<<"\nmath="<<s.m;
                cout<<"\ntotal="<<s.tot;
                cout<<"\n--------------\n";

        }
        fin.close();
    }

    void search()
    {
        int wantedroll,found=0;

        cout<<"enter the roll no to search: ";
        cin>>wantedroll; //2
        ifstream fin;
        fin.open("stud.txt");
           while(1)
           {
               fin>>s.roll>>s.e>>s.m>>s.tot;//reading from file
               //fin.read((char*)&s,sizeof(s));
               if(fin.eof())
                break;
               else
                if(s.roll=wantedroll)
               {
                   found=1;
                   cout<<"\nroll  no: "<<s.roll<<"eng: "<<s.e<<"math: "<<s.m<<"total: "<<s.tot;
                   break;
               }  //if closed
           }//while loop closed
           if(found==0)
            cout<<"\n\n\n\tRecord not found.......";
           fin.close();
    }

    void delete1()
    {
        int wantedroll;
        cout<<"enter the roll no to delete: ";
        cin>>wantedroll; //2

        ifstream fin;
        fin.open("stud.txt");

        ofstream fout;
        fout.open("temp.text");

        int jasus=0;
        while(1)
        {
            fin>>s.roll>>s.e>>s.m>>s.tot;
            //fin.read((char*)&s,sizeof(s));
            if(fin.eof())
                break;

            if(s.roll!=wantedroll) //2!=2
            {
                fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
                //fout.write((char*)&s,sizeof(s));
            }
            else
            {
                jasus=1;
            }
        }//loop closed
        fin.close();
        fout.close();
        if(jasus==0)
            cout<<"\n\n\n\n\n\tnNot found";
        remove("stud.txt");
        rename("temp.txt","stud.txt");

    }

    void modify()
    {
        int wantedroll;
        cout<<"enter the roll no to search: ";
        cin>>wantedroll; //103

        istream fin;
        fin.open("stud.txt");
        int jasus=0;
            while(1)
            {
                fin>>s.roll>>s.e>>s.m>>s.tot;
                //fin.read((char*)&s,sizeof(s));
                if(fin.eof())
                    break;
                if(s.roll!=wantedroll)
                {
                    //fout.write((char*)&s,sizeof(s));
                    fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
                }
                else
                {
                    cout<<"\nenter new data\n";
                    cout<<"enter the marks in english: ";
                    cin>>s.e;
                    cout<<"enter the marks of maths: ";
                    cin>>s.m;
                    s.tot=s.e+s.m;
                    fout<<s.roll<<"\t"<<s.e<<"\t"<<s.m<<"\t"<<s.tot<<endl;
                    cout<<"\n\t\t Record modified: ";
                    jasus=1;
                }
            }//loop closed

            fin.close();
            fout.close();

            if(jasus==0)
                cout<<"\n\n\n\n\n\t\tNot found";

            remove("stud.txt");
            rename("temp.txt","stud.txt");
    }
};//class closed

int main()
{
    char choice;
    college c;

    do{
        cout<<"\n\n--------students record keeper--------\n\n";
        cout<<"\nPress W to write";
        cout<<"\nPress R to read";
        cout<<"\nPress S to search";
        cout<<"\nPress D to delete";
        cout<<"\nPress M to modify";
        cout<<"\nX to exit\n";
        cout<<"\Enter your choice:";
        choice=getchar();//W
        switch(choice)
        {

            case 'W':
            C.Write1();
            break;
            case 'r':
            C.read1();
            break;
            case 's':
            C.search();
            break;
            case 'd':
            C.delete();
            break;
            case 'm':
            C.modify();
            break;

        }
        fflush(stdin);//to clear the memory buffer
    } while(choice!='X');

    cout<<"\n\tSokhey......";

}
