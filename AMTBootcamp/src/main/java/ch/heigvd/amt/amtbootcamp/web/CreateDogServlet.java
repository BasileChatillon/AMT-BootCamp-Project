package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.services.CreateLinkLocal;
import ch.heigvd.amt.amtbootcamp.services.JsonifyDogLocal;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDogServlet extends HttpServlet {

    private final String ATTRIBUT_NAME = "name";
    private final String ATTRIBUT_AGE = "age";
    private final String ATTRIBUT_WEIGHT = "weight";
    private final String ATTRIBUT_QUOTE = "quote";

    @EJB
    DogRessource dogRessource;

    @EJB
    JsonifyDogLocal jsonifyDog;

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

        String nameCheck = request.getParameter(ATTRIBUT_NAME);
        String ageCheck = request.getParameter(ATTRIBUT_AGE);
        String weightCheck = request.getParameter(ATTRIBUT_WEIGHT);
        String quoteCheck = request.getParameter(ATTRIBUT_QUOTE);

        System.out.println(nameCheck);
        System.out.println(ageCheck);
        System.out.println(weightCheck);
        System.out.println(quoteCheck);

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (nameCheck == null || nameCheck.isEmpty()
                || ageCheck == null || ageCheck.isEmpty()
                || weightCheck == null || weightCheck.isEmpty()
                || quoteCheck == null || quoteCheck.isEmpty()) {
            response.sendRedirect(createLink.getServletCreatePath());
            return;
        }

        URL link = createLink.APICustom().toURL();

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
        response.sendRedirect(createLink.getServletDisplayPath());
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
