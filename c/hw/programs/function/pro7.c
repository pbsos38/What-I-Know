int main()
{
    void vc(int r,int h);
    int r,h;
    printf("enter the r&h:");
    scanf("%d%d",&r,&h);
    vc(r,h);
}
void vc(int r, int h)
{
    int volcone,volcyl;
    volcone=1*3.14*r*r*h/3;
    volcyl=3.14*r*r*h;
    printf("vol of cone=%d\nvol of cylinder=%d",volcone,volcyl);

}
