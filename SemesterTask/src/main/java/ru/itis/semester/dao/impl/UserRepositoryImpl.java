package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.semester.dao.UserRepository;
import ru.itis.semester.models.Anime;
import ru.itis.semester.models.AnimeUser;
import ru.itis.semester.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String INSERT = "INSERT INTO users (username, first_name, last_name, password, avatar_id) " +
            "VALUES (?,?,?,?,?)";
    private final static String SELECT_ALL =  "Select * from users u inner join user_anime ua on u.user_id = ua.user_id";
    private final static String SELECT_BY_ID = "Select * from users u inner join user_anime ua on u.user_id = ua.user_id where user_id = ?";
    private static final String DELETE_BY_ID = "Delete from users where user_id = ?";
    private final static String SELECT_BY_USERNAME = "Select * from users where username = ?";
    private final static String UPDATE_AVATAR_ID = "Update users set avatar_id = ? where user_id = ?";
    private final static String SELECT_ADMIN_BY_ID = "select a.user_id from users u inner join admin a on u.user_id = a.user_id where u.user_id=? ";
    private final ResultSetExtractor<List<User>> userListResultSetExtractor = resultSet -> {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            Long id  = resultSet.getLong("user_id");
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String password = resultSet.getString("password");
            Long avatarId = resultSet.getLong("avatar_id");
            User user = new User(id,username,firstName,lastName,password,avatarId);
            do {
                Long animeId = resultSet.getLong("anime_id");
                Integer status = resultSet.getInt("status");
                Integer rating = resultSet.getInt("user_rating");
                AnimeUser animeUser = new AnimeUser(animeId,id,status,rating);
                user.getAnimeList().add(animeUser);
            }while (resultSet.next()&&id==resultSet.getLong("user_id"));
            userList.add(user);
        }
        return userList;
    };
    private final ResultSetExtractor<User> userResultSetExtractor = resultSet -> {
        User user  = new User();
        while (resultSet.next()) {
            Long id  = resultSet.getLong("user_id");
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String password = resultSet.getString("password");
            Long avatarId = resultSet.getLong("avatar_id");
            user = new User(id,username,firstName,lastName,password,avatarId);
            do {
                Long animeId = resultSet.getLong("anime_id");
                Integer status = resultSet.getInt("status");
                Integer rating = resultSet.getInt("user_rating");
                AnimeUser animeUser = new AnimeUser(animeId,id,status,rating);
                user.getAnimeList().add(animeUser);
            }while (resultSet.next());
        }
        return user;
    };




    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<User> rowMapper = (resultSet, i) -> {
        Long id  = resultSet.getLong("user_id");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        Long avatarId = resultSet.getLong("avatar_id");
        return new User(id,username,firstName,lastName,password,avatarId);
    };
    private RowMapper<Long> rowMapperForAdmin = (resultSet, i) -> {
        return resultSet.getLong("user_id");
    };

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USERNAME,rowMapper,username));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAvatarForUser(Long userId, Long avatarId) {
        jdbcTemplate.update(UPDATE_AVATAR_ID,avatarId,userId);
    }

    @Override
    public Optional<Long> findAdminById(Long userId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_ADMIN_BY_ID,rowMapperForAdmin,userId));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.query(SELECT_BY_ID,userResultSetExtractor,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL,userListResultSetExtractor);
    }
    //TODO сделать изначально avatarId = 0
    @Override
    public User save(User item) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"user_id"});
            statement.setString(1,item.getUsername());
            statement.setString(2,item.getFirstName());
            statement.setString(3,item.getLastName());
            statement.setString(4,item.getPassword());
          if(item.getAvatarId()==null) statement.setNull(5,Types.NULL);
          else statement.setLong(5,item.getAvatarId());
            //statement.setLong(5,item.getAvatarId()==null? Types.NULL:item.getAvatarId());
            return statement;
        },key);
        item.setUserId(key.getKey().longValue());
        return item;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID,id);
    }
}
