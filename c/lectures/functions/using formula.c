int main()
{
    void area();
    area();
    printf("back in main");
}
void area()
{
    int r,area;
    printf("enter radius:");
    scanf("%d",&r);
    area=3.14*r*r;
    printf("area=%d\n",area);
}
