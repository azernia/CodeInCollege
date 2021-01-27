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
    /// ReaderTypeLogic 的摘要说明:关于读者类别信息操作的业务处理类
    /// </summary>
    public class ReaderTypeLogic
    {
        /*保存业务处理的出错信息*/
        private string errMessage;
        public string ErrMessage
        {
            set { this.errMessage = value; }
            get { return this.errMessage; }
        }
        public ReaderTypeLogic()
        {
            this.errMessage = "";
        }
        /*向系统中加入新的读者类别信息*/
        public bool AddNewReaderType(ReaderTypeModel readerTypeModel)
        {
            /*首先查询系统中是否存在了该类别,如果存在了不执行加入操作*/
            string sqlString = "select * from [readerType_130610] where readerTypeName='" + readerTypeModel.ReaderTypeName + "'";
            if (DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null).Read())
            {
                this.errMessage = "对不起，系统中存在了该读者类别信息";
                return false;
            }
            /*构造加入新读者类别的sql语句*/
            sqlString = "insert into [readerType_130610] (readerTypeName,loanNum,loanDays,continueDays) values ('";
            sqlString += readerTypeModel.ReaderTypeName + "',";
            sqlString += readerTypeModel.LoanNum + ",";
            sqlString += readerTypeModel.LoanDays + ",";
            sqlString += readerTypeModel.ContinueDays + ")";
            /*调用数据层将新的读者类别加入系统*/
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "添加新读者类别时发生了数据错误!";
                return false;
            }
            return true;
        }
        /*更新读者类别信息*/
        public bool UpdateReaderType(ReaderTypeModel readerTypeModel)
        {
            string sqlString = "select * from [readerType_130610] where readerTypeId=" + readerTypeModel.ReaderTypeId;
            DataSet ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,sqlString,null);
            string oldReaderTypeName = ds.Tables[0].Rows[0]["readerType"].ToString();
            /*如果现在的读者类别和原来的类别名称不一样需要判断新的类别名称是否存在*/
            if(oldReaderTypeName != readerTypeModel.ReaderTypeName)
            {
                sqlString = "select * from [readerType_130610] where readerTypeName='" + readerTypeModel.ReaderTypeName + "'";
                if(DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,sqlString,null).Read())
                {
                    this.errMessage = "对不起，该读者类别名称已经存在!";
                    return false;
                }
            }
            /*执行读者类别信息的更新操作*/
            sqlString = "update [readerType_130610] set readerTypeName='";
            sqlString += readerTypeModel.ReaderTypeName + "',loanNum=";
            sqlString += readerTypeModel.LoanNum + ",loanDays=";
            sqlString += readerTypeModel.LoanDays + ",continueDays=";
            sqlString += readerTypeModel.ContinueDays + " where readerTypeId=" + readerTypeModel.ReaderTypeId;
            if(DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,sqlString,null) < 0)
            {
                this.errMessage = "更新读者时发生了数据库错误!";
                return false;
            }
            return true;
        }
        /*根据读者类别编号删除某个读者类别信息*/
        public bool DeleteReaderType(int readerTypeId)
        {
            string sqlString = "select * from [reader_130610] where readerTypeId=" + readerTypeId;
            if (DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null).Read())
            {
                this.errMessage = "对不起,该读者类别下还存在读者信息!";
                return false;
            }
            sqlString = "delete from [readerType_130610] where readerTypeId=" + readerTypeId;
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "删除读者类别信息时发生了数据库错误！";
                return false;
            }
            return true;
        }

        /*得到所有的读者类别信息*/
        public DataSet GetReaderTypeInfo()
        {
            string sqlString = "select readerTypeId,readerTypeName from [readerType_130610]";
            DataSet ds = new DataSet();
            ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            return ds;
        }

        /*得到某个读者类别的信息*/
        public ReaderTypeModel GetReaderTypeInfoById(int readerTypeId)
        {
            string queryString = "select * from [readerType_130610] where readerTypeId=" + readerTypeId;
            DataSet ds = new DataSet();
            ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null);
            if (ds.Tables[0].Rows.Count == 0) return null;
            DataRow dr = ds.Tables[0].Rows[0];
            ReaderTypeModel readerTypeModel = new ReaderTypeModel();
            readerTypeModel.ReaderTypeId = readerTypeId;
            readerTypeModel.ReaderTypeName = dr["readerTypeName"].ToString();
            readerTypeModel.LoanNum = Convert.ToInt32(dr["loanNum"]);
            readerTypeModel.LoanDays = Convert.ToInt32(dr["loanDays"]);
            readerTypeModel.ContinueDays = Convert.ToInt32(dr["continueDays"]);
            return readerTypeModel;
        }

        /*根据读者类别编号得到类别名称*/
        public string GetReaderTypeNameById(int readerTypeId)
        {
            string queryString = "select readerTypeName from [readerType_130610] where readerTypeId=" + readerTypeId;
            string readerTypeName = DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).ToString();
            return readerTypeName;
        }
    }

}
