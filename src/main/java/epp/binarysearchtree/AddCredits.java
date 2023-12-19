package epp.binarysearchtree;

import java.util.*;

public class AddCredits {


    private Map<String ,Integer> clientIdCreditMap = new HashMap<>();
    private TreeMap<Integer,HashSet> creditClientIdsMap = new TreeMap<>();
    private int addAllCredit = 0;
    private int currentMax = Integer.MIN_VALUE;

    //o(logn)
    public void insert(String clientId,int credit){
        credit = credit- addAllCredit;

        currentMax = Math.max(currentMax,credit);

        if(!clientIdCreditMap.containsKey(clientId)){
            clientIdCreditMap.put(clientId,credit);
            HashSet<String> clients = new HashSet<>();
            clients.add(clientId);
            creditClientIdsMap.put( credit, clients);

        }else{
            HashSet oldGroup = creditClientIdsMap.get(clientIdCreditMap.get(clientId));
            oldGroup.remove(clientId);
            HashSet newGroup = creditClientIdsMap.getOrDefault(credit,new HashSet());
            newGroup.add(clientId);
            creditClientIdsMap.put(credit,newGroup);
            clientIdCreditMap.put(clientId,credit);


        }
    }

    //o(logn)
    public void remove(String clientId){
        if(!clientIdCreditMap.containsKey(clientId)){
            return;
        }
        Integer credit = clientIdCreditMap.get(clientId);
        HashSet creditsGroup = creditClientIdsMap.get(credit);
        creditsGroup.remove(clientId);
        if(creditsGroup.size()==0){
            creditClientIdsMap.remove( credit);
            if(credit== currentMax){
                currentMax = creditClientIdsMap.lastKey();
            }
        }


    }
    //o(logn)
    public int lookup(String clientId){
        Integer credit = clientIdCreditMap.get(clientId);

        return credit+ addAllCredit;

    }
    //o(1)
    public void addAll(int credits){
        addAllCredit +=credits;
    }
    //o(1)
    public String max(){
        HashSet<String> hashSet = creditClientIdsMap.get(currentMax);
        Iterator<String> iterator = hashSet.iterator();
        return iterator.next();
    }

    public static void main(String[] args) {
        AddCredits addCredits = new AddCredits();
        addCredits.insert("pradyumna",76);
        System.out.println(addCredits.lookup("pradyumna"));
        addCredits.addAll(10);
        System.out.println(addCredits.lookup("pradyumna"));
        addCredits.insert("Pooja",96);
        System.out.println(addCredits.lookup("Pooja"));

        System.out.println(addCredits.max());
        addCredits.insert("latu",80);
        System.out.println(addCredits.lookup("latu"));
        addCredits.insert("latu",99);
        System.out.println(addCredits.lookup("latu"));
        System.out.println(addCredits.max());
    }
}
