<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.kingns.config.*"%>
<%@ page import="com.kingns.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

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
			String service=KingnsConfig.service;
			
			String payment_type=KingnsConfig.payment_type;
			

			///////////////////////////////////////////////////////////////////////////////////
			
			//以下参数是需要通过下单时的订单数据传入进来获得
			//必填参数
			//请与贵网站订单系统中的唯一订单号匹配
			        String order_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			        
			        //订单名称，显示在互付收银台里的“商品名称”里，显示在互付的交易管理的“商品名称”的列表里。
			        String title =new String(request.getParameter("subject").getBytes("ISO-8859-1"),"GBK");
			        
			         //String title =request.getParameter("subject");
			        
			        //订单描述、订单详细、订单备注，显示在互付收银台里的“商品描述”里
			        String body =new String(request.getParameter("body").getBytes("ISO-8859-1"),"GBK");
			        
			         //String body =request.getParameter("body");
			        
			        System.out.println(body);
			        
			        //付款企业
			         String payerName =new String(request.getParameter("payerName").getBytes("ISO-8859-1"),"GBK");
			        
			        //订单总金额，显示在互付收银台里的“应付总额”里
			        String total_fee = request.getParameter("Rongmoney");

			        //默认买家互付账号
			        String buyer_email = "";
			        
			        String royalty_parameters = "";
			        
			        //支付方式
			        String paymethod="directPay";

					//网银代码
			        String defaultbank=request.getParameter("defaultbank");
			        
			          //是否分账
			       // String royalty_type=request.getParameter("royalty_type");
			        String royalty_type="No";
			        if(!"No".equals(royalty_type)){
			        	royalty_type="12";
			            royalty_parameters=new String(request.getParameter("royalty_parameters").getBytes("ISO-8859-1"),"GBK");
			        }
					String isApp = request.getParameter("isApp");
			        /////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////构造函数，生成请求URL
					String sHtmlText="";
					if(isApp!=null && !"".equals(isApp)){
						System.out.println("isApp:"+isApp);
						if("h5".equals(isApp) || "web".equals(isApp)){
							System.out.println("isApp:11");
							sHtmlText = KingnsService.BuildForm(service,payment_type,merchant_ID,seller_email,return_url,notify_url,order_no,
									title,body,total_fee,buyer_email,charset,paymethod,defaultbank,key,sign_type,payerName,royalty_type,royalty_parameters,isApp);	
						}else{
							sHtmlText = KingnsService.BuildFormApp(service,payment_type,merchant_ID,seller_email,return_url,notify_url,order_no,
									title,body,total_fee,buyer_email,charset,paymethod,defaultbank,key,sign_type,payerName,royalty_type,royalty_parameters,isApp);
						}
					}
			        
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
