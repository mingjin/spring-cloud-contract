package com.ifpay.paymenttest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {


    public static void main(String[] args) {
        //RandomUtils rs=new RandomUtils();
        //System.out.println(rs.random().substring(2));

//		Random rand = new Random(java.util.UUID.randomUUID().hashCode());
//		System.out.println( rand.nextLong());

        for (int i = 0; i < 1; i++) {
            RadmonTD();
        }
    }


    public static void RadmonTD() {
        String bank = "";
        RandomUtils rs = new RandomUtils();
        String orderno = "31" + rs.random().substring(2);
        System.out.println("orderno is:" + orderno);
        bank = "WXPAY";//ALIPAY
        getweb(bank, orderno);
//		if(Math.random()%2==0)
//		{
//			bank="WXPAY";
//			geth5(bank,orderno);
//		}
//		else
//		{
//			bank="WXPAY";//ALIPAY
//			getweb(bank,orderno);
//		}
    }

    //h5֧��
    @SuppressWarnings("unchecked")
    public static void geth5(String defaultbank, String orderno) {
        //app��ʽpost��������get
        Map signMap = new HashMap();
        signMap.put("service", "online_pay");
        signMap.put("payment_type", "1");
        signMap.put("merchant_ID", "100000000002004");
        signMap.put("seller_email", "402673978@qq.com");
        signMap.put("return_url", "http://127.0.0.1:8090/return_url.jsp");
        signMap.put("notify_url", "http://127.0.0.1:8090/return_url.jsp");
        signMap.put("charset", "GBK");
        signMap.put("order_no", orderno);
        signMap.put("title", "test");
        signMap.put("body", "testproductDesc");
        signMap.put("total_fee", "0.12");
        signMap.put("buyer_email", "");
        signMap.put("paymethod", "directPay");
        signMap.put("defaultbank", defaultbank);
        signMap.put("ext_param1", "testjianfengh5");
        signMap.put("isApp", "h5");//h5
        signMap.put("appName", "wap");
        signMap.put("appMsg", "testmsg");
        signMap.put("appType", "wap");
        signMap.put("userIp", "116.228.54.118");
        signMap.put("backUrl", "http://www.baidu.com/");

        String sign = PaymentFunction.BuildMysign(signMap, "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f");

        String geturl = "";
        try {
            geturl = htmlBuild(signMap, sign);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            requestGet("http://114.215.242.9:18170/portal?" + geturl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //web֧��
    @SuppressWarnings("unchecked")
    public static void getweb(String defaultbank, String orderno) {
        Map signMap = new HashMap();
        signMap.put("service", "online_pay");
        signMap.put("payment_type", "1");
        signMap.put("merchant_ID", "100000000002262");
        signMap.put("seller_email", "jianfeng.xu@ftvalue.com");
        signMap.put("return_url", "http://114.215.242.9:8090/return_url.jsp");
        signMap.put("notify_url", "http://114.215.242.9:8090/notify_url.jsp");
        signMap.put("charset", "GBK");
        signMap.put("order_no", orderno);
        signMap.put("title", "1");
        signMap.put("body", "1");
        signMap.put("total_fee", "0.2");
//		signMap.put("buyer_email", "2");
        signMap.put("paymethod", "directPay");
        signMap.put("defaultbank", defaultbank);
//		signMap.put("ext_param1", "ext_param1");
        signMap.put("isApp", "web");
        signMap.put("userIp", "116.228.54.118");
        //signMap.put("backUrl", "http://www.baidu.com/");
        String sign = PaymentFunction.BuildMysign(signMap, "8ccd54184823a77g6217dgf6dge3493ad0g7d0fce7d6b7b59ad18bf8e7ce301g");
        String geturl = "";
        try {
            geturl = htmlBuild(signMap, sign);
//			System.out.println("geturl = " + geturl);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		signMap.put("sign", sign);
//		signMap.put("sign_type", "MD5");

        try {
//			requestGet("http://192.168.1.34:18170/portal?"+geturl);
            requestGet("http://114.215.242.9:18170/portal?" + geturl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		Map map = new HashMap();
//		map.put("requestUrl", "http://192.168.1.34:18170/portal");
//		HttpRequest.doPost(map, signMap);
    }


    public static String htmlBuild(@SuppressWarnings("rawtypes") Map signMap, String sign) throws UnsupportedEncodingException {

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

    //app֧��
    @SuppressWarnings("unchecked")
    public static void postapp() {
        //app��ʽpost��������get
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
        signMap.put("ext_param1", "testjianfengapp");
        signMap.put("isApp", "app");
        signMap.put("userIp", "116.228.54.118");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("service", "online_pay"));
        params.add(new BasicNameValuePair("payment_type", "1"));
        params.add(new BasicNameValuePair("merchant_ID", "100000000002004"));
        params.add(new BasicNameValuePair("seller_email", "402673978@qq.com"));
        params.add(new BasicNameValuePair("return_url", "http://114.215.242.9:8090/return_url.jsp"));
        params.add(new BasicNameValuePair("notify_url", "http://114.215.242.9:8090/notify_url.jsp"));
        params.add(new BasicNameValuePair("charset", "GBK"));
        params.add(new BasicNameValuePair("order_no", "21170410135210000"));
        params.add(new BasicNameValuePair("title", "test"));
        params.add(new BasicNameValuePair("body", "testproductDesc"));
        params.add(new BasicNameValuePair("total_fee", "0.01"));
        params.add(new BasicNameValuePair("buyer_email", ""));
        params.add(new BasicNameValuePair("paymethod", "directPay"));
        params.add(new BasicNameValuePair("defaultbank", "WXPAY"));
        params.add(new BasicNameValuePair("ext_param1", ""));
        params.add(new BasicNameValuePair("isApp", "app"));
        params.add(new BasicNameValuePair("charset", "GBK"));
        params.add(new BasicNameValuePair("userIp", "116.228.54.118"));


        String sign = PaymentFunction.BuildMysign(signMap, "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f");

        params.add(new BasicNameValuePair("sign_type", "MD5"));


        params.add(new BasicNameValuePair("sign", sign));

        try {
            requestPost("http://114.215.242.9:18170/portal", params);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void requestGet(String urlWithParams) throws Exception {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(urlWithParams);
        // HttpGet httpget = new HttpGet(urlWithParams);
        //��������ĳ�ʱ����
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000).build();
        httpget.setConfig(requestConfig);

        CloseableHttpResponse response = httpclient.execute(httpget);
        System.out.println("StatusCode -> " + response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity);//, "utf-8");
//        System.out.println(jsonStr);

        String html = jsonStr.replace("/Ebank", "http://114.215.242.9:18170/Ebank");
//		System.out.println("html = " + html);
        httpget.releaseConnection();

        Map requestMap = HttpRequest.parseHtml2Map(html);
        HttpRequest.sendPost("http://114.215.242.9:18170/Ebank", requestMap);

    }


    public static void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = null;
        HttpPost httppost = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClientBuilder.create().build();
            httppost = new HttpPost(url);

            //��������ĳ�ʱ����
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(30000)
                    .setConnectTimeout(30000)
                    .setSocketTimeout(30000).build();
            httppost.setConfig(requestConfig);

            httppost.setEntity(new UrlEncodedFormEntity(params));

            response = httpclient.execute(httppost);
            System.out.println(response.toString());

//	        HttpEntity entity = response.getEntity();
//	        String jsonStr = EntityUtils.toString(entity, "utf-8");
//	        System.out.println(jsonStr);

            httppost.releaseConnection();
            response.close();
            httpclient.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            httppost.releaseConnection();
            response.close();
            httpclient.close();
        }


    }

}
