struct height
{
    int f,i;
};
int main()
{
    struct height c1,c2,c3;
    printf("enter the time of 1st height in feets and iches");
    scanf("%d %d",&c1.f,&c1.i);
    printf("enter time of 2nd height in feets and inchs");
    scanf("%d %d",&c2.f,&c2.i);
    int df=abs((c2.f*12+c2.i)-(c1.f*12+c1.i));
    c3.f=df/12;
    c3.i=df%12;
    printf("\n height diff.=%dfeets%dinches",c3.f,c3.i);

}
