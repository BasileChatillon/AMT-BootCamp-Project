/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.web;

import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
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
public class DisplayDogServlet extends HttpServlet {

    @EJB
    private GetDogLocal getDog;

    @EJB
    private DogRessource dogRessource;

    private final String Dsiplay_Path = "http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog";

    private final DeleteDogServlet deleteServlet = new DeleteDogServlet();
    private final UpdateDogServlet updateServlet = new UpdateDogServlet();

    private final String PAGE_ATTRIBUT = "page";
    private final String ENTRY_ATTRIBUT = "entry";

    private final List<Integer> entriesValues = Arrays.asList(5, 10, 50, 100, 1000);

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * Permet de trouver les chiens à afficher en fonction de la page et du
     * nombre de chien par page. Génére églaement les liens de suppression des
     * chiens Forward ces deux choses à la JSP qui affichera le toute dans un
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
        // gestion des paraètres : on commence par les récupérer
        String pageParam = request.getParameter(PAGE_ATTRIBUT);
        String dogsInPageParam = request.getParameter(ENTRY_ATTRIBUT);

        // gestion des paraètres : valeur par défaut des param
        int defaultPageNumber = 1;
        int defaultDogsInPage = 10;

        // gestion des paraètres : On tente de récupéré la valeur des param
        if (pageParam != null && !pageParam.isEmpty()) {
            defaultPageNumber = Integer.parseInt(request.getParameter(PAGE_ATTRIBUT));
        }
        if (dogsInPageParam != null && !dogsInPageParam.isEmpty()) {
            defaultDogsInPage = Integer.parseInt(request.getParameter(ENTRY_ATTRIBUT));
        }

        System.out.println(defaultPageNumber);
        System.out.println(defaultDogsInPage);
        
        int pageMax = (getDog.findNumberOfDog() - 1) / defaultDogsInPage + 1;

        URI firstPage = createLinkPage(1, defaultDogsInPage);

        int previousPageNumber = (defaultPageNumber > 1 ? defaultPageNumber - 1 : 1);
        URI previousPage = createLinkPage(previousPageNumber, defaultDogsInPage);

        int nextPageNumber = (defaultPageNumber < pageMax ? defaultPageNumber + 1 : pageMax);
        URI nextPage = createLinkPage(nextPageNumber, defaultDogsInPage);

        URI lastPage = createLinkPage(pageMax, defaultDogsInPage);

        // Recherche des chiens à afficher en fonctions des param
        List<DogDTO> dogs = getDog.findDogsPages(defaultPageNumber - 1, defaultDogsInPage);

        // Création des lien de suppressions des chiens
        List<URI> urisDelete = deleteServlet.createLinksDeleteServlet(dogs);
        List<String> urisDeleteS = dogRessource.createStringLinks(urisDelete);

        List<URI> urisUpdate = updateServlet.createLinkUpdateServlet(dogs);
        List<String> urisUpdateS = dogRessource.createStringLinks(urisUpdate);
        
        List<URI> urisEntries = createLinksPage(defaultPageNumber, entriesValues);
        List<String> urisEntriesS = dogRessource.createStringLinks(urisEntries);
        for(String s : urisEntriesS){
            System.out.println(s);

        }
        
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

    public URI createLinkPage(int pageNumber, int numberdog) {
        return UriBuilder.fromPath(Dsiplay_Path)
                .queryParam(PAGE_ATTRIBUT, pageNumber)
                .queryParam(ENTRY_ATTRIBUT, numberdog)
                .build();
    }

    public List<URI> createLinksPage(int pageNumber, List<Integer> numbersDog) {
        return numbersDog.stream()
                .map(numberDog -> createLinkPage(pageNumber, numberDog))
                .collect(Collectors.toList());
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
