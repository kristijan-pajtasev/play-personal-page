package controllers;

import models.Post;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Security;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.posts.post;
import views.html.posts.index;
import views.html.posts.create;
import views.html.posts.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;


public class PostsController extends Controller {

    public Result index() {
        List<Post> postsList = Post.find.orderBy("published desc").findList();
        String tags = "";
        for(Post post: postsList) {
            tags += post.tags;
        }
        return ok(index.render(postsList, tags));
    }

    public Result post(String url) {
        Post p = Post.find.where().eq("url_title", url).findUnique();
        if(null == p) {
            return notFound("page not found");

        }
        return ok(post.render(p));
    }

    @Security.Authenticated(Secured.class)
    public Result createPost() {
        return ok(create.render());
    }

    @Security.Authenticated(Secured.class)
    public Result editPost(String url) {
        Post p = Post.find.where().eq("url_title", url).findUnique();
        if(null == p) {
            return notFound("page not found");

        }
        return ok(edit.render(p));
    }

    @Security.Authenticated(Secured.class)
    public Result edit(String url) {
        Post p = Post.find.where().eq("url_title", url).findUnique();
        if(null == p) {
            return notFound("page not found");

        }
        DynamicForm form = Form.form().bindFromRequest();
//        p.id = Long.parseLong(form.get("id"));
        p.setId(Long.parseLong(form.get("id")));
        p.title = form.get("title");
        p.urlTitle = form.get("urlTitle");
        p.post = form.get("post");
        p.tags = form.get("tags");
        p.description = form.get("description");
        p.update();
        return redirect(
                routes.PostsController.post(url)
        );
    }

    @Security.Authenticated(Secured.class)
    public Result create() {
        DynamicForm form = Form.form().bindFromRequest();
        Post newPost = new Post(
                form.get("title"),
                form.get("urlTitle"),
                form.get("post"),
                form.get("tags"),
                form.get("description")
                );
        newPost.save();
        return redirect(
                routes.PostsController.index()
        );
    }
}
