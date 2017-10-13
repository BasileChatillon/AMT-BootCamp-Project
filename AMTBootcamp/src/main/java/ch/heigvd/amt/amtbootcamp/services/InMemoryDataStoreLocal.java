/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author basilechatillon
 */
@Local
public interface InMemoryDataStoreLocal {
    public long saveDog(Dog dog);
    public Dog loadDog(long Id);
    
    public List<Dog> findAllDogs();
}
