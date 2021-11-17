package cinema.servlet;

import cinema.model.Movie;
import cinema.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cinema.model.User;
import cinema.store.PsqlStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class TicketServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        Ticket ticket = GSON.fromJson(req.getReader(), Ticket.class);
        User user = (User) req.getSession().getAttribute("user");
        Movie movie = (Movie) req.getSession().getAttribute("movie");
        System.out.println(user);
        System.out.println(movie);
        ticket.setMovie(movie);
        ticket.setUser(user);
        try {
            PsqlStore.instOf().add(ticket);
            writer.print("200 OK");
        } catch (SQLException e) {
            writer.print("400 Bad Request");
        }
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        Movie movie = (Movie) req.getSession().getAttribute("movie");
        String json = GSON.toJson(PsqlStore.instOf().findAllTicketsByMovie(movie));
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
