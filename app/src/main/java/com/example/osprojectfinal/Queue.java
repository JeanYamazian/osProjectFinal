package com.example.osprojectfinal;

import android.widget.Toast;

public class Queue {

    int front;
    int rear;
    int currentSize;
    int maxSize;

    Process processes [];

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.front = 0;
        this.rear = 0;
        this.processes = new Process[maxSize];
    }

    public void enqueue (Process process) {
        if (isFull()){
            return;
        }
        else {
            if (rear == maxSize){
                rear =0;
            }

            processes [rear] = process;
            rear++;
            currentSize++;
            adjustPriority();
        }

    }


    public void dequeue (){
        if (isEmpty()){
            return;
        }
        else {
            if (front == maxSize){
                front = 0;
            }
            front ++;
            currentSize--;
        }
    }

    public boolean isFull (){
        if (currentSize == maxSize){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEmpty (){
        if (currentSize == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void adjustPriority (){
        Process temp;
        for (int i =0; i<currentSize; ++i){
            String priorityA = processes[i].priority;
            int prA = Integer.parseInt(priorityA);
            for (int j= i; j<currentSize; ++i){
                String priorityB = processes[j].priority;
                int prB = Integer.parseInt(priorityB);

                if (prA < prB){
                    temp = processes[i];
                    processes[i] = processes[j];
                    processes[j] = temp;
                }
            }
        }
    }
}
