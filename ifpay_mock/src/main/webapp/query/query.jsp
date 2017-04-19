<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kingns.config.*" %>
<%@ page import="com.kingns.util.*" %>
<%
String merchant_ID = KingnsConfig.merchant_ID;
String key=KingnsConfig.key;
String charset=KingnsConfig.charset;
String sign_type=KingnsConfig.sign_type;

String order_no=request.getParameter("order_no");
String trade_no=request.getParameter("trade_no");

HashMap params=new HashMap();
params.put("merchant_ID",merchant_ID);
params.put("charset",charset);
params.put("order_no",order_no);
params.put("trade_no",trade_no);

String sign=KingnsFunction.BuildMysign(params,key);
String url="http://mapi.payworth.net/query/payment?";
String paramstr=KingnsFunction.CreateLinkString(params).append("&sign=").append(sign).append("&sign_type=").append(sign_type).toString();

HashMap returnxml=KingnsFunction.GetMessage(url+paramstr);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  
  <body>
  	<div align="center">
    <table align="center">
    	<%
    		if(returnxml!=null && returnxml.size()>0){
    	 %>
    	<tr><td colspan="2" align="center"><h2></h2></td></tr>
    	<tr><td>is_success</td><td><%=returnxml.get("is_success") %></td></tr>
    	<tr><td>result_code</td><td><%=returnxml.get("result_code") %></td></tr>
    	<tr><td>timestamp</td><td><%=returnxml.get("timestamp") %></td></tr>
    	<%
    		HashMap trade=(HashMap)returnxml.get("trade");
    		if(trade!=null && trade.size()>0){
    	%>
    	<tr><td>trade_no</td><td><%=trade.get("trade_no") %></td></tr>
    	<tr><td>order_no</td><td><%=trade.get("order_no") %></td></tr>
    	<tr><td>trade_type</td><td><%=trade.get("trade_type") %></td></tr>
    	<tr><td>amount/td><td><%=trade.get("amount") %></td></tr>
    	<tr><td>fee_amount</td><td><%=trade.get("fee_amount") %></td></tr>
    	<tr><td>subject</td><td><%=trade.get("subject") %></td></tr>
    	<tr><td>trade_date</td><td><%=trade.get("trade_date") %></td></tr>
    	<tr><td>created_time</td><td><%=trade.get("created_time") %></td></tr>
    	<tr><td>status/td><td><%=trade.get("status") %></td></tr>
    	<%
    		}
    		}else{
    			out.println("<h1>111111</h1>");
    		}
    	 %>
    </table>
    </div>
  </body>
</html>
