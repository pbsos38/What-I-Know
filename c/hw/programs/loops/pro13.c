int main()
{
    int i,n,fact=1;
    printf("enter n:");
    scanf("%d",&n);
    i=1;
    while(i<=n)
    {
        fact=fact*i;
    printf("\n%d",fact);
    i++;
    }
}
