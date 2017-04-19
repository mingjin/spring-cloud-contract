<%@ page import="com.ifpay.service.PaymentService" %>
<%@ page import="com.ifpay.utils.KeyUtils" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: harvey.xu
  Date: 2017/3/19
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");

    String key = new KeyUtils().getKey(request.getParameter("key"), request.getParameter("merchantId"));

    Map reqMap = new HashMap();
    reqMap.put("requestUrl", request.getParameter("requestUrl"));
    reqMap.put("service", request.getParameter("service"));
    reqMap.put("payment_type",request.getParameter("paymentType"));
    reqMap.put("merchant_ID", request.getParameter("merchantId"));
    reqMap.put("seller_email", request.getParameter("sellerEmail"));
    reqMap.put("return_url", request.getParameter("returnUrl"));
    reqMap.put("notify_url", request.getParameter("notifyUrl"));
    reqMap.put("charset", request.getParameter("charset"));
    reqMap.put("order_no", request.getParameter("orderNo"));
    reqMap.put("title", request.getParameter("productName"));
    reqMap.put("body", request.getParameter("productDesc"));
    reqMap.put("total_fee", request.getParameter("tradeAmount"));
    reqMap.put("buyer_email", request.getParameter("buyerEmail"));
    reqMap.put("paymethod", request.getParameter("payMethod"));
    reqMap.put("defaultbank", request.getParameter("defaultBank"));
    reqMap.put("payerName", request.getParameter("payerName"));
    reqMap.put("sign_type", request.getParameter("signType"));
    reqMap.put("isApp", request.getParameter("isApp"));
    reqMap.put("appName", request.getParameter("appName"));
    reqMap.put("appMsg", request.getParameter("appMsg"));
    reqMap.put("appType", request.getParameter("appType"));
    reqMap.put("userIp", request.getParameter("userIp"));
    reqMap.put("backUrl", request.getParameter("backUrl"));

    ////构造函数，生成请求URL
    String sHtmlText = "";
    if(reqMap.get("isApp") != null && !"".equals(reqMap.get("isApp"))){
        System.out.println("isApp:" + reqMap.get("isApp"));
        if("web".equals(reqMap.get("isApp"))){
            sHtmlText = PaymentService.BuildWebForm(reqMap, key);
            System.out.println("web****1*****url:" + sHtmlText);
        } else if("h5".equals(reqMap.get("isApp"))){
            sHtmlText = PaymentService.BuildH5Form(reqMap, key);
            System.out.println("h5*****2****url:" + sHtmlText);
        } else if ("app".equals(reqMap.get("isApp"))){
            sHtmlText = PaymentService.BuildAppForm(reqMap, key);
            System.out.println("app****3*****url:" + sHtmlText);
        }
    }

%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="GBK">
    <title>互付付款</title>
</head>
<body>
<table align="center" width="350" cellpadding="5" cellspacing="0">
    <tr>
        <td align="center" class="font_title" colspan="2">订单确认</td>
    </tr>
    <tr>
        <td class="font_content" align="right">订单号：</td>
        <td class="font_content" align="left"><%=request.getParameter("orderNo")%></td>
    </tr>
    <tr>
        <td class="font_content" align="right">付款总金额：</td>
        <td class="font_content" align="left"><%=request.getParameter("tradeAmount")%></td>
    </tr>
    <tr>
        <td align="center" colspan="2"><%=sHtmlText%></td>
    </tr>
</table>
</body>
</html>
