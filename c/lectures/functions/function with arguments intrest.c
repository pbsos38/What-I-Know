int main()
{
    void intrest(int p, int r, int t);
    int p; int r; int t;
    printf("enter the p,r,t:");
    scanf("%d%d%d",&p,&r,&t);
    intrest(p,r,t);//actual agrument
}
void intrest (int p, int r, int t)
{
    int i=(p*r*t)/100;
    printf("intrest is=%d",i);
}

