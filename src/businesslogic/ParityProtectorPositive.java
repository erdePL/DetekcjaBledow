package businesslogic;

public class ParityProtectorPositive {
//---------------------------------------------------------------------------------------------------    
   public static String convertStringToParityProtectedBinaryString(String stringToConvert){
       String binaryString = convertStringToBinaryString(stringToConvert);
       String parityProtectedBinaryString = addParityProtectionToBinaryString(binaryString);
        return parityProtectedBinaryString;
    }
//--------------------------------------------------------------------------------------------------- 
    public static String convertStringToBinaryString(String stringToConvert){//TODO UNUSED METHOD POSSIBLE
        String binaryString = "";
        for(char element : stringToConvert.toCharArray()){
           binaryString += convertCharToBinaryAscii(element) ;
        }
        return binaryString;
    }
//--------------------------------------------------------------------------------------------------- 
    public static String convertCharToBinaryAscii(char charToConvert){
        int asciiCodeOfLetter = (int) charToConvert;
        String binaryAsciiCode = Integer.toBinaryString (asciiCodeOfLetter );
        return binaryAsciiCode;    
}
//---------------------------------------------------------------------------------------------------
    public static String addParityProtectionToBinaryString(String binaryAsciiCode){      
        String parityBit = calculateParityBitForBinaryString(binaryAsciiCode);
        binaryAsciiCode = parityBit + binaryAsciiCode;
        return binaryAsciiCode;
    }
//---------------------------------------------------------------------------------------------------     
    public static String calculateParityBitForBinaryString(String binaryAsciiCode){
    int onesCount = getNumberOfOnesFromString(binaryAsciiCode);
    String parityBit = "";    
    if(onesCount % 2 == 0)
        parityBit = "0";    
    else
        parityBit = "1";
    return parityBit;
    }
//---------------------------------------------------------------------------------------------------        
    public static int getNumberOfOnesFromString(String binaryAsciiChar){
        int onesCount = 0;
        char[] array = binaryAsciiChar.toCharArray();
        for( char item : array ){
            if(item == '1')
                onesCount++;
        }
        return onesCount;
    }          
//---------------------------------------------------------------------------------------------------  
    public static char convertParityProtectedBinaryStringToChar(String charToConvert) throws Exception {
        if(isParityCorrect(charToConvert)){
            String charWithoutParityBit = charToConvert.substring(1);
            Integer integerChar = Integer.parseInt(charWithoutParityBit, 2);
            char convertedChar = (char) integerChar.intValue();
            return convertedChar;
        }
        else
            throw new Exception("Dane niepoprawne, kontrola parzystości wykryła błąd");
    }   
//---------------------------------------------------------------------------------------------------  
    public static boolean isParityCorrect(String receivedString){
        int onesCount = getNumberOfOnesFromString(receivedString);
        if(onesCount % 2 == 0)
        return true;
        else
        return false;
    }
}
