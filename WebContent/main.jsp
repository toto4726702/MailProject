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
			width:85px;
		}
		
		#maniMenu{
			background: #C0392B;
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
		
		#chart1 {
		  margin: 10px;
		  min-width: 100px;
		  min-height: 100px;
		/*
		  Minimum height and width is a good idea to prevent negative SVG dimensions...
		  For example width should be =< margin.left + margin.right + 1,
		  of course 1 pixel for the entire chart would not be very useful, BUT should not have errors
		*/
		}
		
		#chart1 svg {
		  height: 500px;
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
					<li ><a href="${pageContext.request.contextPath}/mainAction">数据分析</a></li>
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
	         		 <a href="#" class="btn btn-large btn-block btn-success" onclick="showSendMail()">
	         		 <b><i class="icon-edit icon-white" style="margin-top:2px;margin-right:5px;"></i>写邮件</b>
	         		 </a>
	        	</div>
	        	<div id="freshBtn"  >
	         		 <a href="#" class="btn btn-large btn-block btn-info">
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
	            	<div id="maniSendMail">
	            	 	 <a class="btn btn-primary"><i class="fui-checkmark-16"></i> <span>发送</span></a>
		           	     <br/><br/>
		           	     <a class="btn"><i class="icon-folder-open icon-white"></i> <span>存为草稿</span></a>
		           	     <br/><br/>
		           	     <a class="btn"><i class="icon-fire icon-white"></i> <span>舍弃</span></a>
		           	     <a class="btn disabled"><i class="fui-settings-16"></i> <span>设置</span></a>  
	            		 <br/><br/>
	            		 <input name="tagsinput" id="tagsinput" class="tagsinput" value="" style="display: none;">
	            	</div>       
	            </div>
            </div>
  			<br/>
		      
  		</div>
  		
  		<div id="top-nav" class="span9">
          <div class="navbar navbar-inverse">
            <div class="navbar-inner">
              <div class="container">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <div class="nav-collapse collapse">
                  <ul class="nav">
                  	<li>
                      <a href="#">
                        	系统消息
                        <span class="navbar-unread">1</span>
                      </a>
                    </li>
                    <li class="active">
                      <a href="#">
                        	收件箱
                        <span class="navbar-unread">1</span>
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
  		
  		<div id="center-view-inbox" class="span9" >
	  		  <div class="pagination" style="margin-top: 0px; margin-bottom: 13px;"> 
	            <ul>
	              <li class="previous"><a href="#"><img src="images/pager/previous.png"></a></li>
	              <li class="active"><a href="#">1</a></li>

	              <li class="next"><a href="#"><img src="images/pager/next.png"></a></li>
	            </ul>
	          </div>
  			<div id="#datawatch" class="divbox" style="height:150px;">
  				<table style="display: block;" class="table" >
  					<thead>
  						<tr align="center" >
  							<td width="50" ><b>已读</b></td>
  							<td width="80" ><b>发件人</b></td>
  							<td width="200"><b>标题</b></td>
  							<td width="200"><b>摘要</b></td>
  							<td width="120"><b>时间</b></td>
  							<td width="50"><b>星标</b></td>
  							<td width="50"><b>重要</b></td>
  							<td width="50"><b>加锁</b></td>
  						</tr>
  					</thead>
  					<tbody>
  						<tr>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  						</tr>
  						<tr>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  							<td>已读</td>
  						</tr>
  					</tbody>
  				</table>
  			</div>
  		</div>
  		
  		<div id="center-view-writemail" class="span9" style="display: none;">
		      <form id="sendMailForm" action="sendMail">
			     <input name="sendTo" type="text" value="" placeholder="Send" class="span9">
			     <input name="copyTo" type="text" value="" placeholder="Copy" class="span9">
			     <input name="title" type="text" value="" placeholder="Title" class="span9">
			     
			     <a id="lock" class="btn btn-danger" onclick="openModal()" ><i class="fui-lock-16"></i></a> 
			     <a id="important" class="btn btn-danger" onclick="setImportant()"><i class="fui-heart-16"></i></a> 
			     <a id="location" class="btn disabled" ><i class="fui-location-16"></i></a> 
			     <a id="attachment" class="btn disabled" ><i class="fui-plus-16"></i></a> 
			     
			     <input id="lockPass" name="lockPass" type="hidden" value="">
			     <input id="importantMail" name="importantMail" type="hidden" value="false">
			     <br/><br/>
			     <textarea name="content" id="myarea"  class="span9"></textarea>
		  	  </form>
  		</div>
    
    	<div id="right-panel"  class="span4" style="margin-top: 0px;">
	  		
  			<h1 style="margin-bottom: 2px;">联系热度</h1>
  		
  			<div id="chart1">
				<svg></svg>
			</div>
		      
  		</div>
  		
  		<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel">Really Confidential?</h3>
		  </div>
		  <div class="modal-body">
		    <p>Please enter the unlock password:</p>
		    <input id="passwd" type="password" placeholder="Password" class="span4">
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		    <button class="btn btn-primary" data-dismiss="modal" onclick="lockMail()">Confirm</button>
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
   <script src="js/bootstrap.js" type="text/javascript" ></script>
   <script src="js/d3.v2.js"></script>
   <script src="js/nv.d3.js"></script>
   <script src="js/custom_radio.js"></script>
   <!-- Loading CKEditor -->
   <script type="text/javascript" src="js/editor/ckeditor.js"></script>
   <!-- Loading Tags -->
   <script src="js/jquery.tagsinput.js"></script>
   <script type="text/javascript">
   	 //Initialize UI Components
     $(function(){
   	
    	 $(".todo li").click(function() {
    	        $(this).toggleClass("todo-done");
    	    });
    	 
        // $('#loginTab').tab('show');
        
        //Tooltips 
   	 	$('#lock').tooltip({
   	 		title:"Password Protect",
   	 		placement:"top"
   	 	});
   	 	$('#important').tooltip({
	 		title:"Important Mail",
	 		placement:"top"
	 	});
   	 	$('#location').tooltip({
	 		title:"Where I am",
	 		placement:"top"
	 	});
   		$('#attachment').tooltip({
	 		title:"Add Attachment",
	 		placement:"top"
	 	});
        
        //Tags 
        $("#tagsinput").tagsInput();
        
        //CKEditor 
   		CKEDITOR.replace('myarea', {
   	 		language: 'zh-cn'
   	 	});
   	 	
     });
   	 
 
    function openModal(){
    	$('#myModal').modal('show');
    }
 	
 	function lockMail(){
 		var pass = $("#passwd").val();
 		if(pass==""){
 			//改变颜色并修改pass
 			$("#lock").attr("class","btn btn-danger");
 			
 		}else{
 			$("#lock").attr("class","btn btn-success");
 		}
 		$("#lockPass").val(pass);
 	}
 	
 	function setImportant(){
 		var imp = $("#importantMail").val();
 		if(imp=="true"){
 			//改变颜色并修改pass
 			$("#important").attr("class","btn btn-danger");
 			imp = "false";
 		}else{
 			$("#important").attr("class","btn btn-success");
 			imp = "true";
 		}
 		$("#importantMail").val(imp);
 	}
   	 
   	 function showSendMail(){
   		 
   		 $("#center-view-inbox").fadeOut("slow",function(){
   			 $("#center-view-writemail").fadeIn("slow");
   		 });
   		 
   	 }
   	 
   </script>
   <script>

long_short_data = [ 
  {
    key: '寄件',
    color: '#d62728',
    values: [
      { 
        "label" : "Vince" ,
        "value" : -1
      } , 
      { 
        "label" : "Group B" ,
        "value" : -3
      } , 
      { 
        "label" : "Group C" ,
        "value" : -0
      } , 
      { 
        "label" : "Group D" ,
        "value" : -2
      } , 
      {
        "label" : "Group E" ,
        "value" : -0
      } , 
      { 
        "label" : "Group F" ,
        "value" : -0
      } , 
      { 
        "label" : "Group G" ,
        "value" : -0
      } , 
      {
        "label" : "Group H" ,
        "value" : -0
      }
    ]
  },
  {
	    key: '收件',
	    color: '#d62728',
	    values: [
	      { 
	        "label" : "Vince" ,
	        "value" : 1
	      } , 
	      { 
	        "label" : "Group B" ,
	        "value" : 2
	      } , 
	      { 
	        "label" : "Group C" ,
	        "value" : 3
	      } , 
	      { 
	        "label" : "Group D" ,
	        "value" : 2
	      } , 
	      {
	        "label" : "Group E" ,
	        "value" : 1
	      } , 
	      { 
	        "label" : "Group F" ,
	        "value" : 2
	      } , 
	      { 
	        "label" : "Group G" ,
	        "value" : 3
	      } , 
	      {
	        "label" : "Group H" ,
	        "value" : 2
	      }
	    ]
	  }
];



var chart;
  nv.addGraph(function() {
  chart = nv.models.multiBarHorizontalChart()
      .x(function(d) { return d.label })
      .y(function(d) { return d.value })
      .margin({top: 30, right: 20, bottom: 50, left: 0})
      //.showValues(true)
      //.tooltips(false)
      .barColor(d3.scale.category20().range())
      .showControls(true);

  chart.yAxis
      .tickFormat(d3.format(',.2f'));

  d3.select('#chart1 svg')
      .datum(long_short_data)
    .transition().duration(500)
      .call(chart);

  nv.utils.windowResize(chart.update);

  chart.dispatch.on('stateChange', function(e) { nv.log('New State:', JSON.stringify(e)); });

  return chart;
});



  </script>
  </body>

</html>