<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.math.BigDecimal"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>��������</title>
		<style type="text/css">
.font_content{
	font-family:"����";
	font-size:14px;
	color:#FF6600;
}
.font_title{
	font-family:"����";
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
		//KingnsConfig.java��������Ϣ���������޸ģ�
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
			//���²�������Ҫͨ���µ�ʱ�Ķ������ݴ���������
			//�������
			//�������վ����ϵͳ�е�Ψһ������ƥ��
		        String order_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		        //�������ƣ���ʾ�ڻ�������̨��ġ���Ʒ���ơ����ʾ�ڻ����Ľ��׹���ġ���Ʒ���ơ����б��
		        String title =new String(request.getParameter("subject").getBytes("ISO-8859-1"),"GBK");
		        //����������������ϸ��������ע����ʾ�ڻ�������̨��ġ���Ʒ��������
		        String body =new String(request.getParameter("body").getBytes("ISO-8859-1"),"GBK");
		        System.out.println(body);
		        //�����ܽ���ʾ�ڻ�������̨��ġ�Ӧ���ܶ��
		        String total_fee = request.getParameter("money");
		        //֧����ʽ
		        String paymethod= request.getParameter("paymethod");
		        //��������
		        String defaultbank=request.getParameter("default_bank");		  
		        /////////////////////////////////////////////////////////////////////////////////////////////////////
			////���캯������������URL
			        String sHtmlText = KingnsService.BuildFormBank(service,payment_type,merchant_ID,seller_email,return_url,notify_url,order_no,
		title,body,charset,paymethod,defaultbank,key,sign_type,total_fee);
	%>

	<body>
        <table align="center" width="350" cellpadding="5" cellspacing="0">
            <tr>
                <td align="center" class="font_title" colspan="2">����ȷ��</td>
            </tr>
            <tr>
                <td class="font_content" align="right">�����ţ�</td>
                <td class="font_content" align="left"><%=order_no%></td>
            </tr>
            <tr>
                <td class="font_content" align="right">�����ܽ�</td>
                <td class="font_content" align="left"><%=total_fee%></td>
            </tr>
            <tr>
                <td align="center" colspan="2"><%=sHtmlText%></td>
            </tr>
        </table>
	</body>
</html>
