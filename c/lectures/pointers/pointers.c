int main()
{
    int s=4,*h,**t;
    h=&s;
    t=&h;
    printf("\n=%d",t);
    printf("\n=*t %d",*t);
    **t=9;
    printf("/n value is=%d ",s);
}
