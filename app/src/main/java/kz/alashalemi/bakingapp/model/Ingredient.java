package kz.alashalemi.bakingapp.model;

/**
 * Created by zharas on 11/18/17.
 */

public class Ingredient {

    private double quantity;

    private String ingredient;
    private String measure;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
