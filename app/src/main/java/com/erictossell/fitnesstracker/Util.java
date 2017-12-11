package com.erictossell.fitnesstracker;

/**
 * Created by etossell8259 on 12/11/2017.
 */

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
}
