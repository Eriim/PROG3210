package com.erictossell.fitnesstracker;

import com.erictossell.fitnesstracker.Database.MacroPlan;


/**
 * Created by etossell8259 on 12/11/2017.
 */

// calculates maintenance calories for user
public class Util {
    public Double calculateCalories(Double age, Double feet, Double inches, Double weight, Double activity, String gender){
        Double calories = 0.0;
        if (gender.equals("male")){
            Double height = (feet*12) + inches;
            Double initial = 66.0;
            Double weightCoefficient = 6.23;
            Double heightCoefficient = 12.7;
            Double ageCoefficient = 6.8;
            Double activityCoefficient;

            calories = initial + (weightCoefficient*weight) + (heightCoefficient*height) - (ageCoefficient*age);

            if (activity == 0){
                activityCoefficient = 1.2;
                calories = calories * activityCoefficient;
            }
            if (activity == 1){
                activityCoefficient = 1.375;
                calories = calories * activityCoefficient;
            }
            if (activity == 2){
                activityCoefficient = 1.55;
                calories = calories * activityCoefficient;
            }
            if (activity == 3){
                activityCoefficient = 1.725;
                calories = calories * activityCoefficient;
            }
            if (activity == 4){
                activityCoefficient = 1.9;
                calories = calories * activityCoefficient;
            }
        }
        if (gender.equals("female")){
            Double height = (feet*12) + inches;
            Double initial = 655.0;
            Double weightCoefficient = 4.35;
            Double heightCoefficient = 4.7;
            Double ageCoefficient = 4.7;
            Double activityCoefficient;

            calories = initial + (weightCoefficient*weight) + (heightCoefficient*height) - (ageCoefficient*age);

            if (activity == 0){
                activityCoefficient = 1.2;
                calories = calories * activityCoefficient;
            }
            if (activity == 1){
                activityCoefficient = 1.375;
                calories = calories * activityCoefficient;
            }
            if (activity == 2){
                activityCoefficient = 1.55;
                calories = calories * activityCoefficient;
            }
            if (activity == 3){
                activityCoefficient = 1.725;
                calories = calories * activityCoefficient;
            }
            if (activity == 4){
                activityCoefficient = 1.9;
                calories = calories * activityCoefficient;
            }
        }

        return calories;

    }
    // calculate macronutrients based off calories and weight and goal
    public MacroPlan calculateMacro(long id, Double calories, Double weight, int goal) {
         MacroPlan macroPlan = new MacroPlan();
         double calcCalories;
         double protein;
         double fat;
         double carb;
        switch (goal){
        case 1:
            protein = weight * 1.3;
            fat = calories*0.111 * 0.3;
            calcCalories = calories-(protein*4.00)-(fat*9);
            carb = calcCalories*0.25;
            macroPlan = new MacroPlan(id, protein, fat, carb, calories);

            break;
        case 2:
            protein = weight * 1.1;
            fat = calories*0.111 * 0.3;
            calcCalories = calories-(protein*4.00)-(fat*9);
            carb = calcCalories*0.25;
            macroPlan = new MacroPlan(id, protein, fat, carb, calories);

            break;

        case 3:
            protein = weight * 1.0;
            carb = weight * 1.3;
            calcCalories = calories-(protein*4.00)-(carb*4);
            fat = calcCalories*0.111;
            macroPlan = new MacroPlan(id, protein, fat, carb, calories);

            break;
        case 4:
            protein = weight*0.8;
            fat = calories*0.111 * 0.3;
            calcCalories = calories -(protein*4)-(fat*9);
            carb = calcCalories*0.25;
            macroPlan = new MacroPlan(id, protein, fat, carb, calories);

            break;
        case 5:
            protein = weight*0.8;
            fat = calories*0.111 * 0.3;
            calcCalories = calories -(protein*4)-(fat*9);
            carb = calcCalories*0.25;
            macroPlan = new MacroPlan(id, protein, fat, carb, calories);

            break;

            }
        return macroPlan;
    }
}
