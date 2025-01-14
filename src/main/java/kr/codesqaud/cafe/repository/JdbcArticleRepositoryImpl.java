package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Primary
@Repository
public class JdbcArticleRepositoryImpl implements ArticleRepository {

    private final NamedParameterJdbcTemplate template;

    public JdbcArticleRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(final Article article) {
        final String sql = "INSERT INTO article (user_id, title, writer, contents) " +
                "VALUES (:userId, :title, :writer, :contents)";

        final SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);

        return (long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public Optional<Article> findById(final Long id) {
        final String sql = "" +
                "SELECT article.id, article.user_id, users.username as writer , article.title, article.contents, article.created_at, article.updated_at " +
                "FROM article " +
                "JOIN users ON article.user_id = users.id " +
                "WHERE article.id = :id";

        try (Stream<Article> result = template.queryForStream(sql, Map.of("id", id), articleRowMapper())) {
            return result.findFirst();
        }
    }

    @Override
    public List<Article> findAll() {
        final String sql = "" +
                "SELECT id, user_id, writer , title, contents, created_at, updated_at " +
                "FROM article";
        return template.query(sql, articleRowMapper());
    }

    @Override
    public void update(Article article) {
        final String sql = "" +
                "UPDATE article " +
                "SET title = :title, contents = :contents " +
                "WHERE id = :id";

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", article.getTitle())
                .addValue("contents", article.getContents())
                .addValue("id", article.getId());

        template.update(sql, params);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
