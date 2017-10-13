/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.services.InMemoryDataStoreLocal;
import ch.heigvd.amt.amtbootcamp.services.TestDogGenerationLocal;

import java.io.IOException;
import java.io.PrintWriter;
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
    private TestDogGenerationLocal testDogGeneration;
    
    @EJB
    private InMemoryDataStoreLocal inMemoryDataStore;
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
        
        testDogGeneration.generateDog();
        List<Dog> dogs = inMemoryDataStore.findAllDogs();
        
        request.setAttribute("dogs", dogs);
        
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
