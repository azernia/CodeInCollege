#include<iostream.h>
#include<stdio.h>
#include<malloc.h>

#define ElemType int

typedef struct BTNode
{
	ElemType data;
	struct BTNode *lchild,*rchild;
}BTNode,*BiTNode;

void Create(BiTNode * Tree)//创建二叉树
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

void Print1(BiTNode Tree)//先序遍历
{  
	if(Tree!=NULL)
	{
		printf("%d-",Tree->data);
		Print1(Tree->lchild);
		Print1(Tree->rchild);
	}
}

void Print2(BiTNode Tree)//中序遍历
{  
	if(Tree!=NULL)
	{
		Print2(Tree->lchild);
		printf("%d-",Tree->data);
		Print2(Tree->rchild);
	}
}

void Print3(BiTNode Tree)//后序遍历
{  
	if(Tree!=NULL)
	{
		Print3(Tree->lchild);
		Print3(Tree->rchild);
		printf("%d-",Tree->data);
	}
}

int leaf=0;  //求叶子节点数

int Depth(BiTNode Tree)//深度
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

int Cnode(BiTNode Tree)//总结点
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
	printf("input 根节点:   ");
	Create(&Tree);
	printf("先序遍历:");
	Print1(Tree);
	cout<<endl;
	printf("中序遍历");
	Print2(Tree);
	cout<<endl;
	printf("后序遍历");
	Print3(Tree);
	cout<<endl;
	printf("\n深  度:%d \n",Depth(Tree));
	printf("总结点数:%d \n",Cnode(Tree));
	printf("叶子结点数:%d\n",leaf);
}
