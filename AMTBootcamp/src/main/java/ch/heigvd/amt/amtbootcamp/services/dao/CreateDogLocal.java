package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import javax.ejb.Local;

@Local
public interface CreateDogLocal {

    /**
     * Méthode permettant de créer un chien et de l'ajouter dans la base de
     * donnée
     *
     * @param dog Le nouveau chien
     * @return Le chien créé avec son ID, ou null si la création a échouée
     */
    public DogDTO createDog(Dog dog);

    /**
     * Permet de créer un nombre de chien aléatoire. Son nom, son
     * poids, sa taille et sa quote seront générés aléatoirement
     *
     * @param number Le nombre de chien
     * @return Le nombre de chiens créés
     */
    public int createRandomDogs(int number);

}
