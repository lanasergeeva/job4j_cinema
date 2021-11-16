package cinema.store;

import cinema.model.Movie;
import cinema.model.Ticket;
import cinema.model.User;

import java.sql.SQLException;
import java.util.List;

public interface Store {
    User addUser(User user) throws SQLException;

    User findByEmailUser(String name);

    List<Movie> findAllMovies();

    Ticket add(Ticket ticket);

    List<Ticket> findAllTicketsByMovie(Movie movie);

    Movie findMovieById(int id);

    List<Ticket> findAllTickets();

}
