package com.example.osprojectfinal;

public class Process {

    int id, priority;
    String state, ioInformation;

    public Process(int id, int priority, String state, String ioInformation) {
        this.id = id;
        this.priority = priority;
        this.state = state;
        this.ioInformation = ioInformation;
    }

    public Process() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIoInformation() {
        return ioInformation;
    }

    public void setIoInformation(String ioInformation) {
        this.ioInformation = ioInformation;
    }
}
