<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.kingns.config.*"%>
<%@ page import="com.kingns.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta name="description" content="Ë«³Ç¸¶" />
	  	<meta name="keywords" content="Ë«³Ç¸¶" />
		<meta name="author" content="reapal" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="apple-mobile-web-app-title" content="Ë«³Ç¸¶"/>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta content="telephone=no" name="format-detection" />
		<title>Ë«³Ç¸¶</title>
		<link rel="apple-touch-icon-precomposed" href="/apple-icon-114.png" />
	  	<link rel="apple-touch-startup-image" href="/apple-startup.png" />	
		<script type="text/javascript" src="common/js/html5/jquery.min.js"></script>
		<script type="text/javascript" src="common/js/html5/base.js"></script>
		<script type="text/javascript" src="common/js/html5/cashier.js"></script>
		<script type="text/javascript" src="common/js/html5/keyboard.js"></script>
		
	</head>
    <style type="text/css">
        *{padding: 0; margin: 0;  font-family: "ËÎÌå";text-align: center;}
        .foot{ font-size: 14px; text-align: center;}
        .foot span{ width:16px; height: 16px; background: url("images/error.png")no-repeat; background-size: 14px 14px;display: inline-block; vertical-align: middle; }
    </style>
	
	<%
			String phone = request.getParameter("phone");
			String signKey = Util_3DESCoder.encrypt3DESAndEncodeBase64(phone.getBytes("UTF-8"),RongpayConfig.signKey);
			System.out.println("phone:::"+phone);
	        String sHtmlText = RongpayService.payToScan(phone,signKey);
	%>

	<body>  
	   
        <div class="w">
		  <section class="cashier c">
		  	<%=sHtmlText%>
		 	</section>
		 </div>
	</body>
</html>
