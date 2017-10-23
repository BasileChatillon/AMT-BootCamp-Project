/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.CreateLinkLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.CreateDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.DeleteDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.GetDogLocal;
import ch.heigvd.amt.amtbootcamp.services.dao.UpdateDogLocal;
import java.net.URI;
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
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
@Stateless
@Path("/dog")
public class DogRessource {

    private final int maxNameLength = 45;
    private final int maxQuoteLegth = 200;

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

    @EJB
    CreateLinkLocal createLinks;

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

        System.out.println("Creating a new Dog");

        if (dog.getName().length() > maxNameLength) {
            dog.setName(dog.getName().substring(maxNameLength));
        }

        if (dog.getQuote().length() > maxQuoteLegth) {
            dog.setQuote(dog.getQuote().substring(maxQuoteLegth));
        }

        DogDTO newDog = createDog.createDog(dog);

        if (newDog == null) {

            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Could not create")
                    .build();
        }

        URI addresse = createLinks.APIGet(newDog.getID());

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

    /**
     * Méthode permettant de mettre à jour un chien selon son ID
     * retourne différente erreur en fonction du problème
     * --> BAD_GATEWAY si aucun chien ne possède l'id donné
     * --> NOT_MODIFIED si le chien a été trouvé mais non modifié
     * --> ACCEPTED si tout va bien
     * 
     * @param id L'id du chien $ modifier
     * @param dog Le nouveau chien
     * @return 
     */
    @Path("/update/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response dogUpdate(@PathParam("id") int id, Dog dog) {
        if (dog.getName().length() > maxNameLength) {
            dog.setName(dog.getName().substring(maxNameLength));
        }

        if (dog.getQuote().length() > maxQuoteLegth) {
            dog.setQuote(dog.getQuote().substring(maxQuoteLegth));
        }

        System.out.println("Updating a Dog");

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

        URI addresse = createLinks.APIGet(id);

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
        System.out.println("Getting a Dog");
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
        System.out.println("Deleting a Dog");

        DogDTO dogToDelete = getDog.findDog(id);

        if (deleteDog.deleteDog(id)) {
            return dogToDelete;
        }

        return null;
    }
}
