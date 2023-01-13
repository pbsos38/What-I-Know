int main()
{
    int a=5;
    int *p;//declaration of pointer variables
    p=&a;
    printf("\na=%d",&a);//100
    printf("\np=%d",p);//100
    printf("\n*p=%d",*p);//5
    *p=7;//modification
    printf("\nnow a=%d",a);//7
}
