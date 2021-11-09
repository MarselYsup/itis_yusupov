package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.semester.dao.StudioRepository;
import ru.itis.semester.models.Studio;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class StudioRepositoryImpl implements StudioRepository {
    private final JdbcTemplate jdbcTemplate;
    public StudioRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final RowMapper<Studio> rowMapper = (resultSet, i) -> {
        Long id = resultSet.getLong("studio_id");
        String name = resultSet.getString("name");
        Long avatarId = resultSet.getLong("avatar_id");
        return new Studio(id,name,avatarId);
    };
    private final static String SELECT_ALL = "Select * from studio";
    private final static String SELECT_BY_ID = "Select * from studio where studio_id = ?";
    private final static String INSERT = "insert into studio(name, avatar_id) VALUES (?,?)";

    @Override
    public Optional<Studio> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID,rowMapper,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Studio> findAll() {
        return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    @Override
    public Studio save(Studio item) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"studio_id"});
            statement.setString(1,item.getName());
            if(item.getAvatarId()==0L) statement.setNull(2, Types.NULL);
            else statement.setLong(2,item.getAvatarId());
            return statement;
        },key);
        item.setStudioId(key.getKey().longValue());
        return item;
    }

    @Override
    public void delete(Long id) {

    }
}
