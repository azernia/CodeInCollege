#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <conio.h> 
#include <iostream>
#include <malloc.h>
using namespace std;
 
#define MAXSIZE 5 //定义航线数量

typedef struct WCM //等候替补的客户信息
{
	char name[10]; //姓名
	int book_amt; //订票量
	struct WCM *next;
}QNode,*QPtr;

typedef struct WCNL //等候替补的客户名单 
{
	QPtr front; 
	QPtr rear;  
}LinkQueue;

typedef struct MNL //乘员名单
{
	char name[10]; //客户姓名
	int book_amt; //订票量
	int grade; //舱位等级
	struct MNL *next;
}LinkList;

struct AirLine
{
	char arrivecity[10]; //抵达城市
	char air_num[10]; //航班号
	char time[10]; //飞机时间
	char price[7]; //票价
	int tkt_amt; //乘员定额
	int tkt_sur; //余票量
	LinkList *order; //乘员名单域，指向乘员名单链表的头指针
	LinkQueue wait; //指向排队等候名单队头队尾的指针
}LineInfo;

struct AirLine *start;

void Display(struct AirLine *info) //打印每条航线的基本信息
{
	printf("%8s\t%3s\t%s\t\t%4s\t%3d\t%10d\n",info->arrivecity,info->air_num,info->time,info->price,info->tkt_amt,info->tkt_sur);
}

void All_M() //打印全部航线信息
{
	struct AirLine *info;
	int i=0;
	info=start;
	printf("TERMINUS\tFLIGHTNUMBER\tTIME\tPRICE\tTOTAL\tSURPLUS\n");
	while(i<MAXSIZE)
	{
		Display(info); //调用display函数来输出
		info++;
		i++;
	}
	printf("\n\n");
}

void Search() //根据客户提出的终点站名输出航线信息 
{
	struct AirLine *info,*find();
	char name[10]; //要抵达的城市名称
	int i=0;
	info=start;
	printf("Please select the terminus name:");
	scanf("%s",name);
	while(i<MAXSIZE)
	{
		if(!strcmp(name,info->arrivecity)) 
		{
			break;
		}
		info++;
		i++;
	}
	if(i>=MAXSIZE) //没有匹配的航线
	{
		printf("Sorry,There is no this airline!\n");
	}
	else
	{
		printf("TERMINUS\tFLIGHT NUMBER\tTIME\tPRICE\tTOTAL\tSURPLUS\n");
		Display(info); //调用Display函数输出航线信息
	}
}

struct AirLine *find() //根据系统提出的航班号查询并以指针形式返回
{
	struct AirLine *info;
	char number[10]; //查询的航班号
	int i=0;
	info=start;
	printf("Please select the air number:");
	scanf("%s",number);
	while(i<MAXSIZE)
	{
		if(!strcmp(number,info->air_num)) //有匹配航班
		{
			return info;
		}
		info++;
		i++;
	} //无匹配航班
	printf("Sorry,There is no this airline!\n");
	return NULL;
}

void BCM() //打印订票的客户信息
{
	LinkList *p;
	struct AirLine *info;
	info=find();
	p=info->order;
	if(p!=NULL) //按顺序输出客户信息链表
	{
		printf("NAME BOOKINGNUM SEAT_GRADE\n");
		while(p)
		{
			printf("%s\t\t%d\t%d\n",p->name,p->book_amt,p->grade);
			p=p->next;
		}
	}
	else //没有人订这个航班
		printf("This airline have'nt the customer's massage!\n");
}

LinkList *InsertLink(LinkList *head,int amount,char name[],int grade) //增加订票乘员名单域的客户信息
{
	LinkList *p1,*p2;
	p1=head;
	p2=(LinkList*)malloc(sizeof(LinkList));
	if(!p2) //存储空间不足
	{
		printf("\nOut of memory!!\n");
		return NULL;
	}
	strcpy(p2->name,name);
	p2->book_amt=amount;
	p2->grade=grade;
	p2->next=NULL;
	if(head==NULL) //若原订票客户信息为空
	{
		head=p2;
		p2->next=NULL;
	}
	else
	{
		head=p2;
	}
	p2->next=p1;
	return head;
}

LinkQueue InsertQueue(LinkQueue q,char name[],int amount) //增加排队等候的客户名单域
{
	QPtr p;
	p=(QPtr)malloc(sizeof(QNode));
	strcpy(p->name,name);
	p->book_amt=amount;
	p->next=NULL;
	if(q.front==NULL) //若原排队等候客户名单域为空
	{
		q.front=p;
	}
	else
	{
		q.rear->next=p;
	}
	q.rear=p;
	return q;
}

void Booking() //办理订票业务
{
	struct AirLine *info;
	int amount,grade; //订票数量,舱位等级
	char name[10]; //要订的航班号
	info=start;
	if(!(info=find())) //根据客户提供的航班号进行查询，如为空，退出该模块
	{
		return;
	}
	printf("Plese select you need tickets:");
	scanf("%d",&amount);
	if(amount>info->tkt_amt) //若客户订票额超过乘员定票总额，退出
	{
		printf("\nSorry,your tickets over the total tickets!");
		return;
	}
	if(amount<=info->tkt_sur) //若客户订票额末超过余票量，订票成功并等记信息
	{
		int i;
		printf("Please Input Your Name:");
		scanf("%s",name);
		printf("Please Select You need grade(1,2or3):");
		scanf("%d",&grade);
		info->order=InsertLink(info->order,amount,name,grade); //在订票乘员名单域中添加客户信息
		for(i=0;i<amount;i++) //依次输出该订票客户的座位号
		{
			printf("%s's seat number is:%d\n",name,info->tkt_amt-info->tkt_sur+i+1);
		}
		info->tkt_sur-=amount; //该航线的余票量应减掉该客户的订票量
		printf("\nGood Luck!\n");
	}
	else //若满员或余票额少于订票额，询问客户是否需要进行排队等候
	{
		char r;
		printf("\nThere is no enough ticket，would you like to wait?(Y/N)");
		r=getch();
		printf("%c",r);
		if(r=='Y'||r=='y')
		{
			printf("\nPleas input your name:");
			scanf("%s",name);
			info->wait=InsertQueue(info->wait,name,amount); //在排队等候乘员名单域中添加客户信息
			printf("\nRegister Successful( > c < ) !\n");
		}
		else
		{
			printf("\nLooking forward to your next booking\n");
		}
	}
}

void Refund() //退票模块
{
	struct AirLine *info;
	QNode *t,*back,*f,*r;
	int grade;
	LinkList *p1,*p2,*head;
	char cusname[10];
	if(!(info=find())) //调用查询函数，根据客户提供的航线进行搜索
	{
		return;
	}
	head=info->order;
	p1=head;
	printf("Please input your name:");
	scanf("%s",cusname);
	while(p1!=NULL) //根据客户提供的姓名到订票客户名单域进行查询
	{
		if(!strcmp(cusname,p1->name))
		{
			break;
		}
		p2=p1;
		p1=p1->next;
	}
	if(p1==NULL) //若未找到，退出本模块
	{
		printf("Sorry,There is no your ticket(≧﹏ ≦)!\n");
		return;
	}
	else //若信息查询成功，删除订票客户名单域中的信息
	{
		if(p1==head)
		{
			head=p1->next;
		}
		else
		{
			p2->next=p1->next;
		}
		info->tkt_sur+=p1->book_amt;
		grade=p1->grade;
		printf("%sRefund Successful!\n",p1->name);
		free(p1);
	}
	info->order=head; //重新将航线名单域指向订票单链表的头指针
	f=(info->wait).front; //f指向排队等候名单队列的头结点
	r=(info->wait).rear; //r指向排队等候名单队列的尾结点
	t=f; //t为当前满点条件的排队候补名单域
	while(t)
	{
		if(info->tkt_sur>=info->wait.front->book_amt) //若满足条件者为头结点
		{
			int i;
			info->wait.front=t->next;
			printf("%sBooking Successful!\n",t->name);
			for(i=0;i<t->book_amt;i++) //输出座位号
			{
				printf("%s's Seat Number is:%d\n",t->name,(info->tkt_sur)-i);
			}
			info->tkt_sur=t->book_amt;
			info->order=InsertLink(info->order,t->book_amt,t->name,grade);/*插入到订票客户名单链表中*/
			free(t);
			break;
		}
		back=t;
		t=t->next;
		if((info->tkt_sur)>=(t->book_amt)&&t!=NULL)//若满足条件者不为头结点
		{
			int i;
			back->next=t->next;
			printf("%sBooking Successful!\n",t->name);
			for(i=0;i<t->book_amt;i++) //输出座位号
			{
				printf("<%s>'s seat number is:%d\n",t->name,(info->tkt_sur)-i);
			}
			info->tkt_sur-=t->book_amt;
			info->order=InsertLink(info->order,t->book_amt,t->name,grade);//插入到订票客户名单链表中
			free(t);
			break;
		}
		if(f==r)
		{
			break;
		}
	}
}

int Menu() 
{
	int c;
	printf("\tBOOKING SYSTEM\n");
	printf("**************************\n");
	printf("1.Check Air Line Message\n");
	printf("2.Check Customer's Message\n");
	printf("3.Check Air Line\n");
	printf("4.Booking\n");
	printf("5.Refind\n");
	printf("6.Quit\n");
	printf("**************************\n");
	do
	{
		printf("Please Select(1-6):");
		scanf("%d",&c);
	}while(c<1||c>6);
	return c;
}

void main()
{
	struct AirLine air[MAXSIZE]= //初始化航线信息
	{
		{"beijing","1","1200","860",30,30},
		{"shanghai","2","1000","770",20,20},
		{"london","3","1330","960",10,10},
		{"harbin","4","1700","760",50,50},
		{"dalian","5","1400","550",40,40}
	};
	start=air;
	for(;;)
	{
		system("cls"); 
		switch(Menu())
		{
		case 1:All_M(); 
			break;
		case 2:BCM(); 
			break;
		case 3:Search(); 
			break;
		case 4:Booking(); 
			break;
		case 5:Refund(); 
			break;
		case 6:printf("\nThanks for your using,See you!～(￣▽￣～)(～￣▽￣)～\n"); 
			exit(0); 
		}
		printf("\nPLEASE TOUCH ANY KEY...\n");
		getch(); 
	}
}