package com.todo.controller;

import java.io.IOException;
import java.util.List;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TaskServlet extends HttpServlet {

    private TaskDAO dao;

    @Override
    public void init() {
        dao = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String priorityParam = request.getParameter("priority");

        List<Task> taskList;

        if (priorityParam != null) {
            int priority = Integer.parseInt(priorityParam);
            taskList = dao.getTasksByPriority(priority);
        } else {
            taskList = dao.getAllTasks();
        }

        request.setAttribute("taskList", taskList);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteTask(id);
        } else {
            // Handle Add Task
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String priorityStr = request.getParameter("priority");

            if (title == null || description == null || priorityStr == null || title.isEmpty() || description.isEmpty() || priorityStr.isEmpty()) {
                request.setAttribute("error", "All fields are required!");
                request.getRequestDispatcher("add-Task.jsp").forward(request, response);
                return;
            }

            int priority = Integer.parseInt(priorityStr);

            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setPriority(priority);

            dao.addTask(task);
        }

        response.sendRedirect("TaskServlet");
    }
}
