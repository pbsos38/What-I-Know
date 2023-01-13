int main()
{
    int s=4,*h,**t;
    h=&s;
    t=&h;
    printf("\nt=%d",t);//304
    printf("\n*t=%d",*t);//102
    printf("\n**t=%d",s);//4
    **t=9;//modify
    printf("\n*h=%d",*h);//9
}
