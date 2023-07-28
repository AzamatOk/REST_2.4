package org.rest.controller;

import com.google.gson.Gson;
import org.rest.model.Event;
import org.rest.model.User;
import org.rest.service.EventService;
import org.rest.service.UserService;
import org.rest.service.impl.EventServiceImpl;
import org.rest.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EventController extends HttpServlet {
    private EventService eventService = new EventServiceImpl();
    private Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (id == null) {
            out.print(gson.toJson(eventService.getAll()));
        } else {
            if (eventService.getById(Integer.parseInt(id)) == null){
                out.print("Error 404");
            }
            else {
                out.print(gson.toJson(eventService.getById(Integer.parseInt(id))));
            }
        }
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        eventService.save(gson.fromJson(request.getReader(), Event.class));
        PrintWriter out = response.getWriter();
        out.print("Event was saved");
        out.flush();
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        eventService.update(gson.fromJson(req.getReader(), Event.class));
        PrintWriter out = resp.getWriter();
        out.println("Event was updated" );
        out.flush();
    }
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        eventService.deleteById(Integer.parseInt(id));
        PrintWriter out = resp.getWriter();
        out.println("Event was deleted");
        out.flush();
    }
}
