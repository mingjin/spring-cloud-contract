<%
/* *
 ���ܣ���������ת��ҳ�棨ҳ����תͬ��֪ͨҳ�棩
 �汾��1.0
 ���ڣ�2012-05-01
 ˵����
 ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 �ô������ѧϰ���о������ӿ�ʹ�ã�ֻ���ṩһ���ο���

 //***********ҳ�湦��˵��***********
 ��ҳ����ڱ������Բ���
 ��ҳ�������ҳ����תͬ��֪ͨҳ�桱�����ɻ���������ͬ�����ã��ɵ�����֧����ɺ����ʾ��Ϣҳ���硰����ĳĳĳ���������ٽ����֧���ɹ�����
 �ɷ���HTML������ҳ��Ĵ���Ͷ���������ɺ�����ݿ���³������
 TRADE_FINISHED(��ʾ����Ѿ�ȷ���ջ�����ʽ������);
 
  *************ע��******************
 

 ��ʱ���ʵĽ���״̬�仯˳���ǣ��ȴ���Ҹ�����������
 
 ÿ���յ���������֪ͨ�У��Ϳ��Ի�ȡ����ʽ��׵Ľ���״̬�������̻���Ҫ�����̻������Ų�ѯ�̻���վ�Ķ������ݣ�
 �õ���ʶ������̻���վ�е�״̬��ʲô�����̻���վ�еĶ���״̬��ӻ���֪ͨ�л�ȡ����״̬�����Աȡ�
 
 **********************************
 
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ifpay.service.PaymentFunction" %>
<%@ page import="com.ifpay.utils.KeyUtils" %>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>֧���ɹ��ͻ��˷���</title>
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
  <body>
<%
	request.setCharacterEncoding("utf-8");

    //��ȡ����GET����������Ϣ
    Map params = PaymentFunction.transformRequestMap(request.getParameterMap());
    String key = new KeyUtils().getKey("", params.get("seller_id").toString());

    String verifyUrl = null;
    if (params.get("buyer_email").toString().equals("1")) {
        verifyUrl = "http://114.215.202.134:18160/verify/notify?";
    } else if (params.get("buyer_email").toString().equals("2")) {
        verifyUrl = "http://114.215.242.9:18160/verify/notify?";
    } else if (params.get("buyer_email").toString().equals("3")){
        verifyUrl = "http://***.***.***.***/verify/notify?";
    } else if (params.get("buyer_email").toString().equals("4")){
        verifyUrl = "http://mapi.payworth.net/verify/notify?";
    }

	//�ж�responsetTxt�Ƿ�Ϊture�����ɵ�ǩ�����mysign���õ�ǩ�����sign�Ƿ�һ��
	//responsetTxt�Ľ������true����������������⡢���������ID��notify_idһ����ʧЧ�й�
	//mysign��sign���ȣ��밲ȫУ���롢����ʱ�Ĳ�����ʽ���磺���Զ�������ȣ��������ʽ�й�
	String mysign = PaymentFunction.BuildMysign(params, key);

	String responseTxt = PaymentFunction.Verify(verifyUrl, params.get("seller_id").toString(), request.getParameter("notify_id"));
	String sign = request.getParameter("sign");


	//��ȡ������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
	String trade_no = request.getParameter("trade_no");				//�������׺�
	String order_no = request.getParameter("order_no");	        //��ȡ������
	String total_fee = request.getParameter("total_fee");	            //��ȡ�ܽ��
	String title = request.getParameter("title");				//��Ʒ���ơ���������
	String body = request.getParameter("body");
//	String buyer_email = request.getParameter("buyer_email");		//��һ����˺�
	String trade_status = request.getParameter("trade_status");		//����״̬
	//��ȡ������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���Ͻ����ο�)//
	String verifyStatus;
	
	//����У��responseTxt���ж��÷��ؽ���Ƿ����ڱ�����
	if (mysign.equals(sign) && responseTxt.equals("true")){
		
		//�������������ҵ���߼�����д�������´�������ο�������	
		if (trade_status.equals("TRADE_FINISHED")) {
		//֧���ɹ������û�������������ݶ����ţ�out_trade_no������total_fee�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
		//һ��Ҫ������жϣ���ֹ����ܸĽ�ֻ֧����С���Ķ�����
		//���������������ִ���̻���ҵ�����
		} else {
		
	//֧��ʧ�ܴ�����ض���
		}
		
		verifyStatus = "��֤�ɹ�";
		//�������������ҵ���߼�����д�������ϴ�������ο�������
		
	} else {
		verifyStatus = "��֤ʧ��";
	}
%>
<table align="center" width="350" cellpadding="5" cellspacing="0">
            <tr>
                <td align="center" class="font_title" colspan="2">֪ͨ����</td>
            </tr>
            <tr>
                <td class="font_content" align="right">�������׺ţ�</td>
                <td class="font_content" align="left"><%=trade_no %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">�����ţ�</td>
                <td class="font_content" align="left"><%=order_no %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">�����ܽ�</td>
                <td class="font_content" align="left"><%=total_fee %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">��Ʒ���⣺</td>
                <td class="font_content" align="left"><%=title %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">��Ʒ������</td>
                <td class="font_content" align="left"><%=body %></td>
            </tr>
           
            <tr>
                <td class="font_content" align="right">����״̬��</td>
                <td class="font_content" align="left"><%=trade_status %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">��֤״̬��</td>
                <td class="font_content" align="left"><%=verifyStatus %></td>
            </tr>
        </table>
  </body>
</html>
