struct rect
{
    int l,b,a;
};
int main()
{
    struct rect r,*p;//declarationof pointer to structure
    p=&r;
    printf("enter l&b");
    scanf("%d %d",&r.l,&r.b);
    (*p).a=(*p).l*(*p).b;//or p->a=p->l*p->b;
    printf("area is=%d",(*p).a);
}
