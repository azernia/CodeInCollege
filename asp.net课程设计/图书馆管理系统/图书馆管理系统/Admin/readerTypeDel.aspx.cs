﻿using System;
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

public partial class Admin_readerTypeDel : System.Web.UI.Page
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
        /*读者类别编号*/
        int readerTypeId = Int32.Parse(Request.QueryString["readerTypeId"]);
        ReaderTypeLogic readerTypeLogic = new ReaderTypeLogic();
        if (!readerTypeLogic.DeleteReaderType(readerTypeId))
        {
            Response.Write("<script>alert('" + readerTypeLogic.ErrMessage + "');</script>");
        }
        else
        {
            Response.Write("<script>alert('读者类别信息删除成功!');location.href='readerTypeManage.aspx';</script>");
        }

    }
}
