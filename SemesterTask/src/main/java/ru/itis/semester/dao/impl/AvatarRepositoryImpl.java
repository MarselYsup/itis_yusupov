package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.semester.dao.AvatarRepository;
import ru.itis.semester.models.Avatar;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class AvatarRepositoryImpl implements AvatarRepository {
    private final JdbcTemplate jdbcTemplate;

    public AvatarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final static String INSERT = "Insert INTO avatars(original_name, storage_name, size, mimeType) VALUES (?,?,?,?)";
    private final static String SELECT_BY_ID = "Select * from avatars where avatar_id = ?";
    private final static String SELECT_ALL = "Select * from avatars";
    private final static String DELETE_BY_ID = "Delete from avatars where avatar_id = ?";
    private RowMapper<Avatar> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("avatar_id");
        String originalName = resultSet.getString("original_name");
        String storageName = resultSet.getString("storage_name");
        Long size = resultSet.getLong("size");
        String type = resultSet.getString("mimetype");

        return new Avatar(id,originalName,storageName,size,type);
    };
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
    public Avatar save(Avatar item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"avatar_id"});
            statement.setString(1, item.getOriginalName());
            statement.setString(2, item.getStorageName());
            statement.setLong(3, item.getSize());
            statement.setString(4, item.getMimeType());
            return statement;
        },keyHolder);
        item.setAvatarId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID,id);
    }
}
