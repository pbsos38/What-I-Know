int main()
{
    int sum=0,i,n;
    printf("enetr no of employes:");
    scanf("%d",&n);

    int s[n];
    for(i=0;i<=n;i++)
    {
        printf("enter salary:",i);
        scanf("%d",&s[i]);
    }
    printf("salary is s[i]:\n");
    for(i=0;i<=n;i++)
    {
        printf("%d;lac\n",s[i]);
        sum=sum+i;
    }
    printf("total sol=%d",sum);
}
