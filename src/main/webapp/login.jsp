<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="org.apache.catalina.webresources.JarResourceRoot"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 入力エラーがあった場合には再度このページが開かれるのでその際になぜエラーになったのかをお知らせします。リクエストスコープデータ名errorの値を受け取る-->
<%
List<String> error = (List<String>)request.getAttribute("error");
%>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <h3>ログイン画面</h3>
        </div>
<!-- errorを赤色で表示させる。-->
		<% if (error != null && error.size() != 0) { %>
	        <%for(String er : error){ %>
	        <div class="row justify-content-center">
	            <p style="color:red"><%=er %></p>
	        </div>
	        <%} %>
        <% } %>
        <div class="row justify-content-center">
<!-- login-servletを指定して、入力データ（user_id, password）を送る-->
            <form action="login-servlet" method="post">
                <div class="form-group">
                    <label>ユーザID</label>
                    <input type="text" name="user_id" class="form-control" placeholder="User-ID">
                </div>
                <div class="form-group">
                    <label>パスワード</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    <small class="form-text text-muted">We'll never share your Password with anyone else.</small>
                </div>
                <button type="submit" class="btn btn-outline-info">ログイン</button>
            </form>
        </div>
    </div>
</body>
</html>