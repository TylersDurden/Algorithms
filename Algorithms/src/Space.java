/** <SPACE.java> **/
import java.util.*;

/**********- - <___SPACE__> - - *********
 *                                      *  
 *  - (  (  S   P   A   C   E  )  )  -  * 
 *                                      * 
 * @Author ScottRobbins                 *
 ****************************************/
public class Space {
    
    //Mapping of points in space to their respective value or weight
    public static Map<Vector<Double>,Double> SPACE = new HashMap<>();
    public static Map<Vector<Integer>,String> SPaCE = new HashMap<>();
    
    //Points in space mapped to their index in the SPACE arrays 
    //Because index values are a bit unintuitive use refMap for retrieval 
    public static Map<Integer,Point> pointMap = new HashMap<>();
    public static Map<Point,Integer> refMap   = new HashMap<>(); 
    
    //A mapping of each point to other points in space from it's own perspective 
    //public static Map<Point,Integer[]> neuralPathways = new HashMap<>();
    
  public Space(String type){
        if(type.split(" , ")[0].trim().compareTo("numbers")==0){
            //Prepare user defined dimensions 
            int dim1 = Integer.parseInt(type.split(" , ")[1].split("x")[0]);//4
            int dim2 = Integer.parseInt(type.split(" , ")[1].split("x")[1]);//4
           //generate a map based on those dimensions
            double [][] reference = genMap(4,4); 
            System.out.print("\nField of numbers created.\n"+
            "Showing randomly generated field weights:\n");
            double [] refkeys = getFieldWeights(4,4);
            double [] dims = {dim1,dim2};
            Neural net = new Neural(pointMap,dims,refMap);//Connect points to eachother and create a "network"
            System.out.print("Network Created from Points.\nEnter 2 points to traverse in form:\n0,0 : 1,3\n");
            System.out.print("Select:\n (1) - Shortest Path\n (2) - Longest Path\n"+
                                      " (3) - Path of least resistance\n (4) - Path of most resistance");
            //For now just hardcoding both 
            Vector<Double> ptA = new Vector<>();  ptA.add(3.0); ptA.add(1.0);
            Vector<Double> ptB = new Vector<>();  ptB.add(0.0); ptB.add(0.0);
            
            /** down to the right works 
             * 
             */
            int typ = 1;
            System.out.print("\nFinding the Shortest Path between "+ptA.toString()+"->"+ptB.toString()+"\n");
           
           /** Run shortest Path traverse**/
            Charge pathFinder = new Charge(net.neuralPathways, Neural.cortex,ptA,ptB,typ);
          /** Other types of Traversal! **/
          
        }
    }
    
    /** Retrive the field weights from the pointMap of Space **/
    double[] getFieldWeights(int dimx,int dimy){
        
     double [] fWeights = new double [dimx*dimy];
        
     for(Map.Entry<Integer,Point>entry:pointMap.entrySet()){
         fWeights[entry.getKey()] = entry.getValue().getWeight();
         System.out.print("{"+entry.getValue().getWeight()+"} ");
            
       if(entry.getKey()!=0 && (entry.getKey()+1)%dimx==0){
         System.out.print("\n");
       }
      }
        
      return fWeights;
    }
    
    /** generate map with dimensions x by y with random field weights. **/
    double[][] genMap(int dimx, int dimy){
        
        int index = 0;
        double [][] tempField = new double[dimx][dimy];
        Map<Vector<Double>,Double> space = new HashMap<>() ;      
        for(int i=0;i<dimy;i++){
            for(int j=0;j<dimx;j++){
                tempField[j][i] = 1*Math.random()+2*Math.random();
                Vector<Double> cord = new Vector<>();
                cord.add((double)j); cord.add((double)i);
                System.out.print(" {"+j+","+i+"} ");
                space.put(cord,tempField[j][i]);
                pointMap.put(index,new Point(cord,tempField[j][i]));
                refMap.put(new Point(cord,tempField[j][i]),index);
                index+=1;
            }
            System.out.print("\n");
        }
           SPACE = space;
           return tempField;
    }
    
    public static void main(String[]args){
        
        Scanner sc = new Scanner(System.in);
        //Assuming for now user has selected numbers 4x4 
        
        
        new Space("numbers , 4x4");
        
        /** Using generics we should be able to use this same
        class on different data types **/
        new Space("letters");
    }
    
}/** <SPACE.java> **/
