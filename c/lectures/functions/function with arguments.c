int main()
{
    void rect(int l, int b);
    int ln;int b;
    printf("enter the l&b:");
    scanf("%d%d",&ln,&b);
    rect(ln,b);//actual agrument
}
void rect (int l, int b)
{
    int a=l*b;
    printf("area=%d",a);
}
