<%
/* *
 功能：互付主动通知调用的页面（服务器异步通知页面）
 版本：1.0
 日期：2012-05-01
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习互付互付接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保互联网可以访问该页面。
 该页面调试工具请使用写文本函数log_result，该函数已被默认开启
 TRADE_FINISHED(表示买家已经确认收货，这笔交易完成);
 该服务器异步通知页面面主要功能是：防止订单未更新。如果没有收到该页面打印的 success 信息，互付会在24小时内按一定的时间策略重发通知
 
 *************注意******************
 
 即时到帐的交易状态变化顺序是：等待买家付款→交易完成
 
 每当收到互付发来通知中，就可以获取到这笔交易的交易状态，并且商户需要利用商户订单号查询商户网站的订单数据，
 得到这笔订单在商户网站中的状态是什么，把商户网站中的订单状态与从互付通知中获取到的状态来做对比。
 
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

	System.out.println("收到信息===============" + request.getParameter("orderId"));
	
	//获取互付POST过来反馈信息
	Map params = PaymentFunction.transformRequestMap(request.getParameterMap());
	
	//判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
	//responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
	//mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
	String mysign = PaymentFunction.BuildMysign(params, key);

	String responseTxt = KingnsFunction.Verify(request.getParameter("notify_id"));
	String sign = request.getParameter("sign");
	
	//获取互付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
	String trade_no = request.getParameter("trade_no");				//互付交易号
	String order_no = request.getParameter("order_no");	        //获取订单号
	String total_fee = request.getParameter("total_fee");	        	//获取总金额
	String title=request.getParameter("title");					//商品名称、订单名称
	if(title!=null && !"".equals(title)){
		title = new String(title.getBytes("ISO-8859-1"),"GBK");
	}

	String body = request.getParameter("body");//商品描述、订单备注、描述
	if(body != null && !"".equals(body)){
		body = new String(body.getBytes("ISO-8859-1"), "GBK");
	}

	String trade_status = request.getParameter("trade_status");		//交易状态
	

	//建议校验responseTxt，判读该返回结果是否由融宝发出
	if(mysign.equals(sign) && responseTxt.equals("true")){

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		if(trade_status.equals("TRADE_FINISHED")){
		
	//支付成功，如果没有做过处理，根据订单号（out_trade_no）及金额（total_fee）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
	//一定要做金额判断，防止恶意窜改金额，只支付了小金额的订单。
	//如果有做过处理，不执行商户的业务程序
	out.print("success");	//互付后台程序获取此标记后，不再发送通知，请不要修改或删除
		}
		else {
	//支付失败处理相关订单
	out.print("success");	//互付后台程序获取此标记后，不再发送通知，请不要修改或删除
		}
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

	}else{//验证失败
		out.print("fail");
	}
%>
