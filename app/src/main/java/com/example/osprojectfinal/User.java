package com.example.osprojectfinal;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class User implements Parcelable {

    String firstName, lastName, email, address, position, username, password,membership_id;
    String phoneNumber;
    String priorityLevel;
    String id;

    public User() {
    }


    public User(String firstName, String lastName, String email, String phoneNumber, String address, String position, String id, String priorityLevel, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.position = position;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.priorityLevel = priorityLevel;

    }

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        address = in.readString();
        position = in.readString();
        username = in.readString();
        password = in.readString();
        membership_id = in.readString();
        phoneNumber = in.readString();
        priorityLevel = in.readString();
        id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getPosition() {
        return position;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(position);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(membership_id);
        parcel.writeString(phoneNumber);
        parcel.writeString(priorityLevel);
        parcel.writeString(id);
    }
}
