package controller;

import mg.framework.annotation.Controller;
import mg.framework.annotation.UrlMapping;

@Controller
public class UserController {

    @UrlMapping(route="/users")
    public void list() {

    }

}