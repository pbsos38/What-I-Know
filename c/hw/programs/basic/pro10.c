int main()
{
    int n,r1,r2,r3,r4,s;
    printf("enter any 5digit no.:");
    scanf("%d",&n);
    r1=n%10;
    n=n/10;
    r2=n%10;
    n=n/10;
    r3=n%10;
    n=n/10;
    r4=n%10;
    n=n/10;
    s=r1*10000+r2*1000+r3*100+r4*10+n;
    printf("rev=%i",s);
}
