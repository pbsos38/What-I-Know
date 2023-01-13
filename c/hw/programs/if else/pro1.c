main()
{
    int a,b,temp;
    printf("enter a:");
    scanf("%d",&a);
    printf("enter b:");
    scanf("%d",&b);
    if(a!=b)
        {
            temp=a;
        a=b;
        b=temp;
        printf("a=%d\nb=%d",a,b);
        }
        else
            printf("same");
}
