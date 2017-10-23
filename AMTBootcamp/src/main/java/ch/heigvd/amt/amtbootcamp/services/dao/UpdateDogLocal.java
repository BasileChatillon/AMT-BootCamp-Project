package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import javax.ejb.Local;

@Local
public interface UpdateDogLocal {

    public boolean updateDog(int id, Dog dog);
}
