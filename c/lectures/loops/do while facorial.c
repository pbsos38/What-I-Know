int main()
{
    int i,n,fact=1;
    printf("enetr no:");
    scanf("%d",&n);
    i=1;
    while(i<=n)
    {
        fact=fact*i;
        printf("fact is=%d\n",fact);
        i++;
    }
}
