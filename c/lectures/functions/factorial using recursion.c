int main()
{
    int factorial(int n);
    int n,fact;
    printf("enter the n:");
    scanf("%d",&n);
    fact=factorial(n);
    printf("\n factorial is =%d",fact);
}
int factorial(int n)
{
    int fact;
    if (n==1)
    return(1);
    else
        fact=factorial(n-1)*n;
    return(fact);
}
