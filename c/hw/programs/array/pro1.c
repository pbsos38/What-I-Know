int main()
{
    int sum=0,i,n;

    printf("enter no:");
    scanf("%d",&n);
    int s[n];
    for(i=0;i<n;i++)
    {
        printf("enter price:",i);
        scanf("%d",&s[i]);
    }
    for(i=0;i<n;i++)
    {
        sum=sum+s[i];
        printf("sum=%d\n",sum);
    }
}
