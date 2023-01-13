main()
{
    int a,b,c,d;
    printf("enter a,b,c,d");
    scanf("%d%d%d%d",&a,&b,&c,&d);
    {(a>b)&&(a>c)&&(a>d)?printf("a"):printf("d");}
    {(b>c)&&(b>d)?printf("b"):printf("d");}
    {(c>d)?printf("c"):printf("d");}
}

