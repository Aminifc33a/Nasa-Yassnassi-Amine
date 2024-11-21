<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Asteroid</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header text-center">
            <h1>Create Asteroid</h1>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/asteroids/create" method="post">
                <div class="mb-3">
                    <label for="id" class="form-label">ID:</label>
                    <input type="number" id="id" name="id" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="magnitude" class="form-label">Magnitude:</label>
                    <input type="number" id="magnitude" name="magnitude" class="form-control" step="0.01" required>
                </div>
                <div class="mb-3">
                    <label for="diameter" class="form-label">Diameter (km):</label>
                    <input type="number" id="diameter" name="diameter" class="form-control" step="0.01" required>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" id="hazardous" name="hazardous" value="true" class="form-check-input">
                    <label for="hazardous" class="form-check-label">Is hazardous</label>
                    <input type="hidden" name="hazardous" value="false">
                </div>
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Create</button>
                    <a href="${pageContext.request.contextPath}/asteroids" class="btn btn-secondary">Back to Asteroids List</a>
                </div>
                <c:if test="${!empty errorMessage}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${errorMessage}
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
