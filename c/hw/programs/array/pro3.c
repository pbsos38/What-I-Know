int main()
{
    int i,n,sum=0;
    printf("enter no.: ");
    scanf("%d",&n);
    int a[n];
    for(i=0;i<n;i++)
    {
        printf("enter value at a[%d]:",i);
        scanf("%d",&a[i]);
    }
    int b[n];
    for(i=0;i<n;i++)
    {
        printf("enter value of b[%d]:",i);
    scanf("%d",&b[i]);
    }
    printf("sum of 2 arrays is: \n");
    int c[i];
    for(i=0;i<n;i++)
    {
        c[i]=a[i]+b[i];
        printf("c[%d]:%d\n",i,c[i]);
    }
}

