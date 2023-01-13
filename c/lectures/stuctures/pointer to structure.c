struct rect
{
    int l,b,a;

};
int main()
{
    struct rect r;
    struct rect*p;// dec. of pointer to structure
    p=&r;
    printf("enter l&b:");
    scanf("%d %d",&r.l,&r.b);
    (*p).a=(*p).l*(*p).b;//or   p->=p->l*p->b;
    printf("area is=%d",p->a);
}
