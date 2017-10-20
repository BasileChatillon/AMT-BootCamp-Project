/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.dao.DeleteDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
@Stateless
@Path("/dog/delete")
public class DogDelete {
    @Context
    UriInfo uriInfo;
    
    @EJB
    DeleteDogLocal deleteDog;
    
    @EJB
    GetDogLocal getDog;
    
    /**
     * Permet de supprimer un chien selon son ID
     * Si la suppression échoue, retourne null
     * @param id l'ID du chien à supprimer
     * @return Le chien qu'on a supprimé
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DogDTO dogDelete(@PathParam("id") int id) {
        DogDTO dogToDelete = getDog.findDog(id);
        
        if (deleteDog.deleteDog(id))
            return dogToDelete;
            
        return null;
    }
    
    /**
     * Permet de créer le lien de suppression d'un chien
     * @param dog Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    public URI createLinkDeleteDog(DogDTO dog) {
        // Crée l'url pour pouvoir supprimer le chien
        return uriInfo.getBaseUriBuilder()
                      .path(DogDelete.class)
                      .path(DogDelete.class, "dogDelete")
                      .build(dog.getID());        
    }

    /**
     * Permet de récuprer les url de suppresion d'une liste de chiens
     * @param dogs la List de chien dont on veut créé les liens de suppresion
     * @return La liste des liens de suppression
     */
    public List<URI> createLinksDeleteDogs(List<DogDTO> dogs) {
        return dogs.stream()
                   .map(dog -> createLinkDeleteDog(dog))
                   .collect(Collectors.toList());
    }
    
    /**
     * Convertit des URIs de en string
     * @param uris La liste des URIs que l'on veut transformer
     * @return Les URIs en string
     */
    public List<String> createStringLinksDeleteDogs(List<URI> uris) {
        return uris.stream()
                   .map(uri -> uri.toString())
                   .collect(Collectors.toList());
    }
}
