<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		
		function asynRequest(){
		var xmlHttpReq;
		if(window.XMLHttpRequest){
			xmlHttpReq=new XMLHttpRequest();
		}else{
			xmlHttpReq=new ActiveXObject("Microsoft.XMLHTTP");		
		}
		xmlHttpReq.onreadystatechange=function(){
			if(xmlHttpReq.readyState=4&&xmlHttpReq.status==200){
				document.getElementById('dvone').innerHTML=xmlHttpReq.responseText;
			}
		}
		//加个id是为了防止加载缓存中的数据，保持数据是最新的，但是如果使用post,就不必如此了
		//然而，在以下情况中，请使用 POST 请求：
		//无法使用缓存文件（更新服务器上的文件或数据库）
		//向服务器发送大量数据（POST 没有数据量限制）
		//发送包含未知字符的用户输入时，POST 比 GET 更稳定也更可靠
		xmlHttpReq.open("get","loadsms.action?id="+Math.random(),true);	
		xmlHttpReq.send(null);
		}
	</script>
  </head>
  
  <body>
   <div id="dvone" style="border:1px solid black;height:40;width:300;padding:5"></div>
   <br />
   <button type ="button" onclick="asynRequest()">点击</button>
  </body>
</html>
