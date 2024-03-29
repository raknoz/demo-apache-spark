package org.example.controllers;

import com.google.gson.Gson;
import org.example.model.Response;
import org.example.model.StatusResponse;
import org.example.model.User;
import org.example.services.UserService;
import org.example.services.UserServiceMapImpl;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

public class UserController {

  public static void main(String[] args) {
    final UserService userService = new UserServiceMapImpl();

    post("/users", (request, response) -> {
      response.type("application/json");

      User user = new Gson().fromJson(request.body(), User.class);
      userService.addUser(user);

      return new Gson().toJson(new Response(StatusResponse.SUCCESS));
    });

    get("/users", (request, response) -> {
      response.type("application/json");

      return new Gson().toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(userService.getUsers())));
    });

    get("/users/:id", (request, response) -> {
      response.type("application/json");

      return new Gson().toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(userService.getUser(request.params(":id")))));
    });

    put("/users/:id", (request, response) -> {
      response.type("application/json");

      User toEdit = new Gson().fromJson(request.body(), User.class);
      User editedUser = userService.editUser(toEdit);

      if (editedUser != null) {
        return new Gson().toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(editedUser)));
      } else {
        return new Gson().toJson(new Response(StatusResponse.ERROR, new Gson().toJson("User not found or error in edit")));
      }
    });

    delete("/users/:id", (request, response) -> {
      response.type("application/json");

      userService.deleteUser(request.params(":id"));
      return new Gson().toJson(new Response(StatusResponse.SUCCESS, "user deleted"));
    });

    options("/users/:id", (request, response) -> {
      response.type("application/json");
      return new Gson().toJson(new Response(StatusResponse.SUCCESS, (userService.userExist(request.params(":id"))) ? "User exists" : "User does not exists"));
    });

  }
}
