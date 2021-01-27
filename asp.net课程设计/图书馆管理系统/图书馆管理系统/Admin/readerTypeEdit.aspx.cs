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

public partial class Admin_readerTypeEdit : System.Web.UI.Page
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
            /*根据读者类别编号得到该类别的信息然后显示到界面上*/
            int readerTypeId = Int32.Parse(Request.QueryString["readerTypeId"]);
            ReaderTypeModel readerModel = (new ReaderTypeLogic()).GetReaderTypeInfoById(readerTypeId);
            this.ReaderTypeName.Text = readerModel.ReaderTypeName;
            this.LoanNum.Text = readerModel.LoanNum.ToString();
            this.LoanDays.Text = readerModel.LoanDays.ToString();
            this.ContinueDays.Text = readerModel.ContinueDays.ToString();
        }
    }
    protected void Btn_Edit_Click(object sender, EventArgs e)
    {
        /*建立读者对象模型，并从界面传入各个属性的值*/
        int readerTypeId = Int32.Parse(Request.QueryString["readerTypeId"]);
        ReaderTypeModel readerTypeModel = new ReaderTypeModel();
        readerTypeModel.ReaderTypeId = readerTypeId;
        readerTypeModel.ReaderTypeName = this.ReaderTypeName.Text;
        readerTypeModel.LoanNum = Int32.Parse(this.LoanNum.Text);
        readerTypeModel.LoanDays = Int32.Parse(this.LoanDays.Text);
        readerTypeModel.ContinueDays = Int32.Parse(this.ContinueDays.Text);
        /*调用业务层进行读者类别信息的修改*/
        ReaderTypeLogic readerTypeLogic = new ReaderTypeLogic();
        if (readerTypeLogic.UpdateReaderType(readerTypeModel))
        {
            Response.Write("<script>alert('读者类别信息更新成功!');location.href='readerTypeEdit.aspx?readerTypeId=" + readerTypeId + "';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + readerTypeLogic.ErrMessage + "');</script>");
        }
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Write("<script>location.href='readerTypeManage.aspx';</script>");
    }
}
