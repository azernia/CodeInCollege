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

public partial class Admin_readerManage : System.Web.UI.Page
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
            /*初始化读者类别下拉框的信息*/
            this.ReaderType.Items.Add(new ListItem("请选择", "0"));
            DataSet ds = (new ReaderTypeLogic()).GetReaderTypeInfo();
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                DataRow dr = ds.Tables[0].Rows[i];
                ListItem li = new ListItem(dr["readerTypeName"].ToString(), dr["readerTypeId"].ToString());
                this.ReaderType.Items.Add(li);
            }
        }
    }
    protected void Btn_Query_Click(object sender, EventArgs e)
    {
        /*取得查询的各个参数*/
        string readerId = this.ReaderId.Text;
        string readerName = this.ReaderName.Text;
        int readerTypeId = Int32.Parse(this.ReaderType.SelectedValue);
        /*调用业务层进行查询并绑定*/
        ReaderLogic readerLogic = new ReaderLogic();
        this.GridView1.DataSourceID = null;
        this.GridView1.DataSource = readerLogic.QueryReaderInfo(readerId, readerName, readerTypeId);
        this.GridView1.DataBind();

    }
    protected void GridView1_RowDataBound(object sender, GridViewRowEventArgs e)
    {
        if (e.Row.RowType == DataControlRowType.DataRow)
        {
            //当鼠标选择某行时变颜色
            e.Row.Attributes.Add("onmouseover", "c=this.style.backgroundColor;this.style.backgroundColor='#00ffee';");
            e.Row.Attributes.Add("onmouseout", "this.style.backgroundColor=c;");
            e.Row.Cells[4].Text = Convert.ToDateTime(e.Row.Cells[4].Text).ToShortDateString();
        }
    }
    protected void GridView1_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        /*取得查询的各个参数*/
        string readerId = this.ReaderId.Text;
        string readerName = this.ReaderName.Text;
        int readerTypeId = Int32.Parse(this.ReaderType.SelectedValue);
        /*调用业务层进行查询并绑定*/
        ReaderLogic readerLogic = new ReaderLogic();
        this.GridView1.DataSourceID = null;
        this.GridView1.DataSource = readerLogic.QueryReaderInfo(readerId, readerName, readerTypeId);
        this.GridView1.PageIndex = e.NewPageIndex;
        this.GridView1.DataBind();
    }
}
