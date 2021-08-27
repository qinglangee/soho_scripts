package model.util;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.sql.*;

/** Database-related utility method classes */
public class DbUtil implements Serializable {

  private static final long serialVersionUID = -820551281211138559L;
  public String dbFile = "sqlite_data.db";

  transient Connection c = null;
  Statement stmt = null;

  public DbUtil() {
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
      System.out.println("Connect database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE cityinfo " +
                    "(city text PRIMARY KEY NOT NULL," +
                    " info text)";
      stmt.executeUpdate(sql);

    } catch (Exception e) {
    }
  }

  public void update(String sql) {
    try (Statement stmt = c.createStatement()) {
      stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** return research result */
  public List<Map<String, Object>> select(String sql) {
    List<Map<String, Object>> list = new ArrayList<>();
    ResultSet rs = null;
    try (Statement stmt = c.createStatement()) {
      rs = stmt.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount(); // ResultSet total column
      while (rs.next()) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < columnCount; i++) {
          String name = rsmd.getColumnName(i + 1);
          Object obj = rs.getObject(i + 1);
          map.put(name, obj);
        }
        list.add(map);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {

          rs.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }
    return list;
  }

  /** check if return result is null */
  public boolean exist(String sql) {
    ResultSet rs = null;
    try (Statement stmt = c.createStatement()) {
      rs = stmt.executeQuery(sql);
      boolean exist = rs.next();
      return exist;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {

          rs.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }
    return false;
  }
}
