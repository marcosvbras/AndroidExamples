package com.androidexamples.app.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcos on 08/01/2017.
 */

public class ParcelableExample1 implements Parcelable {

    // ao passar um objeto parcelable por uma Intent, utilizar: intent.putExtra(key, objeto)
    // ao recuperar o objeto, utilizar: Objeto obj = (Objeto)intent.GetParcelableExtra(key)

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

    // Parcelable

    public ParcelableExample1() {}

    protected ParcelableExample1(Parcel in) {
        id = in.readLong();
        age = in.readInt();
        name = in.readString();
        weight = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Dados a serem serializados
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeDouble(weight);
    }

    // Lê os dados na mesma ordem em que foram escritos
    public void readFromParcel(Parcel parcel) {
        id = parcel.readLong();
        age = parcel.readInt();
        name = parcel.readString();
        weight = parcel.readDouble();
    }

    public static final Creator<ParcelableExample1> CREATOR = new Creator<ParcelableExample1>() {
        @Override
        public ParcelableExample1 createFromParcel(Parcel in) {
            return new ParcelableExample1(in);
        }

        @Override
        public ParcelableExample1[] newArray(int size) {
            return new ParcelableExample1[size];
        }
    };
}
