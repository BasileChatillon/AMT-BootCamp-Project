/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author basilechatillon
 */
@Stateless
public class TestDogGeneration implements TestDogGenerationLocal{
    
    @EJB
    private InMemoryDataStoreLocal inMemoryDataStore;

    @Override
    public void generateDog() {
        for ( int i = 0 ; i < 10 ; ++i) {
            String nom = "dogo-" + i;
            int age = (int) (Math.random() * 20);
            double poids = round((Math.random() * 40 + 10), 1);
            String quote = "Wof-" + i;
            Dog dog = new Dog(nom, age, poids, quote);
            inMemoryDataStore.saveDog(dog);
        }
    }
    
    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
