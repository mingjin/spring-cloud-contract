package com.ifpay.paymenttest;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Httphelp {


    public static String htmlBuild(@SuppressWarnings("rawtypes") Map signMap, String sign)  {

        StringBuffer sbHtml = new StringBuffer();
        @SuppressWarnings({"rawtypes", "unchecked"})
        List keys = new ArrayList(signMap.keySet());

        String name;
        String value;
        for (int i = 0; i < keys.size(); i++) {
            name = (String) keys.get(i);
            value = (String) signMap.get(name);
            if (value != null && !"".equals(value)) {
                sbHtml.append(name).append("=" + value + "&");
            }
        }
        sbHtml.append("sign=").append(sign).append("&");
        sbHtml.append("sign_type=").append("MD5");

        return sbHtml.toString();
    }

    public String RadmonTD(String url, String ebankurl, String merchid, String email, String key, String return_url, String notify_url) throws UnsupportedEncodingException {
        String resopne = "";
        String bank = "";
        try
        {
            RandomUtils rs = new RandomUtils();
            String orderno = "31" + rs.random().substring(2);
            //System.out.println("orderno, is "+orderno);
            if(Math.random()%2==0)
            {
                bank = "WXPAY";
                //System.out.println("geth5,bank is "+bank+" orderno is :"+orderno+"   url is:"+url);
                resopne = getweb(bank, orderno, url, ebankurl, merchid, email, key, return_url, notify_url);
            }
            else
            {
                bank="ALIPAY";
                //System.out.println("getweb,bank is "+bank+" orderno is :"+orderno+"   url is:"+url);
                resopne=getweb(bank, orderno, url, ebankurl, merchid, email, key, return_url, notify_url);
            }
        }
        catch (Exception ex)
        {
            resopne=ex.getMessage();
        }
        return resopne;
    }

    @SuppressWarnings("unchecked")
    public String geth5(String defaultbank, String orderno, String url, String ebankurl) {
        String resopne = "";
        //app方式post，其他是get
        Map signMap = new HashMap();
        signMap.put("service", "online_pay");
        signMap.put("payment_type", "1");
        signMap.put("merchant_ID", "100000000002004");
        signMap.put("seller_email", "402673978@qq.com");
        signMap.put("return_url", "http://127.0.0.1:8090/return_url.jsp");
        signMap.put("notify_url", "http://127.0.0.1:8090/return_url.jsp");
        signMap.put("charset", "GBK");
        signMap.put("order_no", "21180410135210001");
        signMap.put("title", "test");
        signMap.put("body", "testproductDesc");
        signMap.put("total_fee", "0.12");
        signMap.put("buyer_email", "");
        signMap.put("paymethod", "directPay");
        signMap.put("defaultbank", defaultbank);
        signMap.put("ext_param1", "");
        signMap.put("isApp", "h5");
        signMap.put("appName", "wap");
        signMap.put("appMsg", "testmsg");
        signMap.put("appType", "wap");
        signMap.put("userIp", "116.228.54.118");
        signMap.put("backUrl", "http://www.baidu.com/");

        String sign = PaymentFunction.BuildMysign(signMap, "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f");
        String geturl = "";
        try {
            geturl = htmlBuild(signMap, sign);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            //System.out.println("geth5 url is: "+url+geturl);
            resopne = requestGet(url + geturl, ebankurl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resopne;
    }

    //web支付
    @SuppressWarnings("unchecked")
    public String getweb(String defaultbank, String orderno, String url, String ebankurl, String merchid, String email, String key, String return_url, String notify_url) throws UnsupportedEncodingException {
        String resopne = "";
        //System.out.println("return_url is: "+return_url);
        //System.out.println("notify_url is: "+notify_url);
        Map signMap = new HashMap();
        signMap.put("service", "online_pay");
        signMap.put("payment_type", "1");
        signMap.put("merchant_ID", merchid);
        signMap.put("seller_email", email);
        signMap.put("return_url", return_url);
        signMap.put("notify_url", notify_url);
        signMap.put("charset", "GBK");
        signMap.put("order_no", orderno);
        signMap.put("title", "test");
        signMap.put("body", "testproductDesc");
        signMap.put("total_fee", "0.1");
        signMap.put("buyer_email", "2");
        signMap.put("paymethod", "directPay");
        signMap.put("defaultbank", defaultbank);
        signMap.put("ext_param1", "");
        signMap.put("isApp", "web");
        signMap.put("userIp", "116.228.54.118");
//		signMap.put("backUrl", "http://www.baidu.com/");
        String sign = PaymentFunction.BuildMysign(signMap, key);

        String geturl = "";
        geturl = htmlBuild(signMap, sign);
        try {
            //System.out.println("getweb url is: "+url+geturl);
            resopne = requestGet(url + geturl, ebankurl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resopne;
    }

    //app支付
    @SuppressWarnings("unchecked")
    public void postapp() {
        //app方式post，其他是get
        @SuppressWarnings("rawtypes")
        Map signMap = new HashMap<String, String>();

        signMap.put("service", "online_pay");
        signMap.put("payment_type", "1");
        signMap.put("merchant_ID", "100000000002004");
        signMap.put("seller_email", "402673978@qq.com");
        signMap.put("return_url", "http://127.0.0.1:8090/return_url.jsp");
        signMap.put("notify_url", "http://127.0.0.1:8090/notify_url.jsp");
        signMap.put("charset", "GBK");
        signMap.put("order_no", "21170410135210000");
        signMap.put("title", "test");
        signMap.put("body", "testproductDesc");
        signMap.put("total_fee", "0.01");
        signMap.put("buyer_email", "");
        signMap.put("paymethod", "directPay");
        signMap.put("defaultbank", "WXPAY");
        signMap.put("ext_param1", "");
        signMap.put("isApp", "app");
        signMap.put("userIp", "116.228.54.118");

//	    List<NameValuePair> params = new ArrayList<NameValuePair>();
//	      params.add(new BasicNameValuePair("service", "online_pay"));
//	      params.add(new BasicNameValuePair("payment_type", "1"));
//	      params.add(new BasicNameValuePair("merchant_ID", "100000000002004"));
//	      params.add(new BasicNameValuePair("seller_email", "402673978@qq.com"));
//	      params.add(new BasicNameValuePair("return_url", "http://127.0.0.1:8090/return_url.jsp"));
//	      params.add(new BasicNameValuePair("notify_url", "http://127.0.0.1:8090/notify_url.jsp"));
//	      params.add(new BasicNameValuePair("charset", "GBK"));
//	      params.add(new BasicNameValuePair("order_no", "21170410135210000"));
//	      params.add(new BasicNameValuePair("title", "test"));
//	      params.add(new BasicNameValuePair("body", "testproductDesc"));
//	      params.add(new BasicNameValuePair("total_fee", "0.01"));
//	      params.add(new BasicNameValuePair("buyer_email", ""));
//	      params.add(new BasicNameValuePair("paymethod", "directPay"));
//	      params.add(new BasicNameValuePair("defaultbank", "WXPAY"));
//	      params.add(new BasicNameValuePair("ext_param1", ""));
//	      params.add(new BasicNameValuePair("isApp", "app"));
//	      params.add(new BasicNameValuePair("charset", "GBK"));
//	      params.add(new BasicNameValuePair("userIp", "116.228.54.118"));


//      String sign = PaymentFunction.BuildMysign(signMap, "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f");
//
//		signMap.put("sign_type", "MD5");
//		signMap.put("sign", sign);
//
//	        try {
//				requestPost("http://114.215.242.9:18170/portal",signMap);
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

    }


    public String requestGet(String urlWithParams, String ebankurl) throws Exception {

        CloseableHttpClient httpclient = null;
        HttpGet httpget = null;
        CloseableHttpResponse response = null;
        String jsonStr = "";
        try {
            httpclient = HttpClientBuilder.create().build();

            httpget = new HttpGet(urlWithParams);
            //配置请求的超时设置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000).build();
            httpget.setConfig(requestConfig);
            //System.out.println("requestGet url is: "+urlWithParams);
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            jsonStr = EntityUtils.toString(entity);//, "utf-8");


            String html = jsonStr.replace("/Ebank", ebankurl + "/Ebank");

            Map requestMap = HttpRequest.parseHtml2Map(html);
            HttpRequest.sendPost(ebankurl + "/Ebank", requestMap);

        } catch (Exception ex) {
            jsonStr = ex.getMessage();
        } finally {
            if (httpget != null) {
                httpget.releaseConnection();
            }
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }

        }

        //System.out.println("StatusCode -> " + response.getStatusLine().getStatusCode());
        //jsonStr = EntityUtils.toString(entity);//, "utf-8");
        //System.out.println("jsonStr is :"+jsonStr);
        return jsonStr;
    }


//	public  String requestPost(String url,Map params) throws ClientProtocolException, IOException {
//		CloseableHttpClient httpclient=null;
//		HttpPost httppost=null;
//		CloseableHttpResponse response=null;
//		String jsonStr ="";
//		try
//		{
//		    httpclient = HttpClientBuilder.create().build();
//		    httppost = new HttpPost(url);
//
//		    //配置请求的超时设置
//	        RequestConfig requestConfig = RequestConfig.custom()
//	                .setConnectionRequestTimeout(5000)
//	                .setConnectTimeout(5000)
//	                .setSocketTimeout(5000).build();
//	        httppost.setConfig(requestConfig);
//
//	        httppost.setEntity(new UrlEncodedFormEntity(params));
//
//	        response = httpclient.execute(httppost);
//	        //System.out.println(response.toString());
//
//	        HttpEntity entity = response.getEntity();
//	        jsonStr = EntityUtils.toString(entity, "utf-8");
////	        System.out.println(jsonStr);
//
//	        httppost.releaseConnection();
//	        response.close();
//	        httpclient.close();
//
//		}
//		catch(Exception ex)
//		{
//			jsonStr=ex.getMessage();
//			 //System.out.println(ex.getMessage());
//		}
//		finally
//		{
//
//			if(httppost!=null)
//			{
//				httppost.releaseConnection();
//			}
//			if(response!=null)
//			{
//				response.close();
//			}
//			if(httpclient!=null)
//			{
//				httpclient.close();
//			}
//
//		}
//		return jsonStr;
//	}

    public String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
