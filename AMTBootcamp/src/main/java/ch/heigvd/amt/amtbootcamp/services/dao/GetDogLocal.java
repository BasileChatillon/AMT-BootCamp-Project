/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author basilechatillon
 */
@Local
public interface GetDogLocal {
    public DogDTO findDog(int ID);
    public List<DogDTO> findAllDogs();
    public List<DogDTO> findDogs(List<Integer> IDs);
    public int findNumberOfDog();
    public List<DogDTO> findDogsPages(int page, int nbDogInPage);
}
