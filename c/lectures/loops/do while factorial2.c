int main()
{
    int i,fact=1,n;
    printf("enter no");
    scanf("%d",&n);
    i=1;
    do
    {
        fact=fact*i;
        printf("fact is=%d\n",fact);
        i++;
    }
    while(i<=n);
}
