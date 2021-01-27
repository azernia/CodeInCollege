using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

namespace Library.Model
{
    /// <summary>
    /// BooksModel 的摘要说明:本模型对应数据库中的图书信息表books表
    /// </summary>
    public class BooksModel
    {
        /*图书信息表
        CREATE TABLE [dbo].[books_130610] (
	        [bookId] [int] IDENTITY (1, 1) NOT NULL ,					    //图书编号
	        [bookName] [nvarchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,     //图书名称
	        [bookTypeId] [int] NULL ,							            //图书类别编号
	        [author] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,       //作者
	        [publishing] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,   //出版社
	        [bookMoney] [float] NULL ,                                      //定价
	        [bookNum] [char] (10) COLLATE Chinese_PRC_CI_AS NULL ,          //图书数量
	        [introduction] [text] COLLATE Chinese_PRC_CI_AS NULL            //介绍
        ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]*/
        private int bookId;
        private string bookName;
        private int bookTypeId;
        private string author;
        private string publishing;
        private float bookMoney;
        private int bookNum;
        private string introduction;
        public int BookId
        {
            set { this.bookId = value; }
            get { return this.bookId; }
        }
        public string BookName
        {
            set { this.bookName = value; }
            get { return this.bookName; }
        }
        public int BookTypeId
        {
            set { this.bookTypeId = value; }
            get { return this.bookTypeId; }
        }
        public string Author
        {
            set { this.author = value; }
            get { return this.author; }
        }
        public string Publishing
        {
            set { this.publishing = value; }
            get { return this.publishing; }
        }
        public float BookMoney
        {
            set { this.bookMoney = value; }
            get { return this.bookMoney; }
        }
        public int BookNum
        {
            set { this.bookNum = value; }
            get { return this.bookNum; }
        }
        public string Introduction
        {
            set { this.introduction = value; }
            get { return this.introduction; }
        }
        public BooksModel()
        {
            this.bookId = 0;
            this.bookName = "";
            this.bookTypeId = 0;
            this.author = "";
            this.publishing = "";
            this.bookMoney = 0.0F;
            this.bookNum = 0;
            this.introduction = "";
        }
    }

}
