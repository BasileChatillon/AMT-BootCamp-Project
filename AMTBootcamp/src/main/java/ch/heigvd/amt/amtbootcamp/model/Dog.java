/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.model;

/**
 *
 * @author basilechatillon
 */
public class Dog {

    private String name;
    private int age;
    private double weight;
    private String quote;

    public Dog() {
    }

    public Dog(String name, int age, double weight, String quote) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.quote = quote;
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

    @Override
    public String toString() {
        return "Dog : " + name + ", age: " + age + ", weight : " + weight
                + "is saying " + quote;
    }
}
