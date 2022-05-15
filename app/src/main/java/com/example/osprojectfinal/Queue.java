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
                rear = 0;
            }

            processes [rear] = process;
            rear++;
            currentSize++;
            adjustPriority();

        }

    }


    public Process dequeue (){
        Process process;
        if (isEmpty()){
            return null;
        }
        else {
            if (front == maxSize){
                front = 0;
            }
            process = processes[front];
            front ++;
            currentSize--;
        }
        return process;
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

    public void adjustPriority () {
        Process temp;
        int counter = 0;
        int priority1,priority2;
        for(int i=0;i<currentSize;++i)
        {
            priority1 = Integer.parseInt(processes[i].getPriority());
            counter++;
            for(int j=counter;j<currentSize;j++)
            {
                priority2 = Integer.parseInt(processes[j].getPriority());
                if(priority1<priority2)
                {
                    temp = processes[i];
                    processes[i] = processes[j];
                    processes[j] = temp;
                }
            }
        }

    }
}