package org.softuni.mobilele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final String mysqlHost;
    private final String mysqlUser;
    private final String mysqlPassword;
    private final String jdbcUrl;

    @Autowired
    public DBInit(
            @Value("${MYSQL_HOST}") String mysqlHost,
            @Value("${MYSQL_USER}") String mysqlUser,
            @Value("${MYSQL_PASSWORD}") String mysqlPassword,
            @Value("${spring.datasource.url}") String jdbcUrl) {
        this.mysqlHost = mysqlHost;
        this.mysqlUser = mysqlUser;
        this.mysqlPassword = mysqlPassword;
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("MySQL parameters:%nHost: %s%nUser: %s%nPassword: %s%nUrl: %s%n",
                mysqlHost, mysqlUser, mysqlPassword, jdbcUrl);

//        System.out.println("Default DB password: " + defaultDBPassword);
    }

}
