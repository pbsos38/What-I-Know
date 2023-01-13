int main()
{
    int n,rev=0,r,sum=0;
    printf("enter no:");
    scanf("%d",&n);
    while(n!=0)
    {
        r=n%10;
        rev=rev*10+r;
        n=n/10;
        sum=sum+r;
    }
    printf("\n reverse no is=%d",rev);
    printf("\n sum=%d",sum);

}
