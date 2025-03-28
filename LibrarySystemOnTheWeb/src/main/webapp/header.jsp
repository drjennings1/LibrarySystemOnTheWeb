<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css">
        <title>Dylan's Library</title>
    </head>
    <body>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="library?action=books">Available Books</a>
            <a href="library?action=borrowers">Borrowers</a>
            <a href="library?action=borrow">Borrow</a>
            <a href="library?action=return">Return</a>
            <a href="library?action=stats">Statistics</a>
        </nav>
