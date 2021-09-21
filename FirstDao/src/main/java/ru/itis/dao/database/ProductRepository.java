package ru.itis.dao.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.model.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements CrudRepository<Product,Long>{
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_BY_ID = "Select * from product where id = ?;";
    private static final String SELECT_ALL = "Select * from product;";
    private static final String INSERT = "Insert into product(name,category,country,price) values(?,?,?,?);";
    private static final String UPDATE_BY_ID = "Update product set name = ? ,category = ? ,country = ? ,price = ? where id = ? ;";
    private static final String DELETE_BY_ID = "Delete from product where id = ?;";
    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Product> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String category = resultSet.getString("category");
        String country = resultSet.getString("country");
        Integer price = resultSet.getInt("price");
        return new Product(id,name,category,country,price);
    };

    @Override
    public Optional<Product> findById(Long id) {
        try {
             return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID,rowMapper,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
       return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    @Override
    public Product save(Product item) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"id"});
            statement.setString(1,item.getName());
            statement.setString(2,item.getCategory());
            statement.setString(3,item.getCountry());
            statement.setInt(4,item.getPrice());
            return statement;
        },key);
        item.setId(key.getKey().longValue());
        return item;
    }

    @Override
    public void update(Long id, Product item) {
        jdbcTemplate.update(UPDATE_BY_ID,item.getName(),item.getCategory(),item.getCountry(),item.getPrice(),id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID,id);
    }
}
