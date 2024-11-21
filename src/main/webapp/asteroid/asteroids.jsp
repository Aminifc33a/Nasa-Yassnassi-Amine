<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Asteroids List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header text-center">
            <h1>Asteroids List</h1>
        </div>
        <div class="card-body">
            <c:if test="${user.role == 'astronomer'}">
                <div class="d-flex justify-content-between mb-3">
                    <form action="sync-asteroids" method="post" class="d-inline">
                        <button type="submit" class="btn btn-primary">Sync with NASA</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/asteroids/reset" method="post" class="d-inline">
                        <button type="submit" class="btn btn-warning">Reset Asteroids</button>
                    </form>
                    <a href="${pageContext.request.contextPath}/asteroids/create" class="btn btn-success">Add Asteroid</a>
                </div>
            </c:if>
            <table class="table table-striped">
                <thead class="table-dark">
                <tr>
                    <th>Name</th>
                    <th>Magnitude</th>
                    <th>Hazardous</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${asteroids}" var="asteroid">
                    <tr>
                        <td>${asteroid.name}</td>
                        <td>${asteroid.absoluteMagnitude}</td>
                        <td>${asteroid.isPotentiallyHazardous ? "Yes" : "No"}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/asteroid?id=${asteroid.id}" class="btn btn-info btn-sm">Details</a>
                            <c:if test="${user.role == 'astronomer'}">
                                <a href="${pageContext.request.contextPath}/asteroid/update?id=${asteroid.id}" class="btn btn-warning btn-sm">Edit</a>
                                <form action="${pageContext.request.contextPath}/asteroid/delete" method="post" class="d-inline">
                                    <input type="hidden" name="id" value="${asteroid.id}">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this asteroid?')">Delete</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>