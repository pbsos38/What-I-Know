int main()
{
    int n,r,c,temp;
    printf("enter no.");
    scanf("%d",&n);
    int a[n];
    for(r=0;r<n;r++)
    {
        printf("enter value a[%d]:",r);
        scanf("%d",&a[r]);
    }
    for(r=n-2;r>=0;r--)
    {
        for(c=0;c<=r;c++)
        {
            if(a[c]>a[c+1])
            {
                temp=a[c];
                a[c]=a[c+1];
                a[c+1]=temp;
            }
    }
}
printf("\n sorted array:\n");
for(r=0;r<=n;r++)
            {
            printf("%d\n",a[r]);
            }
    }
