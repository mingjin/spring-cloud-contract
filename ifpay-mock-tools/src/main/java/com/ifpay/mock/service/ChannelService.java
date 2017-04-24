package com.ifpay.mock.service;

import com.alibaba.druid.util.StringUtils;
import com.ifpay.mock.common.utils.DateUtil;
import com.ifpay.mock.common.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by harvey.xu on 2017/4/18.
 */
public class ChannelService {

    private static Logger logger = LoggerFactory.getLogger(ChannelService.class.getName());

    /**
     * 组装返码Map
     * @param receiveMap
     * @param signMap
     * @return
     */
    public static Map buildReCodeUrlMap(TreeMap receiveMap, Map signMap) {

        String txnTime = DateUtil.getTimeFormat();
        signMap.put("encoding", receiveMap.get("encoding"));
        signMap.put("signMethod", "02");
        signMap.put("txnType", receiveMap.get("txnType"));
        signMap.put("txnSubType", receiveMap.get("txnSubType"));
        signMap.put("channelType", receiveMap.get("channelType"));
        signMap.put("merId", receiveMap.get("merId"));
        signMap.put("termId", receiveMap.get("termId"));
        signMap.put("orderId", receiveMap.get("orderId"));
        signMap.put("orderTime", receiveMap.get("orderTime"));
        signMap.put("orderBody", receiveMap.get("orderBody"));
        signMap.put("txnAmt", receiveMap.get("txnAmt"));
        signMap.put("currencyType", receiveMap.get("currencyType"));

        if (!StringUtils.isEmpty(receiveMap.get("txnSubType").toString())) {
            if (receiveMap.get("txnSubType").equals("010130")) {
                signMap.put("txnTime", txnTime);
                signMap.put("payAccessType", receiveMap.get("payAccessType"));
                signMap.put("txnSeqId", "951" + txnTime + RandomUtils.getNumber(8));
                signMap.put("prepayId", "wx" + txnTime  + RandomUtils.getCharAndNumr(20));
                signMap.put("codeUrl", "weixin://wxpay/bizpayurl?pr=" + RandomUtils.getCharAndNumr(7));
            } else if (receiveMap.get("txnSubType").equals("010302")) {
                signMap.put("backEndUrl", receiveMap.get("backEndUrl"));
                signMap.put("termIp", receiveMap.get("termIp"));
                signMap.put("orderDetail", receiveMap.get("orderDetail"));
                signMap.put("accountFlag", "N");
                signMap.put("seqId", "901" + txnTime + RandomUtils.getNumber(8));
                signMap.put("codeUrl", "https://qr.alipay.com/bax" + RandomUtils.getNumber(5) + RandomUtils.getCharAndNumr(16));
            }
        } else {
            logger.error("txnSubType is null!");
        }
        return signMap;
    }

    /**
     * 组装异步通知Map
     * @param finalMap
     * @param notifySignMap
     * @return
     */
    public static Map buildAsynNotifyMap(Map finalMap, Map notifySignMap) {

        notifySignMap.put("encoding", finalMap.get("encoding"));
        notifySignMap.put("signMethod", "02");
        notifySignMap.put("txnType", finalMap.get("txnType"));
        notifySignMap.put("txnSubType", finalMap.get("txnSubType"));
        notifySignMap.put("channelType", finalMap.get("channelType"));
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
        notifySignMap.put("txnTime", DateUtil.getDate("yyyyMMddHHmmss"));
        notifySignMap.put("settleAmt", finalMap.get("txnAmt"));
        notifySignMap.put("settleCurrencyCode", "156");
        notifySignMap.put("settleDate", DateUtil.getDate("yyyyMMdd"));

        if (!StringUtils.isEmpty(finalMap.get("txnSubType").toString())) {
            if (finalMap.get("txnSubType").equals("010130")) {
                notifySignMap.put("payAccessType", finalMap.get("payAccessType"));
                notifySignMap.put("txnSeqId", finalMap.get("txnSeqId"));
                notifySignMap.put("endTime", DateUtil.getDate("yyyyMMddHHmmss"));
                notifySignMap.put("transactionId", "4008142001" + DateUtil.getTimeFormat() + RandomUtils.getNumber(4));
                notifySignMap.put("bankType", "CFT");
            } else if (finalMap.get("txnSubType").equals("010302")) {
                notifySignMap.put("orderDetail", finalMap.get("orderDetail"));
                notifySignMap.put("seqId", finalMap.get("seqId"));
                notifySignMap.put("transactionId", DateUtil.getDate("yyyyMMdd") + "2100100" + RandomUtils.getNumber(13));
            }
        } else {
            logger.error("txnSubType is null!");
        }
        return notifySignMap;
    }

}
