int main()
{
    int a[2][2]={1,2,3,4},b[2][2]={{1,2},{3,4}},r,c;
    for(r=0;r<2;r++)
    {
        for(c=0;c<2;c++)
            {
                a[r][c]=a[r][c]*b[r][c];
                printf("%d",a[r][c]);
            }
            printf("\n");
    }
}
