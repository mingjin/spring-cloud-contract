<%
/* *
 ���ܣ���������֪ͨ���õ�ҳ�棨�������첽֪ͨҳ�棩
 �汾��1.0
 ���ڣ�2012-05-01
 ˵����
 ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 �ô������ѧϰ���������ӿ�ʹ�ã�ֻ���ṩһ���ο���

 //***********ҳ�湦��˵��***********
 ������ҳ���ļ�ʱ�������ĸ�ҳ���ļ������κ�HTML���뼰�ո�
 ��ҳ�治���ڱ������Բ��ԣ��뵽�������������ԡ���ȷ�����������Է��ʸ�ҳ�档
 ��ҳ����Թ�����ʹ��д�ı�����log_result���ú����ѱ�Ĭ�Ͽ���
 TRADE_FINISHED(��ʾ����Ѿ�ȷ���ջ�����ʽ������);
 �÷������첽֪ͨҳ������Ҫ�����ǣ���ֹ����δ���¡����û���յ���ҳ���ӡ�� success ��Ϣ����������24Сʱ�ڰ�һ����ʱ������ط�֪ͨ
 
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
<%
	String key = KingnsConfig.key;
	request.setCharacterEncoding("GBK");

	System.out.println("�յ���Ϣ===============" + request.getParameter("orderId"));
	
	//��ȡ����POST����������Ϣ
	Map params = PaymentFunction.transformRequestMap(request.getParameterMap());
	
	//�ж�responsetTxt�Ƿ�Ϊture�����ɵ�ǩ�����mysign���õ�ǩ�����sign�Ƿ�һ��
	//responsetTxt�Ľ������true����������������⡢���������ID��notify_idһ����ʧЧ�й�
	//mysign��sign���ȣ��밲ȫУ���롢����ʱ�Ĳ�����ʽ���磺���Զ�������ȣ��������ʽ�й�
	String mysign = PaymentFunction.BuildMysign(params, key);

	String responseTxt = KingnsFunction.Verify(request.getParameter("notify_id"));
	String sign = request.getParameter("sign");
	
	//��ȡ������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)
	String trade_no = request.getParameter("trade_no");				//�������׺�
	String order_no = request.getParameter("order_no");	        //��ȡ������
	String total_fee = request.getParameter("total_fee");	        	//��ȡ�ܽ��
	String title=request.getParameter("title");					//��Ʒ���ơ���������
	if(title!=null && !"".equals(title)){
		title = new String(title.getBytes("ISO-8859-1"),"GBK");
	}

	String body = request.getParameter("body");//��Ʒ������������ע������
	if(body != null && !"".equals(body)){
		body = new String(body.getBytes("ISO-8859-1"), "GBK");
	}

	String trade_status = request.getParameter("trade_status");		//����״̬
	

	//����У��responseTxt���ж��÷��ؽ���Ƿ����ڱ�����
	if(mysign.equals(sign) && responseTxt.equals("true")){

		//�������������ҵ���߼�����д�������´�������ο�������
		if(trade_status.equals("TRADE_FINISHED")){
		
	//֧���ɹ������û�������������ݶ����ţ�out_trade_no������total_fee�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
	//һ��Ҫ������жϣ���ֹ����ܸĽ�ֻ֧����С���Ķ�����
	//���������������ִ���̻���ҵ�����
	out.print("success");	//������̨�����ȡ�˱�Ǻ󣬲��ٷ���֪ͨ���벻Ҫ�޸Ļ�ɾ��
		}
		else {
	//֧��ʧ�ܴ�����ض���
	out.print("success");	//������̨�����ȡ�˱�Ǻ󣬲��ٷ���֪ͨ���벻Ҫ�޸Ļ�ɾ��
		}
		//�������������ҵ���߼�����д�������ϴ�������ο�������

	}else{//��֤ʧ��
		out.print("fail");
	}
%>
