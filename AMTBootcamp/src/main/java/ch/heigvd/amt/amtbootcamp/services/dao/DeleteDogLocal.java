package ch.heigvd.amt.amtbootcamp.services.dao;

import javax.ejb.Local;

@Local
public interface DeleteDogLocal {

    /**
     * Permet de supprimer un chien de la DB en fonction de son ID
     *
     * @param id l'ID du chien à supprimmer
     * @return La vérification si le chien a bien été supprimé
     */
    public boolean deleteDog(int id);
}
