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

public partial class Reader_bookQuery : System.Web.UI.Page
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
            /*初始化图书类别下拉框的信息*/
            this.BookType.Items.Add(new ListItem("请选择图书类别", "0"));
            DataSet ds = (new BookTypeLogic()).GetBookTypeInfo();
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                DataRow dr = ds.Tables[0].Rows[i];
                ListItem li = new ListItem(dr["bookTypeName"].ToString(), dr["bookTypeId"].ToString());
                this.BookType.Items.Add(li);
            }
        }
    }
    protected void GridView1_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        /*取得查询的各个参数*/
        string bookName = this.BookName.Text;
        int bookTypeId = Int32.Parse(this.BookType.SelectedValue);
        string author = this.Author.Text;
        /*调用业务层得到查询的结果数据集*/
        DataSet ds = new DataSet();
        ds = (new BooksLogic()).QueryBooksInfo(bookName, bookTypeId, author);
        /*将查询结果集绑定到gridview控件上*/
        this.GridView1.DataSourceID = null;
        this.GridView1.DataSource = ds;
        this.GridView1.PageIndex = e.NewPageIndex; ;
        this.GridView1.DataBind();
    }
    protected void GridView1_RowDataBound(object sender, GridViewRowEventArgs e)
    {
        if (e.Row.RowType == DataControlRowType.DataRow)
        {
            //当鼠标选择某行时变颜色
            e.Row.Attributes.Add("onmouseover", "c=this.style.backgroundColor;this.style.backgroundColor='#00ffee';");
            e.Row.Attributes.Add("onmouseout", "this.style.backgroundColor=c;");
            /*如果出版社的文字长度够长就剪切些*/
            string publishing = e.Row.Cells[3].Text;
            if (publishing.Length > 6)
            {
                e.Row.Cells[3].Text = publishing.Substring(0, 6) + "...";
            }
        }
    }
    protected void Btn_Query_Click(object sender, EventArgs e)
    {
        /*取得查询的各个参数*/
        string bookName = this.BookName.Text;
        int bookTypeId = Int32.Parse(this.BookType.SelectedValue);
        string author = this.Author.Text;
        /*调用业务层进行查询*/
        this.GridView1.DataSourceID = "";
        this.GridView1.DataSource = (new BooksLogic()).QueryBooksInfo(bookName, bookTypeId, author);
        this.GridView1.DataBind();
    }
}
