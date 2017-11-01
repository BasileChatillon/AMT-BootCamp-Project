package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.CreateLinkLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayDogServlet extends HttpServlet {

    @EJB
    private GetDogLocal getDog;

    @EJB
    CreateLinkLocal createLink;

    private final String ATTRIBUT_PAGE = "page";
    private final String ATTRIBUT_ENTRY = "entry";

    private final List<Integer> entriesValues = Arrays.asList(5, 10, 50, 100, 1000);

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * Permet de trouver les chiens à afficher en fonction de la page et du
     * nombre de chiens par page. Génére également les liens de suppression des
     * chiens.
     * Forward ces deux choses à la JSP qui affichera le tout dans un
     * tableau
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gestion des paramètres de pagination : on commence par les récupérer
        System.out.println("Servlet-display::GET");
        String pageParam = request.getParameter(ATTRIBUT_PAGE);
        String dogsInPageParam = request.getParameter(ATTRIBUT_ENTRY);

        // Gestion des paramètres : valeur par défaut des paramètres
        int defaultPageNumber = 1;
        int defaultDogsInPage = 10;

        // Gestion des paramètres : On tente de récupérer la valeur des param
        if (pageParam != null && !pageParam.isEmpty())
            defaultPageNumber = Integer.parseInt(pageParam);
        if (dogsInPageParam != null && !dogsInPageParam.isEmpty())
            defaultDogsInPage = Integer.parseInt(dogsInPageParam);

        System.out.println("Servlet-display::GET - nb page " + defaultPageNumber + " nb chien/page " + defaultDogsInPage);
        
        int pageMax = (getDog.findNumberOfDog() - 1) / defaultDogsInPage + 1;
        int previousPageNumber = (defaultPageNumber > 1 ? defaultPageNumber - 1 : 1);
        int nextPageNumber = (defaultPageNumber < pageMax ? defaultPageNumber + 1 : pageMax);

        System.out.println("Servlet-display::GET - creation lien de navigation entre les pages");
        URI firstPage = createLink.ServletDisplayPage(1, defaultDogsInPage);
        URI previousPage = createLink.ServletDisplayPage(previousPageNumber, defaultDogsInPage);
        URI nextPage = createLink.ServletDisplayPage(nextPageNumber, defaultDogsInPage);
        URI lastPage = createLink.ServletDisplayPage(pageMax, defaultDogsInPage);

        
        // Recherche des chiens à afficher en fonctions des paramètres
        List<DogDTO> dogs = getDog.findDogsPages(defaultPageNumber - 1, defaultDogsInPage);

        // Création des liens de suppressions des chiens
        System.out.println("Servlet-display::GET - creation lien Servlet Delete");
        List<URI> urisDelete = createLink.ServletDelete(dogs, defaultPageNumber, defaultDogsInPage);
        List<String> urisDeleteS = createLink.createStringLinks(urisDelete);

        System.out.println("Servlet-display::GET - creation lien Servlet Update");
        List<URI> urisUpdate = createLink.ServletUpdate(dogs, defaultPageNumber, defaultDogsInPage);
        List<String> urisUpdateS = createLink.createStringLinks(urisUpdate);

        System.out.println("Servlet-display::GET - creation lien pour le nombre de dog par pages");
        List<URI> urisEntries = createLink.ServletDisplayPage(defaultPageNumber, entriesValues);
        List<String> urisEntriesS = createLink.createStringLinks(urisEntries);

        // Ajout des attributs dans la requête
        request.setAttribute("dogs", dogs);
        request.setAttribute("urisDelete", urisDeleteS);
        request.setAttribute("urisUpdate", urisUpdateS);
        request.setAttribute("pageActual", defaultPageNumber);
        request.setAttribute("firstPage", firstPage.toString());
        request.setAttribute("previousPage", previousPage.toString());
        request.setAttribute("nextPage", nextPage.toString());
        request.setAttribute("lastPage", lastPage.toString());
        request.setAttribute("pageMax", pageMax);
        request.setAttribute("urisEntries", urisEntriesS);
        request.setAttribute("entrieValues", entriesValues);

        // Forward de la requête
        request.getRequestDispatcher("/WEB-INF/pages/DogDisplay.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Display givens dogs from the db.";
    }// </editor-fold>

}
