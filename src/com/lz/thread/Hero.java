package com.lz.thread;

import com.sun.org.apache.xerces.internal.impl.dv.xs.IDDV;

public class Hero {
    private String name;
    public int maxHp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    private float hp;

    private int damage;

    public synchronized void recover(){
        if(hp == maxHp){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hp += 1;
    }
    public synchronized void hurt(){
        if (hp == 1) {
            try {
                // 让占有this的减血线程，暂时释放对this的占有，并等待
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        hp -= 1;
        this.notify();
    }

    public void attackHero(Hero h){
        h.hp -= damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead()) {
            System.out.println(h.name +"死了！");
        }
    }
    public boolean isDead(){
        return hp==0?true:false;
    }
}
