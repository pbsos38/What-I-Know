int main()
{
       void rep(int n,int rev,int r,int sum);
       int n,rev=0, r, sum=0;
    printf("enter no:");
    scanf("%d",&n);
    rep(n,rev=0,r,sum=0);
}
void rep(int n, int rev,int r,int sum)
{
    while(n!=0)
    {
        int r=n%10;
        int rev=rev*10+r;
        int n=n/10;
        int sum=sum+r;
    }
    printf("\n reverse no is=%d",rev);
    printf("\n sum=%d",sum);
}
