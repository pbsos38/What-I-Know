int main()
{
    int no,a=0,b=0;
    char choice;
    printf("enter no(1/2/3):");
    scanf("%d",&no);
    switch(no)
    {
        case 1:
            printf("enter choice(*,/,-,+)");
            scanf("%d",&choice);
            switch(choice)
        {
            case'+':
                printf("enter a&b=");
                scanf("%d%d",&a,&b);
                printf("sum of a&b:%d",a+b);
                break;
        }
        {
            case'*':
                printf("enter a&b:");
                scanf("%d%d",&a,&b);
                printf("area of rectangle:%d",a*b);
                break;
        }
        {
            case'-':
                printf("enter a&b:");
                scanf("%d%d",&a,&b);
                printf("subtraction:%d",a-b);
                break;
        }
        {
            case'/':
                printf("enter a&b:");
                scanf("%d%d",&a,&b);
                printf("division:%d",a/b);
                break;
        }
            break;
            default:
                printf("wrong choice ");


        }
}
