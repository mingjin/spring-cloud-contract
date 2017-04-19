<%@ page import="com.vip.vpal.utils.HttpRequest" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: harvey.xu
  Date: 2015/11/9
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");
    Map<String, String> parmas = new HashMap<String, String>();
    parmas.put("txId", request.getParameter("txId"));
    String result = HttpRequest.sendGet(request.getParameter("requestUrl"), parmas);


    out.println("请求数据：" + request.getParameter("requestUrl") + "?" + "txId=" + parmas.get("txId"));
    out.println("<br>");
    out.println("<br>");
    out.println("——————————————————————————————————————————————————————————————————————————————————————————");
    out.println("<br>");
    out.println(result.contains("resultCode\":\"00\"") == true ? "成功了！" : "失败了！");
    out.println("<br>");
    out.println("服务端返回：" + result);
%>

<html>
<head>
    <title>获取商户通知参数</title>

</head>
</html>

