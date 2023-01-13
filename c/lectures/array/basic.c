int main()
{
    int sum=0,i,n,max;
    printf("enter no. of employees: ");
    scanf("%d",&n);

    int s[n];
    for(i=0;i<n;i++)
    {
        printf("enter salary :",i);
        scanf("%d",&s[i]);
    }
    printf("salary s[i]:\n");
    for(i=0;i<n;i++)
    {
        printf("%d lac\n",s[i]);
        sum=sum+s[i];
    }
    {
    printf("total sum=%d lac\n",sum);
    }
    max=s[0];
    for(i=0;i<n;i++)
    {
        if(max<s[i]) // min>s[i]
        {
            max=s[i];

        }
    }
    printf("max=s[%d]\n",i);
}
