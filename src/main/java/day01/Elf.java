package day01;

import java.util.List;

class Elf {
    final List<Foodstuff> foodstuffs;

    Elf(List<Foodstuff> foodstuffs) {
        this.foodstuffs = foodstuffs;
    }

    int caloriesInFoodstuffs() {
        return foodstuffs.stream() //
                .mapToInt(foodstuff -> foodstuff.calories) //
                .sum();
    }
}