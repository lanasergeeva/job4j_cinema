package cinema.servlet;

import cinema.model.User;
import cinema.store.PsqlStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class AuthServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        User user = GSON.fromJson(req.getReader(), User.class);
        User check = PsqlStore.instOf().findByEmailUser(user.getName());
        if (check == null || !user.getPassword().equals(check.getPassword())) {
            writer.print("400 Bad Request");
        } else {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", check);
            writer.print(check);
        }
        writer.flush();
    }
}
