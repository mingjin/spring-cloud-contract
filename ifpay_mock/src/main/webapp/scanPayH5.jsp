<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.kingns.config.*"%>
<%@ page import="com.kingns.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta name="description" content="" />
	  	<meta name="keywords" content="" />
		<meta name="author" content="reapal" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="apple-mobile-web-app-title" content=""/>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta content="telephone=no" name="format-detection" />
		<title></title>
		<link rel="apple-touch-icon-precomposed" href="/apple-icon-114.png" />
	  	<link rel="apple-touch-startup-image" href="/apple-startup.png" />	
		<script type="text/javascript" src="common/js/html5/jquery.min.js"></script>
		<script type="text/javascript" src="common/js/html5/base.js"></script>
		<script type="text/javascript" src="common/js/html5/cashier.js"></script>
		<script type="text/javascript">
			function doSubmit(){
				document.forms["rysubmit"].submit();
			}
		</script>
		
		
	</head>
    <style type="text/css">
        *{padding: 0; margin: 0;  font-family: "����";}
        .header {  width: 100%;  height: 50px;  line-height: 50px;  text-align: center; background: #333;}
        .header h2{  font-size: 18px; color: #fff;  font-weight: normal; font-family: "����"; }
        .header .back {  width: 50px; height: 50px; background:url("images/icon_back.png")no-repeat 10px 10px; position: absolute; left: 0; top: 0; background-size: 26px 30px }
        .avatar {margin:10% auto; width:50%;overflow: hidden;text-align: center; }
        .avatar span { border-radius: 100%;  display: block; padding-top: 50px; width: 50px; background: url(images/PIC.jpg) no-repeat; background-size: 50px 50px; margin: 0 auto;}
        .avatar p{ margin-top: 5%; font-size: 16px;}
        .paybox{ margin: 20px;}
        .paybox p{ position: relative;  padding: 5px 30px 5px 10px;  font-size: 14px;  border: 1px solid #e3e3e3;  height: 30px;}
        .paybox input {  border: none;  outline: none;  width: 100%;  height: 30px;  line-height: 30px;  color: #707070;  font-size: 16px;  border-radius: 0px;  overflow: hidden;  }
        .paybox label {  font-size: 16px;  position: absolute;  top: 10px;  font-weight: bold;}
        .foot{ font-size: 14px; text-align: center;}
        .foot span{ width:16px; height: 16px; background: url("images/error.png")no-repeat; background-size: 14px 14px;display: inline-block; vertical-align: middle; }
    </style>
	
	<%
		//RongpayConfig.java��������Ϣ���������޸ģ�
			String charset = RongpayConfig.charset;
			String sign_type = RongpayConfig.sign_type;
			String seller_email = "";
			String notify_url = "";//RongpayConfig.notify_url;
			String return_url = "";//RongpayConfig.return_urlH5;
			String service = "scanpay";
			
			

			///////////////////////////////////////////////////////////////////////////////////
			
			//���²�������Ҫͨ���µ�ʱ�Ķ������ݴ���������
			//�������
			//�������վ����ϵͳ�е�Ψһ������ƥ��
			        String order_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			        
			        //�������ƣ���ʾ��˫�Ǹ�����̨��ġ���Ʒ���ơ����ʾ��˫�Ǹ��Ľ��׹���ġ���Ʒ���ơ����б��
			        String title =new String("goods");
			        
			        //����������������ϸ��������ע����ʾ��˫�Ǹ�����̨��ġ���Ʒ��������
			        String body =new String("goods");
			        System.out.println(body);
			        
			        String Agent = request.getHeader("User-Agent").toLowerCase();  
			        System.out.println(Agent);
					if (Agent.contains("micromessenger")) {// ��΢�������
				       service = "scanpay_wx";
				     }else if(Agent.contains("alipayclient")){
				     	service = "scanpay_ali";
				     }
					System.out.println("service"+service);
					
			        String fomat = "";
			        
		       
		             seller_email = RongpayConfig.seller_email;
			        
			        String payment_type="3";
			        
			        //Ĭ�����˫�Ǹ��˺�
			        String buyer_email = "";
			        
			        //֧����ʽ
			        String paymethod="directPay";
			        
			        //��������
			        String defaultbank="ALIPAY";
			        String pay_cus_no=request.getParameter("pay_cus_no");
			        String isApp="h5";
			        String customerNo = request.getParameter("customerNo");
			        String sign = request.getParameter("sign");
			        String customerNo_encrypt = request.getParameter("customerNo_encrypt");
			       
					System.out.println("defaultbank:::"+defaultbank);
			        /////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////���캯������������URL
			        String sHtmlText = RongpayService.ScanForm(service,payment_type,seller_email,return_url,notify_url,
			        order_no,title,body,buyer_email,charset,
			        paymethod,defaultbank,sign_type,pay_cus_no,fomat,isApp,customerNo,sign,customerNo_encrypt);
	%>

	<body onload="javascript:doSubmit()">  
	   
        <div class="w">
		  <section class="cashier c">
		  	
		     <div class="row-p">
			     <span>
				     <%=sHtmlText%>
			     </span>
		     </div>
		 	</section>
		 </div>
	</body>
</html>
