/** <NEURON.java> **/
import java.util.*;

/**
 * @Author ScottRobbins
 */
public class Neuron {

    public static double x;
    public static double y;
    public static double weight;

    static public Point         uleftPt;
    static public Point         urightPt;
    static public Point         leftPt;
    static public Point         rightPt;
    static public Point         dleftPt;
    static public Point         drightPt;
    static public Point         abovePt;
    static public Point         belowPt;
    
    //need booleans
    public boolean hasul = false;
    public boolean hasup = false;
    public boolean hasur = false;
    public boolean haslft = false;
    public boolean hasrt = false;
    public boolean hasdlft = false;
    public boolean hasdwn = false;
    public boolean hasdrt = false;

    /**
     * {1}-{1}-{1}-{1}-{1}-{1}-{1}
     *  | X | X  \  | / | X | X |  
     * {1}-{1}-{<NEURON>}--{1}-{1}
     *  | X | X  /  | \ | X | X |
     * {1}-{1}-{1}-{1}-{1}-{1}-{1}
     */
    public Neuron(Point element) {

        /**
         * Each neuron is like a point, but its has knowledge
         * of a map relating it's position and weight to the
         * position and weight of every other point. 
         */

        x = element.ax;
        y = element.ay;
        weight = element.getWeight();
        

    }

    void addLeft(Point pt) {
        leftPt = pt; haslft = true;
    }
    
    public Point getLeft(){return leftPt;}

    void addRight(Point pt) {
        rightPt = pt; hasrt = true;
    }
    
    public Point getRt(){return rightPt;}

    void addDownLeft(Point pt) {
        dleftPt = pt; hasdlft =true;
    }
    
    public Point getDwnLft(){return dleftPt;}

    void addDownRight(Point pt) {
        drightPt = pt; hasdrt = true;
    }
    
    public Point getDwnRt(){return drightPt;}
    
    void addUpLeft(Point pt) {
        uleftPt = pt; hasul = true;
    }
    
    public Point getUpLft(){return uleftPt;}

    void addUpRight(Point pt) {
        urightPt = pt; hasur = true;
    }

    public Point getUpRt(){return urightPt;}
    
    void addUp(Point pt) {
        abovePt = pt; hasup = true;
    }
    
    public Point getUp(){return abovePt;}
    
    void addDown(Point pt) {
        belowPt = pt; hasdwn = true;
    }
    
    public Point getDwn(){return belowPt;}

}
/** <NEURON.java> **/
 