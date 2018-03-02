package detekcjabledow;

import businesslogic.HammingsProtector;
import businesslogic.ParityProtectorPositive;
import businesslogic.PolynomialData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowController {
    private JTextField textFieldCharToSend;
    private JTextField textFieldCharToSendInBinaryAscii;
    private JTextField textFieldParityBit;
    private JTextField textFieldHammingsBits;
    private JTextField textFieldPolynomialCrc16;
    private JTextField textFieldPolynomialCrc32;
    private JTextField textFieldPolynomialCrcItu;
    private JTextField textFieldPolynomialSdlc;
    private JTextField textFieldSendingSequence;
    private JTextField textFieldSequenceToDisrupt;
    private JTextField textFieldReceivedSequence;
    private JTextField textFieldDetectedErrors;
    private JTextField textFieldCorrectedBinary;
    private JTextField textFieldReceivedChar;
    private ButtonGroup radioButtonsGroup;
    private ArrayList<JTextField> listOfDataControlTextFields;
    private ArrayList<JTextField> listOfAllTextFields;
    private ArrayList<JRadioButton> listOfJRadioButtons;
    private int selectedRadioButtonTag;
    private String sendingSequence;
    private Container contentPane;

    MainWindowController(ArrayList<JTextField> allTextFields, ButtonGroup radioButtonsGroup, ArrayList<JRadioButton> listOfRadioButtons, Container contentPane) {

        listOfAllTextFields = new ArrayList<>();
        this.textFieldCharToSend = allTextFields.get(0);       listOfAllTextFields.add(textFieldCharToSend);
        this.textFieldCharToSendInBinaryAscii = allTextFields.get(1);    listOfAllTextFields.add(textFieldCharToSendInBinaryAscii);
        this.listOfDataControlTextFields = new ArrayList<>();
        this.textFieldParityBit = allTextFields.get(2);    listOfAllTextFields.add(textFieldParityBit);    listOfDataControlTextFields.add(textFieldParityBit);
        this.textFieldHammingsBits = allTextFields.get(3);       listOfAllTextFields.add(textFieldHammingsBits);   listOfDataControlTextFields.add(textFieldHammingsBits);
        this.textFieldPolynomialCrc16 = allTextFields.get(4);    listOfAllTextFields.add(textFieldPolynomialCrc16);    listOfDataControlTextFields.add(textFieldPolynomialCrc16);
        this.textFieldPolynomialCrc32 = allTextFields.get(5);    listOfAllTextFields.add(textFieldPolynomialCrc32);    listOfDataControlTextFields.add(textFieldPolynomialCrc32);
        this.textFieldPolynomialCrcItu = allTextFields.get(6);   listOfAllTextFields.add(textFieldPolynomialCrcItu);   listOfDataControlTextFields.add(textFieldPolynomialCrcItu);
        this.textFieldPolynomialSdlc = allTextFields.get(7);     listOfAllTextFields.add(textFieldPolynomialSdlc); listOfDataControlTextFields.add(textFieldPolynomialSdlc);
        this.textFieldSendingSequence = allTextFields.get(8);    listOfAllTextFields.add(textFieldSendingSequence);
        this.textFieldSequenceToDisrupt = allTextFields.get(9);      listOfAllTextFields.add(textFieldSequenceToDisrupt);
        this.textFieldReceivedSequence = allTextFields.get(10);     listOfAllTextFields.add(textFieldReceivedSequence);
        this.textFieldDetectedErrors = allTextFields.get(11);     listOfAllTextFields.add(textFieldDetectedErrors);
        this.textFieldCorrectedBinary = allTextFields.get(12);
        this.listOfAllTextFields.add(textFieldCorrectedBinary);
        this.textFieldReceivedChar = allTextFields.get(13);     listOfAllTextFields.add(textFieldReceivedChar);
        this.radioButtonsGroup = radioButtonsGroup;
        this.listOfJRadioButtons = listOfRadioButtons;
        this.selectedRadioButtonTag = -1;
        this.sendingSequence = "";
        this.contentPane = contentPane;
    }

    public void radioButtonItemStateChanged(JRadioButton radioButton){
        Integer textFieldIndex = Integer.parseInt(radioButton.getName() );
        JTextField fieldToChangeState = listOfDataControlTextFields.get(textFieldIndex);
        if(radioButton.isSelected())
            fieldToChangeState.setEditable(true);//Każde z tych pol nasluchuje zmiany stanu na "editable".
            //Taka zmiana stanu wywoluje skrypt wygenerowania danych i wypełnienia nimi pola tekstowe
        else
            fieldToChangeState.setText("");//Jako że wybranie radio buttona prowadzi do potencjalnej zmiany wszystkich danych,
            // za każdym razem należy pozbyć się znajdującej się w polach treści
        textFieldReceivedSequence.setText("");
        textFieldDetectedErrors.setText("");
        textFieldCorrectedBinary.setText("");
        textFieldReceivedChar.setText("");
    }

    public void textFieldCharToSendActionPerformed(){
        for(int i = 1; i< listOfAllTextFields.size(); i++)//RESET STANU OKNA
            listOfAllTextFields.get(i).setText("");
        textFieldSequenceToDisrupt.setEditable(false);
        radioButtonsGroup.clearSelection();

        if( ! textFieldCharToSend.getText().isEmpty()){//KONWERSJA ZNAKU NA BINARNY
            char charToSend = textFieldCharToSend.getText().charAt(0);
            String charInBinaryAscii;
            charInBinaryAscii = ParityProtectorPositive.convertCharToBinaryAscii(charToSend);
            textFieldCharToSendInBinaryAscii.setText(charInBinaryAscii);

            int i = 0;//AKTYWACJA/DEZAKTYWACJA WŁAŚCIWYCH RADIO BUTTONOW
            for(; i<2; i++)
                listOfJRadioButtons.get(i).setEnabled(true);
            for(; i<6; i++)
                listOfJRadioButtons.get(i).setEnabled(false);
        }
        else{
            int i = 0;//AKTYWACJA/DEZAKTYWACJA WŁAŚCIWYCH RADIO BUTTONOW (ODWROTNA DO POWYŻSZEJ), W ZALEŻNOŚCI OD WYBRANEGO SPOSOBU KONTROLI DANYCH
            for(; i<2; i++)
                listOfJRadioButtons.get(i).setEnabled(false);
            for(; i<6; i++)
                listOfJRadioButtons.get(i).setEnabled(true);
        }
    }

    public void textFieldParityBitPropertyEditableChanged(){
        selectedRadioButtonTag = 0;
        textFieldParityBit.setText(ParityProtectorPositive.calculateParityBitForBinaryString(textFieldCharToSendInBinaryAscii.getText()) );
        textFieldSendingSequence.setText(ParityProtectorPositive.convertStringToParityProtectedBinaryString(textFieldCharToSend.getText()));
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(textFieldSendingSequence.getText());
        textFieldParityBit.setEditable(false);
    }

    public void textFieldHammingsBitsPropertyEditableChanged(){
        selectedRadioButtonTag = 1;
        textFieldSendingSequence.setText( HammingsProtector.addHammingsControlToBinarySequence( textFieldCharToSendInBinaryAscii.getText()) );
        textFieldHammingsBits.setText( HammingsProtector.getParityBitsFromHammingWord( textFieldSendingSequence.getText()) );
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(textFieldSendingSequence.getText());
        textFieldHammingsBits.setEditable(false);
    }

    public void textFieldPolynomialCrc16PropertyEditableChanged(){
        selectedRadioButtonTag = 2;
        PolynomialData rngData = new PolynomialData(1,13,2,1);
        textFieldSendingSequence.setText(rngData.returnAsString());
        sendingSequence = rngData.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(rngData.returnAsString());
        textFieldPolynomialCrc16.setText("11000000000000101");
        textFieldPolynomialCrc16.setEditable(false);
    }

    public void textFieldPolynomialCrc32PropertyEditableChanged(){
        selectedRadioButtonTag = 3;
        PolynomialData rngData = new PolynomialData(6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
        textFieldSendingSequence.setText(rngData.returnAsString());
        sendingSequence = rngData.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(rngData.returnAsString());
        textFieldPolynomialCrc32.setText("100000100110000010001110110110111");
        textFieldPolynomialCrc32.setEditable(false);
    }

    public void textFieldPolynomialCrcItuPropertyEditableChanged(){
        selectedRadioButtonTag = 4;
        PolynomialData rngData = new PolynomialData(4,7,4,1,1);
        textFieldSendingSequence.setText(rngData.returnAsString());
        sendingSequence = rngData.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(rngData.returnAsString());
        textFieldPolynomialCrcItu.setText("10001000000100001");
        textFieldPolynomialCrcItu.setEditable(false);
    }

    public void textFieldPolynomialSdlcPropertyEditableChanged(){
        selectedRadioButtonTag = 5;
        PolynomialData rngData = new PolynomialData(4,7,4,1,1);
        textFieldSendingSequence.setText(rngData.returnAsString());
        sendingSequence = rngData.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(rngData.returnAsString());
        textFieldPolynomialSdlc.setText("10001000000100101");
        textFieldPolynomialSdlc.setEditable(false);
    }

    public void textFieldSequenceToDisruptActionPerformed(){
        String receivedSequence = textFieldSequenceToDisrupt.getText();
        boolean onlyLegalCharactersInSequence = isStringBinaryDigitsOnly(receivedSequence);//SPRAWDZANIE POPRAWNOSCI DANYCH WPROWADZONYCH PRZEZ UZYTKOWNIKA
        if( ! onlyLegalCharactersInSequence)
            JOptionPane.showMessageDialog(contentPane, "W polu tekstowym znajdują się symbole inne niż \"0\" i \"1\"");
        boolean sequencesLengthCorrect = true;
        if(textFieldSequenceToDisrupt.getText().length() != textFieldSendingSequence.getText().length()){
            sequencesLengthCorrect = false;
            JOptionPane.showMessageDialog(contentPane, "Liczba znaków nie odpowiada przesyłanemu ciągowi!");
        }
        if(onlyLegalCharactersInSequence && sequencesLengthCorrect){
            textFieldReceivedSequence.setText( receivedSequence );
            String polynomialString;//DEKLARACJE ZMIENNYCH POTRZEBNYCH DO KONTROLI WIELOMIANEM (CRC16, CRC32...)
            PolynomialData data;
            PolynomialData checksumPolDat;

            switch (selectedRadioButtonTag){//INICJALIZACJA DANYCH W ZALEZNOSCI OD WYBRANEGO TYPU SPRAWDZANIA POPRAWNOSCI,
                case 0:                     //ORAZ WYWOLANIE ODPOWIADAJACYCH FUNKCJI SPRAWDZAJACYCH
                    parityCheckingAndUpdatingTextFields(receivedSequence);
                    break;
                case 1:
                    hammingCheckingAndUpdatingTextFields(receivedSequence);
                    break;
                case 2:
                    polynomialString = "11000000000000101";//crc16
                    data = new PolynomialData(sendingSequence,1,13,2,1);
                    checksumPolDat = data;
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 3:
                    polynomialString = "100000100110000010001110110110111";//crc32
                    data = new PolynomialData(sendingSequence,6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
                    checksumPolDat = data;
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 4:
                    polynomialString = "10001000000100001"; //crcItu
                    data = new PolynomialData(sendingSequence,4,7,4,1,1);
                    checksumPolDat = data;
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 5:
                    polynomialString = "10001000000100101"; //SDLC
                    data = new PolynomialData(sendingSequence,4,7,4,1,1);
                    checksumPolDat = data;
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
            }
        }
    }

    private boolean isStringBinaryDigitsOnly(String receivedString) {
        char charToCheck;
        boolean isCorrect = true;
        for(int i = 0; i<receivedString.length(); i++){
            charToCheck = receivedString.charAt(i);
            if(charToCheck != '0' && charToCheck != '1')
                isCorrect = false;
        }
        return isCorrect;
    }

    private void parityCheckingAndUpdatingTextFields(String receivedString){//GŁÓWNA LOGIKA SPRAWDZANIA PARZYSTOŚCIĄ
        try{
            textFieldReceivedChar.setText("");
            ParityProtectorPositive.convertParityProtectedBinaryStringToChar(receivedString);
            String text = (new StringBuilder().append( ParityProtectorPositive.convertParityProtectedBinaryStringToChar(receivedString) )).toString();
            textFieldReceivedChar.setText( text );
            textFieldDetectedErrors.setText("");
        }catch(Exception e){
            textFieldDetectedErrors.setText(e.getMessage());}
    }

    private void hammingCheckingAndUpdatingTextFields(String receivedSequence){//GŁÓWNA LOGIKA SPRAWDZANIA BITAMI HAMMINGA
        if( HammingsProtector.isHammingProtectedWordCorrect(receivedSequence) ){
            String sequenceWithoutHammingsBits = HammingsProtector.deleteHammingBitsFromWord(receivedSequence);
            Integer charsAsciiCode = Integer.parseInt(sequenceWithoutHammingsBits, 2);
            char convertedChar = (char) charsAsciiCode.intValue();
            textFieldReceivedChar.setText("" + convertedChar);
            textFieldDetectedErrors.setText("");
            textFieldCorrectedBinary.setText("");
        }else{
            int errorsCount = HammingsProtector.countBitErrors(receivedSequence);
            textFieldDetectedErrors.setText("Ilość błędnych bitów parzystosci:"+errorsCount);

            String errorsPositions = HammingsProtector.getWrongParityBitsPositions(receivedSequence);
            textFieldDetectedErrors.setText(textFieldDetectedErrors.getText() +"  |  na pozycjach:" + errorsPositions);

            int errorLocation = HammingsProtector.getBadBitBasedOnErrorParityBitsPositions(errorsPositions);
            textFieldDetectedErrors.setText(textFieldDetectedErrors.getText() +"  |  Przekłamany bit:" + errorLocation);
            StringBuilder correctedSequence = new StringBuilder(receivedSequence);
            String bitToCorrect = Character.toString(correctedSequence.charAt(errorLocation-1));
            String correctedBit = "1".equals(bitToCorrect)?"0":"1";
            correctedSequence.replace(errorLocation-1, errorLocation, correctedBit);
            textFieldCorrectedBinary.setText(correctedSequence.toString());

            String sequenceWithoutHammingsBits = HammingsProtector.deleteHammingBitsFromWord(correctedSequence.toString());
            Integer charsAsciiCode = Integer.parseInt(sequenceWithoutHammingsBits,2);
            char convertedChar = (char) charsAsciiCode.intValue();
            textFieldReceivedChar.setText(Character.toString(convertedChar));
        }
    }

    private void polynomialCheckingAndUpdatingTextFields(PolynomialData checksum, String polynomial){//GŁÓWNA LOGIKA SPRAWDZANIA WIELOMIANEM
        checksum.computeData(polynomial);
        String receivedData = textFieldSequenceToDisrupt.getText();
        textFieldReceivedSequence.setText( receivedData );

        int[] polynomialFormat;
        switch (polynomial){//TWORZENIE TABLICY FORMATU WIELOMIANU
            case "11000000000000101":
                polynomialFormat = new int[4];
                polynomialFormat[0]=1; polynomialFormat[1]=13; polynomialFormat[2]=2; polynomialFormat[3]=1;
                break;
            case "100000100110000010001110110110111":
                polynomialFormat = new int[15];
                polynomialFormat[0]=6; polynomialFormat[1]=3; polynomialFormat[2]=1; polynomialFormat[3]=6;
                polynomialFormat[4]=4; polynomialFormat[5]=1; polynomialFormat[6]=1; polynomialFormat[7]=2;
                polynomialFormat[8]=1; polynomialFormat[9]=2; polynomialFormat[10]=1; polynomialFormat[11]=2;
                polynomialFormat[12]=1; polynomialFormat[13]=1; polynomialFormat[14]=1;
                break;
            case "10001000000100001":
                polynomialFormat = new int[5];
                polynomialFormat[0]=4; polynomialFormat[1]=7; polynomialFormat[2]=4; polynomialFormat[3]=1;
                polynomialFormat[4]=1;
                break;
            case "10001000000100101":
                polynomialFormat = new int[5];
                polynomialFormat[0]=4; polynomialFormat[1]=7; polynomialFormat[2]=4; polynomialFormat[3]=1;
                polynomialFormat[4]=1;
                break;
            default:
                polynomialFormat = new int[0];
        }

        PolynomialData dataToCheck = new PolynomialData(receivedData,polynomialFormat);//NADAWANIE WIELOMIANOWI WLASCIWEGO FORMATU
        dataToCheck.computeData(checksum.returnAsString());
        String validationMessage;
        if(dataToCheck.returnAsString().equals(polynomial))
            validationMessage = " - Dane prawidłowe";
        else
            validationMessage = " - Dane nie prawidłowe";
        textFieldDetectedErrors.setText(dataToCheck.returnAsString()+ "" +validationMessage);
    }

}
