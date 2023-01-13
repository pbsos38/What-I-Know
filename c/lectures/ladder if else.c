main()
{
    int unit,bill,gst,gross;
    printf("enter unit:");
        scanf("%d",&unit);
        if(unit<=500)
            bill=unit*5;
        else
            if(unit<=600)
            bill=unit*6;
        else
            if(unit<=700)
            bill=unit*7;
        gst=bill*18/100;
        gross=gst+bill;
        printf("gross bill=%d",gross);
}
