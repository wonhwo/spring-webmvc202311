<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>webStudy</title>
</head>
<body>
    <h2>${a}</h2>
<ul>
    <li>아이디 : ${id}</li>
    <li>비밀번호 : ${pw}</li>
</ul>
    <a href="/login">다시 가입</a>
</body>
</html>