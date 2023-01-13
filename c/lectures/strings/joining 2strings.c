int main()
{
    char s1[50],s2[50],i,j;
    printf("enter the string:");
    scanf("%s",s1);
    printf("enter strings:");
    scanf("%s",s2);
    for(i=0;s1[i]!='\0';i++);
        for(j=0;s2[j]!='\0';j++,i++)
    {
        s1[i]=s2[j];

    }
    s1[i]='\0';
    printf("\n the string is=%s",s1);
}
