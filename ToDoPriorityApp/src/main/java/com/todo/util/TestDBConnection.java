package com.todo.util;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("✅ Database Connected Successfully!");
            } else {
                System.out.println("❌ Failed to Connect Database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
