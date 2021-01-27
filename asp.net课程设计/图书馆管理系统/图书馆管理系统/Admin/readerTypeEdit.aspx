<%@ Page Language="C#" AutoEventWireup="true" CodeFile="readerTypeEdit.aspx.cs" Inherits="Admin_readerTypeEdit" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
   <form method="post" id="frmAnnounce" runat="server">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px" align="center">
          <img src="../images/ADD.gif" width=14px height=14px>读者信息管理--&gt;读者类别信息修改</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者类别名称：<asp:TextBox ID="ReaderTypeName" runat="server" Width="191px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          借用数目：<asp:TextBox ID="LoanNum" runat="server" Width="78px"></asp:TextBox>
            天</tr>
        <tr>
	  <td style="height: 26px">
          借用天数：<asp:TextBox ID="LoanDays" runat="server" Width="78px"></asp:TextBox>
            天</tr>
        <tr>
	  <td style="height: 26px">
          续借天数：<asp:TextBox ID="ContinueDays" runat="server" Width="75px"></asp:TextBox>
            天</tr>
          <tr>
              <td style="height: 26px" align="center">
                  <asp:Button ID="Btn_Edit" runat="server" OnClick="Btn_Edit_Click" Text="更改" />&nbsp;
                  <asp:Button ID="Btn_Cancle" runat="server" OnClick="Btn_Cancle_Click" Text="取消" /></td>
          </tr>
         
      </table>
    </form>
</body>
</html>