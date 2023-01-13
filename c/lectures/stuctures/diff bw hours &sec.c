struct clock
{
    int h,m;
};
int main()
{
    struct clock c1,c2,c3;
    printf("enter the time of 1st clock in hours and min.");
    scanf("%d %d",&c1.h,&c1.m);
    printf("enter time of 2nd clock in hours and min.");
    scanf("%d %d",&c2.h,&c2.m);
    int df=((c2.h*60+c2.m)-(c1.h*60+c1.m));
    c3.h=df/60;
    c3.m=df%60;
    printf("\n time diff.=%dhours %dminutes",c3.h,c3.m);

}
