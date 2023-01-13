int main()
{
    void grace(int *p,int n);
    int n,i;
    printf("enter n:");
    scanf("%d",&n);
    int marks[n];
    for(i=0;i<n;i++)
    {
        printf("enter marks:",i);
        scanf("%d",&marks[i]);
    }
    grace(&marks[0],n);
    printf("\nafter grace:\n");
    for(i=0;i<n;i++)
    {
        printf("%d\n",marks[i]);
    }
}
void grace(int *p,int n)
{
    int i;
    for(i=0;i<n;i++)
    {
        printf("%d\n",*(p+i));



    if(*(p+i)<50)
    {
      *(p+i)=50;
    }
    }

}
