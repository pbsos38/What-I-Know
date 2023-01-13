int main()
    {
        int i,j,k;
        for(i=1;i<=4;i++)
        {
            for(j=3;j>=i;j--)
            {
                printf("\t");  //or printf(" ");
            }
            for(k=1;k<=i;k++)
            {
                printf("*\t\t");  // or printf("*  ");
            }
            printf("\n");
        }

    }
