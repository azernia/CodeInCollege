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
    /// ReaderModel 的摘要说明:关于读者信息的数据模型
    /// </summary>
    public class ReaderModel
    {
        /*读者信息表
        CREATE TABLE [dbo].[reader_130610] (
            [readerId] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,			//读者编号
            [readerPassword] [varchar] (32) COLLATE Chinese_PRC_CI_AS NULL ,		//读者密码
            [readerName] [nvarchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,			//读者姓名
            [readerSex] [nvarchar] (2) COLLATE Chinese_PRC_CI_AS NULL ,			    //读者性别
            [readerBirthday] [datetime] NULL ,						                //读者生日
            [readerTypeId] [int] NULL ,							                    //读者类型
            [readerIsLock] [int] NULL ,							                    //读者是否被锁定
            [readerPhone] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,			//读者电话
            [readerEmail] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,			//读者Email
            [readerAddress] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,			//读者地址
            [readerPhotoUrl] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,		//读者个人照片路径
            [readerMemo] [ntext] COLLATE Chinese_PRC_CI_AS NULL 				    //附加信息
        ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]*/
        #region Varible
        private string readerId;
        private string readerPassword;
        private string readerName;
        private string readerSex;
        private DateTime readerBirthday;
        private int readerTypeId;
        private int readerIsLock;
        private string readerPhone;
        private string readerEmail;
        private string readerAddress;
        private string readerPhotoUrl;
        private string readerMemo;
        #endregion
        #region Attribute
        public string ReaderId
        {
            set {this.readerId = value;}
            get { return this.readerId; }
        }
        public string ReaderPassword
        {
            set { this.readerPassword = value; }
            get { return this.readerPassword; }
        }
        public string ReaderName
        {
            set { this.readerName = value; }
            get { return this.readerName; }
        }
        public string ReaderSex
        {
            set { this.readerSex = value; }
            get { return this.readerSex; }
        }
        public DateTime ReaderBirthday
        {
            set { this.readerBirthday = value; }
            get { return this.readerBirthday; }
        }
        public int ReaderTypeId
        {
            set { this.readerTypeId = value; }
            get { return this.readerTypeId; }
        }
        public int ReaderIsLock
        {
            set { this.readerIsLock = value; }
            get { return this.readerIsLock; }
        }
        public string ReaderPhone
        {
            set { this.readerPhone = value; }
            get { return this.readerPhone; }
        }
        public string ReaderEmail
        {
            set { this.readerEmail = value; }
            get { return this.readerEmail; }
        }
        public string ReaderAddress
        {
            set { this.readerAddress = value; }
            get { return this.readerAddress; }
        }
        public string ReaderPhotoUrl
        {
            set { this.readerPhotoUrl = value; }
            get { return this.readerPhotoUrl; }
        }
        public string ReaderMemo
        {
            set { this.readerMemo = value; }
            get { return this.readerMemo; }
        }
        #endregion
        public ReaderModel()
        {
            readerId = "";
            readerPassword = "";
            readerName = "";
            readerSex = "";
            readerBirthday = Convert.ToDateTime("1900-1-1");
            readerTypeId = 0 ;
            readerIsLock = 0;
            readerPhone = "";
            readerEmail = "";
            readerAddress = "";
            readerPhotoUrl = "";
            readerMemo = "";
        }
    }
}

