int main()
{
    int r,c,a,b;
    r=c;
    printf("enter no.:");
    scanf("%d%d",&r,&c);
    int z1[r][c],z2[r][c],z3[r][c];
    for(a=0;a<=r;a++)
    {
        for(b=0;b<=c;b++)
        {
            printf("enter no.[%d][%d]:",a,b);
            scanf("%d",z1[a][b]);
        }
        for(a=0;a<r;a++)
        {
            printf("enter the no.[%d][%d]",a,b);
            scanf("%d",&z2[r][c]);
        }
        for(a=0;a<r;a++)
        {
            for(b=0;b<c;b++)
            {
                z3[a][b]=z1[a][b]+z2[a][b];
                printf("%d",z3[a][b]);
            }
        }
    }
}

