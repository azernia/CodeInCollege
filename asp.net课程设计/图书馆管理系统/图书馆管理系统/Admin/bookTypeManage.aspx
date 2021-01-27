<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookTypeManage.aspx.cs" Inherits="Admin_bookTypeManage" %>

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
          <img src="../images/list.gif" width=14px height=14px>图书信息管理--&gt;图书类型信息</td>
        </tr>
        <tr>
          <td style="height: 37px" align="center">
              <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" BackColor="#DEBA84"
                  BorderColor="#DEBA84" BorderStyle="None" BorderWidth="1px" CellPadding="3" CellSpacing="2"
                  DataKeyNames="bookTypeId" DataSourceID="BookTypeDataSource" Width="373px">
                  <FooterStyle BackColor="#F7DFB5" ForeColor="#8C4510" />
                  <Columns>
                      <asp:BoundField DataField="bookTypeId" HeaderText="类别编号" InsertVisible="False" ReadOnly="True"
                          SortExpression="bookTypeId" />
                      <asp:BoundField DataField="bookTypeName" HeaderText="类别名称" SortExpression="bookTypeName" />
                      <asp:TemplateField HeaderText="操作">
                          <ItemTemplate>
                            <a href='bookTypeEdit.aspx?bookTypeId=<%# Eval("bookTypeId") %>'><img src="../images/edit.gif" width=12px height=12px border=0px>修改</a>
                            &nbsp;&nbsp;
                            <a href='bookTypeDel.aspx?bookTypeId=<%# Eval("bookTypeId") %>'><img src="../images/delete.gif" width=12px height=12px border=0px />删除</a>
                          </ItemTemplate>
                      </asp:TemplateField>
                  </Columns>
                  <RowStyle BackColor="#FFF7E7" ForeColor="#8C4510" />
                  <SelectedRowStyle BackColor="#738A9C" Font-Bold="True" ForeColor="White" />
                  <PagerStyle ForeColor="#8C4510" HorizontalAlign="Center" />
                  <HeaderStyle BackColor="#A55129" Font-Bold="True" ForeColor="White" />
              </asp:GridView>
              <asp:SqlDataSource ID="BookTypeDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:LibraryConnectionString %>"
                  SelectCommand="SELECT [bookTypeName], [bookTypeId] FROM [bookType_130610]"></asp:SqlDataSource>
       
        </td>
      </tr>
    </table>
  </form>
</body>
</html>