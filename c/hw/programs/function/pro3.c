int main()
{
    void vol();
    vol();
    printf("back in main");
}
void vol()
{
    int r,h,vol;
    printf("enter r&h:");
    scanf("%d%d",&r,&h);
    vol=3.14*r*r*h;
    printf("vol of cylinder:%d  ",vol);

}
