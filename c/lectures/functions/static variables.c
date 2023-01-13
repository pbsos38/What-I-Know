int main()
{
    void show()
    show();
    show();

}
void show()
{
    static int x=5;
    printf("x=%d",x)
    x=x+10;

}
