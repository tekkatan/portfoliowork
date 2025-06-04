# Welcome

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 

# CyclicBarrierExample Projects

1. GeoManager
 - Usage of Functional Programming
 - Threads controlled by a 
   CyclicBarrier.
 - Included are different way of retrieving key and values from a Map using:
    1. Streams
    2. Predicates

**Example**
```java
 public static <K,V> Map <K,V> queryAll(Predicate<String> predicate, Map<K,V> map){
        // filter out the cities that match the predicate key
        // return map
        Map <K,V> mapReturned=new ConcurrentHashMap<>();
        try{
            mapReturned=map
                .entrySet()
                .stream()
                .filter(x->predicate.test((String)x.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        }catch(Exception e){
            e.printStackTrace();
        }
        return mapReturned;
    }
```

**Usage**
```java
public void doTask(CyclicBarrier c1, CyclicBarrier c2, CyclicBarrier c3, String key){
        try{
            // load data
            Map<String, List<String>> map=mapCities();
            c1.await();
            // show data with provided key: Czech Republic
            getValues_WithStreams(map, key);
            c2.await();
            // show list using a predicate
            getValues_WithPredicate(map);
            // close program
            c3.await();
        }catch( InterruptedException |BrokenBarrierException e){
            e.printStackTrace();
        }finally{
            System.out.println("Closing doTask");
        }
    }
    public static void main(String[] args) {
        String key="Czech Republic";
        ExecutorService service=null;
        try{
            service=Executors.newFixedThreadPool(2);
            var manager= new GeoManager();
            var c1=new CyclicBarrier(2);
            var c2=new CyclicBarrier(2);
            var c3=new CyclicBarrier(2,
            ()-> System.out.println("---All tasks done---"));
            for(int i=0;i<2;i++)
                service.submit(()->manager.doTask(c1, c2, c3, key));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(service!=null) service.shutdown();
        }
    }

```

2. MetroManager
- ReentrantLock Example of usage in the 
  intercom of a metro making announcements.
- [view code](https://github.com/tekkatan/portfoliowork/blob/main/javaprojects/cyclicbarrierexample/MetroManager.java "goto cyclicbarrier example of metro announcement")


3. TrainManager
- CyclicBarrierExample of usage in a train.
- [view code](https://github.com/tekkatan/portfoliowork/blob/main/javaprojects/cyclicbarrierexample/TrainManager.java "goto cyclicbarrier example of train")
