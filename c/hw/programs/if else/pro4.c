main()
{
    int msg,call,billc,billm,gst,gross;
    printf("enter msg:");
        scanf("%d",&msg);
        printf("call:");
        scanf("%d",&call);
        if(msg<=50)
            billm=msg*.10;
        else
            if(msg>50)
            billm=msg*.25;
        else
            if(call<=200)
            billc=call*.75;
            else
                if(call>200)
                billc=call*.95;
            else
                if(billc<=1000)
                gst=(billc+billm)*12.5/100;
            else
                if(billc>1000)
                gst=(billc+billm)*22.5/100;
        gross=gst+billc+billm+100;
        printf("gross bill=%d",gross);
}
