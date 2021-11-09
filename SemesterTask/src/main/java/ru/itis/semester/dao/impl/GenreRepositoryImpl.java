package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.semester.dao.GenreRepository;
import ru.itis.semester.models.Genre;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GenreRepositoryImpl implements GenreRepository {
    private final JdbcTemplate jdbcTemplate;
    public GenreRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final RowMapper<Genre> rowMapper = (resultSet, i) -> {
        String title = resultSet.getString("genre_title");
        Long id = resultSet.getLong("genre_id");
        return new Genre(id,title);
    };
    private final static String SELECT_ALL = "Select * from genres";
    private final static String SELECT_BY_ID = "Select * from genres where genre_id = ?";
    @Override
    public Optional<Genre> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID,rowMapper,id));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    @Override
    public Genre save(Genre item) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
