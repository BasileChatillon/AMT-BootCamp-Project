/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;

/**
 *
 * @author basilechatillon
 */
@Singleton
public class InMemoryDataStore implements InMemoryDataStoreLocal{
        
    private long dogIdCounter = 0;
    
    private final Map<Long, Dog> dogs = new HashMap<>();

    @Override
    public long saveDog(Dog dog) {
        dogIdCounter++;
        dogs.put(dogIdCounter, dog);
        return dogIdCounter;
    }

    @Override
    public Dog loadDog(long Id) {
        return dogs.get(Id);
    }

    @Override
    public List<Dog> findAllDogs() {
         return new ArrayList<>(dogs.values());
    }
}
