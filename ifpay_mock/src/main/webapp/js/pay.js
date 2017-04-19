/**
 * Created by harvey.xu on 2017/03/19.
 */
function getRandom(id) {
    document.getElementById(id).value = parseInt(100000000000000 * Math.random());
}
// 获取当前的日期时间 格式“yyyyMMddHHMMSS” ， paytime 是元素id
function getNowFormatDate(payTime) {
    var date = new Date();
    var month = formatDate(date.getMonth() + 1);
    var strDate = formatDate(date.getDate());
    var hours = formatDate(date.getHours());
    var minutes = formatDate(date.getMinutes());
    var seconds = formatDate(date.getSeconds());
    var currentdate = "" + date.getFullYear() + month + strDate + hours + minutes + seconds + parseInt(1000 * Math.random());
    document.getElementById(payTime).value = currentdate;
}
// yyyyMMdd
function getFormatDate(id) {
    var date = new Date();
    var month = formatDate(date.getMonth() + 1);
    var strDate = formatDate(date.getDate());
    var currentdate = "" + date.getFullYear() + month + strDate;
    document.getElementById(id).value = currentdate;
}
// 拼装批次号
function getBatchCurrnum(batchCurrnum) {
    var date = new Date();
    var month = formatDate(date.getMonth() + 1);
    var strDate = formatDate(date.getDate());
    var hours = formatDate(date.getHours());
    var minutes = formatDate(date.getMinutes());
    var seconds = formatDate(date.getSeconds());
    var currentdate = "Batch" + date.getFullYear() + month + strDate + hours + minutes + seconds + parseInt(1000 * Math.random());
    document.getElementById(batchCurrnum).value = currentdate;
}
// 拼装商户订单号
function getOrderId(orderId) {
    var date = new Date();
    var month = formatDate(date.getMonth() + 1);
    var strDate = formatDate(date.getDate());
    var hours = formatDate(date.getHours());
    var minutes = formatDate(date.getMinutes());
    var seconds = formatDate(date.getSeconds());
    var currentdate = "Order" + date.getFullYear() + month + strDate + hours + minutes + seconds + parseInt(1000 * Math.random());
    document.getElementById(orderId).value = currentdate;
}
function formatDate(str) {
    if (str >= 0 && str <= 9) {
        str = "0" + str;
    }
    return str;
}

function SelectValue(s, userId) {
    document.getElementById(userId).value = s;
}

function SelectMerchant(s, merchantId, sellerEmail) {
    var i=parseInt(s);
    switch(i)
    {
        case 1:
            document.getElementById(merchantId).value="100000000002004";
            document.getElementById(sellerEmail).value="402673978@qq.com";
            break;
        case 2:
            document.getElementById(merchantId).value="100000000002262";
            document.getElementById(sellerEmail).value="jianfeng.xu@ftvalue.com";
            break;
        case 3:
            document.getElementById(merchantId).value="100000000002084";
            document.getElementById(sellerEmail).value="282514781@qq.com";
            break;
        case 4:
            document.getElementById(merchantId).value="100000000002224";
            document.getElementById(sellerEmail).value="306662588@qq.com";
            break;
        case 5:
            document.getElementById(merchantId).value="100000000002285";
            document.getElementById(sellerEmail).value="hong.li@ftvalue.com";
            break;
    }
    // document.getElementById(merchantId).value = s;
}
//判断生产环境key不能为空
function checkKey(requestUrlTemp, key) {
    return true;
    // 以下废弃
    //var str = document.getElementById(requestUrlTemp).options[document.getElementById(requestUrlTemp).selectedIndex].text;
    ////alert(str);
    //var keyStr = document.getElementById(key).value;
    ////alert(keyStr);
    //if (str == '生产环境' && !keyStr && typeof(keyStr) != "undefined") {
    //    alert("选择生产环境时，商户KEY不能为空！");
    //    return false;
    //}
    //else {
    //    return true;
    //}
}

// 选择URL
function SelectUrl(s,requestUrl,notifyUrl,returnUrl,buyerEmail){
    var i=parseInt(s);
    switch(i)
    {
        case 1:
            document.getElementById(requestUrl).value="http://114.215.202.134:18170/portal";
            document.getElementById(notifyUrl).value="http://127.0.0.1:8090/notify_url.jsp";
            document.getElementById(returnUrl).value="http://127.0.0.1:8090/return_url.jsp";
            document.getElementById(buyerEmail).value=1;
            break;
        case 2:
            document.getElementById(requestUrl).value="http://114.215.242.9:18170/portal";
            document.getElementById(notifyUrl).value="http://127.0.0.1:8090/notify_url.jsp";
            document.getElementById(returnUrl).value="http://127.0.0.1:8090/return_url.jsp";
            document.getElementById(buyerEmail).value=2;
            break;
        case 3:
            document.getElementById(requestUrl).value="http://10.28.98.191:18170/portal";
            document.getElementById(notifyUrl).value="http://127.0.0.1:8090/notify_url.jsp";
            document.getElementById(returnUrl).value="http://127.0.0.1:8090/return_url.jsp";
            document.getElementById(buyerEmail).value=3;
            break;
        case 4:
            document.getElementById(requestUrl).value="https://ebank.payworth.net/portal";
            document.getElementById(notifyUrl).value="http://127.0.0.1:8090/notify_url.jsp";
            document.getElementById(returnUrl).value="http://127.0.0.1:8090/return_url.jsp";
            document.getElementById(buyerEmail).value=4;
            break;
    }
}

function getRandom() {

    var Num="";
    for(var i=0;i<4;i++)
    {
        Num+=parseInt(10 * Math.random());
    }
    document.getElementById("cvv").value=Num;
}

function initRequestUrl() {
    // 本地调试
    var requestUrl="http://10.199.253.31:8901/paypalmock/VpalVcpServlet";

    // 测试环境--待定
    //var requestUrl="http://ip:port/VpalVcpServlet";

    document.getElementById("requestUrl").value=requestUrl;
}

function getRandomAcctNo(payeeName, payeeAcctNo, payeeAcctBankName, remark) {
    var payeeNameArray=["孙亮","许建峰","刘融","李红","孙良","许剑锋","刘小融","李虹","范伟杰","范伟婕"];
    var payeeAcctNoArray=["6222021001036221015","6214850210332646","6217001210063580944","6228480392725997118","6222021001036221015",
                          "6214850210332646","6217001210063580945","6228480392725997119","9558801001173521509","9558801001173521506"];
    var payeeAcctBankNameArray=["中国工商银行","招商银行","中国建设银行","中国农业银行","中国工商银行",
                                "招商银行","中国建设银行","中国农业银行","中国工商银行","中国工商银行"];
    var remarkArray=["成功","成功","卡号错","卡号错","用户名错","用户名错","用户名错","用户名错","成功","用户名错"];
    var i = Math.floor(Math.random()*10);
    document.getElementById(payeeName).value=payeeNameArray[i];
    document.getElementById(payeeAcctNo).value=payeeAcctNoArray[i];
    document.getElementById(payeeAcctBankName).value=payeeAcctBankNameArray[i];
    document.getElementById(remark).value=remarkArray[i];
}

function getRandomAmount(amount) {
    document.getElementById(amount).value=Math.random().toFixed(2);
}