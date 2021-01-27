







void display(struct AirLine *info)//打印
{
	printf("%8s\t%3s\t%s\t\t%4s\t%3d\t%10d\n",info->price,info->bnum,info->surplus);
}








void List()//列出全部航班信息
{
	struct AirLine *info;
	int i=0;
	info=start;
	printf("终点站\t航班号\t飞行时间\t票价\t乘员定额\t余票量\n");
	while(i<MAXSIZE)
	{
		display(info);
		info++;
		i++;
	}
	printf("\n\n");
}









void Search()//按站名查询
{
	struct AirLine *info,*find();
	char name[10];//you want
	int i=0;
	info=start;
	printf("请输入你想去的城市：");
	scanf("%s",name);
	while(i<MAXSIZE)
	{
		if(!stcmp(name,info->arrivecity))
		{

			break;
		}
		info++;
		i++;
	}
	if(i>=MAXSIZE)
	{
		printf("抱歉，暂未提供到该城市的航线\n");
	}
	else
	{
	printf("终点站\t航班号\t飞行时间\t票价\t乘员定额\t余票量\n");
	display(info);
	}
}





void CustomerLink()//显示客户信息
{
	linklist *p;
	struct AirLine *info;
	info=find();
	p=info->order;
	if(p!=NULL)
	{
		printf("客户姓名 订票数额 舱位等级\n");
		while(p)
		{
			printf("%s\t\t%d\t%d\n",p->name,p->booknum,p->grade);
			p=p->next;
		}
	}
	else
	{
		printf("该航班并没有客户信息");
}







void Book()//订票
{
	struct AirLine *info;
	int	amount,grade;
	char name[10];
	info=start;
	if(!(info=find()))
	{
		return;
	}
	printf("请输入需要的票数");
	scanf("%d",&amount);
	if(amount<=info->surplus)//订票成功
	{	
		info->book=InsertLink(info->book,amount,name,grade);
		info->surplus-=amount;
		printf("旅途愉快");
	}
	else
	{
		char r;
		printf("没有足够的票数，是否申请排队等候Y/N");
		r=getch();
		printf("%c",r);
		if(r=='Y'||r=='y')
		{
			printf("请输入您的姓名");
			scanf("%s",name);
			info->wait=InsertQueue(info->wait,name,amount);
			printf("申请成功");
		}
		else
		{
			printf("\n欢迎再次使用本系统\n");
		
		}
	}
}


void Refund()
{
	struct AirLine *info;
	qnode *t,*back,*f,*r;
	int grade;
	lionklist *p1,*p2,*head;
	cahr cusname[10];
	if(!(info=find()))
	{
		return;
	}
	head=info->Book;
	p1=head;
	printf("请输入姓名");
	scanf("%s",cusname);
	while(p1!=NULL)
	{
		if(!strcmp(cusname,p1->name))
		{
			break;
		}
		p2=p1;
		p1=p1->next;
	}
	if(p1==NULL)
	{
		printf("抱歉！您并没有定过票\n");
		return;
	}
	else
	{
		if(p1==head)
		{
			head=p1->next;
		}
		else
		{
			p2->next=p1->next;
		}
		info->surplus+=p1->bnum;
		grade=p1->grade;
		printf("%s退票成功\n",p1->name);
		free(p1);
	}
	info->Book=head;
	f=(info->wait).front;
	r=(info->wait).rear;
	t=f;
	while(t)
	{
		if(info->surplus>=info->wait.front->booknum)
		{
			int i;
			info->wait.front=t->next;
			printf("%s订票成功\n",t->name);
		}
	}
}













