main()
{
    int p,s,pr,lo;
    printf("pp:\nsp:");
    scanf("%d %d",&p,&s);
    if(s>p)
    {
        pr=s-p;
    pr=pr*100/p;
    printf("profit:%d",pr);
    }
    else
    {
        lo=p-s;
        lo=lo*100/p;
        printf("loss:%d",lo);
    }
}
