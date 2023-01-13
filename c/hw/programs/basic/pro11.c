main()
{
    int n,r1,r2,r3,r4,r5,s;
    printf("enter no.:");
    scanf("%d",&n);
    r1=n%10;
    n=n/10;
    r2=n%10;
    n=n/10;
    r3=n%10;
    n=n/10;
    r4=n%10;
    n=n/10;
    r5=n%10;
    n=n/10;
    s=r1+n;
    printf("sum=%i",s);
}
