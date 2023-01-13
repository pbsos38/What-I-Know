int main()
{
    int rect(int l, int  b);
    int l,b;
    printf("enter the value");
    scanf("%d%d",&l,&b);
    int ar=rect(l,b);
    printf("area=%d",ar);

}
int rect(int l, int b)
{
    int a;
    a=l*b;
    return(a);
}
