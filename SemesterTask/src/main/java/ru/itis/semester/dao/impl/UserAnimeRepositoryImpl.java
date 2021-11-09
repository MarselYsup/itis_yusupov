package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.semester.dao.UserAnimeRepository;
import ru.itis.semester.models.AnimeUser;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UserAnimeRepositoryImpl implements UserAnimeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String UPDATE_RATING = "Update user_anime set user_rating = ? where user_id=? and anime_id = ?";
    private final static String SELECT_BY_USER_ID_AND_ANIME_ID = "select * from user_anime where user_id=? and anime_id = ?";
    private final static String SELECT_BY_USER_ID = "select * from user_anime ua inner join anime a on a.anime_id = ua.anime_id  where user_id=? order by a.anime_title";
    private final static String INSERT_USER_ANIME = "insert into user_anime(user_id, anime_id, status, user_rating) values (?,?,?,?) on conflict (user_id,anime_id) do update" +
            " set status = excluded.status, user_rating = excluded.user_rating;";
    private final static String SELECT_BY_ANIME_ID = "select * from user_anime where anime_id=?";

    public UserAnimeRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final RowMapper<AnimeUser> rowMapper = (resultSet, i) -> {
        Long userId = resultSet.getLong("user_id");
        Long animeId = resultSet.getLong("anime_id");
        Integer status = resultSet.getInt("status");
        Integer rating = resultSet.getInt("user_rating");
        return new AnimeUser(animeId,userId,rating,status);
    };
    private final RowMapper<AnimeUser> rowMapperForList = (resultSet, i) -> {
        Long userId = resultSet.getLong("user_id");
        Long animeId = resultSet.getLong("anime_id");
        Integer status = resultSet.getInt("status");
        Integer rating = resultSet.getInt("user_rating");
        AnimeUser animeUser =  new AnimeUser(animeId,userId,rating,status);
        String title  = resultSet.getString("anime_title");
        animeUser.setTitle(title);
        return animeUser;
    };


    @Override
    public Optional<AnimeUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<AnimeUser> findAll() {
        return null;
    }

    @Override
    public AnimeUser save(AnimeUser item) {
        jdbcTemplate.update(INSERT_USER_ANIME,item.getUserId(),item.getAnimeId(),
                item.getStatus(),item.getUserRating());
        return item;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AnimeUser> findByUserId(Long userId) {
        return jdbcTemplate.query(SELECT_BY_USER_ID,rowMapperForList,userId);
    }

    @Override
    public Optional<AnimeUser> findByUserIdAndAnimeId(Long userId, Long animeId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USER_ID_AND_ANIME_ID,rowMapper,userId,animeId));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateRating(Integer rating, Long userId, Long animeId) {
        jdbcTemplate.update(UPDATE_RATING,rating,userId,animeId);
    }

    @Override
    public List<AnimeUser> findByAnimeId(Long animeId) {
        return jdbcTemplate.query(SELECT_BY_ANIME_ID,rowMapper,animeId);
    }


}
