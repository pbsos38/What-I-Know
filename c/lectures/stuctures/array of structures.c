struct student
{
    int rollno;
    float cpp,java,andriod,total,per;
};
    int main()
    {
        int n,i;
        printf("enter no. of students");
        scanf("%d",&n);
        struct student s[n];
        for(i=0;i<n;i++)
        {
            printf("enter roll no&marks in 3 subjects");
            scanf("%d%f%f%f",&s[i].rollno,&s[i].cpp,&s[i].java,&s[i].andriod);
            s[i].total=s[i].cpp+s[i].java+s[i].andriod;
            s[i].per=s[i].total*100/300;
        }
        printf("\n students marks:\n");
        float max=s[0].total;
        float per=s[0].per;
        for(i=0;i<n;i++)
        {
            printf("\n rollno=%d cpp=%f java=%f andriod=%f \n",s[i].rollno,s[i].cpp,s[i].java,s[i].andriod);
            if(max<s[i].total)
            {
                if(per>s[i].per)
                {
                    per=s[i].per;
                }
                max=s[i].total;
            }
        }
        printf("\n max=%f",max);
        printf("\n per=%f",per);
    }
