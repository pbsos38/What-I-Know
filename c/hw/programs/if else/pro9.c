main()
{
    int l,L;
    printf("enter no of days:");
    scanf("%d",&l);
    L=l%366;
    if(L==0)
        printf("leap year");
    else
        printf("non leap year");
}
