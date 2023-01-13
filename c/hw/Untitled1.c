main()
{
    int m;
    printf("enter marks:");
    scanf("%d",&m);
    if(m<=80 && m>=40)
        if(m>=70)
        printf("grade:A");
    else
        if(m>=60)
        printf("grade:B");
    else
        if(m>=50)
        printf("grade:C");
    else
        if(m>=40)
        printf("grade:D");
    else
    if(m<40)
        printf("fail");
}
