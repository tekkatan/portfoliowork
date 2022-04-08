package nl.cubicated.cyclicbarrierexample;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GeoManager {
    private final static Map<String,List<String>> mapCities(){
        System.out.println("add keys and values to map");
        Map<String,List<String>> map= new ConcurrentHashMap<>();
        map.put("France",List.of("Bordeaux","Nice","Paris","Toulouse"));
        map.put("Belgium",List.of("Bruxelles","Antwerpen","Luik","Gent"));
        map.put("Czech Republic",List.of("Brno","Pilsen","Prague","Karlstein"));
        return map;
    }
    private void getValues_WithStreams(Map<String,List<String>> map, String key){
        Map<String, List<String>> returnedValuesStreams= getValues_byUsingStreams(map,key);
        System.out.println("Values using key: "+key+ " : "+returnedValuesStreams);
    }
    private void getValues_WithPredicate(Map<String,List<String>> map){
        Predicate<String> key2=s->s.equals("France");
        Map<String, List<String>> map_usingPredicate=queryAll(key2,map);
        System.out.println("Values using Predicate key: France : "+ map_usingPredicate);
    }

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
    public static <K,V> Map <K,V> getValues_byUsingStreams(Map<K,V> map, String key){
        // filter out the cities that match the key value
        // returns a map
        Map <K,V> mapReturned= new ConcurrentHashMap<>();
        try{
            mapReturned=map
                .entrySet()
                .stream()
                .filter(entry->key.equals(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }catch(Exception e){
            e.printStackTrace();
        }
        return mapReturned;
    }

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

}
