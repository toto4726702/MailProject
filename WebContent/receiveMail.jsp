<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Flat UI</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.ico">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
  </head>
  <body>
  
    <div class="span9">
      <form action="sendMail">
	     <input name="sendTo" type="text" value="" placeholder="Send" class="span9">
	     <input name="copyTo" type="text" value="" placeholder="Copy" class="span9">
	     <input name="title" type="text" value="" placeholder="Title" class="span9">
	     <a id="lock" class="btn btn-danger" href="#myModal" data-toggle="modal"><i class="fui-lock-16"></i></a><br/><br/>
	     <textarea name="content" id="myarea"  class="span9"></textarea>
	     <input type="submit" >
  	  </form>
    </div>
    
    <!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">Really Confidential?</h3>
	  </div>
	  <div class="modal-body">
	    <p>Please enter the unlock password:</p>
	    <input type="password" placeholder="Password" class="span4">
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	    <button class="btn btn-primary" data-dismiss="modal" onclick="lockMail()">Confirm</button>
	  </div>
	</div>
  	
  
  
	<!-- Load JS here for greater good =============================-->
    <script src="js/jquery-1.8.2.min.js"></script>
    <script src="js/jquery-ui-1.10.0.custom.min.js"></script>
    <script src="js/jquery.dropkick-1.0.0.js"></script>
    <script src="js/custom_checkbox_and_radio.js"></script>
    <script src="js/custom_radio.js"></script>
    <script src="js/jquery.tagsinput.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.placeholder.js"></script>
    <script src="http://vjs.zencdn.net/c/video.js"></script>
    <script src="js/application.js"></script>
    <!-- Loading CKEditor -->
    <script type="text/javascript" src="js/editor/ckeditor.js"></script>
    <!--[if lt IE 8]>
      <script src="js/icon-font-ie7.js"></script>
      <script src="js/icon-font-ie7-24.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	//Initialize UI Component
    	CKEDITOR.replace('myarea', {
    		language: 'zh-cn'
    	});
    	
    	//Tooltips
    	$('#lock').tooltip({
    		title:"Password Protect",
    		placement:"top"
    	});
    	
    	function lockMail(){
    		$("#lock").attr("class","btn btn-success");
    	}
    </script>
  </body>
</html>
  