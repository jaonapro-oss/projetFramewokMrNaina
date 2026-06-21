package mg.framework;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mg.framework.util.ClasseUtilitaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrontControllerServlet extends HttpServlet {

    private ClasseUtilitaire util = new ClasseUtilitaire();
    private List<String> controllers = new ArrayList<>();

    @Override
    public void init() throws ServletException {

        try {

            controllers =  util.findControllers("controller");

            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (controllers == null || controllers.isEmpty()) {

            response.getWriter().println(
                    "Aucun controller trouve");

            return;
        }

        for (String controller : controllers) {

            response.getWriter().println(controller);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (controllers == null || controllers.isEmpty()) {

            response.getWriter().println(
                    "Aucun controller trouve");

            return;
        }

        for (String controller : controllers) {

            response.getWriter().println(controller);
        }
    }


}