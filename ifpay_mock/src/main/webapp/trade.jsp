<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head> 
<title>��ʱ���ʽ���</title> 
<LINK href="images/layout.css" type=text/css rel=stylesheet>
<style type="text/css"> 
.box{
	width:896px; height:396px; background:url(images/zjfkbj.gif) no-repeat; border-collapse:collapse;
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
function CheckForm()
{
	if (document.kingnspayment.method.value == "netpay") {
		document.kingnspayment.action="index.jsp";
	}else{
		document.kingnspayment.action="rongpayto.jsp";
	}
	if (document.kingnspayment.rongorder.value.length == 0) {
		alert("��Ʒ���Ʋ�����Ϊ�գ���������Ʒ����.");
		document.kingnspayment.rongorder.focus();
		return false;
	}
	if (document.kingnspayment.rongmoney.value.length == 0) {
		alert("�����븶����.");
		document.kingnspayment.rongmoney.focus();
		return false;
	}
	var reg	= new RegExp(/^\d*\.?\d{0,2}$/);
	if (! reg.test(document.kingnspayment.rongmoney.value))
	{
        alert("����ȷ���븶����");
		document.kingnspayment.rongmoney.focus();
		return false;
	}
	if (Number(document.kingnspayment.rongmoney.value) < 0.01) {
		alert("����������С��0.01.");
		document.kingnspayment.rongmoney.focus();
		return false;
	}

} 
</SCRIPT>
</head>
<BODY >
<FORM name=kingnspayment onSubmit="return CheckForm();" action=rongpayto.jsp method=post>
<input type="hidden" id="card" name="card" value="1" />
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top">
   	  <table align="center" class="box">
          <tr>
            <td align="top" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              
              <tr>
                <td align="center"><table width="80%" border="0" cellspacing="0" cellpadding="0" style="padding:5px; margin-top:50px;">
                    <TR>&nbsp;&nbsp;
                    </TR>
                    <TR>
                      <TD height="40" align="right">��Ʒ���ƣ� </TD>
                      <TD class=form-star>* </TD>
                      <TD style="padding:5px;"><INPUT  name=subject maxlength="200"></TD>
                    </TR>
                    <tr>
                      <td height="40" align="right">��Ʒ����:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;"><input type="text" name="body" value=""/></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="40" align="right">������:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;"><input type="text" name="Rongmoney" value=""/></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td height="40" align="right">������ʽ:</td>
                        <TD class=form-star>* </TD>
                        <td style="padding:5px;">
                            <select name="method" id="method">
                                <option value="-1">��ѡ��</option>
                                <option value="netpay">����</option>
                                <option value="qrcode">��ά��</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                      <td height="40" align="right">���뷽ʽ:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;">
                         <select name="isApp" id="isApp">
                           <option value="-1">��ѡ��</option>
                           <option value="app">app����</option>
                           <option value="web">web����</option>
                           <option value="h5">h5����</option>
                         </select>
                      </td>
                    </tr>   
                    <tr>
                      <td height="40" align="right">֧����ʽ:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;">
                         <select name="defaultbank" id="defaultbank">
                           <option value="WXPAY">΢��֧��</option>
                           <option value="ALIPAY">֧����</option>
                         </select>
                      </td>
                    </tr>       
                     <tr>
                      <td height="40" align="right">��ע:</td>
                      <TD class=form-star>* </TD>
                      <td style="padding:5px;"><input type="text" name="payerName" value=""/></td>
                      <td>&nbsp;</td>
                    </tr>                                                                           
                    
                    <tr>
                      <td height="40">&nbsp;</td>
                      <td style="padding:5px;">    	
                        <INPUT TYPE=image NAME="SUB" src="images/qrzfk.gif">
                        </td>
                      <td>&nbsp;</td>
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
	if(document.getElementById("defaultbank").checked == true)
	{
		document.getElementById("bank").style.display = "block";
	}
	else
	{
		document.getElementById("bank").style.display = "none";
	}
}

</script>

