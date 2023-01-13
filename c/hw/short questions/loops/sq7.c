int main()
{
    int i,v,f;
    for(v=2;v<=3;v++)
    {
        f=1;
        for(i=1;i<=v;i++)
            f=f*i;
        printf("%d : %d\n",v,f);
        }
}
