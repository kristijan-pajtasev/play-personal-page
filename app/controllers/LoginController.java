package controllers;

import models.Sessions;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login.index;
import static play.data.Form.*;
import play.data.*;

import java.util.List;

public class LoginController extends Controller {

    public Result index() {

        return ok(index.render(form(Login.class)));
    }

    public Result authenticate() {

        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(index.render(loginForm));
        } else {
            String username = loginForm.get().username;
            if(User.authenticate(username, loginForm.get().password)) {
                session().clear();
                deleteSessions(Sessions.find.where().eq("username", username).findList());
                String uuid = java.util.UUID.randomUUID().toString();
                session("uuid", uuid);
                session("username", username);
                new Sessions(uuid, username).save();
                return redirect(
                        routes.PostsController.index()
                );
            } else {
                return badRequest(index.render(loginForm));
            }
        }
    }

    private void deleteSessions(List<Sessions> sessions) {
        for(Sessions session: sessions) {
            session.delete();
        }
    }

    public static class Login {

        public String username;
        public String email;
        public String password;

    }

}
