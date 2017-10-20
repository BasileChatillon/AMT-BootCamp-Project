/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest.dto;

import ch.heigvd.amt.amtbootcamp.model.Dog;

/**
 * L'ID a été rajoutée pour permettre à l'utilisateur d'utiliser l'API
 * @author basilechatillon
 */
public class DogDTO {
    private int ID;
    private String name;
    private int age;
    private double weight;
    private String quote;
    
    public DogDTO() {}

    public DogDTO(int ID, String name, int age, double weight, String quote) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.quote = quote;
    }
    
    public DogDTO(Dog dog, int ID) {
        this.ID = ID;
        this.name = dog.getName();
        this.age = dog.getAge();
        this.weight = dog.getWeight();
        this.quote = dog.getQuote();
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getQuote() {
        return quote;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
    
}
