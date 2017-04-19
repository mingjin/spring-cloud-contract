<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.ifpay.service.PaymentService" %>
<%@ page import="com.ifpay.utils.KeyUtils" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="GBK">
		<title>网银直连支付交易</title>
		<style type="text/css">
.font_content{
	font-family:"宋体";
	font-size:14px;
	color:#FF6600;
}
.font_title{
	font-family:"宋体";
	font-size:16px;
	color:#FF0000;
	font-weight:bold;
}
table{
	border: 1px solid #CCCCCC;
}
		</style>
	</head>
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
		reqMap.put("currency", request.getParameter("currency"));
		reqMap.put("notify_url", request.getParameter("notifyUrl"));
		reqMap.put("charset", request.getParameter("charset"));
		reqMap.put("order_no", request.getParameter("orderNo"));
		reqMap.put("title", request.getParameter("productName"));
		reqMap.put("body", request.getParameter("productDesc"));
		reqMap.put("total_fee", request.getParameter("tradeAmount"));
		reqMap.put("paymethod", request.getParameter("paymethod"));
		reqMap.put("sign_type", request.getParameter("signType"));
		reqMap.put("defaultbank", request.getParameter("defaultBank"));
		reqMap.put("isApp", request.getParameter("isApp"));

		////构造函数，生成请求URL
		String sHtmlText = PaymentService.BuildEBankForm(reqMap, key);
		System.out.println("sHtmlText = " + sHtmlText);
	%>

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
