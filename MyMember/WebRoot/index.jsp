<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="login page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <c:set var="status" value="${requestScope.status}"></c:set>
  <body>
  
   <script type="text/javascript">
  	
  </script>
    <form action="login.action" id="loginForm" method="post" >
	   <table align="center" style="border:1px solid black;width:300px;height:100px;background-color:green;">
   			<tr>
   			<td>用户名:</td>
   			<td><input type="text" name="username" value="" style="width:150px;"/></td>
   			</tr>
   			<tr>
   			<td>密  码：</td>
   			<td><input type="password" name="password" value="" style="width:150px;"/></td>
   			</tr>
   			<tr>
   			<td colspan="2" align="center">
   			<input name="sub" type="submit" value="登陆"/>
   			<input name="reset" type="reset" value="取消"/>
   			</td>
   			</tr>
	   </table>
	   <div id="show" align="center">
	   	<c:if test="${status==0}"><span style="color:red;">用户名或密码错误</span></c:if>
	   </div>
    </form>
  </body>
</html>
