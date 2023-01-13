struct complex// a user defined data type
{
    int r,i;
};
int main()
{
    struct complex c1,c2,c3;
    printf("enter 1st Real & complex number:");
    scanf("%d %d",&c1.r,&c1.i);
    printf("enter 2nd real & complex no:");
    scanf("%d %d",&c2.r,&c2.i);
    c3.r=c1.r+c2.r;
    c3.i=c1.i+c2.i;
    printf("sum=%d   %d",c3.r,c3.i);
}
