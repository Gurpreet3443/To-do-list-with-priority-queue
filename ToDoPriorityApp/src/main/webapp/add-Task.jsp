<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Add New Task</h1>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="error-message"><%= error %></div>
    <% } %>

    <form action="TaskServlet" method="post">
        <label>Title:</label><br>
        <input type="text" name="title" required><br>

        <label>Description:</label><br>
        <input type="text" name="description" required><br>

        <label>Priority:</label><br>
        <input type="number" name="priority" required><br><br>

        <input type="submit" value="Add Task">
    </form>
</div>
</body>
</html>
