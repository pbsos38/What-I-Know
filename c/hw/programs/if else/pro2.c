main()
{
    int r,ac,vc,h;
    printf("enter r:");
        scanf("%d",&r);
    if(r<10)
    {
        ac=3.14*r*r;
        printf("area of circle:%d",ac);
    }
        else
       {
           printf("enter h: ");
        scanf("%d",&h);
            vc=3.14*r*r*h;
            printf("volume of cylinder:%d",vc);
            }
}
