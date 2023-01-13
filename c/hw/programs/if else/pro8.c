main()
{
    int p,s,pr,lo;
    printf("pp:\nsp:");
    scanf("%d %d",&p,&s);
        if(s>p)
    {
        pr=s-p;
        printf("profit amount=%d\n",pr);
        pr=pr*100/p;
        printf("profit:%d",pr);
    }
    else
        if(p>s)
    {
        lo=p-s;
        printf("loss amount=%d\n",lo);
        lo=lo*100/p;
        printf("loss:%d",lo);
    }
     else
        if(s=p)
        printf("no profit");
}
