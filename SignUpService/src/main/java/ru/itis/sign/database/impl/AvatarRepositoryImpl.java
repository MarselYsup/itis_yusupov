package ru.itis.sign.database.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.sign.database.AvatarRepository;
import ru.itis.sign.models.Avatar;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
//TODO сделать удаление аватарки, если добавили еще
public class AvatarRepositoryImpl implements AvatarRepository {
    private final JdbcTemplate jdbcTemplate;

    public AvatarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final static String SELECT_BY_USER_ID = "Select * from avatars where id_user = ?";
    private final static String INSERT = "Insert into avatars(original_name, storage_name," +
            " size, type, id_user) VALUES(?,?,?,?,?) ";
    private final static String SELECT_BY_ID = "Select * from avatars where id = ?";
    private final static String SELECT_ALL = "Select * from avatars";
    private RowMapper<Avatar> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("id");
        String originalName = resultSet.getString("original_name");
        String storageName = resultSet.getString("storage_name");
        Long size = resultSet.getLong("size");
        String type = resultSet.getString("type");
        Long idUser = resultSet.getLong("id_user");

        return new Avatar(id,originalName,storageName,size,type,idUser);
    };

    @Override
    public Avatar save(Avatar avatar) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"id"});
            statement.setString(1, avatar.getOriginalFileName());
            statement.setString(2, avatar.getStorageFileName());
            statement.setLong(3, avatar.getSize());
            statement.setString(4, avatar.getType());
            statement.setLong(5,avatar.getUserId());
            return statement;
        },keyHolder);
        avatar.setId(keyHolder.getKey().longValue());
        return avatar;
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID,rowMapper,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Avatar> findAll() {
       return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, Avatar item) { }

    @Override
    public Optional<Avatar> findByUserId(Long userId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USER_ID,rowMapper,userId));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
