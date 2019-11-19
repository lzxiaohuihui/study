package com.lz.list;

import java.io.PushbackInputStream;
import java.util.Arrays;
import java.util.Map;

public class Maze {
    private int ord;
    private Position seat;
    private int di;
    private static Position[][] map = paint();

    @Override
    public String toString() {
        return seat+"";
    }

    public Maze(int ord, Position seat, int di) {
        this.ord = ord;
        this.seat = seat;
        this.di = di;
    }

    public Maze() {
    }

    public void markPos(Position position){
        position.setFlag(true);
    }
    public boolean pass(Position position){
        return !position.isFlag();
    }

    public Position nextPos(Position pos, int di){
        switch (di){
            case 1 : return map[pos.getX()][pos.getY()+1];
            case 2 : return map[pos.getX()+1][pos.getY()];
            case 3 : return map[pos.getX()][pos.getY()-1];
            case 4 : return map[pos.getX()-1][pos.getY()];
        }
        return null;
    }

    public static Position[][] paint(){
        int block[][] = new int[10][10];
        for (int i=0;i<10;i++){
            block[0][i] = 1;
            block[9][i] = 1;
            block[i][0] = 1;
            block[i][9] = 1;
        }
        block[1][3] = 1;
        block[2][3] = 1;
        block[1][7] = 1;
        block[2][7] = 1;
        block[3][5] = 1;
        block[3][6] = 1;
        block[4][2] = 1;
        block[4][3] = 1;
        block[4][4] = 1;
        block[5][4] = 1;
        block[6][2] = 1;
        block[6][6] = 1;
        block[7][2] = 1;
        block[7][3] = 1;
        block[7][4] = 1;
        block[7][6] = 1;
        block[7][7] = 1;
        block[8][1] = 1;

        System.out.println("地图：");
        for (int j =0;j<10;j++){
            System.out.println(Arrays.toString(block[j]));
        }
        Position map[][] = new Position[10][10];
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (block[i][j]==1){
                    map[i][j] = new Position(i,j,true);
                }else {
                    map[i][j] = new Position(i,j,false);
                }
            }
        }
        return map;
    }
    public static void main(String[] args) throws InterruptedException {

        MyStack myStack = new MyStack();
        Position start = new Position(1,1);
        Position end = new Position(8,8);


        Maze maze;
        Position curpos = start;
        int curstep = 1;
        maze = new Maze(1,start,1);
        System.out.println("足迹：");
        do {
            if (maze.pass(curpos)){
                maze.markPos(curpos);
                System.out.print(curpos+",");
                maze = new Maze(1,curpos,1);
                myStack.push(maze);
                if (curpos.getX()==end.getX() && curpos.getY()==end.getY()){
                    break;
                }
                curpos = maze.nextPos(curpos,1);
                curstep++;
            }
            else {
                if (!myStack.stackEmpty()){
                    maze = (Maze) myStack.pop();

                    while (maze.di==4 && !myStack.stackEmpty()){
                        maze.markPos(maze.seat);
                        maze = (Maze) myStack.pop();
                    }
                    if (maze.di<4){
                        maze.di++;
                        myStack.push(maze);
                        curpos = maze.nextPos(maze.seat,maze.di);
                    }
                }
            }
//            Thread.sleep(200);
        }while (!myStack.stackEmpty());
        System.out.println("\n最终线路：");
        myStack.stackTraverse();
    }
}

class Position{
    private int x;
    private int y;
    private boolean flag;


    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, boolean flag) {
        this.x = x;
        this.y = y;
        this.flag = flag;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.getX()+"-"+this.getY();
    }
}
