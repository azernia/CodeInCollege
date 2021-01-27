<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookBorrowInfo.aspx.cs" Inherits="Reader_bookBorrowInfo" %>

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
	  <td style="height: 15px" align="center" colspan=8>
          <img src="../images/ADD.gif" width=14px height=14px>图书借阅管理--&gt;图书借阅信息</td>
        </tr>
        <tr>
          <td style="height: 14px">图书编号</td><td style="height: 14px">图书名称</td>
          <td style="height: 14px">借阅时间</td>
          <td style="height: 14px">状态操作</td>
        </tr>
         <asp:Literal ID="Result" runat="server"></asp:Literal>
      </table>
      
    </form>
</body>
</html>
