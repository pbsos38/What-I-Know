int main()
{
    int C,D,temp;
    printf("enter C and d :");
    scanf("%d %d",&C,&D);

    temp=C;
    C=D;
    D=temp;

    printf("C=%d \n D=%d",C,D);
}
