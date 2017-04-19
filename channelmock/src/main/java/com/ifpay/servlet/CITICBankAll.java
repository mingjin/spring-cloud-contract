package com.ifpay.servlet;

import com.alibaba.fastjson.JSONObject;
import com.ifpay.common.utils.*;
import com.ifpay.service.ChannelService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by harvey.xu on 2017/4/10.
 */
@WebServlet(name = "CITICBankServlet")
public class CITICBankAll extends HttpServlet {

    private static Logger logger = LogManager.getLogger(CITICBankAll.class.getName());

    private static ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
    static {
        poolTaskExecutor.setQueueCapacity(100000);
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setMaxPoolSize(20);
        poolTaskExecutor.setKeepAliveSeconds(5000);
        poolTaskExecutor.initialize();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        long startReturnCodeUrl = System.currentTimeMillis();
        logger.info("----------CITICBank return codeUrl Start:" + startReturnCodeUrl + "------------");

        String receiveJson = new String(Base64.decodeBase64(request.getParameter("sendData").replace("#", "+")));
        logger.info("receiveJson = " + receiveJson);

        TreeMap receiveMap = JsonUtil.jsonStrToMap(receiveJson);
        receiveMap.remove("signAture");

        String key = KeyUtils.getKey(receiveMap.get("merId").toString());
        String respCode = "0000";
        String respMsg = "交易成功";
        String txnTime = DateUtil.getTimeFormat();
        String txnSeqId = "951" + txnTime + RandomUtil.getNumber(8);
        String seqId = "901" + txnTime + RandomUtil.getNumber(8);
        String prepayId = "wx" + txnTime  + RandomUtil.getCharAndNumr(20);
//        String codeUrl = "weixin://wxpay/bizpayurl?pr=" + RandomUtil.getCharAndNumr(7);
        String transactionId = "4008142001" + DateUtil.getTimeFormat() + RandomUtil.getNumber(4);

        // 如果金额等于99.99返回状态为失败
        if (receiveMap.get("txnAmt").equals("9999")) {
            respCode = "1001";
            respMsg = "交易出现异常。详情请咨询";
        }

        Map<String, Object> concurrentHashMap = new ConcurrentHashMap();
        /* 组装全局map */
        concurrentHashMap.put("key", key);
        concurrentHashMap.put("encoding", receiveMap.get("encoding"));
        concurrentHashMap.put("txnType", receiveMap.get("txnType"));
        concurrentHashMap.put("txnSubType", receiveMap.get("txnSubType"));
        concurrentHashMap.put("channelType", receiveMap.get("channelType"));
        concurrentHashMap.put("payAccessType", receiveMap.get("payAccessType"));
        concurrentHashMap.put("merId", receiveMap.get("merId"));
        concurrentHashMap.put("termId", receiveMap.get("termId"));
        concurrentHashMap.put("orderId", receiveMap.get("orderId"));
        concurrentHashMap.put("orderTime", receiveMap.get("orderTime"));
        concurrentHashMap.put("orderBody", receiveMap.get("orderBody"));
        concurrentHashMap.put("txnAmt", receiveMap.get("txnAmt"));
        concurrentHashMap.put("currencyType", receiveMap.get("currencyType"));
        concurrentHashMap.put("backEndUrl", receiveMap.get("backEndUrl"));
        concurrentHashMap.put("respCode", respCode);
        concurrentHashMap.put("respMsg", respMsg);
        concurrentHashMap.put("txnTime", txnTime);
        concurrentHashMap.put("txnSeqId", txnSeqId);
        concurrentHashMap.put("seqId", seqId);
        concurrentHashMap.put("transactionId", transactionId);
        /* 组装全局map */

//        TreeMap signMap = new TreeMap();
//        signMap.put("encoding", receiveMap.get("encoding"));
//        signMap.put("signMethod", "02");
//        signMap.put("txnType", receiveMap.get("txnType"));
//        signMap.put("txnSubType", receiveMap.get("txnSubType"));
//        signMap.put("channelType", receiveMap.get("channelType"));
//        signMap.put("payAccessType", receiveMap.get("payAccessType"));
//        signMap.put("merId", receiveMap.get("merId"));
//        signMap.put("termId", receiveMap.get("termId"));
//        signMap.put("orderId", receiveMap.get("orderId"));
//        signMap.put("orderTime", receiveMap.get("orderTime"));
//        signMap.put("orderBody", receiveMap.get("orderBody"));
//        signMap.put("txnAmt", receiveMap.get("txnAmt"));
//        signMap.put("currencyType", receiveMap.get("currencyType"));
//        signMap.put("respCode", respCode);
//        signMap.put("respMsg", respMsg);
//        signMap.put("txnSeqId", txnSeqId);
//        signMap.put("txnTime", txnTime);
//        signMap.put("prepayId", prepayId);
//        signMap.put("codeUrl", codeUrl);
//
//        if (StringUtils.isNotEmpty(receiveMap.get("txnSubType").toString())) {
//            if (receiveMap.get("txnSubType").equals("010130")) {
//                signMap.put("payAccessType", receiveMap.get("payAccessType"));
//                signMap.put("txnSeqId", txnSeqId);
//            } else if (receiveMap.get("txnSubType").equals("010302")) {
//                signMap.put("backEndUrl", receiveMap.get("backEndUrl"));
//                signMap.put("termIp", receiveMap.get("termIp"));
//                signMap.put("orderDetail", receiveMap.get("orderDetail"));
//                signMap.put("accountFlag", "N");
//                signMap.put("seqId", txnSeqId);
//            }
//        } else {
//            logger.error("txnSubType is null!");
//        }

        // 根据不同渠道拼装请求Map
        TreeMap signMap = new TreeMap();
        signMap.put("respCode", respCode);
        signMap.put("respMsg", respMsg);
        signMap.put("txnTime", txnTime);
        signMap.put("txnSeqId", txnSeqId);
        signMap.put("seqId", seqId);
        signMap.put("prepayId", prepayId);
//        signMap.put("codeUrl", codeUrl);
        signMap = ChannelService.buildChannelMap(receiveMap, signMap);

        String signAture = null;
        try {
            signAture = MD5Utils.md5Sign(signMap, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        signMap.put("signAture", signAture.toUpperCase());

        String responseJson = JSONObject.toJSONString(signMap);
        logger.info("responseJson = " + responseJson);
        String responseStr = "sendData=" + URLEncoder.encode(Base64.encodeBase64String(responseJson.getBytes()));
        PrintWriter out = response.getWriter();
        out.write(responseStr);

        final Map finalMap = concurrentHashMap;
        /* 多线程处理 */
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                /* notify back to payment*/
                long startAsynNotify = System.currentTimeMillis();
                logger.info("----------CITICBank asyn notify back to payment Start:" + startAsynNotify + " orderId: " + finalMap.get("orderId") + "------------");
                try {
                    // 增加等待时间，先返码，后通知
                    Thread.currentThread().sleep(300);

                    TreeMap notifySignMap = new TreeMap();
                    notifySignMap.put("encoding", finalMap.get("encoding"));
                    notifySignMap.put("signMethod", "02");
                    notifySignMap.put("txnType", finalMap.get("txnType"));
                    notifySignMap.put("txnSubType", finalMap.get("txnSubType"));
                    notifySignMap.put("channelType", finalMap.get("channelType"));
                    notifySignMap.put("payAccessType", finalMap.get("payAccessType"));
                    notifySignMap.put("merId", finalMap.get("merId"));
                    notifySignMap.put("termId", finalMap.get("termId"));
                    notifySignMap.put("orderId", finalMap.get("orderId"));
                    notifySignMap.put("orderTime", finalMap.get("orderTime"));
                    notifySignMap.put("orderBody", finalMap.get("orderBody"));
                    notifySignMap.put("txnAmt", finalMap.get("txnAmt"));
                    notifySignMap.put("currencyType", finalMap.get("currencyType"));
                    notifySignMap.put("backEndUrl", finalMap.get("backEndUrl"));
                    notifySignMap.put("respCode", finalMap.get("respCode"));
                    notifySignMap.put("respMsg", finalMap.get("respMsg"));
                    notifySignMap.put("txnSeqId", finalMap.get("txnSeqId"));
                    notifySignMap.put("txnTime", finalMap.get("txnTime"));
                    notifySignMap.put("settleAmt", finalMap.get("txnAmt"));
                    notifySignMap.put("settleCurrencyCode", "156");
                    notifySignMap.put("settleDate", DateUtil.getDate("yyyyMMdd"));
                    notifySignMap.put("transactionId", finalMap.get("transactionId"));
                    notifySignMap.put("endTime", DateUtil.getDate("yyyyMMddHHmmss"));
                    notifySignMap.put("bankType", "CFT");

                    String signAtureNotify = null;
                    try {
                        signAtureNotify = MD5Utils.md5Sign(notifySignMap, finalMap.get("key").toString());
                    } catch (Exception e) {
                        logger.info("========md5util===========ex");
                        e.printStackTrace();
                    }
                    notifySignMap.put("signAture", signAtureNotify.toUpperCase());

                    String notifyJson = JSONObject.toJSONString(notifySignMap);
                    logger.info("notifyJson = " + notifyJson);
                    String notifyRequest = "sendData=" + URLEncoder.encode(Base64.encodeBase64String(notifyJson.getBytes()));

                    // 请求payment
                    String paymentResponse = HttpRequest.sendPost(finalMap.get("backEndUrl").toString(), notifyRequest);
                    if (StringUtils.isNotEmpty(paymentResponse)) {
                        String paymentResponseJson;
                        if (!paymentResponse.startsWith("sendData=")) {
                            paymentResponseJson = paymentResponse;
                            logger.info("paymentResponse is not start with sendData=");
                        } else {
                            paymentResponseJson = new String(Base64.decodeBase64(paymentResponse.replace("sendData=", "").replace("#", "+")), "utf-8");
                        }
//                        logger.info("paymentResponseJson = " + paymentResponseJson);

                        if (paymentResponseJson.contains("\"respCode\":\"0000\"")) {
                            logger.info(">>>>>>>>>异步通知payment成功!transactionId = " + finalMap.get("transactionId"));
                        } else {
                            logger.error(">>>>>>>>>异步通知payment失败!paymentResponseJson = " + paymentResponseJson);
                        }
                    } else {
                        logger.error("请求失败，请检查系统是否正常!请求地址为： " + finalMap.get("backEndUrl"));
                    }
                } catch (Exception e) {
                    logger.info("===========all========ex");
                    e.printStackTrace();
                }
                logger.info("----------CITICBank return notify back to payment End:" + (System.currentTimeMillis() - startAsynNotify) + "ms------------");
                /* notify back to payment*/
                return true;
            }
        });
        poolTaskExecutor.submit(futureTask);

        logger.info("----------CITICBank return codeUrl End:" + (System.currentTimeMillis() - startReturnCodeUrl) + "ms " + receiveMap.get("orderId") + " ------------");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        logger.info("Hello to get CITIC Bank All Mock!");
        PrintWriter out = response.getWriter();
        out.println("Hello to get CITIC Bank All Mock!");
    }
}
