package mg.framework;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import mg.framework.mapping.Mapping;
import mg.framework.util.ClasseUtilitaire;

public class FrontControllerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        try {

            List<String> controllers =
                    ClasseUtilitaire.findControllers(
                            "controller");

            HashMap<String, Mapping> routes =
                    ClasseUtilitaire.findRoutes(
                            controllers);

            getServletContext().setAttribute("routes", routes);

            System.out.println(
                    "===== ROUTES =====");

            for (String route :
                    routes.keySet()) {

                Mapping mapping =
                        routes.get(route);

                System.out.println(
                        route
                        + " -> "
                        + mapping.getController()
                        + "."
                        + mapping.getMethod());

            }

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    private void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain");

        String uri =
                request.getRequestURI();

        String context =
                request.getContextPath();

        String route =
                uri.substring(context.length());

        @SuppressWarnings("unchecked")
        HashMap<String, Mapping> routes = (HashMap<String, Mapping>) getServletContext().getAttribute("routes");

        Mapping mapping = routes.get(route);

        if (mapping == null) {

            response.getWriter().println("Route inconnue : " + route);

            response.getWriter().println();

            response.getWriter().println("Routes disponibles :");

            for (String r :
                    routes.keySet()) {

                Mapping m =
                        routes.get(r);

                response.getWriter().println(
                        r
                        + " -> "
                        + m.getController()
                        + "."
                        + m.getMethod());

            }

            return;

        }

        response.getWriter().println(
                "Route : "
                + route);

        response.getWriter().println(
                "Controller : "
                + mapping.getController());

        response.getWriter().println(
                "Méthode : "
                + mapping.getMethod());

    }

}