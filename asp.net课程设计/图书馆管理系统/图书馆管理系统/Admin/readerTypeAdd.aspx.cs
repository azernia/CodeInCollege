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

public partial class Admin_readerTypeAdd : System.Web.UI.Page
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
    }
    protected void Btn_Add_Click(object sender, EventArgs e)
    {
        /*建立读者类型对象模型并传入界面上的各个参数*/
        ReaderTypeModel readerModel = new ReaderTypeModel();
        readerModel.ReaderTypeName = this.ReaderTypeName.Text;
        readerModel.LoanNum = Int32.Parse(this.LoanNum.Text);
        readerModel.LoanDays = Int32.Parse(this.LoanDays.Text);
        readerModel.ContinueDays = Int32.Parse(this.ContinueDays.Text);
        /*调用业务层进行添加*/
        ReaderTypeLogic readerTypeLogic = new ReaderTypeLogic();
        if (readerTypeLogic.AddNewReaderType(readerModel))
        {
            Response.Write("<script>alert('读者类别信息添加成功!');location.href='readerTypeManage.aspx';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + readerTypeLogic.ErrMessage + "');</script>");
        }
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        this.ReaderTypeName.Text = "";
        this.LoanNum.Text = "";
        this.LoanDays.Text = "";
        this.ContinueDays.Text = "";
    }
}
