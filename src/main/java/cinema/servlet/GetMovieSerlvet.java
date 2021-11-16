package cinema.servlet;

import cinema.model.Movie;
import cinema.model.Ticket;
import cinema.store.PsqlStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class GetMovieSerlvet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        int id = Integer.parseInt(req.getReader().readLine());
        Movie movie = PsqlStore.instOf().findMovieById(id);

        System.out.println(movie);

        HttpSession sc = req.getSession();
        sc.setAttribute("movie", movie);
        writer.print("200 OK");
        writer.flush();
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(PsqlStore.instOf().findAllMovies());
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }*/
}
