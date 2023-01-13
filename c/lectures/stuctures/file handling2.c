int main()
{
    char Rname[30];
    char Wname[30];
    struct FILE *RP, *WP;
    printf("enter the file name to read:");
    scanf("%s",&Rname);
    RP=fopen(Rname,"R");
    if(RP=='\0')
    {
        printf("file not found\n");
    }
    printf("enter file name to write=");
    scanf("%s",&Wname);
    WP=fopen(Wname,"w");
    while (!eof(RP))
    {
        char A=fgets(RP);
        fprintf(WP,A);
    }
    fclose(RP);
    fclose(WP);

}
