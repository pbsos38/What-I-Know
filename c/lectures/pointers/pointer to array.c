int main()
{
    int n,i;
    printf("enter the no.");
    scanf("%d",&n);
    int a[n],*p;
    for(i=0;i<n;i++)
    {
        printf("enter the values:",i);
        scanf("%d",&a[i]);
    }
    p=&a[0];
    printf("\n output\n");
    for(i=0;i<n;i++)
    {
        printf("%d",a[i]);//or printf("%d",*(p+i));
    }
    *(p+i)=*((p+i)+10);//modification
    printf("\n the value is:");
    for(i=0;i<n;i++)
    printf("%d\n",a[i]);

}
