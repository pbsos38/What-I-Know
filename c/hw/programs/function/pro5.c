Nint main()
{
    void palindrome();
        palindrome();
        printf("back in main");
}
void palindrome()
{
    int i,n,rev=0,sum=0,photo,r=0;
    printf("enter no:");
    scanf("%d",&n);
    for(i=11;i<=n;i++)
    {
        photo=i;
        rev=0;
        while(photo!=0)
        {
            r=photo%10;
            photo=photo/10;
            rev=rev*10+r;
            sum=sum+photo;
        }
        if(i==rev)
        {
            printf("\n palindrome no.=%d",i);
        }
    }
        }


