/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.JsonifyDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
public class UpdateDogServlet extends HttpServlet {

    private final String Param_Name = "name";
    private final String Param_Age = "age";
    private final String Param_Weight = "weight";
    private final String Param_Quote = "quote";

    private final String Param_ID = "id";
    private final String Delete_Path = "http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/update";

    @EJB
    DogRessource dogRessource;

    @EJB
    JsonifyDogLocal jsonifyDog;

    @EJB
    GetDogLocal getDog;

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

            DogDTO dogToModify = getDog.findDog(id);

            request.setAttribute("dogToModify", dogToModify);
        }

        // Forward de la requête
        request.getRequestDispatcher("/WEB-INF/pages/DogUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCheck = request.getParameter(Param_Name);
        String nameCheck = request.getParameter(Param_Name);
        String ageCheck = request.getParameter(Param_Age);
        String weightCheck = request.getParameter(Param_Weight);
        String quoteCheck = request.getParameter(Param_Quote);

        System.out.println(nameCheck);
        System.out.println(ageCheck);
        System.out.println(weightCheck);
        System.out.println(quoteCheck);

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (idCheck == null || idCheck.isEmpty()
                || nameCheck == null || nameCheck.isEmpty()
                || ageCheck == null || ageCheck.isEmpty()
                || weightCheck == null || weightCheck.isEmpty()
                || quoteCheck == null || quoteCheck.isEmpty()) {
            response.sendRedirect("http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog");
            return;
        }

        URL link = dogRessource.createLinkUpdate(Integer.parseInt(request.getParameter(Param_ID))).toURL();

        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String JSON = jsonifyDog.jsonifyDog(new Dog(request.getParameter(Param_Name),
                Integer.parseInt(request.getParameter(Param_Age)),
                Double.parseDouble(request.getParameter(Param_Weight)),
                request.getParameter(Param_Quote)));

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

    public URI createLinkUpdateServlet(int ID) {
        return UriBuilder.fromPath(Delete_Path)
                .queryParam(Param_ID, ID)
                .build();
    }

    public URI createLinkUpdateServlet(DogDTO dog) {
        return createLinkUpdateServlet(dog.getID());
    }

    public List<URI> createLinkUpdateServlet(List<DogDTO> dogs) {
        return dogs.stream()
                .map(dog -> createLinkUpdateServlet(dog))
                .collect(Collectors.toList());
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
