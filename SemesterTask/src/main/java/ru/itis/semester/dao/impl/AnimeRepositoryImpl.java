package ru.itis.semester.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.semester.dao.AnimeRepository;
import ru.itis.semester.models.Anime;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class AnimeRepositoryImpl implements AnimeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String UPDATE_RATING = "Update anime set rating = ? where anime_id = ?";
    private final static String SELECT_BY_ID= "select * from anime a left join anime_genre ag on a.anime_id = ag.anime_id " +
            "left join genres g on ag.genre_id = g.genre_id where a.anime_id = ?";
    private final static String SELECT_ALL = "select * from anime a left join anime_genre ag on a.anime_id = ag.anime_id " +
            "left join genres g on ag.genre_id = g.genre_id  ";
    private final static String INSERT = "INSERT INTO anime(anime_title, year, episodes, description, rating, studio_id, avatar_id) " +
            "VALUES (?,?,?,?,?,?,?)";
    private final static String INSERT_GENRES = "INSERT INTO anime_genre(genre_id, anime_id) VALUES (?,?)";
    public AnimeRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final ResultSetExtractor<List<Anime>> animeListResultSetExtractor = row -> {
        List<Anime> animeList = new ArrayList<>();
        while (row.next()) {
            Long animeId = row.getLong("anime_id");
            String animeTitle = row.getString("anime_title");
            Integer year = row.getInt("year");
            Integer countOfEpisodes = row.getInt("episodes");
            String description = row.getString("description");
            Integer rating = row.getInt("rating");
            Long studioId = row.getLong("studio_id");
            Long avatarId = row.getLong("avatar_id");
            Anime anime = new Anime(animeId,animeTitle,year,countOfEpisodes,description,rating,new HashMap<>(),studioId,avatarId);
            do {
                Long genreId = row.getLong("genre_id");
                String genre = row.getString("genre_title");
                anime.getGenres().put(genreId,genre);
            }while (row.next()&&animeId==row.getLong("anime_id"));
            animeList.add(anime);
        }
        return animeList;
    };
    private final ResultSetExtractor<Anime> animeResultSetExtractor = row -> {
        Anime anime = new Anime();
        while (row.next()) {
            Long animeId = row.getLong("anime_id");
            String animeTitle = row.getString("anime_title");
            Integer year = row.getInt("year");
            Integer countOfEpisodes = row.getInt("episodes");
            String description = row.getString("description");
            Integer rating = row.getInt("rating");
            Long studioId = row.getLong("studio_id");
            Long avatarId = row.getLong("avatar_id");
            anime = new Anime(animeId,animeTitle,year,countOfEpisodes,description,rating,new HashMap<>(),studioId,avatarId);
            do {
                Long genreId = row.getLong("genre_id");
                String genre = row.getString("genre_title");
                anime.getGenres().put(genreId,genre);
            }while (row.next());
        }
        return anime;
    };

    @Override
    public Optional<Anime> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.query(SELECT_BY_ID,animeResultSetExtractor,id));
        }  catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Anime> findAll() {
        return jdbcTemplate.query(SELECT_ALL,animeListResultSetExtractor);
    }

    @Override
    public Anime save(Anime item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, new String[]{"anime_id"});
            statement.setString(1,item.getAnimeTitle());
            statement.setInt(2,item.getYear());
            statement.setInt(3,item.getCountOfEpisodes());
            statement.setString(4,item.getDescription());
            statement.setInt(5,item.getRating());
            if(item.getStudioId()==null) statement.setNull(6, Types.NULL);
            else statement.setLong(6,item.getStudioId());
            if(item.getAvatarId()==null) statement.setNull(7, Types.NULL);
            else statement.setLong(7,item.getAvatarId());
            return statement;
        }, keyHolder);
        item.setAnimeId(keyHolder.getKey().longValue());
        for (Map.Entry<Long,String> genre:
             item.getGenres().entrySet()) {
            jdbcTemplate.update(INSERT_GENRES,genre.getKey(),item.getAnimeId());
        }
        return item;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void updateRating(Integer rating,Long animeId) {
        jdbcTemplate.update(UPDATE_RATING,rating,animeId);
    }
}
