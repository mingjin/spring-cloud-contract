package com.ifpay.paymenttest;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class payment extends AbstractJavaSamplerClient {
	private String url; 
	private String ebankurl; 
	private String merchid; 
	private String email;
	private String key; 
	private String notifyurl;
	private String returnurl;
	
    private String resultData;  
    private static long start = 0;  
    private static long end = 0; 
	
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();  
        params.addArgument("url", "http://114.215.242.9:18170/portal?");  
        params.addArgument("ebankurl", "http://114.215.242.9:18170");  
        params.addArgument("merchid", "100000000002004");  
        params.addArgument("email", "402673978@qq.com"); 
        params.addArgument("key", "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f"); 
        params.addArgument("notifyurl", "http://www.baidu.com");  
        params.addArgument("returnurl", "http://www.baidu.com"); 
        return params;  
    }  
    
    
    //每个线程测试前执行一次，做一些初始化工作；  
    public void setupTest(JavaSamplerContext arg0) {  
    	ebankurl = arg0.getParameter("ebankurl"); 
    	url = arg0.getParameter("url");  
    	merchid = arg0.getParameter("merchid"); 
    	email = arg0.getParameter("email"); 
    	key = arg0.getParameter("key"); 
    	
    	notifyurl = arg0.getParameter("notifyurl"); 
    	returnurl = arg0.getParameter("returnurl"); 
    }  
    
    
    //开始测试，从arg0参数可以获得参数值；  
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();  
        sr.setSamplerData("请求url："+url);  

        try {
            sr.sampleStart();// jmeter 开始统计响应时间标记  
            // 通过下面的操作就可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里面了。
           
            Httphelp hh=new Httphelp();
//	        System.out.println("url is:"+url); 
//	        System.out.println("ebankurl is:"+ebankurl);
//	        System.out.println("merchid is:"+merchid); 
//	        System.out.println("email is:"+email);
//	        System.out.println("key is:"+key); 
	        
	        
	        resultData=hh.RadmonTD(url,ebankurl,merchid,email,key,notifyurl,returnurl);
	        //System.out.println("resultData is:"+resultData); 
            
//            if (resultData != null && resultData.length() > 0) {  
//                sr.setResponseData("结果是："+resultData, null);  
//                sr.setDataType(SampleResult.TEXT);  
//            }  
            sr.setSuccessful(true);  
        } catch (Throwable e) {  
            sr.setSuccessful(false);  
            e.printStackTrace();  
        } finally {  
            sr.sampleEnd();// jmeter 结束统计响应时间标记  
        }  
        return sr;  
    }  
    
    
    
    //测试结束时调用；  
    public void teardownTest(JavaSamplerContext arg0) {  
        end = System.currentTimeMillis();  
        // 总体耗时  
        //System.err.println("cost time:" + (end - start) + "毫秒");  
    }


    public static void main(String[] args) {
        for (int i = 0; i <1 ; i++) {
            Arguments params = new Arguments();
            params.addArgument("url", "http://114.215.242.9:18170/portal?");

            JavaSamplerContext ctx = new JavaSamplerContext(params);
            payment test = new payment();
            test.setupTest(ctx);
            test.runTest(ctx);
            test.teardownTest(ctx);
//        System.exit(0);
        }
    }
    
    
    
	

}
