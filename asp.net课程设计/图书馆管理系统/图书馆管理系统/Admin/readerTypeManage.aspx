<%@ Page Language="C#" AutoEventWireup="true" CodeFile="readerTypeManage.aspx.cs" Inherits="Admin_readerTypeManage" %>

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
          <img src="../images/list.gif" width=14px height=14px>读者信息管理--&gt;读者类型信息</td>
        </tr>
        <tr>
          <td style="height: 37px" align="center">
              &nbsp;&nbsp;<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False"
                  DataKeyNames="readerTypeId" DataSourceID="ReaderTypeDataSource" Width="559px" BackColor="#CCCCCC" BorderColor="#999999" BorderStyle="Solid" BorderWidth="3px" CellPadding="4" CellSpacing="2" ForeColor="Black">
                  <Columns>
                      <asp:BoundField DataField="readerTypeName" HeaderText="读者类别" SortExpression="readerTypeName" />
                      <asp:BoundField DataField="loanDays" HeaderText="借用天数" SortExpression="loanDays" />
                      <asp:BoundField DataField="continueDays" HeaderText="续借天数" SortExpression="continueDays" />
                      <asp:TemplateField HeaderText="操作">
                          <ItemTemplate>
                            <a href='readerTypeEdit.aspx?readerTypeId=<%# Eval("readerTypeId") %>'><img src="../images/edit.gif" width=12px height=12px border=0px>修改</a>
                            &nbsp;&nbsp;
                            <a href='readerTypeDel.aspx?readerTypeId=<%# Eval("readerTypeId") %>'><img src="../images/delete.gif" width=12px height=12px border=0px />删除</a>
                          </ItemTemplate>
                      </asp:TemplateField>
                  </Columns>
                  <FooterStyle BackColor="#CCCCCC" />
                  <RowStyle BackColor="White" />
                  <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                  <PagerStyle BackColor="#CCCCCC" ForeColor="Black" HorizontalAlign="Left" />
                  <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
              </asp:GridView>
              &nbsp;
       
        </td>
      </tr>
    </table>
        <asp:SqlDataSource ID="ReaderTypeDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:LibraryConnectionString %>"
            SelectCommand="SELECT * FROM [readerType_130610]"></asp:SqlDataSource>
  </form>
</body>
</html>