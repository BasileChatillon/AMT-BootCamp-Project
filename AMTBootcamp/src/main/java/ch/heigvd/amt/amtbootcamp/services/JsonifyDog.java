
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import javax.ejb.Stateless;

/**
 * On devrait utiliser une libairie, mais j ai pas réussi à les ajouter
 */
@Stateless
public class JsonifyDog implements JsonifyDogLocal {
    
    /**
     * Méthode de pauvre qui permet de transofmer un chien en JSON
     * On a un peu honte d'avoir fait ça
     * 
     * @param dog le chien à jonifier
     * @return Le JSON d'un chien
     */
    @Override
    public String jsonifyDog(Dog dog) {
        return "{"
                + "\"name\": \"" + dog.getName() + "\""
                + ", \"age\": " + dog.getAge()
                + ", \"weight\": " + dog.getWeight()
                + ", \"quote\": \"" + dog.getQuote() + "\""
                + "}";
    }
}
