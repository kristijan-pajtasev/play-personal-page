package controllers;

import models.Employment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.employment.create;
import views.html.employment.edit;
import views.html.employment.index;

import java.util.List;


public class EmploymentController extends Controller {

    public Result index() {
        List<Employment> employmentsList = Employment.find.orderBy("startDate").findList();
        String tags = "";
        for(Employment employment: employmentsList) {
            tags += employment.getTags();
        }
        return ok(index.render(employmentsList, tags));
    }


    @Security.Authenticated(Secured.class)
    public Result createEmployment() {
        return ok(create.render());
    }

    @Security.Authenticated(Secured.class)
    public Result create() {
        Form.form(Employment.class).bindFromRequest().get().save();
        return redirect(
                routes.EmploymentController.index()
        );
    }

    @Security.Authenticated(Secured.class)
    public Result editEemployment(Long id) {
        Employment employment = Employment.find.byId(id);
        if(null == employment) {
            return notFound("page not found");

        }
        return ok(edit.render(employment));
    }

    public Result employment(Long id) {
        Employment employment = Employment.find.byId(id);
        if(null == employment) {
            return notFound("page not found");

        }
        return ok(views.html.employment.employment.render(employment, ""));
    }

    @Security.Authenticated(Secured.class)
    public Result edit() {
        Form.form(Employment.class).bindFromRequest().get().update();
        return redirect(
                routes.EmploymentController.index()
        );
    }
}
