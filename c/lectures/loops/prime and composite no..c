int main()
{
    int i,n,x=0;
    printf("enter no:");
    scanf("%d",&n);
    for(i=1;i<=n;i++)
    {
        if(n%i==0)
            x=x+1;
    }
    if(x==2)
        printf("prime");
    else
        printf("composite");
}
