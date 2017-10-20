/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
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
@Path("/dog/get")
public class DogGet {
    @Context
    UriInfo uriInfo;
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DogDTO dogGet(@PathParam("id") int id){
        return null;
    }
}
