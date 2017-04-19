<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kingns.config.*" %>
<%@ page import="com.kingns.util.*" %>
<%
String merchant_ID = KingnsConfig.merchant_ID;
String key=KingnsConfig.key;
String charset=KingnsConfig.charset;
String sign_type=KingnsConfig.sign_type;

String orig_order_no=request.getParameter("orig_order_no");
String order_no=request.getParameter("order_no");
String amount=request.getParameter("amount");
String note=request.getParameter("note");


HashMap params=new HashMap();
params.put("merchant_ID",merchant_ID);
params.put("charset",charset);
params.put("orig_order_no",orig_order_no);
params.put("order_no",order_no);
params.put("amount",amount);
params.put("note",note);

String sign=KingnsFunction.BuildMysign(params,key);
String url="http://interface.kingns.com/service/refund?";
String paramstr=KingnsFunction.CreateLinkString(params).append("&sign=").append(sign).append("&sign_type=").append(sign_type).toString();

HashMap returnxml=KingnsFunction.GetMessage(url+paramstr);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>退款返回信息</title>
  </head>
  
  <body>
  	<div align="center">
    <table align="center">
    	<%
    		if(returnxml!=null && returnxml.size()>0){
    	 %>
    	<tr><td colspan="2" align="center"><h2>退款返回</h2></td></tr>
    	<tr><td>是否成功：</td><td><%=returnxml.get("is_success") %></td></tr>
    	<tr><td>错误代码：</td><td><%=returnxml.get("result_code") %></td></tr>
    	<tr><td>时间戳：</td><td><%=returnxml.get("timestamp") %></td></tr>
    	<%
    		}else{
    			out.println("<h1>获取数据失败</h1>");
    		}
    	 %>
    </table>
    </div>
  </body>
</html>