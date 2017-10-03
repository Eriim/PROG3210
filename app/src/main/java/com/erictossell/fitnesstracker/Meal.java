package com.erictossell.fitnesstracker;

/**
 * Created by erict on 9/26/2017.
 */

public class Meal {
    private String name;
    private Integer calories;
    private Integer fat;
    private Integer protein;
    private Integer carbohydrates;
    private String servingSize;

    public Meal(){

    }
    public Meal(String name, Integer calories, Integer fat, Integer protein, Integer carbohydrates, String servingSize) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.servingSize = servingSize;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    public Integer getCalories(){
        return calories;
    }
    public void setFat(Integer fat) {
        this.fat = fat;
    }
    public Integer getFat(){
        return fat;
    }
    public void setProtein(Integer protein) {
        this.protein = protein;
    }
    public Integer getProtein(){
        return protein;
    }
    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
    public Integer getCarbohydrates(){
        return carbohydrates;
    }
    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }
    public String getServingSize(){
        return servingSize;
    }

}
