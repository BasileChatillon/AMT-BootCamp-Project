/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.dao.CreateDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.DeleteDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.UpdateDogLocal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
@Stateless
@Path("/dog")
public class DogRessource {

    private final String pathDocker = "http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/api/";

    @Context
    UriInfo uriInfo;

    @EJB
    DeleteDogLocal deleteDog;

    @EJB
    CreateDogLocal createDog;

    @EJB
    GetDogLocal getDog;

    @EJB
    UpdateDogLocal updateDog;

    /**
     * Permet d'ajouter un chien dans la base de donnée
     *
     * @param dog le chien qui sera crée
     * @return retourn un erreur si le chien n'a pas été créé, ou l'URL pour
     * recevoir les infromations du chien
     */
    @Path("/custom")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDog(Dog dog) {

        DogDTO newDog = createDog.createDog(dog);

        if (newDog == null) {

            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Could not create")
                    .build();
        }

        URI addresse = DogRessource.this.createLinkGet(newDog.getID());

        return Response.created(addresse)
                .status(Response.Status.CREATED)
                .build();
    }

    /**
     * Permet de crée des chiens de manière aléatoire
     *
     * @param number Le nombre de chien à générer
     * @return Les chiens généré
     */
    @Path("/random/{number}")
    @GET
    public Response dogCreateRandom(@PathParam("number") int number) {
        System.out.println("Number of dog to create " + number);
        int nbCreated = createDog.createRandomDogs(number);
        Response rsp;

        if (number > 0) {
            if (nbCreated == 0) {
                rsp = Response.status(Response.Status.EXPECTATION_FAILED)
                        .entity("Could not Create any Dogs")
                        .build();
            } else if (nbCreated == number) {
                String tmp = "All the dogs created (" + nbCreated + ")";
                rsp = Response.status(Response.Status.CREATED)
                        .entity(tmp)
                        .build();
            } else {
                String tmp = "Number of dog Created : " + nbCreated + "( of the " + number + " dogs requested";
                rsp = Response.status(Response.Status.CREATED)
                        .entity(tmp)
                        .build();
            }
        } else {
            rsp = Response.status(Response.Status.CONFLICT)
                    .entity("0 dog requested)")
                    .build();
        }
        return rsp;
    }

    @Path("/update/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response dogUpdate(@PathParam("id") int id, Dog dog) {

        // Si le chien n'existe pas dans la db on retourne not foud
        if (getDog.findDog(id) == null) {
            System.err.println("Le chien est inconnu");
            return Response.status(Response.Status.BAD_GATEWAY)
                    .entity("Could not find dog")
                    .build();
        }

        // Si il y a eu une erreur lors de la mise à jour
        if (!updateDog.updateDog(id, dog)) {
            System.err.println("Le chien ne s'est pas ajouté");
            return Response.status(Response.Status.NOT_MODIFIED)
                    .entity("Could not update dog")
                    .build();
        }

        System.out.println("Le chien a bien été ajouté");

        URI addresse = createLinkGet(id);

        return Response.created(addresse)
                .status(Response.Status.ACCEPTED)
                .build();
    }

    /**
     * Permet de récuprer les inforamtions d'un chien
     *
     * @param id l'ID du chien dont on veut obtenir les informations
     * @return Le JSON du chien
     */
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DogDTO dogGet(@PathParam("id") int id) {
        return getDog.findDog(id);
    }

    /**
     * Permet de supprimer un chien selon son ID Si la suppression échoue,
     * retourne null
     *
     * @param id l'ID du chien à supprimer
     * @return Le chien qu'on a supprimé
     */
    @Path("/delete/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DogDTO dogDelete(@PathParam("id") int id) {
        DogDTO dogToDelete = getDog.findDog(id);

        if (deleteDog.deleteDog(id)) {
            return dogToDelete;
        }

        return null;
    }

    /**
     * Permet de créer le lien de récupération d'un chien
     *
     * @param dog Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    public URI createLinkGet(DogDTO dog) {
        // Crée l'url pour pouvoir récupérer le chien
        return createLinkGet(dog.getID());
    }

    /**
     * Permet de créer l'URL pour visioner un chien.
     *
     * @param ID l'ID du chien dont on doit créer l'URL
     * @return L'URI
     */
    public URI createLinkGet(int ID) {
        return UriBuilder.fromPath(pathDocker)
                .path(DogRessource.class)
                .path(DogRessource.class, "dogGet")
                .build(ID);
    }
    
    /**
     * Permet de créer l'URL pour créer des chien àléatoire
     *
     * @param number  Le nombre de chien a creer
     * @return L'URI
     */
    public URI createLinkCreateRandom(int number) {
        return UriBuilder.fromPath(pathDocker)
                .path(DogRessource.class)
                .path(DogRessource.class, "dogCreateRandom")
                .build(number);
    }

    /**
     * Permet de créer le lien de suppression d'une ID de chien
     *
     * @param id Le chien qui va permettre la création de l'ID
     * @return l'URI destiné à la suppresion du chien
     */
    public URI createLinkDelete(int id) {
        // Crée l'url pour pouvoir supprimer le chien
        return UriBuilder.fromPath(pathDocker)
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
    public URI createLinkDelete(DogDTO dog) {
        // Crée l'url pour pouvoir supprimer le chien
        return createLinkDelete(dog.getID());
    }

    /**
     * Permet de récuprer les url de suppresion d'une liste de chiens
     *
     * @param dogs la List de chien dont on veut créé les liens de suppresion
     * @return La liste des liens de suppression
     */
    public List<URI> createLinksDelete(List<DogDTO> dogs) {
        return dogs.stream()
                .map(dog -> createLinkDelete(dog))
                .collect(Collectors.toList());
    }

    /**
     * Convertit des URIs de en string
     *
     * @param uris La liste des URIs que l'on veut transformer
     * @return Les URIs en string
     */
    public List<String> createStringLinks(List<URI> uris) {
        return uris.stream()
                .map(uri -> uri.toString())
                .collect(Collectors.toList());
    }
}
