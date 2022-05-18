package com.example.osprojectfinal;

public class Process {

    String id, priority, state, ioInformation, initializedBy;

    public Process(String id, String priority, String state, String ioInformation, String initializedBy) {
        this.id = id;
        this.priority = priority;
        this.state = state;
        this.ioInformation = ioInformation;
        this.initializedBy = initializedBy;
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

    public String getInitializedBy() {
        return initializedBy;
    }

    public void setInitializedBy(String initializedBy) {
        this.initializedBy = initializedBy;
    }
}
