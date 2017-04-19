package com.ifpay.service;

import com.ifpay.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *������payment_service
 *���ܣ������ⲿ����ӿڿ���
 */
public class PaymentService {

	/**
	 * web֧��
	 * @param reqMap
	 * @param key
	 * @return
	 */
	public static String BuildWebForm(Map reqMap, String key) throws UnsupportedEncodingException {

		Map signMap = new HashMap();
		signMap.put("service", reqMap.get("service"));
		signMap.put("payment_type",reqMap.get("payment_type"));
		signMap.put("merchant_ID", reqMap.get("merchant_ID"));
		signMap.put("seller_email", reqMap.get("seller_email"));
		signMap.put("return_url", reqMap.get("return_url"));
		signMap.put("notify_url", reqMap.get("notify_url"));
		signMap.put("charset", reqMap.get("charset"));
		signMap.put("order_no", reqMap.get("order_no"));
		signMap.put("title", reqMap.get("title"));
		signMap.put("body", reqMap.get("body"));
		signMap.put("total_fee", reqMap.get("total_fee"));
		signMap.put("buyer_email", reqMap.get("buyer_email"));
		signMap.put("paymethod", reqMap.get("paymethod"));
		signMap.put("defaultbank", reqMap.get("defaultbank"));
		signMap.put("ext_param1", reqMap.get("payerName"));
		signMap.put("isApp", reqMap.get("isApp"));
		signMap.put("userIp", reqMap.get("userIp"));

		String sign = PaymentFunction.BuildMysign(signMap, key);//����ǩ�����

		StringBuffer sbHtml = HttpRequest.htmlBuild(reqMap, signMap, sign);// ƴװhtml
		return sbHtml.toString();
	}

	public static String BuildH5Form(Map reqMap, String key) throws UnsupportedEncodingException {
		Map signMap = new HashMap();
		signMap.put("service", reqMap.get("service"));
		signMap.put("payment_type", reqMap.get("payment_type"));
		signMap.put("merchant_ID", reqMap.get("merchant_ID"));
		signMap.put("seller_email", reqMap.get("seller_email"));
		signMap.put("return_url", reqMap.get("return_url"));
		signMap.put("notify_url", reqMap.get("notify_url"));
		signMap.put("charset", reqMap.get("charset"));
		signMap.put("order_no", reqMap.get("order_no"));
		signMap.put("title", reqMap.get("title"));
		signMap.put("body", reqMap.get("body"));
		signMap.put("total_fee", reqMap.get("total_fee"));
		signMap.put("buyer_email", reqMap.get("buyer_email"));
		signMap.put("paymethod", reqMap.get("paymethod"));
		signMap.put("defaultbank", reqMap.get("defaultbank"));
		signMap.put("ext_param1", reqMap.get("payerName"));
		signMap.put("isApp", reqMap.get("isApp"));
		signMap.put("appName", reqMap.get("appName"));
		signMap.put("appMsg", reqMap.get("appMsg"));
		signMap.put("appType", reqMap.get("appType"));
		signMap.put("userIp", reqMap.get("userIp"));
		signMap.put("backUrl", reqMap.get("backUrl"));

		String sign = PaymentFunction.BuildMysign(signMap, key);//����ǩ�����

		StringBuffer sbHtml = HttpRequest.htmlBuild(reqMap, signMap, sign);// ƴװhtml
		return sbHtml.toString();
	}


	/**
	 * app֧��
	 * @param reqMap
	 * @param key
	 * @return
	 */
	public static String BuildAppForm(Map reqMap, String key) throws UnsupportedEncodingException {

		Map signMap = new HashMap<String,String>();
		signMap.put("service", reqMap.get("service"));
		signMap.put("payment_type",reqMap.get("payment_type"));
		signMap.put("merchant_ID", reqMap.get("merchant_ID"));
		signMap.put("seller_email", reqMap.get("seller_email"));
		signMap.put("return_url", reqMap.get("return_url"));
		signMap.put("notify_url", reqMap.get("notify_url"));
		signMap.put("charset", reqMap.get("charset"));
		signMap.put("order_no", reqMap.get("order_no"));
		signMap.put("title", reqMap.get("title"));
		signMap.put("body", reqMap.get("body"));
		signMap.put("total_fee", reqMap.get("total_fee"));
		signMap.put("buyer_email", reqMap.get("buyer_email"));
		signMap.put("paymethod", reqMap.get("paymethod"));
		signMap.put("defaultbank", reqMap.get("defaultbank"));
		signMap.put("ext_param1", reqMap.get("ext_param1"));
		signMap.put("isApp", reqMap.get("isApp"));
		signMap.put("userIp", reqMap.get("userIp"));

		String sign = PaymentFunction.BuildMysign(signMap, key);//����ǩ�����
		signMap.put("sign_type", reqMap.get("sign_type"));
		signMap.put("sign", sign);
//		String url = reqMap.get("requestUrl").toString();
		String response = HttpRequest.doPost(reqMap, signMap);
		return response;
	}

	/**
	 * ����֧��
	 * @param reqMap
	 * @param key
	 * @return
	 */
	public static String BuildEBankForm(Map reqMap, String key) throws UnsupportedEncodingException {

		Map signMap = new HashMap<String,String>();
		signMap.put("service", reqMap.get("service"));
		signMap.put("payment_type",reqMap.get("payment_type"));
		signMap.put("merchant_ID", reqMap.get("merchant_ID"));
		signMap.put("seller_email", reqMap.get("seller_email"));
		signMap.put("return_url", reqMap.get("return_url"));
		signMap.put("notify_url", reqMap.get("notify_url"));
		signMap.put("charset", reqMap.get("charset"));
		signMap.put("order_no", reqMap.get("order_no"));
		signMap.put("title", reqMap.get("title"));
		signMap.put("body", reqMap.get("body"));
		signMap.put("total_fee", reqMap.get("total_fee"));
		signMap.put("paymethod", reqMap.get("paymethod"));
		signMap.put("defaultbank", reqMap.get("defaultbank"));
		signMap.put("isApp", "web");

		String sign = PaymentFunction.BuildMysign(signMap, key);// ����ǩ�����

		StringBuffer sbHtml = HttpRequest.htmlBuild(reqMap, signMap, sign);// ƴװhtml

		return sbHtml.toString();
	}

	/**
	 * ɨ��
	 */
//	public static String ScanForm(
//			String service,
//			String payment_type,
//			String seller_email,
//			String return_url,
//			String notify_url,
//			String order_no,
//			String title,
//			String body,
//            String buyer_email,
//            String charset,
//            String paymethod,
//            String defaultbank,
//            String sign_type,
//            String pay_cus_no,
//            String _format,
//            String mobile,
//            String customerNo,
//            String ssign,
//            String customerNo_encrypt
//            ){
//		Map sPara = new HashMap();
//		sPara.put("service",service);
//		sPara.put("payment_type",payment_type);
//		sPara.put("return_url", return_url);
//		sPara.put("notify_url", notify_url);
//		sPara.put("charset", charset);
//		sPara.put("order_no", order_no);
//		sPara.put("title", title);
//		sPara.put("body", body);
//		sPara.put("buyer_email", buyer_email);
//		sPara.put("paymethod", paymethod);
//		sPara.put("defaultbank", defaultbank);
//		sPara.put("pay_cus_no", pay_cus_no);
//		sPara.put("ext_param1", "ext_param11");
//		sPara.put("ext_param2", "ext_param22");
//		sPara.put("ext_param3", "0.09");
//		sPara.put("_format", _format);
//		sPara.put("isApp", mobile);
//
//		StringBuffer sbHtml = new StringBuffer();
//		String key="";
//		String merchant_ID="";
//		try {
//			merchant_ID = java.net.URLDecoder.decode(customerNo,"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		String email = "";
//		String msg="";
//		String phone = "";
//		String CustumerName = "";
//		String status="";
//		if(!"scanpay_wx".equals(service)&&!"scanpay_ali".equals(service)){
//			sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//	        sbHtml.append("<div style=\"text-align: center; \">");
//	        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ����ʹ��΢�Ż�֧����ɨ��֧����</p>");
//	        sbHtml.append("</div>");
//
//		}else{
//			if(merchant_ID==null||"".equals(merchant_ID)){
//				sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//		        sbHtml.append("<div style=\"text-align: center; \">");
//		        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ���ͻ���Ϣ���������������������룡</p>");
//		        sbHtml.append("</div>");
//			}else{
//				//�˴���ȡ����
//				String getInfoURL=KingnsConfig.getInfoByCustomerIdUrl;
//				HttpClientService clientService=new HttpClientService();
//				Map map=clientService.getKeyByHttp(merchant_ID,getInfoURL);
//				System.out.println("ɨ����ò�ѯ��Ϣ "+map.toString());
//				if("200".equals(map.get("statusCode").toString())){
//					JSONObject json=JSONObject.fromObject(map.get("response").toString());
//					msg = (String)json.get("msg");
//					key = (String)json.get("key");
//					//phone = (String)json.get("phone");
//					email = (String)json.get("email");
//					status = (String)json.get("status");
//					CustumerName = (String)json.get("name");
//				}
//				if(msg!=null&&!"".equals(msg)){
//					sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//			        sbHtml.append("<div style=\"text-align: center; \">");
//			        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ��").append(msg).append(",�����������룡</p>");
//			        sbHtml.append("</div>");
//
//				}else{
//
//					if(!"1".equals(status)){
//						sbHtml.append("<form id=\"rysubmit\" name=\"rysubmit\" ").append("action=\"").append(KingnsConfig.create_url).append("\" method=\"get\">");
//						sbHtml.append("<div style=\"text-align: center; \">");
//						sbHtml.append("<input type=\"hidden\" name=\"merchant_ID\" value=\"").append(merchant_ID).append("\" >");
//				        sbHtml.append("</div>");
//				        sbHtml.append("</form>");
//					}else{
//						String customerNo_dec ="";
//						try {
//							String keys = key.substring(0,12) + key.substring(key.length()-12,key.length());
//							customerNo_encrypt = customerNo_encrypt.replace(" ", "+");
//							customerNo_dec = new String(Util_3DESCoder.decodeBase64AndDecrypt3DES(customerNo_encrypt,keys),"UTF-8");
//							System.out.println("ɨ����ֻ��ţ�"+customerNo_dec);
//						} catch (UnsupportedEncodingException e) {
//							sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//					        sbHtml.append("<div style=\"text-align: center; \">");
//					        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ��ϵͳ�쳣��</p>");
//					        sbHtml.append("</div>");
//					        System.out.println(sbHtml.toString());
//							return sbHtml.toString();
//						}
//						if(customerNo_dec==null||"".equals(customerNo_dec)||!customerNo_dec.equals(merchant_ID)){
//							sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//					        sbHtml.append("<div style=\"text-align: center; \">");
//					        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ����ǩʧ��,�����������룡</p>");
//					        sbHtml.append("</div>");
//						}else{
//							String Csign = PaymentFunction.BuildMysignString(merchant_ID+customerNo_encrypt, key);//����ǩ�����
//							if(!Csign.equals(ssign)){
//								sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" method=\"get\">");
//						        sbHtml.append("<div style=\"text-align: center; \">");
//						        sbHtml.append("<p style=\"padding: 0; \"> ������Ϣ����ǩʧ��,�����������룡</p>");
//						        sbHtml.append("</div>");
//							}else{
//								sPara.put("merchant_ID", merchant_ID);
//								sPara.put("seller_email", email);
//
//								String mysign = PaymentFunction.BuildMysign(sPara, key);//����ǩ�����
//								System.out.println("sing:"+mysign);
//								List keys = new ArrayList(sPara.keySet());
//								String gateway=KingnsConfig.rongpay_url;
//
//								//GET��ʽ����
//								sbHtml.append("<script type=\"text/javascript\" src=\"common/js/html5/keyboard.js\"></script>");
//								sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" action=\"").append(gateway).append("\" method=\"get\">");
//
//								String name ="";
//								String value ="";
//								for (int i = 0; i < keys.size(); i++) {
//									 name=(String) keys.get(i);
//									 value=(String) sPara.get(name);
//									if(value!=null && !"".equals(value)){
//										sbHtml.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"" + value + "\"/>");
//									}
//								}
//						        sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"").append(mysign).append("\"/>");
//						        sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"").append(sign_type).append("\"/>");
//						        sbHtml.append("<div class=\"avatar\"><span></span><p>�տ��˻���").append(CustumerName).append("</p></div>");
//						        sbHtml.append("<div class=\"paybox\">");
//				        		sbHtml.append("<p>");
//		        				sbHtml.append("<input id=\"text1\" readonly=\"readonly\" style=\"height:28px;width:98%;outline:none;padding-left:3px;\" name=\"amount\" placeholder=\"��������\"/>");
//								sbHtml.append("<label for=\"amount\" id=\"money\">��</label>");
//								sbHtml.append("</p>");
//								sbHtml.append("</div>");
//								sbHtml.append("<div style=\"margin: 20px; \">");
//	//							sbHtml.append("<input type=\"submit\" class=\"button_p2p\" value=\"ȷ�ϸ���\">");
//								sbHtml.append("</div>");
//								sbHtml.append("</form>");
//								sbHtml.append("<script type=\"text/javascript\">(function(){ var input1 = document.getElementById('text1');new KeyBoard(input1);})(); </script>");
//
//						        //submit��ť�ؼ��벻Ҫ����name����
//
//							}
//						}
//					}
//				}
//			}
//		}
//		System.out.println(sbHtml.toString());
//		return sbHtml.toString();
//	}
}
