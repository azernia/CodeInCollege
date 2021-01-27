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
using System.Collections;

namespace Library.Logic
{
    /// <summary>
    /// BookTypeLogic 的摘要说明：关于图书类别信息管理的业务操作
    /// </summary>
    public class BookTypeLogic
    {
        /*插入图书类别信息的sql语句*/
        private const string SQL_INSERT_BOOK_TYPE = "insert into [bookType_130610](bookTypeName) values (@bookTypeName)";
        /*各种sql语句的参数常量字符串*/
        private const string PARM_BOOK_TYPE_NAME = "@bookTypeName";
        /*业务处理错误信息*/
        private string errMessage;
        public string ErrMessage
        {
            set { this.errMessage = value; }
            get { return this.errMessage; }
        }
         /*向图书类别信息表中加入图书类别信息*/
        public bool InsertBookTypeInfo(BookTypeModel bookTypeModel)
        { 
            /*判断类别名称是否为空*/
            if(bookTypeModel.BookTypeName == "")
            {
                this.errMessage = "请输入图书类别名称!";
                return false;
            }
            /*判断该图书类别是否已经存在*/
            string sqlString = "select * from [bookType_130610] where bookTypeName = '" + bookTypeModel.BookTypeName + "'";
            if(DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,sqlString,null).Read())
            {
                this.errMessage = "该图书类别已经存在!";
                return false;
            }

            /*首先初始化sql语句的参数信息*/
            SqlParameter[] parms = this.GetInsertBookTypeInfoParms();
            parms[0].Value = bookTypeModel.BookTypeName;
            int effectCount = DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,SQL_INSERT_BOOK_TYPE,parms);
            if(effectCount < 0)
            {
                this.errMessage = "图书类别信息添加不成功!";
                return false;
            }
            return true;
        }

        private SqlParameter[] GetInsertBookTypeInfoParms()
        {
            SqlParameter[] parms = DBOperation.GetCachedParameters(SQL_INSERT_BOOK_TYPE);
            if (parms == null)
            {
                parms = new SqlParameter[]{
											  new SqlParameter(PARM_BOOK_TYPE_NAME,SqlDbType.NVarChar) 
										  };
                DBOperation.CacheParameters(SQL_INSERT_BOOK_TYPE, parms);
            }
            return parms;
        }

        /*更新某个图书类别的类别名称*/
        public bool UpdateBookTypeName(BookTypeModel bookTypeModel)
        {
            /*首先查询系统中是否存在该图书类别名称*/
            string sqlString = "select * from [bookType_130610] where bookTypeName='" + bookTypeModel.BookTypeName + "'";
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            if(ds.Tables[0].Rows.Count > 0)
            {
                this.errMessage = "对不起，该图书类别已经存在!";
                return false;
            }
            /*如果不存在就根据图书类别编号更新类别名称*/
            sqlString = "update [bookType_130610] set bookTypeName='" + bookTypeModel.BookTypeName + "' where bookTypeId=" + bookTypeModel.BookTypeId;
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "图书类别名称修改时发生了错误";
                return false;
            }
            return true;
        }

        /*删除某个图书类别*/
        public bool DeleteBookType(int bookTypeId)
        {
            /*查询该类别下是否还有图书*/
            string sqlString = "select count(*) as count from [books_130610] where bookTypeId=" + bookTypeId;
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            int count = Convert.ToInt32(ds.Tables[0].Rows[0]["count"]);
            if (count > 0)
            {
                this.errMessage = "对不起，该类别下还存在图书!";
                return false;
            }
            /*如果该类别下没有图书就开始执行该类别的删除操作*/
            sqlString = "delete from [bookType_130610] where bookTypeId=" + bookTypeId;
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "删除图书类别时发生了错误!";
                return false;
            }
            return true;
        }

        /*得到所有的图书类别信息*/
        public DataSet GetBookTypeInfo()
        {
            string sqlString = "select * from [bookType_130610]";
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            return ds;
        }

        /*根据图书类别编号得到图书类别名称*/
        public static string GetBookTypeNameById(int bookTypeId)
        {
            string queryString = "select bookTypeName from [bookType_130610] where bookTypeId=" + bookTypeId;
            return DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).ToString();
        }
    }
}
