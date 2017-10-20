/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import javax.ejb.Local;

/**
 *
 * @author basilechatillon
 */
@Local
public interface DeleteDogLocal {
    /**
     * Permet de supprimer un chien de la DB en fonction de son ID
     * @param id l'ID du chien à supprimmer
     * @return La vérification si le chien a bien été supprimé
     */
    public boolean deleteDog(int id);    
}
