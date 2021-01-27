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
    /// ReaderLogic 的摘要说明:关于读者信息的业务操作
    /// </summary>
    public class ReaderLogic
    {
        /*查找是否存在读者信息的sql语句*/
        private const string SQL_SELECT_IS_EXIST_READER_INFO = "select readerPassword from [reader_130610] where readerId=@readerId";
        /*各种sql语句的参数常量字符串*/
        private const string PARM_Reader_Id = "@readerId";
        /*业务处理错误信息*/
        private string errMessage;
        public string ErrMessage
        {
            set { this.errMessage = value; }
            get { return this.errMessage; }
        }
        /*查找读者信息表中是否存在该读者的信息*/
        public bool IsExistReaderInfo(ReaderModel readerModel)
        { 
            /*首先初始化查询sql语句的参数信息*/
            SqlParameter[] parms = this.GetIsExistReaderInfoParms();
            parms[0].Value = readerModel.ReaderId;
            /*得到该读者的记录信息*/
            DataSet readerDs = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, SQL_SELECT_IS_EXIST_READER_INFO, parms);
            if (readerDs.Tables[0].Rows.Count == 0)
            {
                this.errMessage = "对不起，不存在该读者的帐号信息!";
                return false;
            }
            else
            {
                if (readerDs.Tables[0].Rows[0]["readerPassword"].ToString() != readerModel.ReaderPassword)
                {
                    this.errMessage = "对不起，读者的密码不正确!";
                    return false;
                }
            }
            return true;
        }
        private SqlParameter[] GetIsExistReaderInfoParms()
        {
            SqlParameter[] parms = DBOperation.GetCachedParameters(SQL_SELECT_IS_EXIST_READER_INFO);
            if (parms == null)
            {
                parms = new SqlParameter[]{
											  new SqlParameter(PARM_Reader_Id,SqlDbType.VarChar) 
										  };
                DBOperation.CacheParameters(SQL_SELECT_IS_EXIST_READER_INFO, parms);
            }
            return parms;
        }
        /*执行新的读者信息的加入操作*/
        public bool AddNewReaderInfo(ReaderModel readerModel)
        {
            if(readerModel.ReaderId == "")
            {
                this.errMessage = "请输入读者编号信息!";
                return false;
            }
            if(readerModel.ReaderPassword == "")
            {
                this.errMessage = "请输入读者的密码信息！";
                return false;
            }
            /*如果电话输入不为空，需要进行格式验证*/
            if (readerModel.ReaderPhone != "")
            {
                if (!Common.IsVaildPhone(readerModel.ReaderPhone) && !Common.IsValidMobile(readerModel.ReaderPhone))
                {
                    this.errMessage = "你输入的电话格式不正确!";
                    return false;
                }
            }
            /*如果Email输入不为空，需要对其进行格式验证*/
            if (readerModel.ReaderEmail != "")
            {
                if (!Common.IsValidEmail(readerModel.ReaderEmail))
                {
                    this.errMessage = "Email格式输入不正确!";
                    return false;
                }
            }
            /*当通过验证后可以执行新读者信息的添加操作了*/
            string sqlString = "insert into [reader_130610] (readerId,readerPassword,readerName,readerSex,readerBirthday,readerTypeId,readerPhone,readerEmail,readerAddress,readerPhotoUrl,readerMemo) values ('";
            sqlString += readerModel.ReaderId + "','";
            sqlString += readerModel.ReaderPassword + "','";
            sqlString += readerModel.ReaderName + "','";
            sqlString += readerModel.ReaderSex + "','";
            sqlString += readerModel.ReaderBirthday + "',";
            sqlString += readerModel.ReaderTypeId + ",'";
            sqlString += readerModel.ReaderPhone + "','";
            sqlString += readerModel.ReaderEmail + "','";
            sqlString += readerModel.ReaderAddress + "','";
            sqlString += readerModel.ReaderPhotoUrl + "','";
            sqlString += readerModel.ReaderMemo + "')";
            /*sql语句构造完成后直接调用数据层进行读者信息的加入操作*/
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "添加读者信息时发生了数据库错误!";
                return false;
            }
            return true;
        }

        /*根据条件查询读者信息*/
        public DataSet QueryReaderInfo(string readerId, string readerName, int readerTypeId)
        {
            /*根据条件构造查询的sql语句*/
            string queryString = "SELECT [readerId], [readerName], [readerSex], [readerTypeName], [readerBirthday] FROM [readerView] where 1=1";
            if (readerId != "")
                queryString += " and readerId like '%" + readerId + "%'";
            if (readerName != "")
                queryString += " and readerName like '%" + readerName + "%'";
            if (readerTypeId != 0)
                queryString += " and readerTypeId=" + readerTypeId;
            DataSet ds = new DataSet();
            ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null);
            return ds;
        }
        /*根据读者编号得到读者的信息并保存在模型中返回*/
        public ReaderModel GetReaderInfo(string readerId)
        {
            string queryString = "select * from [reader_130610] where readerId='" + readerId + "'";
            DataSet ds = new DataSet();
            ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null);
            if (ds.Tables[0].Rows.Count == 0) return null;
            ReaderModel readerModel = new ReaderModel();/*建立模型*/
            DataRow dr = ds.Tables[0].Rows[0];
            readerModel.ReaderId = readerId;
            readerModel.ReaderPassword = dr["readerPassword"].ToString();
            readerModel.ReaderName = dr["readerName"].ToString();
            readerModel.ReaderSex = dr["readerSex"].ToString();
            readerModel.ReaderBirthday = Convert.ToDateTime(dr["readerBirthday"]);
            readerModel.ReaderTypeId = Convert.ToInt32(dr["readerTypeId"]);
            readerModel.ReaderPhone = dr["readerPhone"].ToString();
            readerModel.ReaderEmail = dr["readerEmail"].ToString();
            readerModel.ReaderAddress = dr["readerAddress"].ToString();
            readerModel.ReaderPhotoUrl = dr["readerPhotoUrl"].ToString();
            readerModel.ReaderMemo = dr["readerMemo"].ToString();
            return readerModel;
        }
        /*更新读者信息*/
        public bool UpdateReaderInfo(ReaderModel readerModel,string rootPath)
        {
            /*得到该读者原来的照片路径,如果新的图片路径跟原来不一样，需要删除老图片*/
            string readerOldPhotoUrl = this.GetReaderPhotoUrl(readerModel.ReaderId);
            /*如果原来已经存在了读者照片*/
            if (readerOldPhotoUrl != "")
            {
                /*如果图片路径不一样，说明管理员选择了新的图片，此时需要删出原来的图片*/
                if (String.Compare(readerOldPhotoUrl,readerModel.ReaderPhotoUrl, true) != 0)
                    System.IO.File.Delete(rootPath + "\\" + readerOldPhotoUrl);
            }
            /*构造更新sql语句然后执行更新操作,要更新的读者信息已经存在于readerModel中*/
            string updateString = "update [reader_130610] set ";
            updateString += "readerPassword='" + readerModel.ReaderPassword + "',";
            updateString += "readerName='" + readerModel.ReaderName + "',";
            updateString += "readerSex='" + readerModel.ReaderSex + "',";
            updateString += "readerBirthday='" + readerModel.ReaderBirthday + "',";
            updateString += "readerTypeId=" + readerModel.ReaderTypeId + ",";
            updateString += "readerPhone='" + readerModel.ReaderPhone + "',";
            updateString += "readerEmail='" + readerModel.ReaderEmail + "',";
            updateString += "readerAddress='" + readerModel.ReaderAddress + "',";
            updateString += "readerPhotoUrl='" + readerModel.ReaderPhotoUrl + "',";
            updateString += "readerMemo='" + readerModel.ReaderMemo + "'";
            updateString += " where readerId='" + readerModel.ReaderId + "'";
            /*调用数据层代码实行更新，如果成功受影响的行数大于0*/
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,updateString,null)< 0)
            {
                this.errMessage = "更新读者信息时发生了错误!";
                return false;
            }
            return true;  
        }

        /*删除某个读者的信息*/
        public bool DeleteReaderInfo(string readerId)
        {
            /*查询该读者是否还有图书没有归还*/
            string queryString = "select * from [loan_130610] where readerId='" + readerId + "' and isReturn=0";
            if (DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).Read())
            {
                this.errMessage = "对不起，该读者还有图书没有归还，不能删除!";
                return false;
            }
            string deleteString1 = "delete from [loan_130610] where readerId='" + readerId + "'";
            string deleteString2 = "delect from [reader_130610] where readerId='" + readerId + "'";
            string[] procedureStrings = new string[] { deleteString1,deleteString2};
            if (!DBOperation.ExecuteStoreProcedure(DBOperation.CONN_STRING_NON_DTC, procedureStrings, null))
            {
                this.errMessage = "删除读者信息时发生了错误！";
                return false;
            }
            return true;
        }

        /*根据读者编号得到照片路径*/
        public string GetReaderPhotoUrl(string readerId)
        {
            string queryString = "select readerPhotoUrl from [reader_130610] where readerId='" + readerId + "'";
            return DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).ToString();
        }

        /*根据读者编号查询是否存在该读者*/
        public static bool IsExistReaderInfo(string readerId)
        {
            string queryString = "select * from [reader_130610] where readerId='" + readerId + "'";
            return DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).Read();
        }

        /*根据读者编号得到读者的姓名*/
        public static string GetReaderNameById(string readerId)
        {
            string queryString = "select readerName from [reader_130610] where readerId='" + readerId + "'";
            return DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).ToString();
        }

        /*根据读者编号得到该读者可借书的天数*/
        public static int GetLoanDays(string readerId)
        {
            string sqlString = "select loanDays from [readerType_130610],[reader_130610] where [reader_130610].readerTypeId=[readerType_130610].readerTypeId and [reader_130610].readerId='" + readerId + "'";
            object loanDays = DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            return Convert.ToInt32(loanDays);
        }
        /*根据读者编号得到该读者可续借图书的天数*/
        public static int GetContinueDays(string readerId)
        {
            string sqlString = "select continueDays from [readerType_130610],[reader_130610] where [reader_130610].readerTypeId=[readerType_130610].readerTypeId and [reader_130610].readerId='" + readerId + "'";
            object continueDays = DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null);
            return Convert.ToInt32(continueDays);
        }
        //修改登陆密码
        public bool ChangePassword(ReaderModel readerModel)
        {
            string updateString = "update [reader_130610] set readerPassword='" + readerModel.ReaderPassword;
            updateString += "' where readerId='" + readerModel.ReaderId + "'";
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, updateString, null) < 0)
                return false;
            return true;
        }
    }

}
