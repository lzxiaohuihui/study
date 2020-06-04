package com.lz.study;

class MyCircularQueue {
    Integer[] data;
    int head,tail;


    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new Integer[k];
        head = -1;
        tail = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(!isFull()){
            if (isEmpty()){
                head ++;
            }
            tail ++;

            data[tail%data.length] = value;
            return true;
        }
        return false;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()){
            data[head%data.length] = null;
            if(head%data.length==tail%data.length){
                head = -1;
                tail = -1;
            }else{
                head ++;
            }
            return true;
        }
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(!isEmpty()){
            return data[head%data.length];
        }
        return -1;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(!isEmpty()){
            return data[tail%data.length];
        }
        return -1;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (head == -1);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail+1)%data.length)==head;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        System.out.println(circularQueue.enQueue(1));  // 返回 true
        System.out.println(circularQueue.enQueue(2));  // 返回 true
        System.out.println(circularQueue.enQueue(3));  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
        System.out.println(circularQueue.Rear());  // 返回 3
        System.out.println(circularQueue.isFull());  // 返回 true
        System.out.println(circularQueue.deQueue());  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 true
        System.out.println(circularQueue.Rear());  // 返回 4
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */