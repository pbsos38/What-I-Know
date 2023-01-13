int main()
{
    int m,n,r,c;
    printf("enetr rows and coloumn:");
    scanf("%d%d",&m,&n);
    int m1[m][n],m2[m][n],m3[m][n];
    for(r=0;r<m;r++)
    {
        for(c=0;c<n;c++)
        {
            printf("enter the no. [%d][%d]:",r,c);
            scanf("%d",&m1[r][c]);
        }
    }
    for(r=0;r<m;r++)
    {
        printf("enter the no.[%d][%d]",r,c);
        scanf("%d",&m2[r][c]);
    }
    for(r=0;r<m;r++)
    {
        m3[r][c]=m1[r][c]+m2[r][c];
        printf("%d  ",m3[r][c]);
    }
}
