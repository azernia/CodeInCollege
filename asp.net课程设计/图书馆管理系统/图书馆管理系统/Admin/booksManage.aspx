<%@ Page Language="C#" AutoEventWireup="true" CodeFile="booksManage.aspx.cs" Inherits="Admin_booksManage" %>

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
          <img src="../images/list.gif" width=14px height=14px>图书信息管理--&gt;图书信息</td>
        </tr>
          <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          图书名称:<asp:TextBox ID="BookName" runat="server" Width="87px"></asp:TextBox>
          所在类别:<asp:DropDownList ID="BookType" runat="server" Width="86px">
          </asp:DropDownList>
          作者:<asp:TextBox ID="Author" runat="server" Width="95px"></asp:TextBox>
          <asp:Button ID="Btn_Query" runat="server" Text="查询" OnClick="Btn_Query_Click" /></td>
        </tr>
        <tr>
          <td style="height: 37px" align="center">
              <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AutoGenerateColumns="False"
                  BackColor="#CCCCCC" BorderColor="#999999" BorderStyle="Solid" BorderWidth="3px"
                  CellPadding="4" CellSpacing="2" DataSourceID="BooksDataSource" ForeColor="Black"
                  Width="597px" OnPageIndexChanging="GridView1_PageIndexChanging" OnRowDataBound="GridView1_RowDataBound" PageSize="8">
                  <FooterStyle BackColor="#CCCCCC" />
                  <Columns>
                      <asp:BoundField DataField="bookId" HeaderText="图书编号" SortExpression="bookId" />
                      <asp:BoundField DataField="bookName" HeaderText="图书名称" SortExpression="bookName" />
                      <asp:BoundField DataField="bookTypeName" HeaderText="所在类别" SortExpression="bookTypeName" />
                      <asp:BoundField DataField="author" HeaderText="作者" SortExpression="author" />
                      <asp:BoundField DataField="publishing" HeaderText="出版社" SortExpression="publishing" />
                      <asp:BoundField DataField="bookNum" HeaderText="库存" SortExpression="bookNum" />
                      <asp:TemplateField HeaderText="操作">
                          <ItemTemplate>
                            <a href='bookEdit.aspx?bookId=<%# Eval("bookId") %>'><img src="../images/edit.gif" width=12px height=12px border=0px>详细</a>
                            &nbsp;&nbsp;
                            <a href='bookDel.aspx?bookId=<%# Eval("bookId") %>'><img src="../images/delete.gif" width=12px height=12px border=0px />删除</a>
                          </ItemTemplate>
                      </asp:TemplateField>
                  </Columns>
                  <RowStyle BackColor="White" />
                  <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                  <PagerStyle BackColor="#CCCCCC" ForeColor="Black" HorizontalAlign="Left" />
                  <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
              </asp:GridView>
              &nbsp;
              
              
        </td>
      </tr>
    </table>
        <asp:SqlDataSource ID="BooksDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:LibraryConnectionString %>"
            SelectCommand="SELECT [bookId], [bookName], [bookTypeName], [author], [publishing], [bookNum] FROM [readerView_130610]">
        </asp:SqlDataSource>
  </form>
</body>
</html>