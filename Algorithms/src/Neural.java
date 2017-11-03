/** <NEURAL.java> **/
import java.util.*;

/**********NEURAL************
 * 
 * @Author ScottRobbins
 */
public class Neural {

    static Vector<Integer>                    location;
    //A mapping of each point to other points in space from it's own perspective 
    public static Map<Vector<Double>, Point>  neuralPathways = new HashMap<>();
    public Map<Integer,Point>                    core = new HashMap<>();
    public Map<Point,Integer>               reference = new HashMap<>();
    public Map<Vector<Integer>,Point> indexedPathways = new HashMap<>();
    
    public static Map<Point,Neuron> cortex = new HashMap<>();
    
    public static int dimx;
    public static int dimy;

    /** <*_NEURAL_CONSTRUCTOR_*>*/
    public Neural(Map<Integer, Point> field, 
                  double []dims,  Map<Point,Integer> ref) {
        dimx = (int)dims[0];
        dimy = (int)dims[1];
        
        System.out.print("Initializing Neural Net.\n");

        for (Map.Entry<Integer, Point> entry : field.entrySet()) {
            Point thisPt = entry.getValue();
            neuralPathways.put(thisPt.findMe(), thisPt);
        }

        System.out.print("\ndimensions:" + " {" + dimx + "," + dimy + "}\n");
        
        /** <ElementIsolation:Works!>      NOW! Start traversing the mappings to create a network */
        cortex = buildPathways(neuralPathways);
        /** ^^<This> is working well, <USE_IT!> **/

    }

    /** Initialize the extensions to each point making it a neuron 
    (every point becomes an object containing every adjacent point). */
    public Map<Point,Neuron> buildPathways(Map<Vector<Double>, Point> pathway) {
        Map<Point,Neuron> brain = new HashMap<>();
        
        int index = 0;
        for(Map.Entry<Vector<Double>,Point> entry:pathway.entrySet()){
            Neuron n;
            Vector<Double> thisLoc = entry.getKey();
            Vector<Double> up  = new Vector<>();//( 0, 1)
            Vector<Double> e   = new Vector<>();//( 0, 0)
            Vector<Double> dwn = new Vector<>();//( 0,-1)
            Vector<Double> lft = new Vector<>();//(-1, 0)
            Vector<Double> rt  = new Vector<>();//( 1, 0)
            //Diagonal Elements next
            Vector<Double> upLft = new Vector<>();//(-1,-1)
            Vector<Double> upRt  = new Vector<>();//( 1,-1)
            Vector<Double> dLft  = new Vector<>();//(-1, 1)
            Vector<Double> dRt   = new Vector<>();//( 1, 1)
            
            System.out.print(entry.getKey()+" : "+entry.getValue().getWeight()+"\n");
           if(entry.getValue().findMe().get(1)>dimy){
               e.add(thisLoc.get(0)); e.add(thisLoc.get(1));
               dwn.add(thisLoc.get(0)); dwn.add(thisLoc.get(1)-1);
               rt.add(thisLoc.get(0)+1); rt.add(thisLoc.get(1));
           }else{
                    up.add(thisLoc.get(0));    up.add(thisLoc.get(1)+1);
                    e.add(thisLoc.get(0));     e.add(thisLoc.get(1));
                    dwn.add(thisLoc.get(0));   dwn.add(thisLoc.get(1)-1);
                    lft.add(thisLoc.get(0)-1); lft.add(thisLoc.get(1));
                    rt.add(thisLoc.get(0)+1);    rt.add(thisLoc.get(1));
               

                   /** <NEXT!> Diagonal Elements need boundary checks first!!!! **/
                    upLft.add(thisLoc.get(0)-1); upLft.add(thisLoc.get(1)+1);
                    upRt.add(thisLoc.get(0)+1);  upRt.add(thisLoc.get(1)+1);
                    dLft.add(thisLoc.get(0)-1);  dLft.add(thisLoc.get(1)-1);
                    dRt.add(thisLoc.get(0)+1);   dRt.add(thisLoc.get(1)+1);
                }

                    try{
                       n = new Neuron(pathway.get(e));
                       n.addUp(pathway.get(up)); 
                       n.addDown(pathway.get(dwn));
                       n.addLeft(pathway.get(lft));
                       n.addRight(pathway.get(rt));
                       n.addDownLeft(pathway.get(dLft));
                       n.addDownRight(pathway.get(dRt));
                       n.addUpLeft(pathway.get(upLft));
                       n.addUpRight(pathway.get(upLft));
                       brain.put(entry.getValue(),n);
                       index+=1;
                    } catch(NoSuchElementException ex){
                        ex.printStackTrace();
                    } catch(NullPointerException ex){
                       // System.out.print("\t"+entry.getKey());
                    }
                    index+=1;
                }
          
        
        System.out.print("\nProcessing "+ index +" patterns...\n* * * * * * * * *\n") ;
         return brain;
    }
    

    public static void main(String[] args) {
    }

}
/** <NEURAL.java> **/
