package businesslogic.ParityControl;

public class HammingController extends ParityControllerPositive{
    public static String addHammingControlToString (String inWord){
        StringBuilder hammingProtectedWord = new StringBuilder(inWord);
        hammingProtectedWord = addBlankParityBits(hammingProtectedWord);  
        for(int i = 1; i<hammingProtectedWord.length(); i*=2){    
            String bitsToCheck = getBitsToCheckByParityBitIndex(hammingProtectedWord.toString(), i);    
            String parityBitValue = ParityControllerPositive.calculateParityBitForBinaryString(bitsToCheck);  
            hammingProtectedWord.replace(i-1,i, parityBitValue);
        }
        return hammingProtectedWord.toString();
    }
//--------------------------------------------------------------------------------------------------- 
    public static StringBuilder addBlankParityBits(StringBuilder baseWord){
        for(int i = 1; i<baseWord.length(); i*=2){
            baseWord.insert(i-1,'?');
        }
        return baseWord;
    }  
//--------------------------------------------------------------------------------------------------- 
    public static String getBitsToCheckByParityBitIndex(String inWord, int parityBitNumber){
        StringBuilder bitsToCheck = new StringBuilder("");
        int portionBeginIndex = parityBitNumber -1;
        int portionEndIndex = portionBeginIndex + parityBitNumber -1;
        int spaceBetweenPortionBegins = parityBitNumber*2;
        while(portionBeginIndex < inWord.length()){           
            int index = portionBeginIndex;
            while(index <= portionEndIndex && index < inWord.length()){
                if(inWord.charAt(index) != '?')
                bitsToCheck.append(inWord.charAt(index));
                index++;
            }
            portionBeginIndex += spaceBetweenPortionBegins;
            portionEndIndex += spaceBetweenPortionBegins;
        }
        return bitsToCheck.toString();
    }
//---------------------------------------------------------------------------------------------------   
    public static boolean isHammingProtectedWordCorrect(String inWord){
        int errorsCount = 0;
        errorsCount = countHammingBitErrors(inWord);
        if(errorsCount == 0)
            return true;
        else
            return false;
    } 
//---------------------------------------------------------------------------------------------------     
    public static int countHammingBitErrors(String inWord){
        int errorsCount = getErrorParityBitsPositions(inWord).length();
        return errorsCount;
    }
//---------------------------------------------------------------------------------------------------     
    public static String getErrorParityBitsPositions (String inWord){
        StringBuilder errorsPositions = new StringBuilder("");
        for(int parityBitNumber= 1;     parityBitNumber < inWord.length();      parityBitNumber*= 2){
            String bitsToCheck = getBitsToCheckByParityBitIndex(inWord, parityBitNumber);
            if( ! (isParityCorrect(bitsToCheck)) )
                errorsPositions.append(parityBitNumber);
        }
        return errorsPositions.toString();
    }
//--------------------------------------------------------------------------------------------------- 
    public static String getParityBitsFromHammingWord(String inWord){
        StringBuilder hammingBits =new StringBuilder("");
        for(int i = 1; i<inWord.length(); i*=2)
            hammingBits.append(inWord.charAt(i-1));
        return hammingBits.toString();
    }
//--------------------------------------------------------------------------------------------------- 
    public static int getBadBitBasedOnErrorParityBitsPositions(String errorPositions){
        int badBitPosition=0;
        for(int i = 0; i<errorPositions.length(); i++){
            badBitPosition+= Character.getNumericValue(errorPositions.charAt(i));
        }
        return badBitPosition;
    }
//---------------------------------------------------------------------------------------------------     
    public static String deleteHammingBitsFromWord(String inWord){
        StringBuilder trimmedWord =new StringBuilder("");
        int hammingBitNumber = 1;
        for(int i = 1; i<inWord.length() +1 ;i++){
            if(i == hammingBitNumber)
                hammingBitNumber *= 2;
            else
                trimmedWord.append(inWord.charAt(i-1)); 
        }
        return trimmedWord.toString();
    }
//---------------------------------------------------------------------------------------------------
}
