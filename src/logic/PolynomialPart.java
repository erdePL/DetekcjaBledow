package logic;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class PolynomialPart {
    Queue bitList;
//---------------------------------------------------------------------------------------------------     
    public PolynomialPart(int numberOfBits){
            bitList = new LinkedList();
            for(int i = 0; i<numberOfBits; i++){
                bitList.add( ThreadLocalRandom.current().nextInt(0,2) );
            }
    }
//---------------------------------------------------------------------------------------------------     
    public PolynomialPart(int ...args ){
        bitList = new LinkedList();
            for(int i = 0; i<args.length; i++){
                bitList.add( args[i] );
            }
    }
//---------------------------------------------------------------------------------------------------     
    public PolynomialPart(PolynomialPart copySource){
        this.bitList = new LinkedList();
        this.bitList.addAll( copySource.bitList );
    }
//---------------------------------------------------------------------------------------------------     
    public int compute(int inBit1, int inBit2){
        int newBit = inBit1 ^ inBit2;
        bitList.add(newBit);
        int erasedBit = (int) bitList.remove();
        return erasedBit;
    }
//---------------------------------------------------------------------------------------------------     
    public void addBasedOnTwo(int inBit1, int inBit2){
        int newBit = inBit1 ^ inBit2;
        bitList.add(newBit); 
    }
//---------------------------------------------------------------------------------------------------     
    public int removeAndReturn(){
        int erasedBit = (int) bitList.remove();
        return erasedBit;
    }
//---------------------------------------------------------------------------------------------------     
    public String returnAsString(){
        StringBuilder data = new StringBuilder("");
        for(Object element : bitList){
            data.append((int) element);
        }
        return data.toString();
    }
}
