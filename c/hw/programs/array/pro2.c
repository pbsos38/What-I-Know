int main()
{
    int i,n,count=0,c=0;
    printf("enter no. of students: ");
    scanf("%d",&n);
    int s[n];
    for(i=0;i<n;i++)
    {
        printf("enter marks:");
        scanf("%d",&s[i]);

    if(s[i]>80)
    {
       c++;
    }
    else
        if(s[i]>60 && s[i]<70)
    {
        count++;
    }
    }
    printf("students with marks >80:%d \n",c);
     printf("students with marks >=60: %d",count);

}
