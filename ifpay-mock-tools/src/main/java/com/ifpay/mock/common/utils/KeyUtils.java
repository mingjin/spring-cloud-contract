package com.ifpay.mock.common.utils;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by harvey.xu on 2017/4/11.
 */
public class KeyUtils {

    private static Logger logger = LoggerFactory.getLogger(KeyUtils.class.getName());

    /**
     * @param merId  渠道
     * @return
     */
    public static String getKey(String merId) {
        String key = "";
        if (!StringUtils.isEmpty(merId)) {
            if (merId.equals("996600008000060")) {
                key = "87370168370964458408069453373324";
            }
            if (merId.equals("996600008000061")) {
                key = "88912588894196800940732731517512";
            }
            if (merId.equals("996600009000068")) {
                key = "83694572786487019829744362696663";
            }
            /*生产环境*/
            if (merId.equals("886600000007427")) {
                key = "83087647051727142242262771953693";
            }
            if (merId.equals("886600000007428")) {
                key = "85684601053298564298066981485117";
            }
            if (merId.equals("886600000007429")) {
                key = "85413651903156121723792205406495";
            }
        }
        return key;
    }

    /**
     * 如果request请求中的key非空，则使用request中的key
     * @param inKey  key
     * @param channel  渠道
     * @return
     */
    public String getKey(String inKey, String channel) {
        String key = "";
        if (!StringUtils.isEmpty(inKey)) {
            key = inKey;
        } else {
//            String keyFlag;
            if (!StringUtils.isEmpty(channel)) {
                if (channel.contains("100000000002262")) {
                    key = "8ccd54184823a77g6217dgf6dge3493ad0g7d0fce7d6b7b59ad18bf8e7ce301g";
//                    keyFlag = "agentpay";
//                    key = new ProUtils().getKeyFromProp(keyFlag);
                }
                if (channel.contains("100000000002004")) {
                    key = "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f";
                }
                if (channel.contains("100000000002084")) {
                    key = "cg99709609c51gbd71f4ef67d432cbd4248f964bea1443b777e97296d7ac64ad";
                }
                if (channel.contains("100000000002224")) {
                    key = "ea7e29d050ce08289e4671062bgaf800g397aeggg313gb8f47ga26c44caff37e";
                }
                if (channel.contains("100000000002285")) {
                    key = "de4011e96d9cc7c4b7bc2dbca60e0bf18ee80e316224ac2ba759c4g65c012d58";
                }
            }
        }
        return key;
    }

}
