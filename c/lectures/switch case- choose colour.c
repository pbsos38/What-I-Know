int main()
{
    char choice;
    printf("cnter choice:");
    scanf("%c",&choice);
    switch(choice)
    {
    case 'a':
        printf("red");
        break;
        case 'b':
        printf("blue");
        break;
        case 'c':
        printf("green");
        break;
        default:
            printf("wrong choice");
    }
}
