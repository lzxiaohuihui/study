package com.lz.study;

import javax.print.attribute.standard.JobKOctets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小灰灰
 */
public class BitNode<main> {
    Object data ;
    BitNode left;
    BitNode right;

    public BitNode(Object data){
        this.data = data;
    }
    public BitNode(){
    }

    public void addNode(Object v){
        if (this.data == null){
            this.data = v;
        }
        else {
            if ((int)v<=(int)this.data){
                if (this.left==null){
                    this.left = new BitNode();
                }
                this.left.addNode(v);
            }
            else {
                if (this.right==null){
                    this.right = new BitNode();
                }
                this.right.addNode(v);
            }
        }
    }

    public List<Object> preOrderTraverse(){
        List<Object> objectList = new ArrayList<>();

        objectList.add(this);
        objectList.addAll(getNode(this.left,1));
        objectList.addAll(getNode(this.right,1));


        return objectList;
    }

    public List<Object> inOrderTraverse(){
        List<Object> objectList = new ArrayList<>();

        objectList.addAll(getNode(this.left,2));
        objectList.add(this);
        objectList.addAll(getNode(this.right,2));


        return objectList;
    }

    public List<Object> postOrderTraverse(){
        List<Object> objectList = new ArrayList<>();

        objectList.addAll(getNode(this.left,3));
        objectList.addAll(getNode(this.right,3));
        objectList.add(this);

        return objectList;
    }

    public List<Object> getNode(BitNode node,int type){
        List<Object> objectList = new ArrayList<>();

        switch (type){
            case 1: {
                if (node != null){
                objectList.addAll(node.preOrderTraverse());
                }
                break;
            }

            case 2: {
                if (node != null){
                objectList.addAll(node.inOrderTraverse());
                }
                break;
            }

            case 3: {
                if (node != null){
                objectList.addAll(node.postOrderTraverse());
                }
                break;
            }

        }

        return objectList;
    }

    @Override
    public String toString() {
        return this.data+"";
    }

//    public static void main(String[] args) {
//        int[] nodes = new int[]{2,1,5,3,6,3,7};
//        BitNode bitNode = new BitNode();
//        for (int node:nodes){
//            bitNode.addNode(node);
//        }
//        System.out.println(bitNode.inOrderTraverse());
//    }
}
