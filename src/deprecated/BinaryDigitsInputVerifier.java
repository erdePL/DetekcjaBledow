package deprecated;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class BinaryDigitsInputVerifier extends InputVerifier{

    @Override
    public boolean verify(JComponent input) {
        JTextField mojePole = (JTextField) input;
        char charToCheck;
        boolean isCorrect = true;
        String text = input.toString();
        
        for(int i = 0; i<text.length(); i++){
            charToCheck = text.charAt(i);
            if(charToCheck != '0' && charToCheck != '1')
                isCorrect = false;
        }
        return isCorrect;
    }
    
}
