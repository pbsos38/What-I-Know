int main()
{
    int ra,ca,rb,cb,r,c,k,sum=0;
    printf("enter order of matrix a=");
    scanf("%d%d",&ra,&ca);
    printf("enter order of matrix b=");
    scanf("%d%d",&rb,&cb);
    int a[ra][ca],b[rb][cb],ab[10][10];
    if(ca!=rb)
    {
        printf("invalid order");
    }
    else
    {
        for(r=0;r<ra;r++)
        {
            for(c=0;c<ca;c++)
            {
                printf("enter value at a[%d][%d]=",r,c);
                scanf("%d",&a[r][c]);
            }
        }

         for(r=0;r<rb;r++)
        {
            for(c=0;c<cb;c++)
            {
                printf("enter value at b[%d][%d]=",r,c);
                scanf("%d",&b[r][c]);
            }
        }
        for(r=0;r<ra;r++)
        {
            for(c=0;c<cb;c++)
            {
                sum=0;
                for(k=0;k<ca;k++)
                {
                    sum=sum+a[r][k]*b[k][c];
                    ab[r][c]=sum;
                }
            }
        }
        for(r=0;r<ra;r++)
        {
            for(c=0;c<cb;c++)
            {
                printf("%d\t",ab[r][c]);
            }
            printf("\n");
        }
    }
}
