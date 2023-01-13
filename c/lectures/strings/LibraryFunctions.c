int main()
{
    char s2[10];
    char s1[10]="bce";
    int l=strln(s2);
    printf("length=%d\n",l);
    strln(s1);
    printf("\nlower=%s",s1);
    strupr(s1);
    printf("\nupper case=%s",s1);
    strcpy(s2,s1);
    printf("\ncopied=%s",s2);
    strrev(s2);
    printf("rev=%s",s2);
    int (rev)=strcmp(s1,s2);
    if(rev==0)
        printf("palindrome");
    else
        printf("not");
}
