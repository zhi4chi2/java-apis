```java
package org.example.demo.java;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    public static void main(String[] args) throws Exception {
        DriverManager.setLogWriter(new PrintWriter(System.out));
        String url = "jdbc:postgresql://127.0.0.1:5432/test";
        String username = "postgres";
        String password = "postgres";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
    }
}
```
