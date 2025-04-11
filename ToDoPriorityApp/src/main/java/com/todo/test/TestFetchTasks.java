package com.todo.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.todo.util.DBConnection;

public class TestFetchTasks {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tasks";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("Tasks from Database:");
            System.out.println("---------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Priority: " + rs.getInt("priority"));
                System.out.println("---------------------");
            }

            if (!found) {
                System.out.println("No Task Found in Database!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
