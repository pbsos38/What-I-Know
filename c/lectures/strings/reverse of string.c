int main()
{
    char s[100];
    int i,count=0;
    printf("enter the string:");
    gets(s);
    for(i=0;s[i]!='\0';i++)
    {
        count++;
    }
    printf("\nlength=%d\n",count);
    for(i=count-1;i>=0;i--)
    {
        printf("%c",s[i]);
    }
}
