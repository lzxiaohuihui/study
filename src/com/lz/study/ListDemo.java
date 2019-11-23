package com.lz.study;


/**
 * @author 小灰灰
 * 顺序表
 */
public class ListDemo {

    private Integer[] array = null;
    private int capacity = 20;

    public void initList(){
        array = new Integer[capacity];
    }
    public void clearList(){
        if ( array != null){
            array = new Integer[capacity];
        }
    }

    public void destroyList(){
        array = null;
    }

    public boolean listEmpty(){
        if(array[0] != null){
            return false;
        }
        return true;
    }
    public int listLength(){
        if ( array != null){
            for (int i=0;i<array.length;i++)
                if (array[i]==null){
                    return i;
                }
        }
        return 0;
    }

    public Integer getElem(int i){
        if ( array != null && 1 <= i && i<= listLength()){
            return  array[i-1];
        }
        return null;
    }

    public int locateElem(Integer e){
        if ( array != null){
            for (int i=1; i<=listLength(); i++){
                if (e.equals(array[i-1])){
                    return i;
                }
            }
        }
        return 0;
    }
    
    public Integer priorElem(Integer cur_e){
        if( array != null){
            if (locateElem(cur_e)>1){
                Integer pre_e = getElem(locateElem(cur_e)-1);
                return pre_e;
            }
        }
        return null;
    }

    public Integer nextElem(int cur_e){
        if ( array != null && locateElem(cur_e)<listLength()){
            int next_e = getElem(locateElem(cur_e)+1);
            return next_e;
        }
        return null;
    }
    public void listInsert(int i, int e){
        if (array != null && i>=1 && i<=listLength()+1){
            for (int j=listLength(); j>=i; j--){
                array[j] = array[j-1];
                if (array[i-1]==null){
                    break;
                }
            }
            array[i-1] = e;
        }
    }
    public Integer listDelete(int i){
        Integer e = null;
        if (array != null && !listEmpty() && i>=1 && i<=listLength()){
            e = array[i-1];
            for (int j=i-1; j<=listLength(); j++){
                array[j] = array[j+1];
            }
        }
        return e;
    }

    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
        listDemo.initList();
        listDemo.listInsert(1,1);
        listDemo.listInsert(2,5);
        listDemo.listInsert(3,7);
        listDemo.listInsert(4,9);
        listDemo.listInsert(5,4);
        listDemo.listInsert(6,8);
        System.out.println(listDemo.priorElem(4));
        System.out.println(listDemo.nextElem(4));
        listDemo.listDelete(3);
        System.out.println(listDemo.getElem(3));
    }



}
