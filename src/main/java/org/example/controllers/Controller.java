package org.example.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Controller {

  public static void main(String[] args) {

    get("/hello", (req, res) -> "Hello there!");

    get("/hello/params/:name/", (req, res) -> "Hello " + req.params(":name"));

    get("/hello/views/:name/", Controller::message, new ThymeleafTemplateEngine());
  }

  public static ModelAndView message(Request req, Response res) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", req.params(":name"));
    return new ModelAndView(params, "hello");
  }
}
