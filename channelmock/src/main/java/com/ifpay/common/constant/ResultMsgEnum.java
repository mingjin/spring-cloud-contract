package com.ifpay.common.constant;

/**
 * Created by harvey.xu on 2016/3/2.
 */
public interface ResultMsgEnum {

    /*
     * 成功
     */
    public String SUCCESS = "SUCCESS";

    /*
     * stage为空
     */
    public String STAGE_ISNULL = "STAGE CANN'T NULL";

    /*
     * userId为空
     */
    public String USERID_ISNULL = "USERID CANN'T NULL";

    /*
     * userId为空
     */
    public String CASENO_ISNULL = "CASENO CANN'T NULL";

    /*
     * 数据库执行异常
     */
    public String DB_ERROR = "DATABASE EXECUTE ERROR";

    /*
     * 系统异常
     */
    public String SYSTEM_ERROR = "SYSTEM ERROR";
}
