<%@ page import="java.util.*,com.todo.model.Task" %>
<html>
<head>
    <title>ToDo Priority App</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>ToDo Priority App</h1>
        <a href="add-Task.jsp" class="btn">Add Task</a>
        <table>
    <tr>
        <th>Description</th>
        <th>Priority</th>
        <th>Action</th>
    </tr>

    <%
        List<Task> tasks = (List<Task>) request.getAttribute("taskList");
        if (tasks == null || tasks.isEmpty()) {
    %>
        <tr><td colspan="3">No Task Available!</td></tr>
    <%
        } else {
            for (Task task : tasks) {
    %>
    <tr>
        <td><%= task.getDescription() %></td>
        <td><%= task.getPriority() %></td>
        
        <td>
            <form action="TaskServlet" method="post">
                <input type="hidden" name="id" value="<%= task.getId() %>">
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <% }
        }
    %>
</table>

        <h2>Filter Tasks by Priority</h2>
        <form action="TaskServlet" method="get">
            <label for="priority">Enter Priority:</label>
            <input type="number" id="priority" name="priority" required>
            <input type="submit" value="Filter">
        </form>
        
    </div>
</body>
</html>
