package com.shiromadev.personalbudget.tables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TableList implements Serializable {
    private static class Node{
        TableItems Data;
        Node Next;
        public Node(){
            Data = null;
            Next = null;
        }
        public Node(TableItems Data){
            this.Data = Data;
            Next = null;
        }
        public TableItems getData() {
            return Data;
        }
        public void setData(TableItems data) {
            Data = data;
        }
        public Node getNext() {
            return Next;
        }
        public void setNext(Node next) {
            Next = next;
        }
    }
    Node head;
    int length;

    public void add(TableItems data) {
        Node current = head;
        if(current != null){
            if(searchElement(data)){
                while (current.getNext() != null && !current.getData().equals(data)){
                    current = current.getNext();
                }
                TableItems newData = current.getData();
                newData.setAmount(newData.getAmount()+ data.getAmount());
                newData.setMoney(newData.getMoney()+ data.getMoney());
                current.setData(newData);
            }
            else {
                while(current.getNext() != null){
                    current = current.getNext();
                }
                Node node = new Node(data);
                current.setNext(node);
                length++;
            }
        }
        else{
            head = new Node(data);
            length++;
        }
    }

    private Boolean searchElement(TableItems element){
        Node current = head;
        boolean be = false;
        while (current != null){
            if (current.getData().equals(element)) {
                be = true;
                break;
            }
            current = current.getNext();
        }
        return be;
    }

    public void remove(int id){

    }

    public TableItems get(int id){
        Node current = head;
        int searchIndex = 0;
        if (id != 0) {
            while (current.getNext() != null && searchIndex != id) {
                searchIndex++;
                current = current.Next;
            }
        }
        return current.getData();
    }

    public int sum(){
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.getData().getMoney();
            current = current.Next;
        }
        return sum;
    }

    public int size() {
        return length;
    }

    public void clear(){

    }
}
