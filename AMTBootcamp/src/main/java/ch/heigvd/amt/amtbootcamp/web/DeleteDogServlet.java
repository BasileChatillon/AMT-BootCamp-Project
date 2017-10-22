/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author basilechatillon
 */
public class DeleteDogServlet extends HttpServlet {
    private final String Param_ID = "id";
    private final String Delete_Path = "http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/delete";
    
    @EJB
    DogRessource dogRessource;

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
        String dogIdDelete = request.getParameter(Param_ID);
        
        if (dogIdDelete != null && !dogIdDelete.isEmpty()) {
            int id = Integer.parseInt(request.getParameter(Param_ID));
            
            URI link = dogRessource.createLinkDelete(id);
            InputStream rsp = link.toURL().openStream();
        }
        
        
        // Forward de la requête
        response.sendRedirect("http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog");
        
    }
    
    public URI createLinkDeleteServlet(int ID){
        return UriBuilder.fromPath(Delete_Path)
                         .queryParam(Param_ID, ID)
                         .build();
    }
    
    public URI createLinkDeleteServlet(DogDTO dog){
        return createLinkDeleteServlet(dog.getID());
    }
    
    public List<URI> createLinksDeleteServlet(List<DogDTO> dogs){
        return dogs.stream()
                   .map(dog -> createLinkDeleteServlet(dog))
                   .collect(Collectors.toList());
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
            return;
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
