package com.revature;

import io.javalin.Javalin;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        Javalin app = Main.start(users);
        Session session = new Session();

        app.get("/", context -> {
            context.result("Welcome to Reimbursement App\n"
                + "/users\n"
                + "/tickets\n"
                + "/login");
        });

        app.get("/login", context -> {
            context.result("Please login (post) or logout (delete)");
        });

        app.post("/login", context -> {
            User in = context.bodyAsClass(User.class);
            // if login is good => session.level = user.level, curUser = user.id, if (first) reset password (pass1234)
        });

        app.delete("/login", context -> {
            session.curUsername = null;
            session.curUserFK = -1;
            session.level = Session.Level.NONE;
            context.result("Logged Out");
        });


        app.get("/users", context -> {
            context.json(users);
        });

        app.post("/users", context -> {
            User newUser = context.bodyAsClass(User.class);

            if (users.contains(newUser))
                context.result("User already exists");
            else {
                users.add(newUser);
                context.result("User added");
            }
        });

        app.delete("/users", context -> {
            User user = context.bodyAsClass(User.class);

            if (session.level == Session.Level.ADMIN){
                //users.remove(user); find user id
                context.result(user.username+" has been removed");
            }
            else
                context.result("Current user does not have a high enough admin level");
        });



        app.get("/tickets", context -> {
            if (session.level == Session.Level.USER){
                //print tickets that are approved/denied
            }
            else if (session.level == Session.Level.ADMIN){
                //print all tickets
            }
            else {
                context.result("Not logged In");
            }
        });

        app.post("/tickets", context -> {

            if (session.level == Session.Level.USER) {
                Ticket newTicket = context.bodyAsClass(Ticket.class);
                //add ticket
            }
            else if (session.level == Session.Level.ADMIN){
                context.result("Conflict of Interest - Not Allowed - Ask Your Manager");
            }
            else {
                context.result("Not logged In");
            }
        });

        app.put("/tickets", context -> {
            if (session.level == Session.Level.USER) {
                context.result("Current user does not have a high enough admin level");
            }
            else if (session.level == Session.Level.ADMIN){
                TicketReq newTicketReq = context.bodyAsClass(TicketReq.class);
                newTicketReq.decision(newTicketReq, session);
                context.result("Conflict of Interest - Not Allowed - Ask Your Manager");
            }
            else {
                context.result("Not logged In");
            }
        });



    }

    public static Javalin start(List<User> users) {
        User u1 = new User(1, "mickey", "mouse", false);
        User u2 = new User(2, "minnie", "mouse", true);
        users.add(u1);
        users.add(u2);
        return (Javalin.create().start(8080));
    }
}



