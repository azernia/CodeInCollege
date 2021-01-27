#include<iostream.h>
#include<stdio.h>
#include<malloc.h>

#define ElemType int

typedef struct BTNode
{
	ElemType data;
	struct BTNode *lchild,*rchild;
}BTNode,*BiTNode;

void Create(BiTNode * Tree)//����������
{  
	ElemType e;
	scanf("%d",&e);
	if(e==0)
	{
		*Tree=NULL;
	}
	else
	{
		(*Tree)=(BiTNode)malloc(sizeof(BTNode));
		(*Tree)->data=e;
		printf("input %d lchild: ",e);
		Create(&(*Tree)->lchild);
		printf("input %d rchild: ",e);
		Create(&(*Tree)->rchild);
	}
}

void Print1(BiTNode Tree)//�������
{  
	if(Tree!=NULL)
	{
		printf("%d-",Tree->data);
		Print1(Tree->lchild);
		Print1(Tree->rchild);
	}
}

void Print2(BiTNode Tree)//�������
{  
	if(Tree!=NULL)
	{
		Print2(Tree->lchild);
		printf("%d-",Tree->data);
		Print2(Tree->rchild);
	}
}

void Print3(BiTNode Tree)//�������
{  
	if(Tree!=NULL)
	{
		Print3(Tree->lchild);
		Print3(Tree->rchild);
		printf("%d-",Tree->data);
	}
}

int leaf=0;  //��Ҷ�ӽڵ���

int Depth(BiTNode Tree)//���
{  
	int s1,s2;
	if(Tree==NULL)
	{
		return 0;
	}
	else
	{
		s1=Depth(Tree->lchild);
		s2=Depth(Tree->rchild);
		if(s1==0 && s2==0) leaf++;
		return (s1>s2?s1:s2)+1;
	}
}

int Cnode(BiTNode Tree)//�ܽ��
{  
	int s1,s2;
	if(Tree==NULL)
	{
		return 0;
	}
	else
	{
		s1=Cnode(Tree->lchild);
		s2=Cnode(Tree->rchild);
		return s1+s2+1;
	}
}

void main()
{

	BiTNode Tree;
	printf("input ���ڵ�:   ");
	Create(&Tree);
	printf("�������:");
	Print1(Tree);
	cout<<endl;
	printf("�������");
	Print2(Tree);
	cout<<endl;
	printf("�������");
	Print3(Tree);
	cout<<endl;
	printf("\n��  ��:%d \n",Depth(Tree));
	printf("�ܽ����:%d \n",Cnode(Tree));
	printf("Ҷ�ӽ����:%d\n",leaf);
}
