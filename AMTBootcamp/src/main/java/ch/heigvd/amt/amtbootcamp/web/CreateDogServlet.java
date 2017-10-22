/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author basilechatillon
 */
public class CreateDogServlet extends HttpServlet {

    private final String Param_Name = "name";
    private final String Param_Age = "age";
    private final String Param_Weight = "weight";
    private final String Param_Quote = "quote";

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
        // Forward de la requête
        request.getRequestDispatcher("/WEB-INF/pages/DogCreate.jsp").forward(request, response);
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

        String nameCheck = request.getParameter(Param_Name);
        String ageCheck = request.getParameter(Param_Age);
        String weightCheck = request.getParameter(Param_Weight);
        String quoteCheck = request.getParameter(Param_Quote);

        System.out.println(nameCheck);
        System.out.println(ageCheck);
        System.out.println(weightCheck);
        System.out.println(quoteCheck);

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (nameCheck == null || nameCheck.isEmpty()
                || ageCheck == null || ageCheck.isEmpty()
                || weightCheck == null || weightCheck.isEmpty()
                || quoteCheck == null || quoteCheck.isEmpty()) {
            response.sendRedirect("http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create");
            return;
        }

        URL link = dogRessource.createLinkCustom().toURL();

        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String JSON = "{"
                + "\"" + Param_Name + "\": \"" + request.getParameter(Param_Name) + "\""
                + ", \"" + Param_Age + "\": " + Integer.parseInt(request.getParameter(Param_Age))
                + ", \"" + Param_Weight + "\": " + Double.parseDouble(request.getParameter(Param_Weight))
                + ", \"" + Param_Quote + "\": \"" + request.getParameter(Param_Quote) + "\""
                + "}";

        System.out.println(JSON);
        
        try (OutputStream os = con.getOutputStream()) {
            os.write(JSON.getBytes());
            os.flush();
            os.close();
        }

        int HttpResult = con.getResponseCode(); 
        con.disconnect();
        
        

        // Forward de la requête
        response.sendRedirect("http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Création d'un chien";
    }// </editor-fold>

}
