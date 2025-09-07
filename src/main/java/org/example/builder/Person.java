package org.example.builder;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String placeOfResidence;

    public Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.age = personBuilder.age;
        this.placeOfResidence = personBuilder.placeOfResidence;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public static class PersonBuilder{
        private String firstName;
        private String lastName;
        private int age;
        private String placeOfResidence;

        public PersonBuilder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonBuilder setAge(int age){
            this.age = age;
            return this;
        }

        public PersonBuilder setPlaceOfResidence(String placeOfResidence){
            this.placeOfResidence = placeOfResidence;
            return this;
        }

        public Person create(){
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", placeOfResidence='" + placeOfResidence + '\'' +
                '}';
    }
}
