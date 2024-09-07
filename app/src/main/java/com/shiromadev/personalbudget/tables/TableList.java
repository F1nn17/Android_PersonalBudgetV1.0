package com.shiromadev.personalbudget.tables;

import java.io.Serializable;

@Deprecated
public class TableList implements Serializable {
    public void add(ItemTable data) {
        Node current = head;
        if(current != null){
            if(searchElement(data)){
                while (current.getNext() != null && !current.getData().equals(data)){
                    current = current.getNext();
                }
                ItemTable newData = current.getData();
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
    Node head;
    int length;

    private Boolean searchElement(ItemTable element) {
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

    public ItemTable get(int id) {
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

    public void remove(int id){

    }

    private static class Node{
        ItemTable Data;
        Node Next;
        public Node(){
            Data = null;
            Next = null;
        }

        public Node(ItemTable Data) {
            this.Data = Data;
            Next = null;
        }

        public ItemTable getData() {
            return Data;
        }

        public void setData(ItemTable data) {
            Data = data;
        }
        public Node getNext() {
            return Next;
        }
        public void setNext(Node next) {
            Next = next;
        }
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
