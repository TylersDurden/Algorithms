/** <Charge.java> **/
import java.util.*;

/****** *~~~~*~~~*~~~~** *<Charge>* **~~~~*~~~*~~~~* *********
 * This class traces paths through a neural set
 * taking into account of the field weights it encounters.
 * Try and make a setting to travel from points A-B following
 * quickest or longest route
 * -----------------------------------------------------------| 
 * @Author ScottRobbins                                       |
 *************************************************************/
public class Charge {
    
    public static final String [] opts = {"short","long","least","most"};
    static String style;
    static Vector <Double> startingPoint = new Vector<>();
    static Vector <Double> endPoint      = new Vector<>();
    static Map<Vector<Double>, Point> brain = new HashMap<>();
    static Map<Point,Neuron> mainframe = new HashMap<>();
    static boolean finished = false; 
    
    /** <*_CHARGE_CONSTRUCTOR_*>**/
    public Charge(Map<Vector<Double>, Point> pathways, 
                  Map<Point,Neuron> cortex,
                  Vector<Double>ptA,
                  Vector<Double>ptB,
                  int styl){
        brain = pathways;
        mainframe = cortex;
        
        System.out.print("\n* * * * ****************** * * * *\n");
        System.out.print("* * * * <Cortex_Initiated> * * * *\n");
        System.out.print("* * * * ****************** * * * *\n");
        
        startingPoint = ptA;
        endPoint      = ptB;
        style = opts[styl];//choosing shortest path  
        
        //Sort the network, and traverse it 
        //sortPaths(cortex);
        //chargeTransport(ptA,ptB,sortPaths(cortex));
        
        /**<figure_out_traversal_better>*/
       System.out.print(ptA.toString()+"-> * * *\n");
      
      transport(transport((transport(ptA,ptB)),ptB),ptB);
       
   }
   
   /** Travel through the network with only spatial concerns, not fieldWeights**/
   Vector<Double> transport(Vector<Double>a,Vector<Double>b){
       //Initialize where charge is and where it's going
       Vector<Double>steps = new Vector<>();
       double ax = a.get(0); 
       double ay = a.get(1);
       double bx = b.get(0);
       double by = b.get(1);
       
       //Find points surrounding ptA
       //Step into one that's closest to direction of PtB
       //Repeat this algorithm. 
      //Use a Point array and Iterate through the getRt, getLft, etc keeping in mind
      //that the description direction might not be exactly correct
      Point [] nearby = new Point[16];
      ArrayList<Point> places = new ArrayList<>(mainframe.keySet());
      
      double dx0 = b.get(0) - a.get(0);
      double dy0 = b.get(1) - a.get(1);
      
      steps.add(ax); steps.add(ay); 
      Point ptCharge = new Point(steps,brain.get(steps).getWeight());
      /** Here we go! single forloop should be able to do quite a bit bc
      we already have neurons programmed and all places. now ready to start
      using the algorithm. Just be careful about the fact that the points 
      in this array list, and mainframe, are not in a logical order! **/
     System.out.print("\t[Finding points nearby]\n");
     int index = 0;
     Map<Integer,Point> solution = new HashMap<>();
      for(Point p:places){
        //identify adjacent points
        if(Point.areTouching(ptCharge,p)){
            double dx = Point.subtractX(p,brain.get(b)).get(0);
            double dy = Point.subtractY(p,brain.get(b)).get(1);
            if((dx<dx0 && dy<dy0) ){
                solution.put(index,p);
                index+=1;
                //System.out.print("**"+p.showMe().toString());
            }
            if(dx==0&&dy!=0){
                solution.put(index,p);
                index+=1;
            }
            if(dy==0&&dx!=0){
                solution.put(index,p);
                index+=1;
            }
            else if(dx==0&&dy==0){
                solution.put(index,p);
                index+=1;
                System.out.print("*ARRIVED"+p.showMe().toString()+" ");
                finished = true;
                break;
            }
            if((dx<=1.75&&dy>=-1)|| (dy<=1.75&&dy>=-1)){
                solution.put(index,p);
                index+=1;
                //System.out.print("*"+p.showMe().toString()+" ");
            }
        }
    }
 
        double distx = 0; double disty=0;
        double min = 0;
        Map<Double,Point>distmap = new HashMap<>();
      for(Map.Entry<Integer,Point>entry:solution.entrySet()){
         double dx = bx - entry.getValue().showMe().get(0);
         double dy = by - entry.getValue().showMe().get(1);
         if(dx<=1 && dx>-1){
             min=distx;
             distmap.put(min,entry.getValue());
         }
        if(dy<=1 && dy>-1){
            distmap.put(dy,entry.getValue());
        }
        if(dx==0){
             distmap.put(dy,entry.getValue());
         }
      }
      if(finished==false){System.out.print("\n\t Best choice: "+distmap.get(min).showMe().toString());}
      return distmap.get(min).showMe();
}
     
    
    /** **/
    Vector<Double> sortPaths(Map<Point,Neuron>field){
        //Keep track of points considered 
        int index=0;
        //Initialize the intended direction of travel
        Vector<Double> direction = new Vector<>();
        double dx = endPoint.get(0) - startingPoint.get(0);
        double dy = endPoint.get(1) - startingPoint.get(1);
        System.out.print("Heading: "+dx+" x direction "+dy+" y direction");
       direction.add(dx);
       direction.add(dy);
       return direction;
        
    }
    
    
    public static void main(String[]args){}
    
    
    
}/** <Charge.java> **/
