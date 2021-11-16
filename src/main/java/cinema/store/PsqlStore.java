package cinema.store;

import cinema.model.Movie;
import cinema.model.Ticket;
import cinema.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private static final PsqlStore INSTANCE = new PsqlStore();

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        PsqlStore.class.getClassLoader()
                                .getResourceAsStream("db.properties")
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }


    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public User addUser(User user) throws SQLException {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn
                     .prepareStatement("INSERT INTO users(name, email, password, phone) VALUES (?, ?, ?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            } catch (Exception e) {
                LOG.error("Exception in addUser User ", e);
            }
        }
        return user;
    }

    @Override
    public User findByEmailUser(String name) {
        User rsl = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("select * from users where email like ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    rsl = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phone"
                            ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in findByEmailUser User ", e);
        }
        return rsl;
    }

    @Override
    public List<Movie> findAllMovies() {
        List<Movie> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM movies")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Movie(it.getInt("id"),
                            it.getString("name")));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in Movies ", e);
        }
        return posts;
    }

    @Override
    public Movie findMovieById(int id) {
        Movie rsl = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("select * from movies where id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    rsl = new Movie(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in findMovieById ", e);
        }
        return rsl;
    }

    @Override
    public Ticket add(Ticket ticket) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn
                     .prepareStatement("INSERT INTO ticket(row, cell, users_id, movies_id) VALUES (?, ?, ?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ticket.getRow());
            ps.setInt(2, ticket.getCell());
            ps.setInt(3, ticket.getUser().getId());
            ps.setInt(4, ticket.getMovie().getId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    ticket.setId(id.getInt(1));
                }
            } catch (Exception e) {
                LOG.error("Exception in addTicket ", e);
            }
        } catch (SQLException throwables) {
            LOG.error("SQLException in addTicket ", throwables);
        }
        return ticket;
    }

    @Override
    public List<Ticket> findAllTicketsByMovie(Movie movie) {
        List<Ticket> tickets = new ArrayList<>();
        int mov = movie.getId();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn
                     .prepareStatement("SELECT * FROM ticket where movies_id = ?")
        ) {
            ps.setInt(1, mov);
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    tickets.add(new Ticket(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("cell")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in findAllPosts ", e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        //int mov = movie.getId();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn
                     .prepareStatement("SELECT * FROM ticket")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    tickets.add(new Ticket(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("cell")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in findAllPosts ", e);
        }
        return tickets;
    }
}
