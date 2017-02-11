package com.example;

import java.util.ArrayList;

/**
 * Created by The Gt Zan on 09-Feb-17.
 */

public class SUTDvisitorQuestion {
    public static void main(String[] args) {
        ArrayList<Visitable> items = new ArrayList<Visitable>();
        SUTDvisitor runner = new SUTDvisitor();
        items.add(new professors("Sun Jun", 0));
        items.add(new adminStaff("Stacey", 5));
        items.add(new studentatSUTD("Allan", 3));

        for (Visitable o: items){
            o.accept(runner);

        }

    }
}

class SUTDvisitor implements Visitor{
    public void visit(employee e){

    }

    @Override
    public void visit(professors p){
        System.out.println("Prof: " + (p.getName() + p.getNo_of_publications()));
    }

    @Override
    public void visit(studentatSUTD s){
        System.out.println("Prof: " + s.getName() + s.getGPA());
    }

    @Override
    public void visit(adminStaff as){
        System.out.println("Prof: " + (as.getName() + as.getEfficiency()));
    }
}
interface Visitor {
    void visit(employee e);
    void visit(professors p);
    void visit(studentatSUTD s);
    void visit(adminStaff as);
}

interface Visitable{
    void accept(Visitor v);

}

class employee implements Visitable{
    public void accept(Visitor v){
        v.visit(this);
    }
}

class professors  implements Visitable{
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    private String name;
    private int no_of_publications;

    public professors (String name, int no_of_publications) {
        this.name = name;
        this.no_of_publications = no_of_publications;
    }

    public String getName () {
        return name;
    }

    public int getNo_of_publications() {
        return no_of_publications;
    }

}

class adminStaff implements Visitable{
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }

    private String name;
    private float efficiency;

    public adminStaff (String name, float efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public float getEfficiency() {
        return efficiency;
    }
}

class studentatSUTD  implements Visitable{
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    private String name;
    private float GPA;

    public studentatSUTD (String name, float GPA) {
        this.name = name;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public float getGPA() {
        return GPA;
    }

}