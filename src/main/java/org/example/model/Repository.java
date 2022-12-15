package org.example.model;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;

public class Repository {

    private static Repository _INSTANCE;
    private ConnectionPool pool;

    private Repository() throws SQLException {
        pool = ConnectionPool.getInstance();
    }

    public static Repository getInstance() throws SQLException {
        if(_INSTANCE == null) {
            _INSTANCE = new Repository();
        }
        return _INSTANCE;
    }

    public List<Task> findAll() throws SQLException {
        Connection con = pool.borrow();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select t.task_name" +
                "from tasks t " +
                "left outer join protocol p on p.task_id = t.task_id");
        List<Task> tasks = new ArrayList<>();
        while (rs.next()) {
            Task task = new Task(rs.getString("TASK_NAME"));
            tasks.add(task);
        }
        rs.close();
        stmt.close();
        pool.release(con);
        return tasks;
    }
}
