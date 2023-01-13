#include <iostream>
using namespace std;
void area(int *l, int *a,int *b)
    {
        *a=(*l)*(*b);
    }

int main()
{
   int *l,*b,*a;

   cout << "Area:\n";

   cout <<"Enter the length: ";
   cin>>*l;
   cout <<"Enter the breadth: ";
   cin>>*b;
   area(&l,&b,&a);

   cout <<"\nArea:" ;
   cout<<area;
}
