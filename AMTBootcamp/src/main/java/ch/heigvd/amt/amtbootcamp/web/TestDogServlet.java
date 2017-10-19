/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.DogDelete;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.InMemoryDataStoreLocal;
import ch.heigvd.amt.amtbootcamp.services.TestDogGenerationLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.DeleteDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDog;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
public class TestDogServlet extends HttpServlet {

    @EJB
    private GetDogLocal getDog;
    
    @EJB
    private DeleteDogLocal deleteDog;
    
    @Context
    UriInfo uriInfo;
    
    private final String PAGE_ATTRIBUT = "page";
    private final String ENTRY_ATTRIBUT = "entry";

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
        String pageParam = request.getParameter(PAGE_ATTRIBUT);
        String dogsInPageParam = request.getParameter(ENTRY_ATTRIBUT);
        
        int defaultPageNumber = 0;
        int defaultDogsInPage = 10;
        
        if (pageParam != null && !pageParam.isEmpty()) {
          defaultPageNumber = Integer.parseInt(request.getParameter(PAGE_ATTRIBUT)) - 1;
        }
        if (dogsInPageParam != null && !dogsInPageParam.isEmpty()) {
          defaultDogsInPage = Integer.parseInt(request.getParameter(ENTRY_ATTRIBUT));
        }
        
        List<DogDTO> dogs = getDog.findDogsPages(defaultPageNumber, defaultDogsInPage);
//        List<URI> uris = deleteDog.createLinksDeleteDogs(dogs);
//        
        request.setAttribute("dogs", dogs);
//        request.setAttribute("uris", uris);
//
//        
        request.getRequestDispatcher("/WEB-INF/pages/Dog.jsp").forward(request, response);
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
