main()
{
    int n,i,fact=1;
    printf("enter n:");
    scanf("%d",&n);
    for(i=1;i<=n;i++)
    {
    fact=fact*i;
    }
        printf("factorial=%d",fact);
}