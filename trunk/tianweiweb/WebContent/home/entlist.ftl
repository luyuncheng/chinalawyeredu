﻿<#escape x as (x)!"">
﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>欢迎来到天威广告业务管理站点</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/base/jquery.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="../js/home.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/login.js"></script>	
</head>

<body>
<div id="main">
  <div id="top">
    <div id="ad"><div id="logo"><img src="../images/logo.gif" /><br />
    深圳市天威广告有限公司网站</div><div id="topbannal"><img src="../images/AD.gif" width="537" height="67" /></div></div>
    <span class="title">&nbsp;&nbsp;</span> <span class="topsp">
<a href="#">设为首页</a>│<a href="#">加入收藏</a>|<a href="../user/userbaseview.action"><#if currentRole=2>我的企业</#if><#if currentRole=1>我的天威</#if></a></span> </div>
  <div id="bannal"> <span class="ad">
    <marquee scrollamount="5" onMouseOver="stop()" onMouseOut="start()" id="noticediv"></marquee>
    </span>
      <div id="menu">&nbsp;
      <a href="/home/home.action">首页</a>&nbsp; &nbsp;
      <a href="#">视窗直播</a> &nbsp;&nbsp; 
      <a href="/home/productlist.action">精品服务</a> &nbsp;&nbsp;
      <a href="/home/entlist.action">企业展示</a> &nbsp;&nbsp; 
      <a href="/home/myshowlist.action">我型我秀</a> &nbsp;&nbsp; 
      <a href="#">网上支付</a> &nbsp; &nbsp;<a href="#">在线帮助</a> 
  </div>
  </div>
  <div id="nr2">
    <div id="left">
	  <#if currentUserid=0>
	  <div class="loginbar"><span class="newsl">用户登陆</span></div>
	  
	  <span class="login">
	  <form id="loginform" action="../common/login.action" method="post">
	      用户名：<input name="loginname" type="text" class="textfield"/><a href="../regist/regist!input.action">注册用户</a><br />
                密&nbsp;&nbsp;码：<input name="password" type="password" class="textfield"/><a href="/regist/findpwd.html">忘记密码</a><br />
                 验证码：<img id="imgObj" src="../verify/VerifyCode.jsp" width="50" height="15" alt="" /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="verifycode" type="text" size="4" class="textfield2" />&nbsp;&nbsp;  <a href="#" onclick="changeImg()">换一张</a><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" name="button" onclick="stlogin()" value="登陆" class="btn"/>&nbsp;&nbsp;
        <input type="reset" name="Submit2" value="重置" class="btn"/>
      </form>
      </span>
      </#if>
       <div class="clear"></div>
				  <div class="leftad">flash</div>
	              <div class="clear"></div>
		<div id="help">
		<span class="helpbar">在线客服</span>
		<span class="helpbody"><li>QQ:123456</li><li>QQ:234567</li><li>QQ:123456</li><li>QQ:234567</li><li>QQ:123456</li><li>QQ:234567</li><li>QQ:123456</li></span>
		<span class="helpbottom"><a href="#">客服热线：96933<br />
加盟热线：0755-83027988</a></span>
		</div>
		<div class="leftad">广告</div>
		<div class="leftad">广告</div>

	</div>
	<div id="right">
	  <div id="search"><span class="search">搜索&nbsp;&nbsp;<select name="select">
	  <option>搜资讯</option>
	  <option>搜产品</option>
	  <option>搜企业</option>
	  <option>搜个人</option>
	</select>
	<input name="" type="text" class="textfield3"/>&nbsp;&nbsp;<input name="" type="submit" class="btn" value="GO!" />
&nbsp;&nbsp;<!--<a href="#">高级搜索</a> <a href="#">帮助</a>--></span>
	  </div>
      <div class="companybar"><span class="newsl">企业展示</span></div>

<#list page.items as t>
<#assign userdetail=userutil.getUserdetail(t.id) />
<div class="companylist">
	<div class="companylogo">
		<li class="logo"><a href="/userpage/index.action?userid=${t.id}" target="_blank"><img src="${t.logo}" /><br />${t.userName}</a></li>
	</div>
	<div class="companyinfo">
		<span class="info">简介：</span>
		<span class="infonr">${userdetail.summary}</span>
		<!--<a class="more" href="#">详细信息>></a>-->
	</div>
</div>
</#list>	  
<span class="skip">${pageString} </div>
  </div>
<div class="clear"></div>
  <div id="bottom">
  <span class="link">友情链接： <a href="#">天威视讯</a>|<a href="#">天威宽频</a>|<a href="#">天威网络电视</a>|<a href="#">深圳迪威特</a>|<a href="#">深圳新闻网</a>|<a href="#">深圳电视台</a>|<a href="#">新浪网</a>|<a href="#">搜狐网</a>|<a href="#">中国网联网</a>|<a href="#">中国文化网</a>|<a href="#">深圳人才网</a>|<a href="#">名车网</a></span>
  <span class="copyright"><a href="#">关于我们</a>  |  <a href="#">广告服务</a>  |  <a href="#">法律声明</a>  |  <a href="#">服务条款</a>  |  <a href="#">联系我们</a>  |  <a href="#">网站地图</a>  |  <a href="#">合作伙伴</a>  |  <a href="#">人才招聘</a>
  <br />
  天威广告有限公司版权所有 Copyright @ 2009 www.TOPWAY.com All Right Reserved
  <br />
  ICP证号：粤ICP备09851114号 深圳市天威视讯股份有限公司旗下网站</span>  </div>
</div>
</div>

</body>
</html>
</#escape>
