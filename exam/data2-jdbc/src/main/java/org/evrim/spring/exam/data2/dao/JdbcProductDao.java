package org.evrim.spring.exam.data2.dao;

import org.evrim.spring.exam.data2.ds.Employee;
import org.evrim.spring.exam.data2.ds.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProductDao {

    JdbcTemplate jdbcTemplate;

    public JdbcProductDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from Product", this::productMapper);
    }

    private Product productMapper(ResultSet resultSet, int i) throws SQLException {
        return new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("quantity"),
                resultSet.getFloat("price"),
                resultSet.getBoolean("available")
        );
    }

    public int insertProduct(Product product) {
        if(product==null) {
            throw new RuntimeException("Product cannot be null");
        }
        if(product.getId() < 0) {
            throw new RuntimeException("Product id must be greater than 0");
        }

        return jdbcTemplate.update(
                "insert into product(id, name, quantity, price, available) " +
                        "values (?, ?, ?, ?, ?)",
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.isAvailable()
        );
    }




}
