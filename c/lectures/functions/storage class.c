int main()
{
    auto int x;//local variables
    void show ()
    printf("%d",x);
    x=10;
    show();
}
void show()
{
    printf("%d",x);//error (not accesible)

}
