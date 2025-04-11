package com.todo.dao;

import com.todo.model.Task;
import com.todo.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, description, priority) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setInt(3, task.getPriority());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks ORDER BY priority ASC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(rs.getInt("priority"));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
 // TaskDAO.java

    public List<Task> getTasksByPriority(int priority) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE priority = ? ORDER BY priority ASC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, priority);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setPriority(rs.getInt("priority"));
                    tasks.add(task);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

 // Method to delete task by id
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}