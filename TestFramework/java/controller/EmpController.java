package controller;

import mg.framework.annotation.Controller;
import mg.framework.annotation.UrlMapping;

@Controller
public class EmpController {

    @UrlMapping(route="/emplist")
    public void liste() {

    }

    @UrlMapping(route="/emplist/new")
    public void create() {

    }

}