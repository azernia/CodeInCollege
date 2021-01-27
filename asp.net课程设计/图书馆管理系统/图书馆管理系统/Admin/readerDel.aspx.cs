using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

using Library.Logic;

public partial class Admin_readerDel : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            /*验证是否登陆了系统*/
            if (Session["adminFlag"] == null)
            {
                Response.Write("<script>top.location.href='../login.aspx';</script>");
                return;
            }
        }
        string readerId = Request.QueryString["readerId"];
        ReaderLogic readerLogic = new ReaderLogic();
        if (readerLogic.DeleteReaderInfo(readerId))
        {
            Response.Write("<script>alert('读者信息删除成功!');location.href='readerManage.aspx';</script>");
        }
        else
            Response.Write("<script>alert('" + readerLogic.ErrMessage + "');location.href='readerManage.aspx';</script>");
    }
}
