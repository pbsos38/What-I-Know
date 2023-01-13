main()
{                        //   do without loop
    int n,i,t,a;
    printf("enter products:");
    scanf("%d",&n);
    for(i=1;i<=n;i++)
    printf("enter price of %d product:\n",i);
    scanf("%d%d%d",&i,&i,&i);
    t=i+i+i;
    printf("%d",t);
}
