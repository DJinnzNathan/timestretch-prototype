package org.example.model;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.*;

public class ConnectionPool {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool _INSTANCE;
    private Map<Integer, Connection> connectionMap = new HashMap<>();
    private Set<Integer> borrowed = new HashSet<>();
    private static final int poolSize = 10;

    private ConnectionPool() throws SQLException {
        for (int i = 0;i<poolSize;i++) {
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://pgdb.wannaco.de:4711/u2",
                    "u2",
                    "s2isQuanxsQWü");
            connectionMap.put(con.hashCode(),con);
        }
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (_INSTANCE == null) {
            _INSTANCE = new ConnectionPool();
        }
        return _INSTANCE;
    }

    public Connection borrow() throws NoSuchElementException {
        Integer freeCon = connectionMap.keySet().stream()
                .filter(it -> !borrowed.contains(it.hashCode()))
                .findFirst().orElseThrow();
        borrowed.add(freeCon);
        LOG.info("Connection "+freeCon+" has been borrowed from Pool. "+(connectionMap.size()-borrowed.size())+" Connections left");
        return connectionMap.get(freeCon);
    }

    public void release(Connection con) throws NoSuchElementException {
        if(!borrowed.contains(con.hashCode())) {
            throw new NoSuchElementException("Connection was borrowed from this Pool");
        }
        if(!connectionMap.containsKey(con.hashCode())) {
            throw new NoSuchElementException("Connection is not managed by this Pool");
        }
        borrowed.remove(con.hashCode());
        LOG.info("Connection "+con.hashCode()+" given back to Pool. "+(connectionMap.size()-borrowed.size())+" Connections left");
    }
}
