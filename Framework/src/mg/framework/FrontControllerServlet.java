package mg.framework;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {

        @Override
        protected void doGet(
                        HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {

                String uri = request.getRequestURI();
                String context = request.getContextPath();

                String path = uri.substring(context.length());

                response.setContentType("text/plain");

                response.getWriter().println("=== Front Controller ===");
                response.getWriter().println();

                response.getWriter().println(
                                "Méthode HTTP : "
                                                + request.getMethod());

                response.getWriter().println(
                                "URI : "
                                                + uri);

                response.getWriter().println(
                                "Context Path : "
                                                + context);

                response.getWriter().println(
                                "Path : "
                                                + path);
        }

        @Override
        protected void doPost(
                        HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {

                String uri = request.getRequestURI();
                String context = request.getContextPath();

                String path = uri.substring(context.length());

                response.setContentType("text/plain");

                response.getWriter().println("=== Front Controller ===");
                response.getWriter().println();

                response.getWriter().println(
                                "Méthode HTTP : "
                                                + request.getMethod());

                response.getWriter().println(
                                "URI : "
                                                + uri);

                response.getWriter().println(
                                "Context Path : "
                                                + context);

                response.getWriter().println(
                                "Path : "
                                                + path);
        }

}