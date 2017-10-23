
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import javax.ejb.Local;


@Local
public interface JsonifyDogLocal {

    public String jsonifyDog(Dog dog);
}
