int main()
{
    int m,h,s;
    printf("minutes:");
    scanf("%i",&m);
    h=m/60;
    s=m%60;
    printf("%ihours %iminutes",h,s);
}
