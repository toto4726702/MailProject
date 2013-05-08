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
	<title>企业邮件平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<sj:head loadAtOnce="true" jquerytheme="cupertino"/>
	<%-- <sb:head /> --%>
	<!-- Loading Flat UI -->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/flat-ui.css" rel="stylesheet">
    <link href="css/nv.d3.css" rel="stylesheet">
    <link href="css/myd3.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.ico">
    <!-- Loading Fitable UI -->
	<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		
		a{
			text-decoration: none;
		}
	
		#main-view{
			width:1340px;
		}
		
		#writeMailBtn{
			display:inline-block;
			margin-top:10px;
			margin-bottom:10px;
			margin-left:0px;
		}
		
		#writeMailBtn a{
			width:120px;
		}
		
		#freshBtn{
			display:inline-block;
			margin-top:10px;
			margin-bottom:10px;
			margin-left:10px;
		}
		
		#freshBtn a{
			width:80px;
		}
		
		#maniMenu{
			background: #34495E;
			background-size: 16px 16px;
			border-radius: 6px 6px 0 0;
			color: white;
			padding: 19px 25px 20px;
		}
		
		#maniArea{
			background: #ECF0F1;
			background-size: 16px 16px;
			#border: 1px solid #F39C12;
			border-radius: 0 0 6px 6px;
			color: white;
			padding: 19px 25px 20px;
		}
		
		#maniArea div a{
			height: 40px;
			margin-right: 6px;
		}
		
		#maniArea div a span{
			position:relative;
			font-size: 15px;
			top: 10px;
		}
		
		#maniArea div a i{
			position:relative;
			top: 12px;
		}
		
		.maniButton{
			background: #BDC3C7;
			width:30px;
			height:30px;
			border-radius: 6px 6px 6px 6px;
		}
		
		#center-view-inbox{
			
		}
		
		#right-panel{
			position: relative;
			bottom:80px;
		}
		
		#hotChart {
		  margin: 10px;
		  min-width: 100px;
		  min-height: 100px;
		/*
		  Minimum height and width is a good idea to prevent negative SVG dimensions...
		  For example width should be =< margin.left + margin.right + 1,
		  of course 1 pixel for the entire chart would not be very useful, BUT should not have errors
		*/
		}
		
		#hotChart svg {
		  height: 500px;
		}
		
		#monthlyChart svg {
		  height: 300px;
		}
		
		#monthlyChart2 svg {
		  height: 300px;
		}
		
		#contactReceiveChart svg{
			height: 300px;
		}
		
		#contactVipChart svg{
			height: 300px;
		}
		
	</style>
</head>
<body  data-spy="scroll" data-target="#leftnav" >

 <div class="navbar navbar-fixed-top navbar-inverse ">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="i-bar"></span> 
				<span class="i-bar"></span> 
				<span class="i-bar"></span> 
			</a> 
			<a class="brand" href="#" style=" padding-top: 8px;font-size: 25px" >企业邮件平台</a>

			<div class="nav-collapse">
				<ul class="nav">
					<li class="active"><a href="${pageContext.request.contextPath}/mainAction">邮箱管理</a></li>
					<li ><a href="${pageContext.request.contextPath}/mainAction">用户资料</a></li>
					<li ><a href="${pageContext.request.contextPath}/mainAction">帮助</a></li>
					<li ><a href="${pageContext.request.contextPath}/login.jsp">登出</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>

<div class="blank" style="height:100px"></div>
   <div id="main-view" class="content container" >
    <div class="row">
    
  		<div id="left-panel"  class="span3">
	  		
  			<h1 style="margin-bottom: 2px;">欢迎，</h1>
  			<h1 style="margin-top: 2px;">${loginname}</h1>
  			<div>
	  			<div id="writeMailBtn">
	         		 <a href="javascript:void(0);" class="btn btn-large btn-block btn-success" onclick="mainToSend()">
	         		 <b><i class="icon-edit icon-white" style="margin-top:2px;margin-right:5px;"></i>写邮件</b>
	         		 </a>
	        	</div>
	        	<div id="freshBtn"  >
	         		 <a href="javascript:void(0);" class="btn btn-large btn-block btn-info" onclick="getMail('none','none')">
	         		 <i class="icon-refresh icon-white" style="margin-top:3px;margin-right:5px;"></i>刷新
	         		 </a>
	        	</div>
        	</div>
			<div>
	        	<div id="maniMenu">
	              <span><b>操作栏</b></span>
	              <i class="icon-wrench icon-white" style="margin-top:2px;margin-right:5px;"></i>
	            </div>
	            <div id="maniArea">
	            	<div id="maniInbox">
	            	 	 <a class="btn btn-success" onclick="smartPrior()"><i class="fui-checkmark-16"></i> <span>智能优先</span></a>
		           	     <br/><br/>
		           	     <div class="btn-group">
						  <button class="btn btn-primary" onclick="unreadPrior()" style="width: 45px;height: 40px;" data-toggle="tooltip" title="未读优先"><i class="fui-new-16"></i></button>
						  <button class="btn btn-primary" onclick="importantPrior()" style="width: 45px;height: 40px;" data-toggle="tooltip" title="重要优先"><i class="fui-heart-16"></i> </button>
						  <button class="btn btn-primary" onclick="lockPrior()" style="width: 45px;height: 40px;" data-toggle="tooltip" title="加锁优先"><i class="fui-lock-16"></i> </button>
						  <button class="btn btn-primary" onclick="markPrior()" style="width: 45px;height: 40px;" data-toggle="tooltip" title="标记优先"><i class="fui-eye-16"></i></button>
						 </div>
						 <br/><br/>
		           	     <a class="btn btn-danger" ><i class="fui-cross-16"></i> <span>删除</span></a>
		           	     <br/><br/>
		           	     <a class="btn" ><i class="fui-menu-16"></i> <span>分类</span></a>
		           	     <br/><br/>
	            	</div> 
	            	<div id="maniSendMail" style="display: none;">
	            	
	            	 	 <a class="btn btn-success" onclick="sendMail()"><i class="fui-checkmark-16"></i> <span>发送</span></a>
		           	     <br/><br/>
		           	     <a class="btn" onclick="openModal('draft')"><i class="icon-folder-open icon-white"></i> <span>存为草稿</span></a>
		           	     <br/><br/>
		           	     <a class="btn btn-danger" onclick="sendToMain();clearSendMail();"><i class="icon-fire icon-white"></i> <span>舍弃</span></a>
		           	     <br/><br/>
		           	     <a class="btn" onclick="openModal('setting')"><i class="fui-settings-16"></i> <span>设置</span></a>  
	            		 <br/><br/>
	            		 <input name="tagsinput" id="tagsinput" class="tagsinput" value="工作" style="display: none;">
	            	</div>
	            	<div id="maniReceiveMail" style="display: none;">
	            	 	 <a class="btn btn-success" onclick="readToMain()"><i class="icon-chevron-left icon-white"></i> <span>返回</span></a>
		           	     <br/><br/>
	            	</div>     
	            	<div id="maniData" style="display: none;">
	            	 	 <a id="maniDataBtn" class="btn btn-success" onclick="dataToMain()"><i class="icon-chevron-left icon-white"></i> <span>返回</span></a>
		           	     <br/><br/>
	            	</div>     
	            </div>
            </div>
  			<br/>
		      
  		</div>
  		
  		<div id="top-nav" class="span9">
          <div class="navbar">
            <div class="navbar-inner">
              <div class="container">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <div class="nav-collapse collapse">
                  <ul class="nav">
                  	<li id="nav-data">
                      <a href="javascript:void(0);" onclick="mainToData()">
                        	系统数据
                        <span class="navbar-unread">1</span>
                      </a>
                    </li>
                    <li id="nav-inbox" class="active">
                      <a href="javascript:void(0);" >
                        	收件箱
                        <span class="navbar-unread" id="navbar-unread">1</span>
                      </a>
                    </li>
                    <li>
                        <a href="#">特殊邮件夹</a>
                        <ul>
                            <li><a href="#">重要邮件</a></li>
                            <li><a href="#">星标邮件</a></li>
                            <li><a href="#">带锁邮件</a></li>
                          </ul> <!-- /Sub menu -->
                    </li>
                    <li>
                      <a href="#">
                        	发件箱
                        <span class="navbar-unread">1</span>
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        	草稿
                        <span class="navbar-unread">1</span>
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        	垃圾桶
                        <span class="navbar-unread">1</span>
                      </a>
                    </li>
                  </ul>
                </div><!--/.nav-collapse -->
              </div>
            </div>
          </div>
        </div>
  		
  		<div id="center-view-data" class="span9" style="display: none;">
  			<div id="data-intro">
		  		<div class="span3" style="width: 200px;">
		          <div class="tile">
		            <img class="tile-image big-illustration" alt="" src="images/illustrations/time.png">
		            <h3 class="tile-title">我的月报</h3>
		            <p>一个月内邮件收发的情况.</p>
		            <a class="btn btn-primary btn-large btn-block" href="javascript:void(0);" onclick="openMonthlyData()">查阅</a>
		          </div>
		        </div>
		        <div class="span3" style="width: 200px;">
		          <div class="tile">
		            <img class="tile-image big-illustration" alt="" src="images/illustrations/colors.png">
		            <h3 class="tile-title">联系总汇</h3>
		            <p>看看和联系人的紧密程度.</p>
		            <a class="btn btn-primary btn-large btn-block" href="javascript:void(0);" onclick="openContacterData()">查阅</a>
		          </div>
		        </div>
		        <div class="span3" style="width: 200px;">
		          <div class="tile">
		            <img class="tile-image big-illustration" alt="" src="images/illustrations/map.png">
		            <h3 class="tile-title">邮件圈子</h3>
		            <p>这个圈子,你我他.</p>
		            <a class="btn btn-primary btn-large btn-block" href="javascript:void(0);" onclick="openGroupData()">查阅</a>
		          </div>
		        </div>
	        </div>
	        <div id="monthly-data" style="display: none;">
	        	<h2>一个月中按星期分布的邮件</h2>
	        	<div id="monthlyChart">
					<svg></svg>
				</div>
				<br/><br/>
				<h2>一个月中按时间段分布的邮件</h2>
	        	<div id="monthlyChart2">
					<svg></svg>
				</div>
	        </div>
	        <div id="contacter-data" style="display: none;">
	        	<h2>联系人收到邮件的百分比</h2>
	        	<div id="contactReceiveChart">
					<svg></svg>
				</div>
				<br/><br/>
				<h2>联系人按VIP分数排名</h2>
	        	<div id="contactVipChart">
					<svg></svg>
				</div>
	        </div>
	        <div id="group-data" style="display: none;">
	        	<h1>我的圈子</h1>
	        	<br/>
	        	<div id="group" style="border: 1px solid #95A5A6">
	        	</div>
	        </div>
  		</div>
  		
  		<div id="center-view-inbox" class="span9" >
	  		  <div class="pagination" style="margin-top: 0px; margin-bottom: 13px;"> 
	            <ul>
	              <li class="previous"><a href="#"><img src="images/pager/previous.png"></a></li>
	              <li class="active"><a href="#">1</a></li>

	              <li class="next"><a href="#"><img src="images/pager/next.png"></a></li>
	            </ul>
	          </div>
  			<div id="#datawatch" class="divbox">
  				<table style="display: block;" class="table" >
  					<thead>
  						<tr align="center" style="text-align: center;">
  							<td width="50" ><b>已读</b></td>
  							<td width="80" ><b>发件人</b></td>
  							<td width="200"><b>标题</b></td>
  							<td width="200"><b>摘要</b></td>
  							<td width="120"><b>时间</b></td>
  							<td width="50"><b>标记</b></td>
  							<td width="50"><b>重要</b></td>
  							<td width="50"><b>加锁</b></td>
  						</tr>
  					</thead>
  					<tbody id="inboxTable">
  					
  					</tbody>
  				</table>
  			</div>
  		</div>
  		
  		<div id="center-view-writemail" class="span9" style="display: none;">
		      <form id="sendMailForm" action="sendMail">
			     <input id="sendTo" type="text" value="" placeholder="Send To" class="span9">
			     <input id="copyTo" type="text" value="" placeholder="Copy To" class="span9">
			     <input id="title" type="text" value="" placeholder="Title" class="span9">
			     
			     <a id="lock" class="btn btn-danger" onclick="openModal('lock')" ><i class="fui-lock-16"></i></a> 
			     <a id="important" class="btn btn-danger" onclick="setImportant()"><i class="fui-heart-16"></i></a> 
			     <a id="location" class="btn disabled" ><i class="fui-location-16"></i></a> 
			     <a id="attachment" class="btn disabled" ><i class="fui-plus-16"></i></a> 
			     
			     <input id="lockPass" name="lockPass" type="hidden" value="">
			     <input id="importantMail" name="importantMail" type="hidden" value="false">
			     <input id="encryptMethod" name="encryptMethod" type="hidden" value="DES">
			     <br/><br/>
			     <textarea name="content" id="myarea"  class="span9"></textarea>
		  	  </form>
  		</div>
  		
  		<div id="center-view-readmail" class="span9" style="display: none;">
  			<div class="well well-small" id="sendToText">
			  
			</div>
			
			<div class="well well-small" id="titleText">
			 
			</div>
			
			<div class="well well-large" style="min-height: 280px;" id="contentText">
  			  
			</div>
		     <!--  
		     <a id="lock" class="btn btn-danger" onclick="openModal('lock')" ><i class="fui-lock-16"></i></a> 
		     <a id="important" class="btn btn-danger" onclick="setImportant()"><i class="fui-heart-16"></i></a> 
		     
		     <input id="lockPass" name="lockPass" type="hidden" value="">
		     <input id="importantMail" name="importantMail" type="hidden" value="false">
		     <input id="encryptMethod" name="encryptMethod" type="hidden" value="DES">
		     -->
		     <br/><br/>
  		</div>
    
    
    	<div id="right-panel"  class="span4" style="margin-top: 0px;">
	  		
  			<h1 style="margin-bottom: 2px;">联系热度</h1>
  			<br/>
  			<div class="btn-group" style="position: relative;left: 35px;">
			  <button class="btn" onclick="getContacter('week')">一周</button>
			  <button class="btn" onclick="getContacter('month')">一月 </button>
			  <button class="btn" onclick="getContacter('year')">一年</button>
			  <button class="btn" onclick="getContacter('all')">全部</button>
			</div>
  			<div id="hotChart">
				<svg></svg>
			</div>
		      
  		</div>
  		
  		<!-- LockModal -->
		<div id="lockModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">是否需要加密?</h2>
		  </div>
		  <div class="modal-body">
		    <p>请输入加密密码:</p>
		    <input id="passwd" type="password" placeholder="Password" class="span4">
		    <label id="labelTelePass" class="checkbox" for="checkBoxTelePass" onclick="checkTelePass()">
	            <input type="checkbox" value="" id="checkBoxTelePass" >
	            	发送至对方手机(密码必须达到6位)
          	</label>
	        <div id="inputTele" class="span3" style="display: none;">
	          	<input id="telePass" type="text" placeholder="Telephone" >
          	</div>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		    <button class="btn btn-primary" data-dismiss="modal" onclick="lockMail()">确认</button>
		  </div>
		</div>
		
		<!-- DraftModal -->
		<div id="draftModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">确认保存为草稿</h2>
		  </div>
		  <div class="modal-body">
		    <p>已保存</p>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		  </div>
		</div>
		
		<!-- SettingModal -->
		<div id="settingModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">邮件设置</h2>
		  </div>
		  <div class="modal-body">
		    <label id="labelEncrypt" class="checkbox" for="checkboxEncrypt" onclick="checkEncrypt()">
	            <input type="checkbox" value="" id="checkboxEncrypt" >
	            	邮件加密
          	</label>
          	<div id="radioEncrypt" class="span3" style="display: none;">
          		<label class="radio" >
	            <input type="radio"  id="optionsRadios1" name="optionsRadios"  value="DES" checked>
	            DES
	            </label>
	            <label class="radio"  >
	            <input type="radio"  id="optionsRadios2" name="optionsRadios" value="AES" >
	            AES
	            </label>
	            <label class="radio"  >
	            <input type="radio"  id="optionsRadios3" name="optionsRadios" value="ECC" >
	            ECC
	            </label>
          	</div>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="setSettings()">关闭</button>
		  </div>
		</div>

		<!-- SendModal -->
		<div id="sendModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">发送提示</h2>
		  </div>
		  <div class="modal-body">
		  	<span id="sendPrompt"></span>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="setSettings()">关闭</button>
		  </div>
		</div>
		
		<!-- CheckLockModal -->
		<div id="checkLockModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h2 id="myModalLabel">解锁提示</h2>
		  </div>
		  <div class="modal-body">
		  	<p>邮件已锁定，请输入密码解锁:</p>
		    <input id="inputLockpass" type="password" placeholder="Password" class="span4">
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="validateLockPass()">解锁</button>
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
   <!-- Loading Bootstrap UI & NVD3 UI -->
   <script src="js/bootstrap.js" type="text/javascript" ></script>
   <script src="js/d3.v2.js"></script>
   <script src="js/nv.d3.js"></script>
   <!-- Loading Checkbox and Radio Component -->
   <script src="js/custom_checkbox_and_radio.js"></script>
   <script src="js/custom_radio.js"></script>
   <!-- Loading CKEditor -->
   <script type="text/javascript" src="js/editor/ckeditor.js"></script>
   <!-- Loading Tags -->
   <script src="js/jquery.tagsinput.js"></script>
   <!-- 独立业务JS -->
   <script src="js/main.js"></script>
   <!-- Loading D3.js -->
   <%-- <script src="js/d3.js"></script>
   <script src="js/myd3.js"></script> --%>
  </body>

</html>