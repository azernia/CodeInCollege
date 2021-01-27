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
    /// BookTypeModel 的摘要说明:本模型对应数据库中的图书类别信息表bookType表
    /// </summary>
    public class BookTypeModel
    {
        /*
            CREATE TABLE [dbo].[bookType_130610] (
	        [bookTypeId] [int] IDENTITY (1, 1) NOT NULL ,                   //图书类别编号
	        [bookTypeName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL    //图书类别名称
            ) ON [PRIMARY]
         */
        private int bookTypeId;
        private string bookTypeName;
        public int BookTypeId
        {
            set { this.bookTypeId = value; }
            get { return this.bookTypeId; }
        }
        public string BookTypeName
        {
            set { this.bookTypeName = value; }
            get { return this.bookTypeName; }
        }
        public BookTypeModel()
        {
            bookTypeId = 0;
            bookTypeName = "";
        }
    }

}
