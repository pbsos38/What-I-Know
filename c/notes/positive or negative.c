int main()
{
    int n;
    printf("enter no:");
    scanf("%d",&n);

    if (n<0)
        printf("no is -ve");
    else
        if (n>0)
        printf("+ve");
    else
        printf("zero");
}
