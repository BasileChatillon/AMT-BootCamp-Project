
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;

import javax.ejb.Local;
import java.util.List;


@Local
public interface RandomDogGenerationLocal {
    public List<Dog> generateDog(int number);
}
