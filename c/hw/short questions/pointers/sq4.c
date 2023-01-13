int main()
{
    int x=2,y=8,z,*p1=&x,*p2=&y;
    z=*p1+*&*p2;
    printf("%d",*&z);
}
