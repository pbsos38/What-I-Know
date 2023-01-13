int main()
{
    void swap(int *a, int *b);
    int x,y;
    printf("enter x&y");
    scanf("%d%d",&x,&y);
    swap(&x,&y);
    printf("now x=%d y=%d",x,y);
}
void swap(int *a, int*b)
{
    int temp=*a;
    *a=*b;
    *b=temp;
}
