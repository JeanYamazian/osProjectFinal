package com.example.osprojectfinal;

public class Process {

    String id, priority;
    String state, ioInformation;

    public Process(String id, String priority, String state, String ioInformation) {
        this.id = id;
        this.priority = priority;
        this.state = state;
        this.ioInformation = ioInformation;
    }

    public Process() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
