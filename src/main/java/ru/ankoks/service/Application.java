package ru.ankoks.service;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;

import static ru.ankoks.jooq.model.Tables.*;
/**
 * User: akoksharov
 * Date: 21.03.2018
 */
public class Application {
    public static void main(String[] args) throws Exception {
//        String user = System.getProperty("jdbc.user");
//        String password = System.getProperty("jdbc.password");
//        String url = System.getProperty("jdbc.url");
//        String driver = System.getProperty("jdbc.driver");

        String user = "swift01";
        String password = "swift01";
        String url = "jdbc:postgresql://localhost:5432/swift01";
        String driver = "org.postgresql.Driver";

        Class.forName(driver).newInstance();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES_9_4);

            Result<Record> fetch = dslContext.select()
                    .from(SPH_USER)
                    .fetch();

            System.out.println(fetch);

            for (Record r : fetch) {
                BigDecimal userId = r.getValue(SPH_USER.ID_USER);
                String login = r.getValue(SPH_USER.NM_LOGIN);

                System.out.println(userId + " - " + login);
            }
//            Result<Record> result = dslContext.select().from(AUTHOR).fetch();

//            for (Record r : result) {
//                Integer id = r.getValue(AUTHOR.ID);
//                String firstName = r.getValue(AUTHOR.FIRST_NAME);
//                String lastName = r.getValue(AUTHOR.LAST_NAME);
//
//                System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
