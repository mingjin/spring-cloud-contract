<%@ page language="java" pageEncoding="gbk"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�˿����</title>
  </head>
  
  <body>
    <div align="center">
    <form action="refund.jsp" method="post">
    <table align="center">
    	<tr><td colspan="2" align="center"><h2>�˿����</h2></td></tr>
    	<tr><td>ԭ�̻�������(*)��</td><td><input type="text" name="orig_order_no" value=""/></td></tr>
    	<tr><td>�˿����(*)��</td><td><input type="text" name="order_no" value=""/></td></tr>
    	<tr><td>�˿���(*)��</td><td><input type="text" name="amount" value=""/></td></tr>
    	<tr><td>�˿�˵����</td><td><input type="text" name="note" value=""/></td></tr>
    	<tr><td></td><td><input type="submit" value="�ύ"/></td></tr>		
    </table>
    </form>
    </div>
  </body>
</html>
