package detekcjabledow;

import businesslogic.HammingController;
import businesslogic.ParityControllerPositive;
import businesslogic.PolynomialData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowController {
    private JTextField textFieldCharToSend;
    private JTextField textFieldCharToSendBinary;
    private JTextField textFieldParityBit;
    private JTextField textFieldHammingsBits;
    private JTextField textFieldPolynomialCrc16;
    private JTextField textFieldPolynomialCrc32;
    private JTextField textFieldPolynomialCrcItu;
    private JTextField textFieldPolynomialSdlc;
    private JTextField textFieldSentSequence;
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
    private String sentSequence;
    private Container windowContainer;

    MainWindowController(ArrayList<JTextField> textFields, ButtonGroup radioButtonsGroup, ArrayList<JRadioButton> radioButtons, Container windowContainer) {

        listOfAllTextFields = new ArrayList<>();
        this.textFieldCharToSend = textFields.get(0);       listOfAllTextFields.add(textFieldCharToSend);
        this.textFieldCharToSendBinary = textFields.get(1);    listOfAllTextFields.add(textFieldCharToSendBinary);
        listOfDataControlTextFields = new ArrayList<>();
        this.textFieldParityBit = textFields.get(2);    listOfAllTextFields.add(textFieldParityBit);listOfDataControlTextFields.add(textFieldParityBit);
        this.textFieldHammingsBits = textFields.get(3);       listOfAllTextFields.add(textFieldHammingsBits);listOfDataControlTextFields.add(textFieldHammingsBits);
        this.textFieldPolynomialCrc16 = textFields.get(4);    listOfAllTextFields.add(textFieldPolynomialCrc16);listOfDataControlTextFields.add(textFieldPolynomialCrc16);
        this.textFieldPolynomialCrc32 = textFields.get(5);    listOfAllTextFields.add(textFieldPolynomialCrc32);listOfDataControlTextFields.add(textFieldPolynomialCrc32);
        this.textFieldPolynomialCrcItu = textFields.get(6);   listOfAllTextFields.add(textFieldPolynomialCrcItu);listOfDataControlTextFields.add(textFieldPolynomialCrcItu);
        this.textFieldPolynomialSdlc = textFields.get(7);     listOfAllTextFields.add(textFieldPolynomialSdlc);listOfDataControlTextFields.add(textFieldPolynomialSdlc);
        this.textFieldSentSequence = textFields.get(8);    listOfAllTextFields.add(textFieldSentSequence);
        this.textFieldSequenceToDisrupt = textFields.get(9);      listOfAllTextFields.add(textFieldSequenceToDisrupt);
        this.textFieldReceivedSequence = textFields.get(10);     listOfAllTextFields.add(textFieldReceivedSequence);
        this.textFieldDetectedErrors = textFields.get(11);     listOfAllTextFields.add(textFieldDetectedErrors);
        this.textFieldCorrectedBinary = textFields.get(12);
        listOfAllTextFields.add(textFieldCorrectedBinary);
        this.textFieldReceivedChar = textFields.get(13);     listOfAllTextFields.add(textFieldReceivedChar);
        this.radioButtonsGroup = radioButtonsGroup;
        this.listOfJRadioButtons = radioButtons;
        this.selectedRadioButtonTag = -1;
        this.sentSequence = "";
        this.windowContainer = windowContainer;
    }

    public void radioButtonItemStateChanged(JRadioButton roboczyRadioButton){
        Integer textFieldIndex = Integer.parseInt(roboczyRadioButton.getName() );
        JTextField fieldToChangeState = listOfDataControlTextFields.get(textFieldIndex);
        if(roboczyRadioButton.isSelected())
            fieldToChangeState.setEditable(true);//Każde z tych pol nasluchuje zmiany stanu na "editable".
            //Taka zmiana stanu wywoluje skrypt wygenerowania danych i wypełnienia nimi pola tekstowe
        else{
            fieldToChangeState.setText("");//Jako że wybranie radio buttona prowadzi do potencjalnej zmiany wszystkich danych,
        }                                  //za każdym razem należy pozbyć się znajdującej się w polach treści
        textFieldReceivedSequence.setText("");
        textFieldDetectedErrors.setText("");
        textFieldCorrectedBinary.setText("");
        textFieldReceivedChar.setText("");
    }
    public void wyslanyZnakActionPerformed(){
        for(int i = 1; i< listOfAllTextFields.size(); i++)//RESET STANU OKNA
            listOfAllTextFields.get(i).setText("");
        textFieldSequenceToDisrupt.setEditable(false);
        radioButtonsGroup.clearSelection();

        if( ! textFieldCharToSend.getText().isEmpty()){//KONWERSJA ZNAKU NA BINARNY
            String inString = textFieldCharToSend.getText();
            String binaryString;
            binaryString = ParityControllerPositive.convertStringToBinaryString(inString);
            textFieldCharToSendBinary.setText(binaryString);

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
    public void bitParzystosciPropertyChanged(){
        selectedRadioButtonTag = 0;
        textFieldParityBit.setText(ParityControllerPositive.calculateParityBitForBinaryString(textFieldCharToSendBinary.getText()) );
        textFieldSentSequence.setText(ParityControllerPositive.convertStringToParityProtectedBinaryString(textFieldCharToSend.getText()));
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(textFieldSentSequence.getText());
        textFieldParityBit.setEditable(false);
    }
    public void bityHammingaPropertyChanged(){
        selectedRadioButtonTag = 1;
        textFieldSentSequence.setText(HammingController.addHammingControlToString(textFieldCharToSendBinary.getText()));
        textFieldHammingsBits.setText( HammingController.getParityBitsFromHammingWord(textFieldSentSequence.getText()) );
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(textFieldSentSequence.getText());
        textFieldHammingsBits.setEditable(false);
    }
    public void wielomianCRC16PropertyChanged(){
        selectedRadioButtonTag = 2;
        PolynomialData data2 = new PolynomialData(1,13,2,1);
        textFieldSentSequence.setText(data2.returnAsString());
        sentSequence = data2.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(data2.returnAsString());
        textFieldPolynomialCrc16.setText("11000000000000101");
        textFieldPolynomialCrc16.setEditable(false);
    }
    public void wielomianCRC32PropertyChanged(){
        selectedRadioButtonTag = 3;
        PolynomialData data2 = new PolynomialData(6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
        textFieldSentSequence.setText(data2.returnAsString());
        sentSequence = data2.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(data2.returnAsString());
        textFieldPolynomialCrc32.setText("100000100110000010001110110110111");
        textFieldPolynomialCrc32.setEditable(false);
    }
    public void wielomianCRCITUPropertyChanged(){
        selectedRadioButtonTag = 4;
        PolynomialData data2 = new PolynomialData(4,7,4,1,1);
        textFieldSentSequence.setText(data2.returnAsString());
        sentSequence = data2.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(data2.returnAsString());
        textFieldPolynomialCrcItu.setText("10001000000100001");
        textFieldPolynomialCrcItu.setEditable(false);
    }
    public void wielomianSDLCPropertyChanged(){
        selectedRadioButtonTag = 5;
        PolynomialData data2 = new PolynomialData(4,7,4,1,1);
        textFieldSentSequence.setText(data2.returnAsString());
        sentSequence = data2.returnAsString();
        textFieldSequenceToDisrupt.setEditable(true);
        textFieldSequenceToDisrupt.setText(data2.returnAsString());
        textFieldPolynomialSdlc.setText("10001000000100101");
        textFieldPolynomialSdlc.setEditable(false);
    }
    public void doZakloceniaActionPerformed(){
        String receivedString = textFieldSequenceToDisrupt.getText();
        boolean poprawne = isStringBinaryDigitsOnly(receivedString);//SPRAWDZANIE POPRAWNOSCI DANYCH WPROWADZONYCH PRZEZ UZYTKOWNIKA
        if(!poprawne)
            JOptionPane.showMessageDialog(windowContainer, "W polu tekstowym znajdują się symbole inne niż \"0\" i \"1\"");
        if(textFieldSequenceToDisrupt.getText().length() != textFieldSentSequence.getText().length()){
            poprawne = false;
            JOptionPane.showMessageDialog(windowContainer, "Liczba znaków nie odpowiada przesyłanemu ciągowi!");
        }
        if(poprawne){
            textFieldReceivedSequence.setText( receivedString );

            String polynomialString;//DEKLARACJE ZMIENNYCH POTRZEBNYCH DO KONTROLI WIELOMIANEM (CRC16, CRC32...)
            PolynomialData data;
            PolynomialData checksumPolDat;

            switch (selectedRadioButtonTag){//INICJALIZACJA DANYCH W ZALEZNOSCI OD WYBRANEGO TYPU SPRAWDZANIA POPRAWNOSCI,
                case 0:                     //ORAZ WYWOLANIE ODPOWIADAJACYCH FUNKCJI SPRAWDZAJACYCH
                    parityCheckingAndUpdatingTextFields(receivedString);
                    break;
                case 1:
                    hammingCheckingAndUpdatingTextFields(receivedString);
                    break;
                case 2:
                    polynomialString = "11000000000000101";//crc16
                    data = new PolynomialData(sentSequence,1,13,2,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 3:
                    polynomialString = "100000100110000010001110110110111";//crc32
                    data = new PolynomialData(sentSequence,6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 4:
                    polynomialString = "10001000000100001"; //crcItu
                    data = new PolynomialData(sentSequence,4,7,4,1,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 5:
                    polynomialString = "10001000000100101"; //SDLC
                    data = new PolynomialData(sentSequence,4,7,4,1,1);
                    checksumPolDat = new PolynomialData(data.getParts());
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
            ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString);
            String text = (new StringBuilder().append( ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString) )).toString();
            textFieldReceivedChar.setText( text );
            textFieldDetectedErrors.setText("");
        }catch(Exception e){
            textFieldDetectedErrors.setText(e.getMessage());}
    }
    private void hammingCheckingAndUpdatingTextFields(String receivedString){//GŁÓWNA LOGIKA SPRAWDZANIA BITAMI HAMMINGA
        if( HammingController.isHammingProtectedWordCorrect(receivedString) ){
            String hammingBitsDeletedWord = HammingController.deleteHammingBitsFromWord(receivedString);
            Integer integerChar = Integer.parseInt(hammingBitsDeletedWord, 2);
            char convertedChar = (char) integerChar.intValue();
            textFieldReceivedChar.setText("" + convertedChar);
            textFieldDetectedErrors.setText("");
            textFieldCorrectedBinary.setText("");
        }else{
            int errorsCount = HammingController.countHammingBitErrors(receivedString);
            textFieldDetectedErrors.setText("Błędne bity parzystosci:"+errorsCount);

            String errorsPositions = HammingController.getErrorParityBitsPositions(receivedString);
            textFieldDetectedErrors.setText(textFieldDetectedErrors.getText() +"   |na pozycjach:" + errorsPositions);

            int errorLocation = HammingController.getBadBitBasedOnErrorParityBitsPositions(errorsPositions);
            textFieldDetectedErrors.setText(textFieldDetectedErrors.getText() +"   |Przekłamany bit:" + errorLocation);
            StringBuilder poprawionyBinarny = new StringBuilder(receivedString);
            String bitDoPoprawy = Character.toString(poprawionyBinarny.charAt(errorLocation-1));
            String poprawionyBit = "1".equals(bitDoPoprawy)?"0":"1";
            poprawionyBinarny.replace(errorLocation-1, errorLocation, poprawionyBit);
            textFieldCorrectedBinary.setText(poprawionyBinarny.toString());

            String bezHammingaString = HammingController.deleteHammingBitsFromWord(poprawionyBinarny.toString());
            Integer bezHammingaInt = Integer.parseInt(bezHammingaString,2);
            char odczytanyZnakChar = (char) bezHammingaInt.intValue();
            String odczytanyZnakSBuilder = Character.toString(odczytanyZnakChar);
            textFieldReceivedChar.setText(odczytanyZnakSBuilder);
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
