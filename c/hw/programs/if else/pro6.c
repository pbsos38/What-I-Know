main()
{
    int unit,bill,gst,gross;
    printf("enter unit:");
        scanf("%d",&unit);
        if(unit<=200)
            bill=unit*2;
        else
            if(unit<=400)
            bill=unit*3;
        else
            if(unit<=600)
            bill=unit*4;
            else
                if(unit>600)
                bill=unit*5;
        gst=bill*10/100;
        gross=gst+bill;
        printf("gross bill=%d",gross);
}
