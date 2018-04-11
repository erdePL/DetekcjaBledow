
package businesslogic.PolynomialControl;

import java.util.ArrayList;

public class PolynomialData {
    ArrayList<PolynomialPart> parts;
//---------------------------------------------------------------------------------------------------     
public PolynomialData(int... args){
        parts = new ArrayList();
        for(int i = 0; i<args.length; i++){
            PolynomialPart part = new PolynomialPart(args[i]);
            parts.add(part);
        }
    }
//---------------------------------------------------------------------------------------------------     
public PolynomialData(String data, int... args){
        parts = new ArrayList();
        //Integer intData = Integer.parseInt(data);
        int sum = 0;
        for(int i = 0; i<args.length; i++){//po każdym parcie
            int[] bitValues = new int[args[i]];
            int k = 0;
            for(int j = sum; j<(sum+args[i]); j++, k++){//po kazdym znaku
                Integer bit = Integer.parseInt(data.substring(j, j+1));
                bitValues[k] = bit.intValue();
            }
            PolynomialPart part = new PolynomialPart(bitValues);
            parts.add(part);
            sum+=args[i];
        }
    }
//---------------------------------------------------------------------------------------------------     
public PolynomialData(ArrayList<PolynomialPart> parts){
        this.parts = new ArrayList();
        for(int i = 0; i<parts.size(); i++){
            PolynomialPart part = new PolynomialPart( parts.get(i) ) ;
            this.parts.add(part);
        }
    }
//---------------------------------------------------------------------------------------------------     
    public String computeData(String inData){
        PolynomialData outData = this;
        for(int i = 0; i<inData.length(); i++){
            outData.compute( Integer.parseInt(inData.substring(i, i+1)) );
        }
        return outData.returnAsString();
    }
//---------------------------------------------------------------------------------------------------     
    public void compute(int inBit){
        int headBit = (int) parts.get(0).bitList.peek();
        int otherBit = inBit;
        for(int i = parts.size()-1; i>=0; i--){
            parts.get(i).addBasedOnTwo(headBit,otherBit);
            otherBit = parts.get(i).removeAndReturn();
        }
    }
//---------------------------------------------------------------------------------------------------     
    public void printData(){
        for(PolynomialPart part : this.parts){
                System.out.print(part.bitList);
            }
        System.out.println();
    }
//---------------------------------------------------------------------------------------------------     
    public String returnAsString(){
        StringBuilder data = new StringBuilder("");
        for(PolynomialPart part : this.parts){
            data.append(part.returnAsString());
        }
        
        return data.toString();
    }
//---------------------------------------------------------------------------------------------------       
    public void setParts(ArrayList<PolynomialPart> parts) {
        this.parts = parts;
    }
//---------------------------------------------------------------------------------------------------   
    public ArrayList<PolynomialPart> getParts() {
        return parts;
    }
}

