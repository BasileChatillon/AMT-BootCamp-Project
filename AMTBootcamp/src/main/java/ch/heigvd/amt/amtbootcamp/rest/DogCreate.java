/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.dao.CreateDogLocal;
import java.net.URI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
@Stateless
@Path("/dog/custom")
public class DogCreate {
    @Context
    UriInfo uriInfo;
    
    @EJB
    GetDogLocal getdog;
    
    @EJB
    CreateDogLocal createDog;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResponse(Dog dog){
        
        DogDTO newDog = createDog.createDog(dog);
    
        if (newDog == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("Could not create")
                           .build();
        }
        
        URI addresse = createLink(newDog.getID());
        
        return Response.created(addresse)
                       .status(Response.Status.CREATED)
                       .build();
    }
    
    public URI createLink(int ID){
        return uriInfo.getBaseUriBuilder()
                      .path(DogGet.class)
                      .path(DogGet.class, "dogGet")
                      .build(ID);
    }
}
