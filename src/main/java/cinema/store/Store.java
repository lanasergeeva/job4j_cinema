package cinema.store;

import cinema.model.Movie;
import cinema.model.Ticket;
import cinema.model.User;

import java.util.List;

public interface Store {
    User add(User user);

    User findByEmailUser(String name);

    List<Movie> findAllMovies();

    Ticket add(Ticket ticket);

    Ticket findAllTicketsByMovie();

}
