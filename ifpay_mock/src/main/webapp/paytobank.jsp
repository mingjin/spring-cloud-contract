<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.math.BigDecimal"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>互付付款</title>
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
		//KingnsConfig.java中配置信息（不可以修改）
			String charset = KingnsConfig.charset;
			String sign_type = KingnsConfig.sign_type;
			String seller_email = KingnsConfig.seller_email;
			String merchant_ID=KingnsConfig.merchant_ID;
			String key = KingnsConfig.key;
			String notify_url = KingnsConfig.notify_url;
			String return_url = KingnsConfig.return_url;
			String currency = "156";
			String payment_type=KingnsConfig.payment_type;
			String service=KingnsConfig.service;
			
			///////////////////////////////////////////////////////////////////////////////////
			//以下参数是需要通过下单时的订单数据传入进来获得
			//必填参数
			//请与贵网站订单系统中的唯一订单号匹配
		        String order_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		        //订单名称，显示在互付收银台里的“商品名称”里，显示在互付的交易管理的“商品名称”的列表里。
		        String title =new String(request.getParameter("subject").getBytes("ISO-8859-1"),"GBK");
		        //订单描述、订单详细、订单备注，显示在互付收银台里的“商品描述”里
		        String body =new String(request.getParameter("body").getBytes("ISO-8859-1"),"GBK");
		        System.out.println(body);
		        //订单总金额，显示在互付收银台里的“应付总额”里
		        String total_fee = request.getParameter("money");
		        //支付方式
		        String paymethod= request.getParameter("paymethod");
		        //网银代码
		        String defaultbank=request.getParameter("default_bank");		  
		        /////////////////////////////////////////////////////////////////////////////////////////////////////
			////构造函数，生成请求URL
			        String sHtmlText = KingnsService.BuildFormBank(service,payment_type,merchant_ID,seller_email,return_url,notify_url,order_no,
		title,body,charset,paymethod,defaultbank,key,sign_type,total_fee);
	%>

	<body>
        <table align="center" width="350" cellpadding="5" cellspacing="0">
            <tr>
                <td align="center" class="font_title" colspan="2">订单确认</td>
            </tr>
            <tr>
                <td class="font_content" align="right">订单号：</td>
                <td class="font_content" align="left"><%=order_no%></td>
            </tr>
            <tr>
                <td class="font_content" align="right">付款总金额：</td>
                <td class="font_content" align="left"><%=total_fee%></td>
            </tr>
            <tr>
                <td align="center" colspan="2"><%=sHtmlText%></td>
            </tr>
        </table>
	</body>
</html>
