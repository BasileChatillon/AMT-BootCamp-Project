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
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DogDTO dogDelete(@PathParam("id") int id) {
        DogDTO dogToDelete = getDog.findDog(id);
        
        if (deleteDog.deleteDog(id))
            return dogToDelete;
            
        return null;
    }
    
    public URI createLinkDeleteDog(DogDTO dog) {
        // Crée l'url de la ressource qu'on vient de créer
        return uriInfo.getBaseUriBuilder()
                      .path(DogDelete.class)
                      .path(DogDelete.class, "dogDelete")
                      .build(dog.getID());        
    }

    public List<URI> createLinksDeleteDogs(List<DogDTO> dogs) {
        return dogs.stream()
                   .map(dog -> createLinkDeleteDog(dog))
                   .collect(Collectors.toList());
    }
    
    public List<String> createStringLinksDeleteDogs(List<URI> uris) {
        return uris.stream()
                   .map(uri -> uri.toString())
                   .collect(Collectors.toList());
    }
}
