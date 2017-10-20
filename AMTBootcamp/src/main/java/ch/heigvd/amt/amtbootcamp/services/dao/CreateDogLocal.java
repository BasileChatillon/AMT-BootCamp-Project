/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.util.List;

/**
 *
 * @author basilechatillon
 */
public interface CreateDogLocal {
    /**
     * Méthode permettant de créé un chien et de l'ajouter dans la base de donnée
     * @param dog Le nouveau chien
     * @return Le chien créé avec son ID, ou null si la création a échouée
     */
    public DogDTO createDog(Dog dog);
    /**
     * Permet de créer un nombre aléatoire de chien aléatoire.
     * Son nom, son poids, sa taille et sa quote seront généré aléatoirement
     * @param number Le nombre de chien 
     * @return retourne la liste de chien créé avec leur ID;
     */
    public List<DogDTO> createRandomDogs(int number);
    
}
