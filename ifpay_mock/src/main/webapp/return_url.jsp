<%
/* *
 功能：付完款后跳转的页面（页面跳转同步通知页面）
 版本：1.0
 日期：2012-05-01
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究互付接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 该页面称作“页面跳转同步通知页面”，是由互付服务器同步调用，可当作是支付完成后的提示信息页，如“您的某某某订单，多少金额已支付成功”。
 可放入HTML等美化页面的代码和订单交易完成后的数据库更新程序代码
 TRADE_FINISHED(表示买家已经确认收货，这笔交易完成);
 
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
<%@ page import="com.ifpay.utils.KeyUtils" %>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>支付成功客户端返回</title>
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
  <body>
<%
	request.setCharacterEncoding("utf-8");

    //获取互付GET过来反馈信息
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

	//判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
	//responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
	//mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
	String mysign = PaymentFunction.BuildMysign(params, key);

	String responseTxt = PaymentFunction.Verify(verifyUrl, params.get("seller_id").toString(), request.getParameter("notify_id"));
	String sign = request.getParameter("sign");


	//获取互付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	String trade_no = request.getParameter("trade_no");				//互付交易号
	String order_no = request.getParameter("order_no");	        //获取订单号
	String total_fee = request.getParameter("total_fee");	            //获取总金额
	String title = request.getParameter("title");				//商品名称、订单名称
	String body = request.getParameter("body");
//	String buyer_email = request.getParameter("buyer_email");		//买家互付账号
	String trade_status = request.getParameter("trade_status");		//交易状态
	//获取互付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	String verifyStatus;
	
	//建议校验responseTxt，判读该返回结果是否由融宝发出
	if (mysign.equals(sign) && responseTxt.equals("true")){
		
		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——	
		if (trade_status.equals("TRADE_FINISHED")) {
		//支付成功，如果没有做过处理，根据订单号（out_trade_no）及金额（total_fee）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//一定要做金额判断，防止恶意窜改金额，只支付了小金额的订单。
		//如果有做过处理，不执行商户的业务程序
		} else {
		
	//支付失败处理相关订单
		}
		
		verifyStatus = "验证成功";
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		
	} else {
		verifyStatus = "验证失败";
	}
%>
<table align="center" width="350" cellpadding="5" cellspacing="0">
            <tr>
                <td align="center" class="font_title" colspan="2">通知返回</td>
            </tr>
            <tr>
                <td class="font_content" align="right">互付交易号：</td>
                <td class="font_content" align="left"><%=trade_no %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">订单号：</td>
                <td class="font_content" align="left"><%=order_no %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">付款总金额：</td>
                <td class="font_content" align="left"><%=total_fee %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">商品标题：</td>
                <td class="font_content" align="left"><%=title %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">商品描述：</td>
                <td class="font_content" align="left"><%=body %></td>
            </tr>
           
            <tr>
                <td class="font_content" align="right">交易状态：</td>
                <td class="font_content" align="left"><%=trade_status %></td>
            </tr>
            <tr>
                <td class="font_content" align="right">验证状态：</td>
                <td class="font_content" align="left"><%=verifyStatus %></td>
            </tr>
        </table>
  </body>
</html>
