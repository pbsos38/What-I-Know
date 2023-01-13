int main()
{
    int x3=9,x4=6;
    const int *const p1=&x3;
    p1=&x4;;               //1
    *p1=88                  //2
}
