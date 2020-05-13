package servlets;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/new")
public class AddUserServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("AddUserPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=utf-8");
        if (firstName != null && secondName != null && email != null && password != null &&
                !firstName.isBlank() && !secondName.isBlank() && !email.isBlank() && !password.isBlank()) {
            if (userService.addUser(new User(firstName, secondName, email, password))) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.sendRedirect("/admin/list");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("message", "Error create!!!");
                doGet(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Bad request!!!");
            doGet(req, resp);
        }
    }
}

