int main()
{
    int n,i,wanted,count=0;
    printf("enter no. of students: ");
    scanf("%d",&n);
    int m[n];
    for(i=0;i<n;i++)
    {
        printf("enter marks of %d students:",i);
        scanf("%d",&m[i]);
    }
    printf("enter marks to be searched:");
    scanf("%d",&wanted);
    for(i=0;i<n;i++)
    {
        if(wanted==m[i])
        {
            printf("found at %d location:",i);
            count++;
        }
    }
    if(count==0)
        printf("not found");
    else printf("found %d times",count);
}
