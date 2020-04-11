package tw.org.iii.brad.brad25;

import java.util.ArrayList;
import java.util.LinkedList;

public class Member {   //B 創出簡單的類別含以下
    private ArrayList<String> names;   //F 改為LL
    private int age;
    public Member(){
        names = new ArrayList<>();
        age = 0;
    }

    public void addName(String name){
        names.add(name);
    }

    public ArrayList<String> getNames(){
        return names;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
