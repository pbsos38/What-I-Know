int main()
{
    int i,v=0;
    char name[20];
    printf("enter name");
    gets(name);
    for(i=0;name[i]!='\0';i++)
    {
        printf("%c",name[i]);
        switch (name[i])
        {
            case'a':case'e':case'i':case'o':case'u':
            v++;
        }
    }
    printf("\n values=%d length=%d",v,i);

}
