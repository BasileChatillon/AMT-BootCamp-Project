/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author basilechatillon
 */
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
        // gestion des paraètres de pagination : on commence par les récupérer
        String dogIdDelete = request.getParameter(ATTRIBUT_ID);
        String pageParam = request.getParameter(ATTRIBUT_PAGE);
        String dogsInPageParam = request.getParameter(ATTRIBUT_ENTRY);

        String linkReturn;

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (pageParam != null && !pageParam.isEmpty() && dogsInPageParam != null && !dogsInPageParam.isEmpty()) {
            System.out.println("ch.heigvd.amt.amtbootcamp.web.DeleteDogServlet.doGet() : page - " + Integer.parseInt(pageParam));
            System.out.println("ch.heigvd.amt.amtbootcamp.web.DeleteDogServlet.doGet() : entry - " + Integer.parseInt(dogsInPageParam));

            URI linkReturn2 = createLink.ServletDisplayPage(Integer.parseInt(pageParam), Integer.parseInt(dogsInPageParam));
            linkReturn = linkReturn2.toString();

            System.out.println("ch.heigvd.amt.amtbootcamp.web.DeleteDogServlet.doGet() : link - " + linkReturn);
        } else {
            linkReturn = createLink.getServletDisplayPath();
        }

        System.out.println("ch.heigvd.amt.amtbootcamp.web.DeleteDogServlet.doGet()" + linkReturn);

        if (dogIdDelete != null && !dogIdDelete.isEmpty()) {
            int id = Integer.parseInt(request.getParameter(ATTRIBUT_ID));

            URI link = createLink.APIDelete(id);
            InputStream rsp = link.toURL().openStream();
        }

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
        return "Short description";
    }// </editor-fold>

}
