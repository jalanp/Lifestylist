package com.a3a04.android.lifestylist.database;

/**
 * Created by Temporary on 2017-04-03.
 */

public class PersonalData {

    private int id;
    private String name;
    private String address;
    private int age;
    private int height;
    private int weight;
    private int integration;

    public PersonalData(){
        this.id = 0;
        this.name = "";
        this.address = "";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.integration = 0;
    }

    public PersonalData(String name, String address, int age, int height, int weight, int integration){
        this.name = name;
        this.address = address;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.integration = integration;
    }

    public PersonalData(int id, String name, String address, int age, int height, int weight, int integration){
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.integration = integration;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setIntegration(int integration){
        this.integration = integration;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public int getAge(){
        return this.age;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWeight(){
        return this.weight;
    }

    public int getIntegration() { return this.integration; }
}
