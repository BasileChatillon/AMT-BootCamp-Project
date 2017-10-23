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
public class GenerateDogServlet extends HttpServlet {

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
        // Forward de la requête
        request.getRequestDispatcher("/WEB-INF/pages/DogGeneration.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nbDogToGenerate = request.getParameter("nbtogenerate");

        int defaultNumberDog = 0;
        if (nbDogToGenerate != null && !nbDogToGenerate.isEmpty()) {
            defaultNumberDog = Integer.parseInt(nbDogToGenerate);
        }

        if (defaultNumberDog > 1234567) {
            defaultNumberDog = 1234567;
        }

        URI link = createLink.APICreateRandom(defaultNumberDog);
        InputStream rsp = link.toURL().openStream();

        // Forward de la requête
        response.sendRedirect(createLink.getServletDisplayPath());
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
