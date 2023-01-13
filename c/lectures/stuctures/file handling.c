int main()
{
    char name[30];
    printf("enter file name:");
   // scanf("%s",&name);
    gets(name);
    struct FIlE *PF;
    PF=fopen(name,"w");
    fprintf(PF,"BCE");
    fprintf(PF,"\nSST");
    printf("bce\nSST");
    fclose(PF);
}
