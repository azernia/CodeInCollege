#include <iostream.h>
#include <malloc.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>

typedef struct FNode
{
	char flight_num[10]; //航班号
	char start_time[10]; //起飞时间
	char end_time[10];   //抵达时间
	char start_place[20];//起飞城市
	char end_place[20];  //抵达城市
	int left;            //空座数
	float price;         //票价
	int isFull;          //航班是否满仓
	struct FNode *next;//指向下一个结点
}FNode;//航班结点

typedef struct PNode
{
	char name[20];      //姓名
	char ID_num[20];    //证件号
	char flight_num[10];//航班号
	int order_num;      //订单号
	int ticket_num;     //订票数量
	struct PNode *next;//指向下一个结点
}PNode;//客户结点

typedef struct PList
{
	PNode *head;
	PNode *rear;
}PList;

void Init_Flight(FNode *&h)//建立一个以h为头结点的空链表,录入航班信息和增加航班后将航班结点插入该链表
{
	h=(FNode *)malloc(sizeof(FNode));//申请头结点的空间
	if(h==NULL) exit(0);
	h->next=NULL;//将头结点h的指针域置为空
}

void Init_Passenger(PList *&pList)//建立一个以pList为头结点的空链表,客户订票后将客户结点插入该链表
{
	pList=(PList *)malloc(sizeof(PList));
	pList->head=(PNode *)malloc(sizeof(PNode));//创建头结点
	pList->rear=pList->head;
	pList->rear->order_num=0;//订单号从0开始计数
	pList->head->next=NULL;
}

void Save_Passenger(PList *PList)
{
	FILE* fp = fopen("passenger.dat","wb");
	if(fp==NULL)
	{
		return;
	}
	PNode *temp=PList->head->next;
	while(temp!=NULL)
	{
		fwrite(temp,sizeof(PNode),1,fp);
		temp = temp->next;
	};
	fclose(fp);
}

void Load_Passenger(PList *PList)
{
	FILE* fp = fopen("passenger.dat","rb");
	if(fp==NULL)
	{
		return;
	}
	PNode *q;
	int n;
	while(!feof(fp))
	{
		q=(PNode *)malloc(sizeof(PNode));
		n =fread(q,sizeof(PNode),1,fp);
		if(n!=1)
		{
			break;
		}
		PList->rear->next=q;
		PList->rear=q;
	}
	PList->rear->next=NULL;
	fclose(fp);
}

void Save_Flight(FNode *h)
{
	FILE* fp = fopen("flightList.dat","wb");
	if(fp==NULL)
	{
		return;
	}
	FNode *temp=h->next;
	while(temp!=NULL)
	{
		fwrite(temp,sizeof(FNode),1,fp);
		temp = temp->next;
	};
	fclose(fp);
}

void Load_Flight(FNode *&h)
{
	FNode *pt=h;
	FILE* fp = fopen("flightList.dat","rb");
	if(fp==NULL)
	{
		return;
	}
	FNode *q;
	int n;
	while(!feof(fp))
	{
		q=(FNode *)malloc(sizeof(FNode));
		n=fread(q,sizeof(FNode),1,fp);
		if(n!=1)
		{
			break;
		}
		pt->next=q;
		pt=q;
	}
	pt->next=NULL;
	fclose(fp);
}

int find_same_flight(FNode *h,char *flight_num)
{
	FNode *t=h->next,*p=h->next;
	int mark=0;
	printf("%-8s%-12s%-12s%-10s%-10s%-8s%-10s%-8s\n","航班号","起飞城市","抵达城市","起飞时间","抵达时间","价格","是否满仓","空座数");
	while(t!=NULL && strcmp(t->flight_num,flight_num)!=0) t=t->next;
	while(p!=NULL){
		if((strcmp(t->start_place,p->start_place)==0)&&(strcmp(t->end_place,p->end_place)== 0)&&(strcmp(t->flight_num,p->flight_num)!=0))
		{
			printf("%-8s%-12s%-12s%-10s%-10s%-8.2f%-10d%-8d\n",p->flight_num, p->start_place,p->end_place,p->start_time,p->end_time,p->isFull,p->left);
			mark=1;
		}
		p=p->next;
	}
	if(mark==0)
	{
		printf("\t\t抱歉，没有可选的航班!\n");
		return 0;
	}
	return 1;
}

void Insert_Flight(FNode *&h,char* flight_num,char* start_place,char* end_place,char* start_time,char* end_time,int left,float price,int isFull)
//在录入航班情况或增加新的航班后,将新的航班结点插入到航线链表中
{
	FNode *q;//定义q为新增加的航班结点的指针的形参
	FNode *p=h;
	q=(FNode *)malloc(sizeof(FNode));
	strcpy(q->flight_num,flight_num);
	strcpy(q->start_place,start_place);
	strcpy(q->end_place,end_place);
	strcpy(q->start_time,start_time);
	strcpy(q->end_time,end_time);
	q->left=left;
	q->price=price;
	q->isFull=isFull;	
	q->next=p->next;//将q结点的指针域置为空
	p->next=q;
	p=p->next;//将指针后移
}

void Insert_Passenger(FNode *&h,PList *&PList,char *name,char *ID_num,char *flight_num,int ticket_num)
//客户订票后，将客户结点插入客户链表中，并修改相应的航班信息。
{
	FNode *p=h->next;
	for(;p!=NULL;p=p->next)
		if(strcmp(p->flight_num,flight_num)==0)
		{
			p->left=p->left-ticket_num;
			if(p->left==0)
			{
				p->isFull=1;
			}
		}
	PNode *q=(PNode *)malloc(sizeof(PNode));//新建结点，存放新的客户订单信息
	strcpy(q->name,name);
	strcpy(q->ID_num,ID_num);
	strcpy(q->flight_num,flight_num);
	q->ticket_num=ticket_num;
	q->next=NULL;
	//将新订单连接到链表中
	PList->rear->next=q;
	q->order_num=PList->rear->order_num+1;//生成订单号
	PList->rear=q;
}

int Delete_Passenger(PList *&PList,FNode *&h,char *name,char *ID_num)
//目的是按照要退票的顾客的姓名查找该顾客结点,进行删除操作
{
	PNode *p,*pr=PList->head;//pr指向顾客链表的头结点
	p=pr->next;
	while(p!=NULL)
	{
		if(strcmp(name,p->name)==0&&strcmp(ID_num,p->ID_num)==0)
		{
			for(FNode *f=h->next;f!=NULL;f=f->next)
				if(strcmp(p->flight_num,f->flight_num)==0)
				{
					f->left=f->left+p->ticket_num;
					f->isFull=0;
					break;
				}
			pr->next=p->next;
			free(p);
			printf("\t\t顾客%s,%s退票成功!\n",name,ID_num);
			return 1;
		}
		pr=pr->next;
		p=pr->next;
	}
	printf("\t\t无此顾客,无法退票!\n");
	return 0;
}

void Add_Flight(FNode *&h)//录入航班信息后调用insert_flight函数增加航班
{
	FNode *p=h;
	char flight_num[10],start_time[10],end_time[10],start_place[20],end_place[20];
	int left,isFull,mark=1;
	float price;
	for(;p->next!=NULL;p=p->next){}//移动指针,找到最后一个结点
	while(mark==1)
	{
		printf("\t\t请输入你要增加的航班号:");
		scanf("%s",flight_num);
		printf("\t\t请输入出发地:");
		scanf("%s",start_place);
		printf("\t\t请输入目的地:");
		scanf("%s",end_place);
		printf("\t\t请输入起飞时间:");
		scanf("%s",start_time);
		printf("\t\t请输入抵达时间:");
		scanf("%s",end_time);
		printf("\t\t请输入票价:");
		scanf("%f",&price);
		printf("\t\t请输入剩余座位数:");
		scanf("%d",&left);
		printf("\t\t请输入是否满仓(0表示没有满仓1以满仓):");
		scanf("%d",&isFull);
		Insert_Flight(p,flight_num,start_place,end_place,start_time,end_time,left,price, isFull);
		printf("\t\t增加航班%s成功!\n",flight_num);
		printf("\t\t是否继续录入航班信息(1表示继续录入;0表示停止录入).\n");
		printf("\t\t请输入:");
		scanf("%d",&mark);
	}
}

int Flight_Num_Check(FNode *h,char *flight_num)//用航班号进行查询
{
	FNode *p=h;
	printf("%-8s%-12s%-12s%-10s%-10s%-8s%-10s%-8s\n","航班号","起飞城市","抵达城市","起飞时间","抵达时间","价格","是否满仓","空座数");
	for(;p!=NULL;p=p->next)
	{
		if(strcmp(p->flight_num,flight_num)==0)
		{
			printf("%-8s%-12s%-12s%-10s%-10s%-8.2f%-10d%-8d\n",p->flight_num, p->start_place,p->end_place,p->start_time,p->end_time,p->price,p->isFull,p->left);
			return 1;
		}
	}
	printf("\t\t抱歉,没有航班号为%s的航班信息!\n",flight_num);
	return 0;
}

int Place_Check(FNode *h,char *start_place,char *end_place)//用起飞抵达城市进行查询
{
	FNode *p=h;
	int mark=0;
	printf("%-8s%-12s%-12s%-10s%-10s%-8s%-10s%-8s\n","航班号","起飞城市","抵达城市","起飞时间","抵达时间","价格","是否满仓","空座数");
	for(;p!=NULL;p=p->next)
	{
		if(strcmp(p->start_place,start_place)==0&&strcmp(p->end_place,end_place)==0)
		{
			printf("%-8s%-12s%-12s%-10s%-10s%-8.2f%-10d%-8d\n",p->flight_num, p->start_place,p->end_place,p->start_time,p->end_time,p->price,p->isFull,p->left);
			mark=1;
		}
	}
	if(mark==0)
	{
		printf("\t\t抱歉,没有从%s到%s的航班信息!\n",start_place,end_place);
		return 0;
	}
	return 1;
}

void Check_All_Flight(FNode *h)
{
	FNode *p=h;
	int m=0;
	printf("%-8s%-12s%-12s%-10s%-10s%-8s%-10s%-8s\n","航班号","起飞城市","抵达城市","起飞时间","抵达时间","价格","是否满仓","空座数");
	for(;p!=NULL;p=p->next)
	{
		printf("%-8s%-12s%-12s%-10s%-10s%-8.2f%-10d%-8d\n",p->flight_num, p->start_place,p->end_place,p->start_time,p->end_time,p->price,p->isFull,p->left);
		m=1;
	}
	if(m==0)
		printf("\t\t航班信息为空!\n");
}

void Flight_Check(FNode *h)//选择使用哪一个函数进行查询
{
	FNode *p=h->next;
	char flight_num[10],start_place[20],end_place[20];
	char a;
    printf("\t\t请选择航班查询方式:\n");
	printf("\t\t1按航班号进行查询;\n");
	printf("\t\t2按起飞抵达城市进行查询;\n");
	printf("\t\t3浏览全部航班信息.\n\t\t请选择输入:");
	a=getch();
	printf("%c\n",a);
	if(a=='1')
	{
		printf("\t\t请输入航班号:");
		scanf("%s",flight_num);
		Flight_Num_Check(p,flight_num);
	}
	else if(a=='2')
	{
		printf("\t\t请输入起飞城市:");
		scanf("%s",start_place);
		printf("\t\t请输入抵达城市:");
		scanf("%s",end_place);
		Place_Check(p,start_place,end_place);
	}
	else if(a=='3')
	{
		Check_All_Flight(p);
	}
	else
		return;
}

int ID_name_check(PList *PList,char *name,char *ID_num)
{
	PNode *p=PList->head->next;
	int mark=0;
	printf("%-8s%-20s%-20s%-10s%-8s%\n","订单号","姓名","证件号","航班号","订票数量");
	for(;p!=NULL;p=p->next)
	{
		if(strcmp(p->ID_num,ID_num)==0&&strcmp(p->name,name)==0)
		{
			printf("%-8d%-20s%-20s%-10s%-8d%\n",p->order_num,p->name,p->ID_num, p->flight_num,p->ticket_num);
			mark=1;
		}
	}
	if(mark==0)
	{
		printf("\t\t抱歉,没有%s,%s的订单信息!\n",name,ID_num);
		return 0;
	}
	return 1;
}

int Order_Num_Check(PList *PList,int order_num)
{
	PNode *p=PList->head->next;
	printf("%-8s%-20s%-20s%-10s%-8s%\n","订单号","姓名","证件号","航班号","订票数量");
	for(;p!=NULL;p=p->next)
	{
		if(p->order_num==order_num)
		{
			printf("%-8d%-20s%-20s%-10s%-8d%\n",p->order_num,p->name,p->ID_num, p->flight_num,p->ticket_num);
			return 1;
		}
	}
	printf("\t\t抱歉,没有订单号为%d的订单信息!\n",order_num);
	return 0;
}

void Check_All_Passenger(PList *PList)
{
	PNode *p=PList->head->next;
	int m=0;
	printf("%-8s%-20s%-20s%-10s%-8s%\n","订单号","姓名","证件号","航班号","订票数量");
	for(;p!=NULL;p=p->next)
	{
		printf("%-8d%-20s%-20s%-10s%-8d%\n",p->order_num,p->name,p->ID_num, p->flight_num,p->ticket_num);
		m=1;
	}
	if(m==0)
		printf("\t\t订单信息为空!\n");
}

void Passenger_Check(PList *PList)
{
	char name[20],ID_num[15];
	int order_num;
	char a;
    printf("\t\t请选择订单查询方式:\n");
	printf("\t\t1按客户姓名和证件号进行查询;\n");
	printf("\t\t2按订单号进行查询;\n");
	printf("\t\t3查看全部订单信息.\n\t\t请选择输入:");
	a=getch();
	printf("%c\n",a);
	if(a=='1')
	{
		printf("\t\t请输入客户姓名:");
		scanf("%s",name);
		printf("\t\t请输入证件号:");
		scanf("%s",ID_num);
		ID_name_check(PList,name,ID_num);
	}
	else if(a=='2')
	{
		printf("\t\t请输入订单号:");
		scanf("%d",&order_num);
		Order_Num_Check(PList,order_num);
	}
	else if(a=='3')
		Check_All_Passenger(PList);
	else
		return;
}


int Book(FNode *&h,PList *&PList)
{
	char name[20];       //姓名
	char ID_num[20];     //证件号
	char flight_num[10]; //航班号
	char start_place[20];//起飞城市
	char end_place[20];  //抵达城市
	int ticket_num;      //订票数量
	int k;
	FNode *p=h->next;
	printf("\t\t请输入信息:\n");
	printf("\t\t请输入起飞城市:");
	scanf("%s",start_place);
	printf("\t\t请输入抵达城市:");
	scanf("%s",end_place);
	if(Place_Check(h,start_place,end_place)==1)
	{
		printf("\t\t航班号:");
		scanf("%s",flight_num);
		while(flight_num==NULL)
		{
			printf("航班号不能为空!\n");
			printf("\t\t航班号:");
			scanf("%s",flight_num);
		}
		while(p!=NULL)
		{
			if(strcmp(p->flight_num,flight_num)==0)
			{
				printf("\t\t姓名:");
				scanf("%s",name);
				printf("\t\t证件号码:");
				scanf("%s",ID_num);
				printf("\t\t订票数量:");
				scanf("%d",&ticket_num);
				while(name==NULL)
				{
					printf("客户姓名不能为空!\n");
					printf("\t\t姓名:");
					scanf("%s",name);
				}
				while(ID_num==NULL)
				{
					printf("客户证件号码不能为空!\n");
					printf("\t\t证件号码:");
					scanf("%s",ID_num);
				}
				while(ticket_num==0)
				{
					printf("客户订票数量不能为空!\n");
					printf("\t\t订票数量:");
					scanf("%d",&ticket_num);
				}
				if(p->left>0&&p->left>=ticket_num)
				{
					Insert_Passenger(h,PList,name,ID_num,flight_num,ticket_num);
					printf("\t\t你应付金额￥%6.2f.\n",p->price* ticket_num);
					getch();
					printf("\t\t恭喜您,订票成功!\n");
					return 1;
				}
				else
				{
					printf("\t\t***很遗憾，该航班已满!***\n");
					printf("\t\t***如选择其他航班请输入-----1\n\t\t***不选则输入-----0***\n");
					printf("\t\t输入数字进行选择:");
					scanf("%d",&k);
					if(k==1)
					{
						printf("\t\t此航线上的其他航班有:\n");
						if(find_same_flight(h,flight_num)==1)
						{
							printf("\t\t请输入您选的航班:");
							scanf("%s",flight_num);
							Insert_Passenger(h,PList,name,ID_num,flight_num, ticket_num);
							printf("\t\t恭喜您,订票成功!\n");
							return 1;
						}
					}
				}
			}
			else
			{
				p=p->next;
			}
		}
		if(p==NULL)
		{
			printf("\t\t对不起,您输入的航班不存在!\n");
		}
	}
	return 0;
}

void Refund(PList *&PList,FNode *&h)
{
	char name[20],ID_num[20];
	printf("\t\t请输入你的姓名:");
	scanf("%s",name);
	printf("\t\t请输入你的证件号:");
	scanf("%s",ID_num);
	Delete_Passenger(PList,h,name,ID_num);
}

void main()
{
	char choice;
	int t=1;
	FNode *flight;
	PList *PList;
	Init_Flight(flight);//初始化航班链表
    Init_Passenger(PList);
	Load_Flight(flight);
	Load_Passenger(PList);
	while(t==1)
	{
		printf("\t\t************飞机订票系统菜单*************\n");
		printf("\t\t*****************************************\n");
		printf("\t\t*         录入信息----------1           *\n");
		printf("\t\t*         订    票----------2           *\n");
		printf("\t\t*         退    票----------3           *\n");
		printf("\t\t*         查询航班----------4           *\n");
		printf("\t\t*         查询订单----------5           *\n");
		printf("\t\t*         保存退出----------0           *\n");
		printf("\t\t*****************************************\n");
		printf("\t\t请选择服务:");
		choice=getch();
		printf("%c\n",choice);
		system("cls");
		if(choice=='1')
		{
			Add_Flight(flight);
			getch();
			system("cls");
		}
		else if(choice=='2')
		{
			Book(flight,PList);
			getch();
			system("cls");
		}
		else if(choice=='3')
		{
			Refund(PList,flight);
			getch();
			system("cls");
		}
		else if(choice=='4')
		{
			Flight_Check(flight);
			getch();
			system("cls");
		}
		else if(choice=='5')
		{
			Passenger_Check(PList);
			getch();
			system("cls");
		}
		else if(choice=='0')
		{
			printf("\t\t再见!\n");
			t=0;
		}
	}
	Save_Flight(flight);
	Save_Passenger(PList);
}
