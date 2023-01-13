int main()
{
    int a[3]={5,3,6},b[3]={5,3,1},c[3],i;
    for(i=2;i>=0;i--)
    {
        c[2-i]=a[i]*b[i];
        printf("%d\n",c[2-i]);
    }
}
