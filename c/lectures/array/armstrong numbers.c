int main()
{
    int r,n1,sum=0,original=0;
    printf("enter no.");
    scanf("%d",&n1);
    original=n1;
    while(r!=0)
    {
        r=n1%10;
        n1=n1/10;
        sum=sum+r*r*r;
    }
    if(original==sum)
        printf("armstrong");
    else
        printf(" non armstrong no.");

}
