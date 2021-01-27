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

using Library.Model;
using Library.Logic;

public partial class Reader_changePassword : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            /*验证是否登陆了系统*/
            if (Session["readerFlag"] == null)
            {
                Response.Write("<script>top.location.href='../login.aspx';</script>");
                return;
            }
        }
    }
    protected void Btn_ChangePassword_Click(object sender, EventArgs e)
    {
        ReaderModel readerModel = new ReaderModel();
        readerModel.ReaderId = Session["readerId"].ToString();
        readerModel.ReaderPassword = this.NewPassword.Text;
        ReaderLogic readerLogic = new ReaderLogic();
        if(readerLogic.ChangePassword(readerModel))
            this.ErrMessage.Text = "<font color=red>密码修改成功!</font>";
        else
            this.ErrMessage.Text = "<font color=red>密码修改失败!</font>";
    }
}
