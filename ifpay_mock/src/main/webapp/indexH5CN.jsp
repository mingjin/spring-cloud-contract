<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head> 
	<meta name="description" content="˫�Ǹ�" />
  	<meta name="keywords" content="˫�Ǹ�" />
	<meta name="author" content="reapal" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name="apple-mobile-web-app-title" content="˫�Ǹ�"/>
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta content="telephone=no" name="format-detection" />
	<title>˫�Ǹ�</title>
	<link rel="apple-touch-icon-precomposed" href="/apple-icon-114.png" />
  	<link rel="apple-touch-startup-image" href="/apple-startup.png" />	
	<link type="text/css" rel="stylesheet" href="common/css/html5/base.css" media="all" />
	<link type="text/css" rel="stylesheet" href="common/css/html5/common.css" media="all" />
	<script type="text/javascript" src="common/js/html5/jquery.min.js"></script>
	<script type="text/javascript" src="common/js/html5/base.js"></script>
	<script type="text/javascript" src="common/js/html5/cashier.js"></script>
</head>
<body>
<div class="w">
	  <header id="top" class="headerred">
	    <h2>˫�Ǹ�</h2>
	    <a class="back" onclick="window.history.back()">����</a>
	  </header>
	
  	  <section class="cashier c">
	    <form name=rongpayment onSubmit="return CheckForm();" action=scanByCustomer.jsp method=post target="_blank">
	   	 	<p class="t">
	        </p>
	        <p class="t">
	        </p>
	        <p class="t">
	        </p>
	        <p class="t">
	        </p>
	        <p class="t">
	            <label for="cardNo">�̻���</label>
	            <input type="tef" class="input" style = "margin-left: 0px;" name="merchant_ID" id="merchant_ID" />
	            <label style="margin-left: 94%;"></label>
	        </p>
	        <p class="t">
	        </p>
	        <input type="submit" class="button_p2p" value="ȷ�ϸ���"/>
	    </form>
 	   </section>
	<footer>
		<div class="copyright">Copyright &copy; 2016 ˫�Ǹ�</div>	
	</footer>
</div>
</body>
</html>

