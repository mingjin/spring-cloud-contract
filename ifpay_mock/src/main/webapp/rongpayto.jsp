<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.kingns.config.*"%>
<%@ page import="com.kingns.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

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
			String service=KingnsConfig.service;
			
			String payment_type=KingnsConfig.payment_type;
			

			///////////////////////////////////////////////////////////////////////////////////
			
			//���²�������Ҫͨ���µ�ʱ�Ķ������ݴ���������
			//�������
			//�������վ����ϵͳ�е�Ψһ������ƥ��
			        String order_no = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			        
			        //�������ƣ���ʾ�ڻ�������̨��ġ���Ʒ���ơ����ʾ�ڻ����Ľ��׹���ġ���Ʒ���ơ����б��
			        String title =new String(request.getParameter("subject").getBytes("ISO-8859-1"),"GBK");
			        
			         //String title =request.getParameter("subject");
			        
			        //����������������ϸ��������ע����ʾ�ڻ�������̨��ġ���Ʒ��������
			        String body =new String(request.getParameter("body").getBytes("ISO-8859-1"),"GBK");
			        
			         //String body =request.getParameter("body");
			        
			        System.out.println(body);
			        
			        //������ҵ
			         String payerName =new String(request.getParameter("payerName").getBytes("ISO-8859-1"),"GBK");
			        
			        //�����ܽ���ʾ�ڻ�������̨��ġ�Ӧ���ܶ��
			        String total_fee = request.getParameter("Rongmoney");

			        //Ĭ����һ����˺�
			        String buyer_email = "";
			        
			        String royalty_parameters = "";
			        
			        //֧����ʽ
			        String paymethod="directPay";

					//��������
			        String defaultbank=request.getParameter("defaultbank");
			        
			          //�Ƿ����
			       // String royalty_type=request.getParameter("royalty_type");
			        String royalty_type="No";
			        if(!"No".equals(royalty_type)){
			        	royalty_type="12";
			            royalty_parameters=new String(request.getParameter("royalty_parameters").getBytes("ISO-8859-1"),"GBK");
			        }
					String isApp = request.getParameter("isApp");
			        /////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////���캯������������URL
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
