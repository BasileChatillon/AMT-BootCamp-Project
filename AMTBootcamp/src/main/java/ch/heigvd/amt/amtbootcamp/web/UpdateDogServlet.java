/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.CreateLinkLocal;
import ch.heigvd.amt.amtbootcamp.services.JsonifyDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
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
public class UpdateDogServlet extends HttpServlet {

    private final String ATTRIBUT_NAME = "name";
    private final String ATTRIBUT_AGE = "age";
    private final String ATTRIBUT_WEIGHT = "weight";
    private final String ATTRIBUT_QUOTE = "quote";
    private final String ATTRIBUT_ID = "id";
    private final String ATTRIBUT_PAGE = "page";
    private final String ATTRIBUT_ENTRY = "entry";

    @EJB
    DogRessource dogRessource;

    @EJB
    JsonifyDogLocal jsonifyDog;

    @EJB
    GetDogLocal getDog;

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

        String dogIdDelete = request.getParameter(ATTRIBUT_ID);
        String pageParam = request.getParameter(ATTRIBUT_PAGE);
        String dogsInPageParam = request.getParameter(ATTRIBUT_ENTRY);

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (pageParam != null && !pageParam.isEmpty()) {
            request.setAttribute(ATTRIBUT_PAGE, Integer.parseInt(pageParam));
        }

        if (dogsInPageParam != null && !dogsInPageParam.isEmpty()) {
            request.setAttribute(ATTRIBUT_ENTRY, Integer.parseInt(dogsInPageParam));
        }

        if (dogIdDelete != null && !dogIdDelete.isEmpty()) {
            int id = Integer.parseInt(dogIdDelete);

            DogDTO dogToModify = getDog.findDog(id);

            request.setAttribute("dogToModify", dogToModify);
        }

        // Forward de la requête
        request.getRequestDispatcher("/WEB-INF/pages/DogUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCheck = request.getParameter(ATTRIBUT_ID);
        String nameCheck = request.getParameter(ATTRIBUT_NAME);
        String ageCheck = request.getParameter(ATTRIBUT_AGE);
        String weightCheck = request.getParameter(ATTRIBUT_WEIGHT);
        String quoteCheck = request.getParameter(ATTRIBUT_QUOTE);
        String pageParam = request.getParameter(ATTRIBUT_PAGE);
        String dogsInPageParam = request.getParameter(ATTRIBUT_ENTRY);

        System.out.println(nameCheck);
        System.out.println(ageCheck);
        System.out.println(weightCheck);
        System.out.println(quoteCheck);

        
                
        // gestion des paraètres pour la gestion des pages
        String linkReturn;
        if (pageParam != null && !pageParam.isEmpty() && dogsInPageParam != null && !dogsInPageParam.isEmpty())
            linkReturn = createLink.ServletDisplayPage(Integer.parseInt(pageParam), Integer.parseInt(dogsInPageParam)).toString();
        else
            linkReturn = createLink.getServletDisplayPath();
        
        // gestion des paraètres : On tente de récupéré la valeur des param
        if (idCheck == null || idCheck.isEmpty()
                || nameCheck == null || nameCheck.isEmpty()
                || ageCheck == null || ageCheck.isEmpty()
                || weightCheck == null || weightCheck.isEmpty()
                || quoteCheck == null || quoteCheck.isEmpty()) {
            response.sendRedirect(linkReturn);
            return;
        }
        

        

        URL link = createLink.APIUpdate(Integer.parseInt(idCheck)).toURL();

        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String JSON = jsonifyDog.jsonifyDog(new Dog(nameCheck,
                Integer.parseInt(ageCheck),
                Double.parseDouble(weightCheck),
                quoteCheck));

        System.out.println(JSON);

        try (OutputStream os = con.getOutputStream()) {
            os.write(JSON.getBytes());
            os.flush();
            os.close();
        }

        int HttpResult = con.getResponseCode();
        con.disconnect();

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
        return "Mis à jour de chien";
    }// </editor-fold>

}
