package detekcjabledow;

import businesslogic.HammingController;
import businesslogic.ParityControllerPositive;
import businesslogic.PolynomialData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowController {
    private JTextField poleTekstoweWyslanyZnak;
    private JTextField poleTekstoweWyslanyBinarny;
    private JTextField poleTekstoweBitParzystosci;
    private JTextField poleTekstoweBityHaminga;
    private JTextField poleTekstoweWielomianCRC16;
    private JTextField poleTekstoweWielomianCRC32;
    private JTextField poleTekstoweWielomianCRCITU;
    private JTextField poleTekstoweWielomianSDLC;
    private JTextField poleTekstowePrzesylanyCiag;
    private JTextField poleTekstoweDoZaklocenia;
    private JTextField poleTekstoweOdebranyCiag;
    private JTextField poleTekstoweWykryteBledy;
    private JTextField poleTekstowePoprawionyBinarny;
    private JTextField poleTekstoweOdebranyZnak;
    private ButtonGroup grupaRadiowa;
    private ArrayList<JTextField> listOfDataControlTextFields;
    private ArrayList<JTextField> listOfTextFields;
    private ArrayList<JRadioButton> listOfJRadioButtons;
    private int selectedRadioButtonTag;
    private String przesylanyCiag;
    private Container okno;

    MainWindowController(ArrayList<JTextField> textFields, ButtonGroup grupaRadiowa, ArrayList<JRadioButton> radioButtons, Container okno) {

        listOfTextFields = new ArrayList<>();
        this.poleTekstoweWyslanyZnak = textFields.get(0);       listOfTextFields.add(poleTekstoweWyslanyZnak);
        this.poleTekstoweWyslanyBinarny = textFields.get(1);    listOfTextFields.add(poleTekstoweWyslanyBinarny);
        listOfDataControlTextFields = new ArrayList<>();
        this.poleTekstoweBitParzystosci = textFields.get(2);    listOfTextFields.add(poleTekstoweBitParzystosci);listOfDataControlTextFields.add(poleTekstoweBitParzystosci);
        this.poleTekstoweBityHaminga = textFields.get(3);       listOfTextFields.add(poleTekstoweBityHaminga);listOfDataControlTextFields.add(poleTekstoweBityHaminga);
        this.poleTekstoweWielomianCRC16 = textFields.get(4);    listOfTextFields.add(poleTekstoweWielomianCRC16);listOfDataControlTextFields.add(poleTekstoweWielomianCRC16);
        this.poleTekstoweWielomianCRC32 = textFields.get(5);    listOfTextFields.add(poleTekstoweWielomianCRC32);listOfDataControlTextFields.add(poleTekstoweWielomianCRC32);
        this.poleTekstoweWielomianCRCITU = textFields.get(6);   listOfTextFields.add(poleTekstoweWielomianCRCITU);listOfDataControlTextFields.add(poleTekstoweWielomianCRCITU);
        this.poleTekstoweWielomianSDLC = textFields.get(7);     listOfTextFields.add(poleTekstoweWielomianSDLC);listOfDataControlTextFields.add(poleTekstoweWielomianSDLC);
        this.poleTekstowePrzesylanyCiag = textFields.get(8);    listOfTextFields.add(poleTekstowePrzesylanyCiag);
        this.poleTekstoweDoZaklocenia = textFields.get(9);      listOfTextFields.add(poleTekstoweDoZaklocenia);
        this.poleTekstoweOdebranyCiag = textFields.get(10);     listOfTextFields.add(poleTekstoweOdebranyCiag);
        this.poleTekstoweWykryteBledy = textFields.get(11);     listOfTextFields.add(poleTekstoweWykryteBledy);
        this.poleTekstowePoprawionyBinarny = textFields.get(12);listOfTextFields.add(poleTekstowePoprawionyBinarny);
        this.poleTekstoweOdebranyZnak = textFields.get(13);     listOfTextFields.add(poleTekstoweOdebranyZnak);
        this.grupaRadiowa = grupaRadiowa;
        this.listOfJRadioButtons = radioButtons;
        this.selectedRadioButtonTag = -1;
        this.przesylanyCiag = "";
        this.okno = okno;
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
        poleTekstoweOdebranyCiag.setText("");
        poleTekstoweWykryteBledy.setText("");
        poleTekstowePoprawionyBinarny.setText("");
        poleTekstoweOdebranyZnak.setText("");
    }
    public void wyslanyZnakActionPerformed(){
        for(int i = 1; i<listOfTextFields.size(); i++)//RESET STANU OKNA
            listOfTextFields.get(i).setText("");
        poleTekstoweDoZaklocenia.setEditable(false);
        grupaRadiowa.clearSelection();

        if( ! poleTekstoweWyslanyZnak.getText().isEmpty()){//KONWERSJA ZNAKU NA BINARNY
            String inString = poleTekstoweWyslanyZnak.getText();
            String binaryString;
            binaryString = ParityControllerPositive.convertStringToBinaryString(inString);
            poleTekstoweWyslanyBinarny.setText(binaryString);

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
        poleTekstoweBitParzystosci.setText(ParityControllerPositive.calculateParityBitForBinaryString(poleTekstoweWyslanyBinarny.getText()) );
        poleTekstowePrzesylanyCiag.setText(ParityControllerPositive.convertStringToParityProtectedBinaryString(poleTekstoweWyslanyZnak.getText()));
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(poleTekstowePrzesylanyCiag.getText());
        poleTekstoweBitParzystosci.setEditable(false);
    }
    public void bityHammingaPropertyChanged(){
        selectedRadioButtonTag = 1;
        poleTekstowePrzesylanyCiag.setText(HammingController.addHammingControlToString(poleTekstoweWyslanyBinarny.getText()));
        poleTekstoweBityHaminga.setText( HammingController.getParityBitsFromHammingWord(poleTekstowePrzesylanyCiag.getText()) );
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(poleTekstowePrzesylanyCiag.getText());
        poleTekstoweBityHaminga.setEditable(false);
    }
    public void wielomianCRC16PropertyChanged(){
        selectedRadioButtonTag = 2;
        PolynomialData data2 = new PolynomialData(1,13,2,1);
        poleTekstowePrzesylanyCiag.setText(data2.returnAsString());
        przesylanyCiag = data2.returnAsString();
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(data2.returnAsString());
        poleTekstoweWielomianCRC16.setText("11000000000000101");
        poleTekstoweWielomianCRC16.setEditable(false);
    }
    public void wielomianCRC32PropertyChanged(){
        selectedRadioButtonTag = 3;
        PolynomialData data2 = new PolynomialData(6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
        poleTekstowePrzesylanyCiag.setText(data2.returnAsString());
        przesylanyCiag = data2.returnAsString();
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(data2.returnAsString());
        poleTekstoweWielomianCRC32.setText("100000100110000010001110110110111");
        poleTekstoweWielomianCRC32.setEditable(false);
    }
    public void wielomianCRCITUPropertyChanged(){
        selectedRadioButtonTag = 4;
        PolynomialData data2 = new PolynomialData(4,7,4,1,1);
        poleTekstowePrzesylanyCiag.setText(data2.returnAsString());
        przesylanyCiag = data2.returnAsString();
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(data2.returnAsString());
        poleTekstoweWielomianCRCITU.setText("10001000000100001");
        poleTekstoweWielomianCRCITU.setEditable(false);
    }
    public void wielomianSDLCPropertyChanged(){
        selectedRadioButtonTag = 5;
        PolynomialData data2 = new PolynomialData(4,7,4,1,1);
        poleTekstowePrzesylanyCiag.setText(data2.returnAsString());
        przesylanyCiag = data2.returnAsString();
        poleTekstoweDoZaklocenia.setEditable(true);
        poleTekstoweDoZaklocenia.setText(data2.returnAsString());
        poleTekstoweWielomianSDLC.setText("10001000000100101");
        poleTekstoweWielomianSDLC.setEditable(false);
    }
    public void doZakloceniaActionPerformed(){
        String receivedString = poleTekstoweDoZaklocenia.getText();
        boolean poprawne = isStringBinaryDigitsOnly(receivedString);//SPRAWDZANIE POPRAWNOSCI DANYCH WPROWADZONYCH PRZEZ UZYTKOWNIKA
        if(!poprawne)
            JOptionPane.showMessageDialog(okno, "W polu tekstowym znajdują się symbole inne niż \"0\" i \"1\"");
        if(poleTekstoweDoZaklocenia.getText().length() != poleTekstowePrzesylanyCiag.getText().length()){
            poprawne = false;
            JOptionPane.showMessageDialog(okno, "Liczba znaków nie odpowiada przesyłanemu ciągowi!");
        }
        if(poprawne){
            poleTekstoweOdebranyCiag.setText( receivedString );

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
                    data = new PolynomialData(przesylanyCiag,1,13,2,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 3:
                    polynomialString = "100000100110000010001110110110111";//crc32
                    data = new PolynomialData(przesylanyCiag,6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 4:
                    polynomialString = "10001000000100001"; //crcItu
                    data = new PolynomialData(przesylanyCiag,4,7,4,1,1);
                    checksumPolDat = new PolynomialData(data.getParts());
                    polynomialCheckingAndUpdatingTextFields(checksumPolDat,polynomialString);
                    break;
                case 5:
                    polynomialString = "10001000000100101"; //SDLC
                    data = new PolynomialData(przesylanyCiag,4,7,4,1,1);
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
            poleTekstoweOdebranyZnak.setText("");
            ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString);
            String text = (new StringBuilder().append( ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString) )).toString();
            poleTekstoweOdebranyZnak.setText( text );
            poleTekstoweWykryteBledy.setText("");
        }catch(Exception e){poleTekstoweWykryteBledy.setText(e.getMessage());}
    }
    private void hammingCheckingAndUpdatingTextFields(String receivedString){//GŁÓWNA LOGIKA SPRAWDZANIA BITAMI HAMMINGA
        if( HammingController.isHammingProtectedWordCorrect(receivedString) ){
            String hammingBitsDeletedWord = HammingController.deleteHammingBitsFromWord(receivedString);
            Integer integerChar = Integer.parseInt(hammingBitsDeletedWord, 2);
            char convertedChar = (char) integerChar.intValue();
            poleTekstoweOdebranyZnak.setText("" + convertedChar);
            poleTekstoweWykryteBledy.setText("");
            poleTekstowePoprawionyBinarny.setText("");
        }else{
            int errorsCount = HammingController.countHammingBitErrors(receivedString);
            poleTekstoweWykryteBledy.setText("Błędne bity parzystosci:"+errorsCount);

            String errorsPositions = HammingController.getErrorParityBitsPositions(receivedString);
            poleTekstoweWykryteBledy.setText(poleTekstoweWykryteBledy.getText() +"   |na pozycjach:" + errorsPositions);

            int errorLocation = HammingController.getBadBitBasedOnErrorParityBitsPositions(errorsPositions);
            poleTekstoweWykryteBledy.setText(poleTekstoweWykryteBledy.getText() +"   |Przekłamany bit:" + errorLocation);
            StringBuilder poprawionyBinarny = new StringBuilder(receivedString);
            String bitDoPoprawy = Character.toString(poprawionyBinarny.charAt(errorLocation-1));
            String poprawionyBit = "1".equals(bitDoPoprawy)?"0":"1";
            poprawionyBinarny.replace(errorLocation-1, errorLocation, poprawionyBit);
            poleTekstowePoprawionyBinarny.setText(poprawionyBinarny.toString());

            String bezHammingaString = HammingController.deleteHammingBitsFromWord(poprawionyBinarny.toString());
            Integer bezHammingaInt = Integer.parseInt(bezHammingaString,2);
            char odczytanyZnakChar = (char) bezHammingaInt.intValue();
            String odczytanyZnakSBuilder = Character.toString(odczytanyZnakChar);
            poleTekstoweOdebranyZnak.setText(odczytanyZnakSBuilder);
        }
    }
    private void polynomialCheckingAndUpdatingTextFields(PolynomialData checksum, String polynomial){//GŁÓWNA LOGIKA SPRAWDZANIA WIELOMIANEM
        checksum.computeData(polynomial);
        String receivedData = poleTekstoweDoZaklocenia.getText();
        poleTekstoweOdebranyCiag.setText( receivedData );

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
        poleTekstoweWykryteBledy.setText(dataToCheck.returnAsString()+ "" +validationMessage);
    }
}
