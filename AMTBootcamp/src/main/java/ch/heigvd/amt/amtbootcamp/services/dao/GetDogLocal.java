package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GetDogLocal {

    /**
     * Méthode qui permet de trouver et récupérer un chien dans la base de
     * donnée
     *
     * @param ID L'ID du chien à trouver
     * @return Le chien en question avec son ID ou null si rien n'a été trouvé
     */
    public DogDTO findDog(int ID);

    /**
     * Permet de récupérer tous les chiens de la base de donnée
     *
     * @return La liste de tous les chiens avec leur ID
     */
    public List<DogDTO> findAllDogs();

    /**
     * Permet de trouver une list de chien dans la base de donnée voir public
     * DogDTO findDog(int ID);
     *
     * @param IDs La liste des IDs des chiens
     * @return Les chiens trouvés
     */
    public List<DogDTO> findDogs(List<Integer> IDs);

    /**
     * Permet de trouver le nombre de chien que la base de donnée contient
     * (utile pour la pagination)
     *
     * @return Le nombre de chien
     */
    public int findNumberOfDog();

    /**
     * Fonction pour la pagination: Permet séléctionner les chiens à afficher en
     * fonction de la page et du nombre de chien par page
     *
     * Si la page ne contient aucun chien, retourne une liste vide.
     *
     * @param page Le numéro de la page à afficher
     * @param nbDogInPage Le nombre de chien compris dans une page
     * @return La liste des chiens compris dans la page
     */
    public List<DogDTO> findDogsPages(int page, int nbDogInPage);
}
