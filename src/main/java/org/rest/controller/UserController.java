package org.rest.controller;

import com.google.gson.Gson;
import org.rest.model.User;
import org.rest.service.UserService;
import org.rest.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (id == null) {
            out.print(gson.toJson(userService.getAll()));
        } else {
            if (userService.getById(Integer.parseInt(id)) == null){
                out.print("Error 404");
            }
            else {
                out.print(gson.toJson(userService.getById(Integer.parseInt(id))));
            }
        }
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        userService.save(gson.fromJson(request.getReader(), User.class));
        PrintWriter out = response.getWriter();
        out.print("User was saved");
        out.flush();
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        userService.update(gson.fromJson(req.getReader(), User.class));
        PrintWriter out = resp.getWriter();
        out.println("User was updated" );
        out.flush();
    }
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        userService.deleteById(Integer.parseInt(id));
        PrintWriter out = resp.getWriter();
        out.println("User was deleted");
        out.flush();
    }
    public void destroy() {

    }

}
