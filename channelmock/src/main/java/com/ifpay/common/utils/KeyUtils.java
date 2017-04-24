package com.ifpay.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by harvey.xu on 2017/4/11.
 */
public class KeyUtils {

    /**
     * @param merId  渠道
     * @return
     */
    public static String getKey(String merId) {
        String key = "";
        if (StringUtils.isNotEmpty(merId)) {
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

}
