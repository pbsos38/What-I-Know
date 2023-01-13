int main()
{
     int x=10,y=8;
     int const *p;
     p=&x;            //1
     *p;              //2
     p=&y;            //3
}
