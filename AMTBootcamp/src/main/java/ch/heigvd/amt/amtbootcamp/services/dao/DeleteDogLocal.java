/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.net.URI;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author basilechatillon
 */
@Local
public interface DeleteDogLocal {
    public boolean deleteDog(int id);
    
    public URI createLinkDeleteDog(DogDTO dog);
    public List<URI> createLinksDeleteDogs(List<DogDTO> dog);
    public List<String> createStringLinksDeleteDogs(List<URI> uris);
}
