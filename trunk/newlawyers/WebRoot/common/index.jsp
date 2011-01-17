<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>中华全国律师协会培训系统</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="js/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/tab.js"></script>
<script language="javascript" type="text/javascript">
function submitOnClick()
{
	
  if(isEmpty(document.form1.loginname.value)){
    alert("请输入用户账号，不能为空！");
    document.form1.loginname.focus();
    return false;
  }else{
    if(getStringLength(document.form1.loginname.value)>20){
      alert("请输入正确用户账号，长度1-20字符！");
      document.form1.loginname.focus();
      return false;
    }
    /*
    else{
      if(!isGoodChar(document.form1.loginname.value)){
        alert("请输入正确用户账号，为字母、数字、汉字！");
        document.form1.loginname.focus();
        return false;
      }
    }*/
  }
  if(isEmpty(document.form1.password.value)){
    alert("请输入用户密码，不能为空！");
    document.form1.password.focus();
    return false;
    }
   // alert(document.form1);
  document.form1.submit();
  return true;

}
function submitForm(e){
	//e=e||window.event
	//if(e.keyCode==13){
  	 //  submitOnClick();
  //}
}
function selectRole(roleid){
 if(roleid==2||roleid==3)
  document.form1.action="/manager/common/login.pl";
 else
 document.form1.action="/common/login.pl";
}
</script>
</head>
<body>
<div class="header">
  <div class="logo left"><a href="index.html"><img src="../images/logo.gif" width="234" height="51" /></a></div>
  <div class="wen_nav left">
    <ul>
      <li><a href="#">省级分站：</a><a href="list.html">北京</a>&nbsp;&nbsp;<a href="#">上海</a>&nbsp;&nbsp;<a href="#">天津</a>&nbsp;&nbsp;<a href="#">重庆</a>&nbsp;&nbsp;<a href="#">广东</a>&nbsp;&nbsp;<a href="#">广西</a>&nbsp;&nbsp;<a href="#">河南</a>&nbsp;&nbsp;<a href="#">江苏</a>&nbsp;&nbsp;<a href="#">浙江</a>&nbsp;&nbsp;<a href="#">湖北</a>&nbsp;&nbsp;<a href="#">湖南</a>&nbsp;&nbsp;<a href="#">四川</a>&nbsp;&nbsp;<a href="#">更多</a></li>
      <li><a href="#">市级分站：</a><a href="list.html">深圳</a>&nbsp;&nbsp;<a href="#">东莞</a>&nbsp;&nbsp;<a href="#">杭州</a>&nbsp;&nbsp;<a href="#">南京</a>&nbsp;&nbsp;<a href="#">长春</a>&nbsp;&nbsp;<a href="#">珠海</a>&nbsp;&nbsp;<a href="#">中山</a>&nbsp;&nbsp;<a href="#">西安</a>&nbsp;&nbsp;<a href="#">太原</a>&nbsp;&nbsp;<a href="#">青岛</a>&nbsp;&nbsp;<a href="#">大连</a>&nbsp;&nbsp;<a href="#">合肥</a>&nbsp;&nbsp;<a href="#">更多</a></li>
    </ul>
  </div>
</div>
<div class="blank15px"></div>
<div class="nav">
  <div class="nav_left"><img src="../images/nav_03.jpg" /></div>
  <div class="nav_con left">
    <div class="soso right">
      <form action="" method="get">
        <input type="text"  class="search_input1" value="站内搜索..."onfocus="if(value=='站内搜索...') {value=''}" onblur="if(value=='') {value='站内搜索...'}" />
        <input name="input" type="button" class="search_btn"/>
      </form>
    </div>
    <ul>
      <li><a href="#">首页</a></li>
      <li><a href="#">培训动态</a></li>
      <li><a href="#">培训通知</a></li>
      <li><a href="#">师资介绍</a></li>
      <li><a href="#">课程介绍</a></li>
      <li><a href="#">培训论坛</a></li>
      <li><a href="#">征集义工</a></li>
    </ul>
  </div>
  <div class="nav_right"><img src="../images/nav_08.jpg" /></div>
</div>
<div class="blank15px"></div>
<div class="con">
  <div class="con_deng_left left">
    <div class="con_denglu">
      <div class="con_denglu_title"></div>
      <div class="con_login">
        <form id="form1" name="form1" method="post" action="../common/login.pl">
        <ul class="ul1_ee">
       <!--   <li>
            <label>选择区域：</label>
            <input type="text" name="textfield2" class="kuang"/>
          </li>-->
          <li>
            <label>登录身份：</label>
            <select name="loginRole" id="loginRole" onchange="selectRole(this.value)">
               <option value="1">律师</option>
               <option value="0">公证员</option>
               <option value="2">管理员</option>
               <option value="3">授课老师</option>
             </select>    
          </li>
          <li>
            <label>帐&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
               
              <input type="text" name="loginname" class="kuang" onkeydown="submitForm()"/>
             
          </li>
          <li>
            <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <input type="password" name="password" class="kuang" onkeydown="submitForm()"/>
          </li>
          <!-- 
          <li>
          
              <label style=" margin:0 0 0 5px;">
                <input type="radio" name="radiobutton" value="radiobutton" />
              </label>
              记住账号　
              <label>
                <input type="radio" name="radiobutton" value="radiobutton" />
              </label>
              记住密码
          </li> -->
          <li> </li>
       
        </ul>
        <ul class="ul2_2">
          <li>
            <input name="submit" type="submit"  class="anliu_2"   onclick="submitOnClick()"/>
            
          </li>
        </ul>
        <!-- 
        <ul class="ul2_4">
          <li>
            <input name="" type="button"  class="anliu_3"/>
          </li>
        </ul>
        -->
        <ul class="ul2_4">
          <li>
            <input name="" type="button"  class="anliu_4" onclick="document.form1.reset()"/>
          </li>
        </ul>
    	     </form>
      </div>
    </div>
    <div class="con_ke">
      <div class="con_ke_title">
        <h2>课件查询</h2>
      </div>
      <div class="line1"></div>
      <form action="../common/lessonQuery.pl" method="post">
        <ul class="log">
         <!--  <li>
            <label>选择区域：</label>
            <input type="text" name="textfield" class="ipu"/>
          </li>
          
          <li>
            <label>登录身份：</label>
            <input type="text" name="textfield2" class="ipu"/>
          </li> -->
          <li>
            <label>课件名称：</label>
            <input type="text" name="title" class="ipu"/>
          </li>
        </ul>
        <input name="" type="button"  class="search_btn2"  onclick="lessonQueryClick()"/>
        <!-- 
        <input name="" type="button"  class="search_btn3" onclick="" />
        -->
      </form>
    </div>
    <div class="con_kej">
      <div class="con_kej_title">
        <h2>课件类型及数量</h2>
      </div>
      <div class="line1"></div>
      <ul class="con_right_bottom_con">
          <s:iterator value="@com.changpeng.lessons.util.CommonDatas@TOP_LEVEL_TYPE">
    <s:if test="typeid!=0">
     <li><span>${lessoncnt }个</span><a href="#">${typename }</a> </li>
    </s:if>
      </s:iterator>

      </ul>
    </div>
    <div class="con_guo">
      <div class="con_guo_title">
        <h2>课件来源及构成</h2>
      </div>
      <div class="line1"></div>
      <img src="../images/tup.gif" width="184" height="177" /> </div>
    <div class="con_qu">
      <div class="con_qu_title">
        <h2>课件增长趋势</h2>
      </div>
      <div class="line1"></div>
      <span><img src="../images/b.gif" width="198" height="122" /></span> </div>
  </div>
  <div class="con_tu_right left">
    <div class="con_t_w">
      <div class="con_t_title">
        <h2>推荐课程</h2>
      </div>
      <div class="con_zhengwen">
       <!-- 
        <div class="con_l left"><img src="../images/tu_3.gif" width="207" height="162" /></div>
        -->
       <s:iterator value="@com.changpeng.lessons.util.CommonDatas@RECOMMAND_LESSONS" status="stat">
       <s:if test="#stat.index!=0">
        <div class="con3_left_center_list left">
          <h3><a href="#" title="${title }"><img src="${httpPic }" width="52" height="35" border="0"/></a></h3>
          <ul class="con_ww">
            <li><a href="#"><strong>${titleTrim}</strong></a></li>
           <!--  <li><a href="#">民商事热点，不可错过</a></li> -->
          </ul>
        </div>
        </s:if>
        <s:else>
        <div class="con_l left"><img src="${httpPic }" width="207" height="162" title="${title }"/></div>
        </s:else>
       </s:iterator>
       
      </div>
    </div>
    <div class="con_text_t">
      <div class="con_text_title">
        <h2>上升最快课程</h2>
      </div>
      <div class="line1"></div>
      <div class="con_wenzi">
        <ul>
          <li><span>9.6</span>1 <a href="#">物权法的理念与实践</a></li>
          <li><span>9.6</span>2 <a href="#">侵权责任法的司法解释</a></li>
          <li><span>9.6</span>3 <a href="#">仲裁实务</a></li>
          <li><span>9.6</span>4 <a href="#">刑事辩护散谈</a></li>
          <li><span>9.6</span>5 <a href="#">行政法理论与实务</a></li>
          <li><span>9.6</span>6 <a href="#">执行中的法律问题</a></li>
          <li><span>9.6</span>7 <a href="#">侵权责任法</a></li>
        </ul>
      </div>
    </div>
    <div class="con_bz">
      <div class="con_bz_title">
        <h2>最受欢迎课程</h2>
      </div>
      <div class="con_renwu">
       <!-- 
        <div class="con_sz">
         <ul>
            <li id="two1" onmouseover="setTab('two',1,7)" class="hover">民商事&nbsp;&nbsp;|</li>
            <li id="two2" onmouseover="setTab('two',2,7)">刑事&nbsp; |</li>
            <li id="two3" onmouseover="setTab('two',3,7)">行政&nbsp;&nbsp;|</li>
            <li id="two4" onmouseover="setTab ('two',4,7)">执行&nbsp;&nbsp;|</li>
            <li id="two5" onmouseover="setTab ('two',5,7)">事务所管理</li>
            <li id="two6" onmouseover="setTab ('two',6,7)">|&nbsp;&nbsp;宣传&nbsp;|</li>
            <li id="two7"onmouseover="setTab ('two',7,7)">其他&nbsp;&nbsp;|</li>
          </ul>
        </div>
         -->
        <div class="Contentbox2">
        <div id="con_two_1">
          <ul class="con22_pic">
          <s:iterator value="@com.changpeng.lessons.util.CommonDatas@POPULAR_LESSON">
            <li><img src="${httpPic }" width="127" height="99" border="0"/>
            <span><a href="#">${titleTrim }</a></span>
            </li>
              </s:iterator>
          </ul>
        </div>
		</div>
      </div>
    </div>
    <div class="con_zui">
      <div class="con_zui_title">
        <h2>最新课程</h2>
      </div>
      <div class="line1"></div>
     <s:iterator value="@com.changpeng.lessons.util.CommonDatas@LATEST_LESSONS">
      <div class="wzi">
        <h4><a href="#">${titleTrim }</a></h4>
        <p>(<s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/>)<s:date name="lessondate" format="yyyy-MM-dd"/></p>
      </div>
     </s:iterator>
    </div>
    <div class="con_px">
      <div class="con_px_title">
        <h2>培训动态</h2>
      <span><a href="#">更多>></a></span></div>
      <ul class="con_right_px">
        <li><span>12-05</span><a href="#">社工专家分析未成年犯触法原因</a></li>
        <li><span>12-05</span><a href="#">驻校社工化解学生“成长的烦恼” </a></li>
        <li><span>12-05</span><a href="#">什么是社会工作者</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
        <li><span>12-05</span><a href="#">社工教育要提倡和传递一种精神</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
      </ul>
    </div>
    <div class="con_tz">
      <div class="con_tz_title">
        <h2>培训通知</h2>
      <span><a href="#">更多>></a></span></div>
      <ul class="con_right_px">
        <li><span>12-05</span><a href="#">社工专家分析未成年犯触法原因</a></li>
        <li><span>12-05</span><a href="#">驻校社工化解学生“成长的烦恼” </a></li>
        <li><span>12-05</span><a href="#">什么是社会工作者</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
        <li><span>12-05</span><a href="#">社工教育要提倡和传递一种精神</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
      </ul>
    </div>
    <div class="con_jsgy">
      <div class="con_jsgy_title">
        <h2>讲师感言</h2>
      <span><a href="#">更多>></a></span></div>
      <ul class="con_right_px">
        <li><span>12-05</span><a href="#">社工专家分析未成年犯触法原因</a></li>
        <li><span>12-05</span><a href="#">驻校社工化解学生“成长的烦恼” </a></li>
        <li><span>12-05</span><a href="#">什么是社会工作者</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
        <li><span>12-05</span><a href="#">社工教育要提倡和传递一种精神</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
      </ul>
    </div>
    <div class="con_yg">
      <div class="con_yg_title">
        <h2>义工心声</h2>
      <span><a href="#">更多>></a></span></div>
      <ul class="con_right_px">
        <li><span>12-05</span><a href="#">社工专家分析未成年犯触法原因</a></li>
        <li><span>12-05</span><a href="#">驻校社工化解学生“成长的烦恼” </a></li>
        <li><span>12-05</span><a href="#">什么是社会工作者</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
        <li><span>12-05</span><a href="#">社工教育要提倡和传递一种精神</a></li>
        <li><span>12-05</span><a href="#">民政工作需要社工理论支持</a></li>
      </ul>
    </div>
  </div>
  <div class="blank15px"></div>
  <div class="xiao_gg"><img src="../images/xiao_banner.gif" width="959" height="66" /></div>
  <div class="blank15px"></div>
  <div class="in_l">
    <div class="in_left left">
      <div class="in_title">会员统计</div>
      <ul class="in_wen">
        <li>执业律师：<s:property value="@com.changpeng.common.CommonDatas@USER_STATICS[1]"/></li>
        <li>实习律师：<s:property value="@com.changpeng.common.CommonDatas@USER_STATICS[2]"/></li>
        <li>律师事务所：<s:property value="@com.changpeng.common.CommonDatas@USER_STATICS[3]"/></li>
        <li>律师协会：<s:property value="@com.changpeng.common.CommonDatas@USER_STATICS[4]"/></li>
      </ul>
    </div>
    <div class="in_left2 left">
      <div class="in_title">最新加盟律协</div>
      <ul class="in_wen2">
            <s:iterator value="@com.changpeng.common.CommonDatas@LATEST_LVXIE">
   
     <li><a href="#">${groupname }</a></li>
      </s:iterator>
      </ul>
    </div>
    <div class="in_left3 left">
      <div class="in_title">最新加盟律师事务所</div>
      <ul class="in_wen3">
           <s:iterator value="@com.changpeng.common.CommonDatas@LATEST_OFFICE">
   
     <li><a href="#">${groupname }</a></li>
      </s:iterator>
      

      </ul>
    </div>
  </div>
  <div class="in_right left">
    <div class="in_r_title">
      <h2>最新热门课评</h2>
    <span><a href="#">更多>></a></span></div>
    <br />
    <div id="g">
      <dl>
        <dt><a href="#">很好，很符合实际！</a></dt>
        <dd class="pic"><a href="#"><img src="../images/ico_02.gif" width="140" height="116" /></a><span><a href="#">民事诉讼法热点</a></span></dd>
        <dd class="text">
          <p>评论人：钟景前&nbsp;&nbsp;&nbsp;&nbsp;评论日期:2010-09-01 15:15&nbsp;&nbsp;&nbsp;&nbsp;★★★★☆&nbsp;&nbsp;&nbsp;29回应</p>
          我在郑州河南省律师协会主办的《侵权责任法》讲座现场第一排中间位，聆听了杨立新
          教授的讲座，由衷佩服杨教授的学识和德行，讲得深入浅出、生动活泼、分析透彻和恰
          到好处。我很高兴在网上再次聆听杨教授的讲座。每一次聆听，必然给我带来更多的收
          获。说句心里话，听听杨教授的讲座，很值！……</dd>
        <dd class="more"><a href="#">[查看全文]</a></dd>
      </dl>
      <dl>
        <dt><a href="#">杨教授讲的好啊</a></dt>
        <dd class="pic"><a href="#"><img src="../images/ico_02.gif" width="140" height="116" /></a><span><a href="#">民事诉讼法热点</a></span></dd>
        <dd class="text">
          <p>评论人：钟景前&nbsp;&nbsp;&nbsp;&nbsp;评论日期:2010-09-01 15:15&nbsp;&nbsp;&nbsp;&nbsp;★★★★☆&nbsp;&nbsp;&nbsp;29回应</p>
          我在郑州河南省律师协会主办的《侵权责任法》讲座现场第一排中间位，聆听了杨立新
          教授的讲座，由衷佩服杨教授的学识和德行，讲得深入浅出、生动活泼、分析透彻和恰
          到好处。我很高兴在网上再次聆听杨教授的讲座。每一次聆听，必然给我带来更多的收
          获。说句心里话，听听杨教授的讲座，很值！……</dd>
        <dd class="more"><a href="#">[查看全文]</a></dd>
      </dl>
      <dl>
        <dt><a href="#">吕老师的课讲得非常生动</a></dt>
        <dd class="pic"><a href="#"><img src="../images/ico_02.gif" width="140" height="116" /></a><span><a href="#">民事诉讼法热点</a></span></dd>
        <dd class="text">
          <p>评论人：钟景前&nbsp;&nbsp;&nbsp;&nbsp;评论日期:2010-09-01 15:15&nbsp;&nbsp;&nbsp;&nbsp;★★★★☆&nbsp;&nbsp;&nbsp;29回应</p>
          我在郑州河南省律师协会主办的《侵权责任法》讲座现场第一排中间位，聆听了杨立新
          教授的讲座，由衷佩服杨教授的学识和德行，讲得深入浅出、生动活泼、分析透彻和恰
          到好处。我很高兴在网上再次聆听杨教授的讲座。每一次聆听，必然给我带来更多的收
          获。说句心里话，听听杨教授的讲座，很值！……</dd>
        <dd class="more"><a href="#">[查看全文]</a></dd>
      </dl>
    </div>
  </div>
  <div class="blank15px"></div>
  <div class="friends_links">
    <ul>
      <span>合作单位：</span>
      <li><a href="#">清华大学法学院</a></li>
      <li><a href="#">人民大学法学院</a></li>
      <li><a href="#">北京大学法学院</a></li>
      <li><a href="#">武汉大学法学院</a></li>
      <li><a href="#">中国政法大学</a></li>
      <li><a href="#">中南财经政法大学</a></li>
      <li><a href="#">西南财经政法大学</a></li>
      <li><a href="#">司法部</a></li>
      <li><a href="#">北京市律师协会</a></li>
      <li><a href="#">上海市律师协会</a></li>
      <li><a href="#">广东省律师协会</a></li>
      <li><a href="#">江苏省律师协会</a></li>
      <li><a href="#">南京市律师协会</a></li>
      <li><a href="#">江苏薛济民律师事务所</a></li>
    </ul>
  </div>
</div>
<div class="copy2">主办单位：中华全国律师协会     技术支持：<a href="#">长鹏软件</a> </br>备案序号：粤ICP备05082150</div>
</body>
</html>
