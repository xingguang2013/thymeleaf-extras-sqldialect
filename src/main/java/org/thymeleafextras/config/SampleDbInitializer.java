package org.thymeleafextras.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;

/**
 * Creates an in-memory HSQLDB database and initializes it with some data.
 */
public class SampleDbInitializer implements WebApplicationInitializer {

    private JdbcTemplate jdbcTemplate;

    public void onStartup(ServletContext servletContext) throws ServletException {
        jdbcTemplate = createJdbcTemplate();
        createProductTable();
        populateProductTable();
    }

    private JdbcTemplate createJdbcTemplate() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
		return new JdbcTemplate(dataSource);
    }

    private void createProductTable() {
        jdbcTemplate.execute(
              "CREATE TABLE products( "
            + "    id INTEGER GENERATED BY DEFAULT AS IDENTITY, "
            + "    description VARCHAR(100), "
            + "    price INTEGER, "
            + "    PRIMARY KEY(id)"
            + ")");
    }

    private void populateProductTable() {
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Table', 150)");
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Chair', 45)");
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Armchair', 250)");
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Drawer', 50)");
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Sofa', 180)");
        jdbcTemplate.execute("INSERT INTO products(description, price) VALUES('Desk', 280)");
    }
}
