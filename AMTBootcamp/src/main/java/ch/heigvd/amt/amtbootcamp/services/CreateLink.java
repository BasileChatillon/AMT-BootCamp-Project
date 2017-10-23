/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.rest.DogRessource;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author basilechatillon
 */
@Stateless
public class CreateLink implements CreateLinkLocal {

    private final String pathDocker = "http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/";
    private final String pathAPI = "api/";
    private final String pathDeleteServlet = "dog/delete";
    private final String pathCreateServlet = "dog/create";
    private final String pathGenerateServlet = "dog/generate";
    private final String pathUpdateServlet = "dog/update";
    private final String pathDisplayServlet = "dog";

    private final String ATTRIBUT_ID = "id";
    private final String ATTRIBUT_PAGE = "page";
    private final String ATTRIBUT_ENTRY = "entry";

    @Override
    public String getAPIPath() {
        return pathDocker + pathAPI;
    }

    @Override
    public String getServletDisplayPath() {
        return pathDocker + pathDisplayServlet;
    }

    @Override
    public String getServletDeletePath() {
        return pathDocker + pathDeleteServlet;
    }

    @Override
    public String getServletCreatePath() {
        return pathDocker + pathCreateServlet;
    }

    @Override
    public String getServletUpdatePath() {
        return pathDocker + pathUpdateServlet;
    }

    @Override
    public String getServletGeneratePath() {
        return pathDocker + pathGenerateServlet;
    }

    /**
     * Permet de créer l'URL pour modifier un chien.
     *
     * @param ID l'ID du chien que l'on désire modifier
     * @return L'URI
     */
    @Override
    public URI APIUpdate(int ID) {
        return UriBuilder.fromPath(getAPIPath())
                .path(DogRessource.class)
                .path(DogRessource.class, "dogUpdate")
                .build(ID);
    }

    /**
     * Génère l?URI pour créer un chien
     *
     * @return
     */
    @Override
    public URI APICustom() {
        return UriBuilder.fromPath(getAPIPath())
                .path(DogRessource.class)
                .path(DogRessource.class, "createDog")
                .build();
    }

    /**
     * Permet de créer le lien de suppression d'une ID de chien
     *
     * @param id Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    @Override
    public URI APIDelete(int id) {
        // Crée l'url pour pouvoir supprimer le chien
        return UriBuilder.fromPath(getAPIPath())
                .path(DogRessource.class)
                .path(DogRessource.class, "dogDelete")
                .build(id);
    }

    /**
     * Permet de créer le lien de suppression d'un chien
     *
     * @param dog Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    @Override
    public URI APIDelete(DogDTO dog) {
        // Crée l'url pour pouvoir supprimer le chien
        return APIDelete(dog.getID());
    }

    /**
     * Permet de récuprer les url de suppresion d'une liste de chiens
     *
     * @param dogs la List de chien dont on veut créé les liens de suppresion
     * @return La liste des liens de suppression
     */
    @Override
    public List<URI> APIDelete(List<DogDTO> dogs) {
        return dogs.stream()
                .map((DogDTO dog) -> APIDelete(dog))
                .collect(Collectors.toList());
    }

    /**
     * Convertit des URIs de en string
     *
     * @param uris La liste des URIs que l'on veut transformer
     * @return Les URIs en string
     */
    @Override
    public List<String> createStringLinks(List<URI> uris) {
        return uris.stream()
                .map((URI uri) -> uri.toString())
                .collect(Collectors.toList());
    }

    /**
     * Permet de créer l'URL pour visioner un chien.
     *
     * @param ID l'ID du chien dont on doit créer l'URL
     * @return L'URI
     */
    @Override
    public URI APIGet(int ID) {
        return UriBuilder.fromPath(getAPIPath())
                .path(DogRessource.class)
                .path(DogRessource.class, "dogGet")
                .build(ID);
    }

    /**
     * Permet de créer le lien de récupération d'un chien
     *
     * @param dog Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    @Override
    public URI APIGet(DogDTO dog) {
        // Crée l'url pour pouvoir récupérer le chien
        return APIGet(dog.getID());
    }

    /**
     * Permet de créer l'URL pour créer des chien àléatoire
     *
     * @param number Le nombre de chien a creer
     * @return L'URI
     */
    @Override
    public URI APICreateRandom(int number) {
        return UriBuilder.fromPath(getAPIPath())
                .path(DogRessource.class)
                .path(DogRessource.class, "dogCreateRandom")
                .build(number);
    }

    @Override
    public URI ServletDelete(int id, int page, int entry) {
        return UriBuilder.fromPath(getServletDeletePath())
                .queryParam(ATTRIBUT_ID, id)
                .queryParam(ATTRIBUT_PAGE, page)
                .queryParam(ATTRIBUT_ENTRY, entry)
                .build();
    }

    @Override
    public URI ServletDelete(DogDTO dog, int page, int entry) {
        return ServletDelete(dog.getID(), page, entry);
    }

    @Override
    public List<URI> ServletDelete(List<DogDTO> dogs, int page, int entry) {
        return dogs.stream()
                .map(dog -> ServletDelete(dog, page, entry))
                .collect(Collectors.toList());
    }

    @Override
    public URI ServletDisplayPage(int pageNumber, int entry) {
        return UriBuilder.fromPath(getServletDisplayPath())
                .queryParam(ATTRIBUT_PAGE, pageNumber)
                .queryParam(ATTRIBUT_ENTRY, entry)
                .build();
    }

    /**
     *
     * @param pageNumber
     * @param entries
     * @return
     */
    @Override
    public List<URI> ServletDisplayPage(int pageNumber, List<Integer> entries) {
        return entries.stream()
                .map(entry -> ServletDisplayPage(pageNumber, entry))
                .collect(Collectors.toList());
    }

    @Override
    public URI ServletUpdate(int id, int page, int entry) {
        return UriBuilder.fromPath(getServletUpdatePath())
                .queryParam(ATTRIBUT_ID, id)
                .queryParam(ATTRIBUT_PAGE, page)
                .queryParam(ATTRIBUT_ENTRY, entry)
                .build();
    }

    @Override
    public URI ServletUpdate(DogDTO dog, int page, int entry) {
        return ServletUpdate(dog.getID(), page, entry);
    }

    @Override
    public List<URI> ServletUpdate(List<DogDTO> dogs, int page, int entry) {
        return dogs.stream()
                .map(dog -> ServletUpdate(dog, page, entry))
                .collect(Collectors.toList());
    }
}
