using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

using System.Data.SqlClient;
using Library.Model;
using Library.Utility;

namespace Library.Logic
{
    /// <summary>
    /// BooksLogic 的摘要说明:关于图书管理的业务操作类
    /// </summary>
    public class BooksLogic
    {
        /*保存业务操作时发生的错误信息*/
        private string errMessage;
        public string ErrMessage
        {
            set { this.errMessage = value; }
            get { return this.errMessage; }
        }
        /*将新的图书信息加入系统中*/
        public bool AddNewBook(BooksModel booksModel)
        {
            string sqlString = "insert into [books_130610] (bookName,bookTypeId,author,publishing,bookMoney,bookNum,introduction) values (";
            sqlString = sqlString + "'" + booksModel.BookName + "',";
            sqlString = sqlString + booksModel.BookTypeId + ",";
            sqlString = sqlString + "'" + booksModel.Author + "',";
            sqlString = sqlString + "'" + booksModel.Publishing + "',";
            sqlString = sqlString + booksModel.BookMoney + ",";
            sqlString = sqlString + booksModel.BookNum + ",";
            sqlString = sqlString + "'" + booksModel.Introduction + "')";
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "将新的图书信息加入系统时发生了错误!";
                return false;
            }
            return true;
        }
        /*更新某个图书的信息*/
        public bool UpdateOneBook(BooksModel booksModel)
        {
            string sqlString = "update [books_130610] set ";
            sqlString = sqlString + "bookName='" + booksModel.BookName + "',";
            sqlString = sqlString + "bookTypeId=" + booksModel.BookTypeId + ",";
            sqlString = sqlString + "author='" + booksModel.Author + "',";
            sqlString = sqlString + "publishing='" + booksModel.Publishing + "',";
            sqlString = sqlString + "bookMoney=" + booksModel.BookMoney + ",";
            sqlString = sqlString + "bookNum=" + booksModel.BookNum + ",";
            sqlString = sqlString + "introduction='" + booksModel.Introduction + "'";
            sqlString = sqlString + " where bookId=" + booksModel.BookId;
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "更新图书信息时发生了错误";
                return false;
            }
            return true;
        }
        /*根据图书编号删除某个图书的信息*/
        public bool DeleteBooksInfo(int bookId)
        {
            /*首先查询该图书对象是否还存在外借的信息*/
            string sqlString = "select * from [loan_130610] where bookId=" + bookId + " and isReturn=0";
            /*如果存在是不能执行删除操作的*/
            if (DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null).Read())
            {
                this.errMessage = "对不起,该图书对象还有没有归还的图书!";
                return false;
            }
            /*删除该图书相关的外借信息sql语句*/
            string deleteLoanString = "delete from [loan_130610] where bookId=" + bookId;
            /*删除该图书信息的sql语句*/
            string deleteBookString = "delete from [books_130610] where bookId=" + bookId;
            string[] procedureStrings = new string[]{deleteLoanString,deleteBookString};
            if (!DBOperation.ExecuteStoreProcedure(DBOperation.CONN_STRING_NON_DTC, procedureStrings, null))
            {
                this.errMessage = "执行图书信息删除时发生了数据库错误!";
                return false;
            }
            return true;
        }
        /*根据查询条件对图书信息进行查询*/
        public DataSet QueryBooksInfo(string bookName, int bookTypeId, string author)
        {
            /*根据各个查询条件构造查询的sql语句*/
            string sqlString = "select * from [readerView_130610] where 1=1";
            if(bookName != "")
                sqlString += " and bookName like '%" + bookName + "%'";
            if(bookTypeId != 0)
                sqlString += " and bookTypeId=" + bookTypeId;
            if (author != "")
                sqlString += " and author like '%" + author + "%'";
            /*调用数据层进行查询并返回结果集*/
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            return ds;
        }
        /*根据图书编号得到某个图书对象的信息并返回该图书对象模型*/
        public BooksModel GetBookInfo(int bookId)
        {
            string sqlString = "select * from [books_130610] where bookId=" + bookId;
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            if (ds.Tables[0].Rows.Count == 0) return null;
            DataRow dr = ds.Tables[0].Rows[0];
            BooksModel booksModel = new BooksModel();
            booksModel.BookId = Convert.ToInt32(dr["bookId"]);
            booksModel.BookName = dr["bookName"].ToString();
            booksModel.BookTypeId = Convert.ToInt32(dr["bookTypeId"]);
            booksModel.Author = dr["author"].ToString();
            booksModel.Publishing = dr["publishing"].ToString();
            booksModel.BookMoney = Convert.ToSingle(dr["bookMoney"]);
            booksModel.BookNum = Convert.ToInt32(dr["bookNum"]);
            booksModel.Introduction = dr["introduction"].ToString();
            return booksModel;
        }
        /*根据图书编号得到图书的库存数量*/
        public static int GetBookNumById(int bookId)
        {
            string queryString = "select bookNum from [books_130610] where bookId=" + bookId;
            return Convert.ToInt32(DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null));
        }
        /*查询是否存在该图书编号的图书对象*/
        public static bool IsExistBook(int bookId)
        {
            string queryString = "select * from [books_130610] where bookId=" + bookId;
            return DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).Read();
        }
        /*根据图书编号得到图书的名称*/
        public static string GetBookNameById(int bookId)
        {
            string queryString = "select bookName from [books_130610] where bookId=" + bookId;
            return DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).ToString();
        }
        public BooksLogic()
        {
            this.errMessage = "";
        }
    }

}
