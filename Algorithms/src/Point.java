/** <Point.java> **/
import java.util.*;

/** The <Point> object represents an arbitraty
 point in space**/
public class Point {

    public Double                ax, ay, az, wt;
    public Vector<Double> me = new Vector<>();

    /** Create a point in space, with a position vector and a weight*/
    public Point(Vector<Double> pt, Double weight) {
        this.ax = pt.get(0);
        this.ay = pt.get(1);
        me.add(this.ax);
        me.add(this.ay);
        //avoiding z for now. 
        this.wt = weight;
       
    }

    public Double getWeight() {
        return this.wt;
    }
    
    /** Use vector addition to return the point that is the sum of two input points */
    public static Vector<Double> addPoints(Point a, Point b){
        Vector<Double>res = new Vector<>();//result
        
        double ax = a.me.get(0);
        double ay = a.me.get(1);
        double bx = b.me.get(0);
        double by = b.me.get(1);
        
        res.add(ax+bx); 
        res.add(ay+by);
        return res;
    }
    /** Subtract y components of ptA from ptB */
    public static Vector<Double> subtractY(Point a, Point b){
        Vector<Double>res = new Vector<>();//result
        
        double ax = a.me.get(0);
        double ay = a.me.get(1);
        double bx = b.me.get(0);
        double by = b.me.get(1);
        
        res.add(ax);
        res.add(by-ay);
        
        return res;
    }
    
    public static boolean areTouching(Point a, Point b){
        boolean answer = false;
        
        double ax = a.me.get(0);
        double ay = a.me.get(1);
        double bx = b.me.get(0);
        double by = b.me.get(1);
        
        if(Math.sqrt((Math.abs((ax-bx)*(ax-bx) + (ay - by)*(ay - by))))<=1.75){
            answer = true;
        } 
    /*else{System.out.print(Math.sqrt((Math.abs((ax-bx)*(ax-bx) + (ay - by)*(ay - by))))+" ");}*/
        
        
        return answer;
    }
    
    /** Subtract x components of ptA from ptB */
    public static Vector<Double> subtractX(Point a, Point b){
        Vector<Double>res = new Vector<>();//result
        
        double ax = a.me.get(0);
        double ay = a.me.get(1);
        double bx = b.me.get(0);
        double by = b.me.get(1);
        
        res.add(bx - ax);
        res.add(by);
        
        return res;
    }
    
    public static boolean comparePoints(Point a, Point b){
        Vector<Double>res = new Vector<>();//result
        boolean eq = false;
        double ax = a.me.get(0);
        double ay = a.me.get(1);
        double bx = b.me.get(0);
        double by = b.me.get(1);
        
        if(ax==bx && ay==by){
            eq = true;
        }
        
        return eq;
    }

    /** Update point's position vector */
    public void update() {
        me.add(this.ax);
        me.add(this.ay);
    }

    /** Show this <Point>* */
    public Point seeMe(Vector<Double> pt, Double weight) {
        return new Point(pt, weight);
    }
    
    /** Return the <me> Vector*/
    public Vector<Double> showMe(){return me;}
    
    public Vector<Double> findMe(){return me;}

    public static void main(String[] args) {
    }

}
/** <Point.java> **/
