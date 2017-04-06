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
    private int integration; //0 = no, 1 = yes
    private int gender; //0 = male, 1 = female
    private int firstLogin; //0 = no, 1 = yes
    private int mealToggle; //0 = no, 1 = yes
    private int workoutToggle; //0 = no, 1 = yes
    private int sleepToggle; //0 = no, 1 = yes


    public PersonalData(){
        this.id = 0;
        this.name = "";
        this.address = "";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.integration = 0;
        this.gender = 0;
        this.firstLogin = 0;
    }

    public PersonalData(String name, String address, int age, int height, int weight, int integration, int gender,
                        int firstLogin, int mealToggle, int workoutToggle, int sleepToggle){
        this.name = name;
        this.address = address;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.integration = integration;
        this.gender = gender;
        this.firstLogin = firstLogin;
        this.mealToggle = mealToggle;
        this.workoutToggle = workoutToggle;
        this.sleepToggle = sleepToggle;
    }

    public PersonalData(int id, String name, String address, int age, int height, int weight, int integration, int gender,
                        int firstLogin, int mealToggle, int workoutToggle, int sleepToggle){
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.integration = integration;
        this.gender = gender;
        this.firstLogin = firstLogin;
        this.mealToggle = mealToggle;
        this.workoutToggle = workoutToggle;
        this.sleepToggle = sleepToggle;
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

    public void setGender(int gender){
        this.gender = gender;
    }

    public void setFirstLogin(int firstLogin){
        this.firstLogin = firstLogin;
    }

    public void setMealToggle(int mealToggle){
        this.mealToggle = mealToggle;
    }

    public void setWorkoutToggle(int workoutToggle){
        this.workoutToggle = workoutToggle;
    }

    public void setSleepToggle(int sleepToggle){
        this.sleepToggle = sleepToggle;
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

    public int getGender() { return this.gender; }

    public int getFirstLogin() { return this.firstLogin; }

    public int getMealToggle() { return this.mealToggle; }

    public int getWorkoutToggle() { return this.workoutToggle; }

    public int getSleepToggle() { return this.sleepToggle; }

}
