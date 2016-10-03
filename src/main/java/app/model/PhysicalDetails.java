package app.model;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PhysicalDetails
{

    private int WEIGHT;
    private int HEIGHT;
    private int age;

    @Enumerated(EnumType.STRING)
    private Sex sex;



    public PhysicalDetails()
    {

    }

    public PhysicalDetails(int WEIGHT, int HEIGHT, int age, Sex sex) {
        this.WEIGHT = WEIGHT;
        this.HEIGHT = HEIGHT;
        this.age = age;
        this.sex = sex;
    }

    public void setWEIGHT(int WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getWEIGHT() {
        return WEIGHT;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getAge() {
        return age;
    }

    public Sex[] getSexList()
    {
        return Sex.values();
    }

    public Sex getSex() {
        return sex;
    }
}
