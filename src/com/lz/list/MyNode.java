package com.lz.list;

public class MyNode {
    private Node head;
    private Node last;
    private int length;


//    public boolean makeNode(Node p, Object e){
//        Node tempNode = new Node(e);
//        if (p.next == null){
//            p.next = tempNode;
//            length++;
//            return true;
//        }
//        return false;
//    }

    public void freeNode(Node p){
        Node tempNode = p;
        while (tempNode!=null){
            tempNode = tempNode.next;
            length--;
        }
        p.next = null;
    }

    public void initList(){
        head = null;
        last = null;
        length = 0;
    }
    public void destroyList(){
        initList();
    }
    public void clearList(){
        initList();
    }
    public void insFirst(Node s){
        s.next = head;
        head = s;
        if (length==0){
            last = s;
        }
        length++;
    }
    public Node delFirst(){
        Node tempNode = head;
        head = head.next;
        length--;
        return tempNode;
    }
    public void append(MyNode myNode){
        last.next = myNode.head;
        last = myNode.last;
        length += myNode.length;
    }
    public Node remove(){
        Node tempNode = last;
        Node j = head;
        while (j!=null) {
            if (j.next==tempNode){
                last = j;
                last.next = null;
            }
            j = j.next;
        }
        length--;
        return tempNode;
    }
    public void insBefore(Node p, Node s){
        Node tempNode = head;
        while(tempNode!=null){
            if (tempNode.next == p){
                s.next = p;
                tempNode.next = s;
                length++;
                break;
            }
            tempNode = tempNode.next;

        }

    }
    public void insAfter(Node p, Node s){
        s.next = p.next;
        p.next = s;
        length++;
        if (s.next==null){
            last = s;
            last.next = null;
        }

    }
    public void setCurElem(Node p, Object data){
        p.next.data = data;
    }
    public Object getCurElem(Node p){
        return p.next.data;
    }
    public boolean listEmpty(){
        if (head==null){
            return true;
        }
        return false;
    }
    public int listLength(){
        int temp=0;
        Node tempNode = head;
        while(tempNode!=null){
            tempNode = tempNode.next;
            temp++;
        }
        return temp;
    }
    public int getHead(){
        return 1;
    }
    public int getLast(){
        return listLength();
    }
    public int priorPos(Node p){
        return locatePos(p)-1;
    }
    public int nextPos(Node p){
        return locatePos(p)+1;
    }
    public int locatePos(Node p){
        Node tempNode = head;
        for (int i = 0; i<=listLength(); i++){
            if (tempNode==p.next){
                return i+1;
            }
            tempNode = tempNode.next;
        }
        return 0;
    }
    public int locateElem(Object data){
        Node tempNode = head;
        for (int i = 0; i<=listLength(); i++){
            if (tempNode.data==data){
                return i+1;
            }
            tempNode = tempNode.next;
        }
        return 0;
    }

    public void insertList (int i, Object e){
        Node node = new Node(e);
        Node sufNode = getElem(i);
        Node preNode = getElem(i-1);
        node.next = preNode.next;
        preNode.next = node;
    }

    public Node getElem(int i){
        Node tempNode = head;
        Node result = null;
        while(tempNode!=null){
            if (priorPos(tempNode)==i){
                result = tempNode;
                break;
            }
            tempNode = tempNode.next;
        }
        return result;
    }
    public void deleteList(int i){
        Node sufNode = getElem(i);
        Node preNode = getElem(i-1);

        preNode.next = sufNode.next;
        sufNode.next = null;
    }

    public static void main(String[] args) {
        MyNode myNode = new MyNode();
//        myNode.initList();
        myNode.insFirst(new Node(1));
        myNode.insFirst(new Node(2));
        myNode.insFirst(new Node(3));
        int i = myNode.locatePos(myNode.head);
        int j = myNode.locateElem(3);
        Node node_2 = myNode.getElem(2);
        myNode.insertList(2,5);
        myNode.deleteList(3);
        myNode.locateElem(1);
        System.out.println(myNode.last.data);
    }
}




class Node{
    Object data;
    Node next;

    public Node(Object data){
        this.data = data;
    }
    public Node(){
        
    }

}