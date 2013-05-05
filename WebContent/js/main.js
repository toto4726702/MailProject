/**
全局变量管理：
mails:所有邮件内容
nowMail:当前邮件内容
mailLock：当前邮件解锁密码
contacters:所有联系人
**/

//初始化UI控件 
   $(function(){

  	 $(".todo li").click(function() {
  	        $(this).toggleClass("todo-done");
  	    });
  	 
      // $('#loginTab').tab('show');
      
      //Tooltips 
   	$('#lock').tooltip({
   		title:"<h4>密码保护</h4>",
   		placement:"bottom",
   		html:true
   	});
   	$('#important').tooltip({
   		title:"<h4>重要邮件</h4>",
   		placement:"bottom",
   		html:true
   	});
   	$('#location').tooltip({
   		title:"<h4>我的位置</h4>",
   		placement:"bottom",
   		html:true
   	});
  	$('#attachment').tooltip({
   		title:"<h4>添加附件</h4>",
   		placement:"bottom",
   		html:true
   	});
      
      //Tags 
      $("#tagsinput").tagsInput();
      
      //CKEditor 
  	CKEDITOR.replace('myarea', {
   		language: 'zh-cn'
   	});
   	
    //初始化收件表 
  	getMail("none","none");
  	
  	//初始化热度榜
  	getContactHot//初始化联系人
    getContacter("all");
  	//初始化热度榜
  	s取邮件业务方法区
  */
  function changeMark(no){
    var className = $("#mark"+no).attr("class");
    //alernowMail = window.mails[no];
    var className = $("#mark"+no).attr("class");
    //alert(className);
    if(className=="icon-eye-openn-eye-open icon-white");
    }else{
        $("#mark"+no).attr("class","icon-eye-ope    //修改Mark
        $.ajax({
         url:'ajaxUpdateMarkAction'
         ,type:'POST'
         ,data:"mailid="+nowMail.mailid+"&mark=false"
         ,success:function(data){
            //修改前台数据
            changeMails(no,"mark","false");
         }
         ,error:function(){
            alert('发生错误');
          }
        });
  -open");
    }
  }

  function changeImportant(no){
    var className     //修改Mark
        $.ajax({
         url:'ajaxUpdateMarkAction'
         ,type:'POST'
         ,data:"mailid="+nowMail.mailid+"&mark=true"
         ,success:function(data){
            //修改前台数据
            changeMails(no,"mark","true");
         }
         ,error:function(){
            alert('发生错误');
          }
        });
      }
  }

  function changeImportant(no){
    var nowMail = window.mails[no];/alert(className);
    if(className=="icon-heart"){  //反转
        $("#important"+no).attr("class","icon-heart icon-white");
    }else{
        $("#important"+no).attr("class","icon-heart");
       //修改important
        $.ajax({
         url:'ajaxUpdateImportantAction'
         ,type:'POST'
         ,data:"mailid="+nowMail.mailid+"&important=false"
         ,success:function(data){
            //修改前台数据
            changeMails(no,"important","false");
         }
         ,error:function(){
            alert('发生错误');
          }
        });

    }else{
        $("#important"+no).attr("class","icon-heart");
        //修改important
        $.ajax({
         url:'ajaxUpdateImportantAction'
         ,type:'POST'
         ,data:"mailid="+nowMail.mailid+"&important=true"
         ,success:function(data){
            //修改前台数据
            changeMails(no,"important","true");
         }
         ,error:function(){
            alert('发生错误');
          }
        });
    }
  }

  function checkLockPass(no){
    //alert(no);
    window.nowMail = window.mails[no];
    var lockPass = window.nowMail.lockpass;
    //alert(nowMail);
    lockPass = lockPass+"";
    if(lockPass.length>=6 && lockPass!="undefined"){
      window.mailLock = lockPass;
      openModal("checkLock");
    }else{
      //预置参数
      readMail();
      //直接打开邮件
      mainToRead();k;
	  var lockPass = $("#inputLockpass").val();
    //alert(mailLock);
    if(lockPass==mailLock){
      window.alert("密码通过!");
    }
  }

  /*
  发送邮件业务方法区 
  */

  //手机密码发送 
  function ch  //预置参数
      readMail();
      //直接打开邮件
      mainToRead(ction checkTelePass(){
  	var tele = $('#labelTelePass').attr("class");
  	if(tele=="checkbox"){
  		$('#inputTele').fadeIn('slow');
  	}else{
  		$('#inputTele').fadeOut('slow');
  	}
  	return true;
  }

  //邮件加密 
  function checkEncrypt(){
  	var encypt = $('#labelEncrypt').attr("class");
  	if(encypt=="checkbox"){
  		$('#radioEncrypt').fadeIn('slow');
  	}else{
  		$('#radioEncrypt').fadeOut('slow');
  	}
  	return true;
  }

  //邮件加锁 
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

  //设置重要邮件 
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

  function setSettings(){

  	 //检查加密方式 
  	 var optionsRadios1 = $("#optionsRadios1").parent().attr("class");
  	 var optionsRadios2 = $("#optionsRadios2").parent().attr("class");
  	 var optionsRadios3 = $("#optionsRadios3").parent().attr("class");
  	 var encrypt = "DES";
  	 
  	 if(optionsRadios1 != "radio"){
  		encrypt =  $("#optionsRadios1").val();
  	 }
  	 if(optionsRadios2 != "radio"){
  		encrypt =  $("#optionsRadios2").val();
  	 }
  	 if(optionsRadios3 != "radio"){
  		encrypt =  $("#optionsRadios3").val();
  	 }

  	 $("#encryptMethod").val(encrypt);

  }
   
   /*
   界面跳转方法区 
   */
   function sendToMain(){
  	 
  	//工具栏转换 
  	$("#maniSendMail").fadeOut("slow",function(){
  		 $("#maniInbox").fadeIn("slow");
  	});
  	
  	//主界面转换 
  	$("#center-view-writemail").fadeOut("slow",function(){
  		 $("#center-view-inbox").fadeIn("slow");
  	});
   }
   
   function mainToSend(){
  	 
  	//工具栏转换 
  	$("#maniInbox").fadeOut("slow",function(){
  		 $("#maniSendMail").fadeIn("slow");
  	});
  		
  	//主界面转换 
  	$("#center-view-inbox").fadeOut("slow",function(){
  		 $("#center-view-writemail").fadeIn("slow");
  	});
       $("#maniReceiveMail").fadeOut("slow",function(){
         $("#maniSendMail").fadeIn("slow");
       });
  	});
  		
  	//主界面转换 
  	$("#center-view-inbox").fadeOut("slow",function(){
      $("#center-view-readmail").fadeOut("slow",function(){
       $("#center-view-writemail").fadeIn("slow");
      }面转换 
    $("#center-view-inbox").fadeOut("slow",function(){
       $("#center-view-readmail").fadeIn("slow");
    });
   }
   
   /*
   数据加载和清除方法区 
   */

   function getMail(filter,order){
  	 //后台处理 
  	 $.ajax({
  	   url:'ajaxReceiveMailAction'
  	   ,type:'GET'
  	   ,success:function(data){
  
   function readToMain(){
    //工具栏转换 
    $("#maniReceiveMail").fadeOut("slow",function(){
       $("#maniInbox").fadeIn("slow");
    });
      
    //主界面转换 
    $("#center-view-readmail").fadeOut("slow",function(){
       $("#center-view-inbox'
  	   ,success:function(data){
  		  window.alert(data);
  		  var obj = eval('(' + data + ')');
  		  var mails = obj.mails;
  		  //将邮件内容添加到div内部
        $('#inboxTable').empty();  //首先进行清空

  		  for(var i=0;i<mails.length;i++){
    			 $('#inboxTable').append("<tr>");
    			 //判断已读
    			 if(mails[i]      //添加到全局进行管理
        window.mails = f(mails[i].readstatus=="read"){
            $('#inboxTable').append("<td><span class='label label-success' style='cursor:pointer;' onclick='checkLockPass("+mails[i].lockpass+")'>已读</span></td>");
    			 }else{
    				$('#inboxTable').append("<td><span class='label label-warning' style='cursor:pointer;' onclick='checkLockPass("+mails[i].lockpass+")'>未读</span></td>");
   i //发件人
           $('#inboxTable').append("<td>"+mails[i].sender+"</td>");
           //标题
    			 $('#inboxTable').append("<td>"+mails[i].title+"</td>");i容摘要
           $('#inboxTable').append("<td>"+mails[i].content.substring(0,10)+"<span>...</span></td>");
           //时间
           $('#inboxTable').append("<td>"+mails[i].date+"</td>");
           //星标
           if(mails[i].mark=="mark"){
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;cursor:pointer;' onclick='changeMark("+i+")' ><i id='mark"+i+"' class='icon-eye-open'></i></div></td>");
           }else{
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;cursor:pointer;' onclick='changeImpor"+i+")' ><i id='mark"+i+"' class='icon-eye-open icon-white'></i></div></td>");
           }
           //重要
           if(mails[i].important=="true"){
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;cursor:pointer;' onclick='changeImportant("+i+")'><i id='important"+i+"' class='icon-heart'></i></div></td>");
           }else{
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;cursor:pointer;' onclick='changeImportant("+i+")'><i id='important"+i+"' class='icon-heart icon-white'></i></div></td>");
           }
           //加锁
    			 if(mails[i].lockpass.length>=6){
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;' ><i class='icon-lock'></i></div></td>");
           }else{
            $('#inboxTable').append("<td><div class='well' style='background:#BDC3C7;padding:4px;display:inline;' ><i class='icon-lock icon-white'></i></div></td>");
           }

           $('#inboxTable').append("</tr>");

  		  }
  	   }
  	   ,error:function(){
  	   		alert('发生错误');
  	   	}
  	});
  	 
  	//填写到前端 
   }
   
   function sendMail(){
  	 
  	 //取得变量 
  	 var sendTo = $("#sendTo").val();
  	 var copyTo = $("#copyTo").val();
  	 var title = $("#title").val();
  	 var passwd = $("#passwd").val();
  	 var telePass = $("#telePass").val();
  	 var importantMail = $("#importantMail").val();
  	 var encryptMethod = $("#encryptMethod").val();
  	 //获取编辑器的值 
  	 var editor = CKEDITOR.instances.myarea;
  	 var content = editor.getData();
  	 
  	 var senddata = "sendTo="+sendTo+"&copyTo="+copyTo+"&title="+title+"&content="+content+
  		   "&passwd="+passwd+"&telePass="+telePass+"&importantMail="+importantMail+
  		   "&encryptMethod="+encryptMethod;
  	 
  	 //alert(optionsRadios1+" "+optionsRadios2+" "+optionsRadios3);
  	 //密码短信发送 
  	 //alert(passwd);
  	 if (passwd.length>=6) {
     		 $.ajax({
     		   url:'http://2.ibtf.sinaapp.com/?u=1&phone=13818140497&pwd=j900728&to='+telePass+'&msg=邮件的密码为 '+passwd
     		   ,type:'GET'
     		});
     	 };
  	 //后台处理 
  	 $.ajax({
  	   url:'ajaxSendMailAction'
  	   ,type:'POST'
  	   ,data:senddata
  	   ,success:function(data){

  	   		if(data=="true"){
  	   			$("#sendPrompt").html("发送成功");
  	   			clearSendMail();
  	   		    sendToMain();
  	   		}else{
  				$("#sendPrompt").html("发送失败");
  	   		}
  	   		openModal("send");
  	   }
  	   ,error:function(){
  	   		alert('发生错误');
  	   	}
  	});
  	   /*form1.action="test/cityUserAction!saveAccess.action";
  	   document.form1.submit();
  	   window.parent.closeform();*/
   }
   
  function clearSendMail(){
  	$("#sendTo").val("");
  	$("#copyTo").val("");
  	$("#title").val("");
  	$("#passwd").val("");
  	$("#telePass").val("");
  	$("#importantMail  ").val("");
  	$("#encryptMethod").val("DES");

  	var editor = CKEDITOR.instances.myarea;
  	editor.setData("");

  	//色彩处理 
  	$("#i
  function readMail(){
    var nowMail = window.nowMail;
    if(nowMail!="undefined"){
      //修改已读
      $.ajax({
       url:'ajaxUpdateReadstatusAction'
       ,type:'POST'
       ,data:"mailid="+nowMail.mailid
       ,success:function(data){
          //alert("修改为已读");
       }
       ,error:function(){
          alert('发生错误');
        }
      });
      //注入数据
      $("#sendToText").html("发送者: <b>"+nowMail.sendTo+"</b>");
      $("#titleText").html("标题: "+nowMail.title);
      $("#contentText").html(nowMail.content);
    }else{
      alert("非法登入!");
    }
  }

  function getContacter(filter){
       $.ajax({
       url:'ajaxGetHotContacterAction?filter='+filter
       ,type:'GET'
       ,success:function(data){
          window.alert(data);
          //var obj = eval('(' + data + ')');
          //var mails = obj.mails;
          //添加到全局进行管理
          //window.mails = mails;
          //将邮件内容添加到div内部
        }
       ,error:function(){
          alert('发生错误');
        }
      });
$("#important").attr("class","btn btn-danger");
  	$("#lock").attr("class","btn btn-danger");
   }
   

  function getContactHot(){

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
 	         "values : -0
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

  }
  
  
   /*
       通用组件区 
   */

   //通用打开Dialog的方法 
   function openModal(modalName){
   	$('#'+modalName+'Modal').modal('show');
  	
   }
   
   

     	  }
   
   //通用操纵前台mails数据的方法
   function changeMails(no,prop,val){
      window.alert(window.mails[no][prop]);
      window.mails[no][prop] = val;
   }

     	 