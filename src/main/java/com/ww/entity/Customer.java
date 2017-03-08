package com.ww.entity;

/**
 * Created by ww on 17/1/5.
 */
public class Customer {
    private String id;

    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void sing(String a, int b, Customer customer) {
        System.out.println(this.firstName + "sing");
    }

    public void sing() {
        System.out.println("sing...");
    }

    public void sing(String name) {
        System.out.println(name + "sing.........");
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName=&#039;%s&#039;, lastName=&#039;%s&#039;]",
                id, firstName, lastName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
