/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.DogCreate;
import ch.heigvd.amt.amtbootcamp.rest.DogDelete;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author basilechatillon
 */
public class TestDogServlet extends HttpServlet {

    @EJB
    private GetDogLocal getDog;
    
    @EJB
    private DogDelete deleteDog;
        
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
        List<URI> uris2 = new ArrayList<>();
        
//        for(DogDTO dog : dogs){
//            uris2.add(deleteDog.createLinkDeleteDog(dog));
//        }
        
        String uris = deleteDog.createLinkDeleteDog(dogs.get(0)).toString();
        //List<URI> uris2 = deleteDog.createLinksDeleteDogs(dogs);
        //List<String> urisS = deleteDog.createStringLinksDeleteDogs(uris);
        //List<String> uris = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        //request.setAttribute("dogs", dog);
        request.setAttribute("uris", uris);
        
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
