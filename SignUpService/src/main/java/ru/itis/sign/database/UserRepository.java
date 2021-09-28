package ru.itis.sign.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.sign.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRepository<User,Long>{
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_BY_NAME="Select * from users where username = ?";
    private static final String SELECT_BY_ID = "Select * from users where id = ?;";
    private static final String SELECT_ALL = "Select * from users;";
    private static final String INSERT = "Insert into users(username,password) values(?,?);";
    private static final String UPDATE_BY_ID = "Update users set username = ? ,password = ? where id = ? ;";
    private static final String DELETE_BY_ID = "Delete from users where id = ?;";
    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<User> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("username");
        String password = resultSet.getString("password");
        return new User(id,name,password);

    };
    private RowMapper<String> namesRowMapper = (resultSet, i) -> {
        String name = resultSet.getString("username");
        return name;
    };

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID,rowMapper,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_NAME,rowMapper,name));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    @Override
    public User save(User item) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"id"});
            statement.setString(1,item.getUsername());
            statement.setString(2,item.getPassword());
            return statement;
        },key);
        item.setId(key.getKey().longValue());
        return item;
    }

    @Override
    public void update(Long id, User item) {
        jdbcTemplate.update(UPDATE_BY_ID,item.getUsername(),item.getPassword(),id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID,id);
    }
    @Override
    public List<String> findAllNames() {
        return jdbcTemplate.query(SELECT_ALL,namesRowMapper);
    }
}
