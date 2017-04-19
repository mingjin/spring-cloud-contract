<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta charset="GBK">
      <title>网银支付交易</title>
      <LINK href="../images/layout.css" type=text/css rel=stylesheet>
      <style type="text/css">
      .box{
          width:896px; height:396px; background:url(../images/zjfkbj.gif) no-repeat; border-collapse:collapse;
      }
      .fong1{
          font-size:14px; color:#3393c9; font-weight: bold; line-height:35px; margin:0px; padding:0px;
      }
      .fong2{
          font-size:12px; color:#666;  line-height:15px; margin:0px; padding:0px;
      }
      .sj_inpt{
          border:solid 1px #d2d2d2; width:130px; line-height:18px; height:18px; float:left;
      }
      </style>
      <SCRIPT language=JavaScript>
      <%
        request.setCharacterEncoding("UTF-8");

        String requestUrl = request.getParameter("requestUrl");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String tradeAmount = request.getParameter("tradeAmount");
        String currency = request.getParameter("currency");
        String defaultBank = request.getParameter("defaultBank");
        String isApp = request.getParameter("isApp");
        String payerName = request.getParameter("payerName");
        String merchantId = request.getParameter("merchantId");
        String key = request.getParameter("key");
        String orderNo = request.getParameter("orderNo");
        String service = request.getParameter("service");
      //  String payMethod = request.getParameter("payMethod");
        String sellerEmail = request.getParameter("sellerEmail");
        String signType = request.getParameter("signType");
        String paymentType = request.getParameter("paymentType");
        String notifyUrl = request.getParameter("notifyUrl");
        String returnUrl = request.getParameter("returnUrl");
        String charset = request.getParameter("charset");
      %>
      </SCRIPT>
    </head>
<BODY >
<FORM name=rongpayment onSubmit="return CheckForm();" action=PayToBank.jsp method=post target="_blank">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  
    <td align="center" valign="top">
   	  <table align="center" class="box">
          <tr>
            <td align="top" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              
              <tr>
                <td align="center"><table width="80%" border="0" cellspacing="0" cellpadding="0" style="padding:5px; margin-top:50px;">
                <input type="hidden" id="requestUrl" name="requestUrl" value="<%=requestUrl%>" />
				<input type="hidden" id="productName" name="productName" value="<%=productName%>"/>
				<input type="hidden" id="productDesc" name="productDesc" value="<%=productDesc%>"/>
				<input type="hidden" id="tradeAmount" name="tradeAmount" value="<%=tradeAmount%>"/>
				<input type="hidden" id="currency" name="currency" value="<%=currency%>"/>
				<input type="hidden" id="defaultBank" name="defaultBank" value="<%=defaultBank%>"/>
				<input type="hidden" id="isApp" name="isApp" value="<%=isApp%>"/>
				<input type="hidden" id="payerName" name="payerName" value="<%=payerName%>"/>
				<input type="hidden" id="merchantId" name="merchantId" value="<%=merchantId%>"/>
                <input type="hidden" id="key" name="key" value="<%=key%>"/>
                <input type="hidden" id="orderNo" name="orderNo" value="<%=orderNo%>"/>
                <input type="hidden" id="service" name="service" value="<%=service%>"/>
                <input type="hidden" id="sellerEmail" name="sellerEmail" value="<%=sellerEmail%>"/>
                <input type="hidden" id="signType" name="signType" value="<%=signType%>"/>
                <input type="hidden" id="paymentType" name="paymentType" value="<%=paymentType%>"/>
                <input type="hidden" id="notifyUrl" name="notifyUrl" value="<%=notifyUrl%>"/>
                <input type="hidden" id="returnUrl" name="returnUrl" value="<%=returnUrl%>"/>
                <input type="hidden" id="charset" name="charset" value="<%=charset%>"/>
                  <tr>
                      <td height="40" align="right">是否直连银行:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;">
                        <input type="radio" id="paymethod" name="paymethod" onClick="on_hide();" value="directPay"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" id="paymethod" name="paymethod" value="bankPay" checked onClick="on_hide();"/>否
                      </td>
                      <td>&nbsp;</td>   
                    </tr>
                    
                    <tr>
                      <td height="40">&nbsp;</td>
                      <td style="padding:5px;">    	
                        <INPUT TYPE=image NAME="SUB" src="../common/images/qrzfk.gif">
                        </td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  <table>
                    <tr>
                      <td colspan="3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="bank" style="display:none">
                       
                          <tr>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CMB"/></td>
                            <td width="10%"><img src="../common/images/cmb.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="ICBC"/></td>
                            <td width="10%"><img src="../common/images/icbc.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CCB"/></td>
                            <td width="10%" align="left"><img src="../common/images/ccb.png"/></td>
                           </tr>
                          <tr>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="BOC"/></td>
                            <td width="10%"><img src="../common/images/boc.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="ABC"/></td>
                            <td width="10%"><img src="../common/images/abc.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="BOCM"/></td>
                            <td width="10%" align="left"><img src="../common/images/bankcomm.png"/></td>
                           </tr>
                          <tr>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="SPDB"/></td>
                            <td width="10%"><img src="../common/images/spdb.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CGB"/></td>
                            <td width="10%"><img src="../common/images/gdb.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CITIC"/></td>
                            <td width="10%" align="left"><img src="../common/images/ecitic.png"/></td>
                           </tr>
                          <tr>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CEB"/></td>
                            <td width="10%"><img src="../common/images/ceb.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CIB"/></td>
                            <td width="10%"><img src="../common/images/cib.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="LITEPAY"/></td>
                            <td width="10%" align="left"><img src="../common/images/LITEPAY.png"/></td>
                           </tr>
                          <tr>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="CMBC"/></td>
                            <td width="10%"><img src="../common/images/cmbc.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="HXB"/></td>
                            <td width="10%"><img src="../common/images/hxb.png"/></td>
                            <td width="5%" height="85" align="right"><input type="radio" name="default_bank" value="SPA"/></td>
                            <td width="10%" align="left"><img src="../common/images/pingan.png"/></td>
                           </tr>
                        </table> 
                      </td>
                      
                    </tr>
                  </table> 
				</td>
              </tr>
            </table></td>
          </tr>
        </table>
        </td>
    </tr>
  </table>

</FORM>

</BODY>
</html>
<script language="javascript">
function on_hide()
{
	if(document.getElementById("paymethod").checked == true)
	{
		document.getElementById("bank").style.display = "block";
	}
	else
	{
		document.getElementById("bank").style.display = "none";
	}
}
</script>

