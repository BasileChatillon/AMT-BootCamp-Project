package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.services.CreateLinkLocal;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDogServlet extends HttpServlet {

    private final String ATTRIBUT_ID = "id";
    private final String ATTRIBUT_PAGE = "page";
    private final String ATTRIBUT_ENTRY = "entry";

    @EJB
    DogRessource dogRessource;

    @EJB
    CreateLinkLocal createLink;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet-delete::GET");
        // Gestion des paramètres de pagination : on commence par les récupérer
        String dogIdDelete = request.getParameter(ATTRIBUT_ID);
        String pageParam = request.getParameter(ATTRIBUT_PAGE);
        String dogsInPageParam = request.getParameter(ATTRIBUT_ENTRY);

        String linkReturn;

        // Gestion des paramètres pour la gestion des pages
        if (pageParam != null && !pageParam.isEmpty() && dogsInPageParam != null && !dogsInPageParam.isEmpty())
            linkReturn = createLink.ServletDisplayPage(Integer.parseInt(pageParam), Integer.parseInt(dogsInPageParam)).toString();
        else
            linkReturn = createLink.getServletDisplayPath();

        System.out.println("Servlet-delete::GET - lien de retour" + linkReturn);

        if (dogIdDelete != null && !dogIdDelete.isEmpty()) {
            int id = Integer.parseInt(dogIdDelete);

            URI link = createLink.APIDelete(id);
            System.out.println("Servlet-delete::GET - lien api de suppression" + link.toString());
            InputStream rsp = link.toURL().openStream();
        }

        System.out.println("Servlet-delete::GET - lien de redirection" + linkReturn);
        // Forward de la requête
        response.sendRedirect(linkReturn);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Supression d'un chien";
    }// </editor-fold>

}
