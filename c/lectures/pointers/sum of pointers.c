int main()
{
    int a,b,*pa,*pb,sum;
    printf("enetr a& b:");
    scanf("%d %d",&a,&b);
    pa=&a;
    pb=&b;
    sum=*pa+*pb;
    printf("sum=%d",sum);
}
