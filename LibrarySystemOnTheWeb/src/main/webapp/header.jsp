<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- For date formatting -->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="images/favicon-16x16.png" type="image/png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="styles/main.css">
    <title>Dylan's Library</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">
        <img src="images/favicon-32x32.png" alt="Logo" width="32" height="32" class="d-inline-block align-text-top">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link fs-3 mx-4" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fs-3 mx-4" href="library?action=books">Available Books</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fs-3 mx-4" href="library?action=borrowers">Borrow/Return</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fs-3 mx-4" href="stats.jsp">Statistics</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

