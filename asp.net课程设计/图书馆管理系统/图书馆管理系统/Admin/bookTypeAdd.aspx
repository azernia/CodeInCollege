﻿<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookTypeAdd.aspx.cs" Inherits="Admin_bookTypeAdd" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
   <form method="post" id="frmAnnounce" runat="server">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px" align="center">
          <img src="../images/ADD.gif" width=14px height=14px>图书类别管理--&gt;图书类别添加</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          请输入图书类别名称：<asp:TextBox ID="BookTypeName" runat="server" Width="191px"></asp:TextBox>
        </tr>
          <tr>
              <td style="height: 26px" align="center">
                  <asp:Button ID="Btn_Add" runat="server" OnClick="Btn_Add_Click" Text="添加" />&nbsp;
                  <asp:Button ID="Btn_Cancle" runat="server" OnClick="Btn_Cancle_Click" Text="取消" /></td>
          </tr>
         
      </table>
    </form>
</body>
</html>