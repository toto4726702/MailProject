<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html >
<html>
<head>
	<title>企业邮箱安全平台-登录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<sj:head loadAtOnce="true" jquerytheme="cupertino"/>
	<sb:head />
	<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>

 <div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="i-bar"></span> 
				<span class="i-bar"></span> 
				<span class="i-bar"></span> 
			</a> 
			<a class="brand" href="#">企业邮箱安全平台</a>

			<div class="nav-collapse">
				<ul class="nav">
					<li class="active"><a href="${pageContext.request.contextPath}/login.jsp">登录界面</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>

<div class="blank" style="height:100px"></div>

    <!-- Carousel
    ================================================== -->
   <div class="content container">
    <div class="row">
      <div class="span8 ">
        <div id="myCarousel" class="carousel slide">
          <div class="carousel-inner">
            <div class="item active">
                <img src="img/Chrysanthemum.jpg" alt="">
                <div class="carousel-caption carouselWord">
                  晓静爱赏花之——烈焰炽菊
                </div>  
            </div>
            <div class="item">
                <img src="img/Desert.jpg"/>
                <div class="carousel-caption carouselWord">
                  晓静爱旅游之——岩台云地
                </div>  
            </div>
            <div class="item">
                <img src="img/Hydrangeas.jpg" alt="">
                <div class="carousel-caption carouselWord">
                  晓静爱摄影之——清晨初放
                </div>  
            </div>
          </div>
          <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
          <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>
        <!-- /.carousel -->
      </div>
      <div class="span4">
      	  <!-- 卡片板 -->
      	  <ul class="nav nav-tabs navbar-inverse" id="loginTab">
			  <li class="active"><a href="#login" data-toggle="tab">登录</a></li>
			  <li><a href="#register" data-toggle="tab">注册</a></li>
		  </ul>
		  <div class="tab-content" >
		  	  <div class="tab-pane active" id="login">
		  	     <s:url var="login_url" action="loginAction" />
		  	  	 <form class="form" action="${login_url}">
					  <div class="control-group">
					    <label class="control-label"  for="inputUser"><b>用户名</b></label>
					    <div class="controls">
					      <input type="text" id="inputUser"  name="username" placeholder="邮箱/手机..." required>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="inputPassword"><b>密码</b></label>
					    <div class="controls">
					      <input type="password" id="inputPassword" name="password" placeholder="6位以上数字和字母..." required>
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="inputCheck"><b>验证码</b></label>
					    <div class="controls">
					      <input class="span2" type="text" id="inputCheck" name="checkcode" placeholder="4位汉字..." required>
					    </div>
					  </div>
					  <div class="control-group">
					    <div class="controls">
					      <label class="checkbox">
					        <input type="checkbox" name="remember">记住我一周
					      </label>
					    </div>
					  </div>
					  <input id="commentCommit" type="submit" class="btn btn-success" value="登陆" onclick="checkComment()">
					  <input id="commentClear" type="reset" class="btn btn-info" value="清空" onclick="checkClear()"><br/><br/>
				  </form>
		  	  </div>
			  <div class="tab-pane" id="register">...</div>
		  </div>
         
      </div>
    </div>
   </div>
   
   <footer class="footer container well" style="margin-top: 30px;">
	<p class="pull-right">
		<a href="#">回到顶部</a>
	</p>
	<p style="color:gray">
		Created by <a href="#" target="_blank">Vince</a>.<br/>
		Copyright 2013 上海电力学院 © 版权所有 
	</p>
</footer>

   <script type="text/javascript">
     $(function(){
   	  
   	  //控制移动
         $('.carousel').carousel({
           interval:10000
         });
   	  
        // $('#loginTab').tab('show');
         
     });

     function checkComment(){
       $(".alert").alert();
     }
   </script>
  </body>

</html>