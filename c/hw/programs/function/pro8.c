int main()
{
    void gr(int a,int b);
        int a,b;
        printf("enter value of a&b");
        scanf("%d%d",&a,&b);
        gr(a,b);
}
void gr(int a,int b)
{
    if(a>b)
        printf("a is greater");
    else
        printf("b is greater");
}
