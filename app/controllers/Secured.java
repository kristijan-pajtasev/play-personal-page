package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

/**
 * Created by Kristijan on 2/14/2016.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        String username =  ctx.session().get("username");
        String uuid =  ctx.session().get("uuid");
        if(null != username && null != uuid) {
            Sessions session = Sessions.find.where().eq("uuid", uuid).findUnique();
            if(null != session && session.username.equals(username)) {
                return username;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.LoginController.authenticate());
    }
}