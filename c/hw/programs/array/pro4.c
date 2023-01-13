int main()
{
    int days[13]={0,31,28,31,30,31,30,31,31,30,31,30,31};
    int sm,em,total=0,i;
    printf("enter start and end month:");
    scanf("%d %d",&sm,&em);
    for(i=sm;i<em;i++)
    {
        printf("%d\t",days[i]);
        total=total+days[i];
    }
    printf("\ntotal days=%d",total);
}
