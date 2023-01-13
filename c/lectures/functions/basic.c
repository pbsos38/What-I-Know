int main()
{
    void rect();//fun. declaration
    rect;//fun calling
    printf("back in main");//fun. defination
}
void rect()
{
    int l,b,a;
    printf("enter l and b:");
    scanf("%d %d",&l,&b);
    a=l*b;
    printf("area is=%d",a);
}

