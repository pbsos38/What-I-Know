int main()
{
    void rep(int a,int b,int c);
    int a,b,c;
    printf("enter a,b,c:");
    scanf("%d%d%d",&a,&b,&c);
    rep(a,b,c);
}
void rep(int a,int b,int c)
{
    if(a>b)
    {
        if(b>c)
            printf("a");
            else
                printf("c");
    }
    else
    {
        if(b>c)
            printf("b");
        else
            printf("c");
    }
}

