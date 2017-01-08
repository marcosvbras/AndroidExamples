package com.androidexamples.app.domain;

/**
 * Created by marcos on 08/01/2017.
 */

@org.parceler.Parcel
public class ParcelableExample2 {

    // Este exemplo necessita da lib Parceler no Gradle
    // ao passar um objeto parcelable por uma Intent, utilizar: intent.putExtra(key, Parcels.wrap(objeto))
    // ao recuperar o objeto, utilizar: Objeto obj = Parcels.unwrap(intent.getParcelableExtra(key))

    // Attributes
    private long id;
    private int age;
    private String name;
    private double weight;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
