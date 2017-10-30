package detekcjabledow;

import logic.PolynomialData;
import logic.HammingController;
import logic.LengthRestrictedDocument;
import logic.ParityControllerPositive;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;

public class MainWindow extends JFrame{
    int selectedRadioButtonTag = -1;
    String polynomial = "";
    String przesylanyCiag = "";    
    //--------------------------------------------------------------------------------------------------------
    public MainWindow(){  //OPERACJE INICJALIZACJI OKNA
        super("Detekcja Błędów");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container okno = getContentPane();
        SpringLayout layoutSprezynowy = new SpringLayout();
        okno.setLayout(layoutSprezynowy);
    //--------------------------------------------------------------------------------------------------------
    ArrayList<JLabel> listOfLabels = new ArrayList<>();
    JLabel etykietaWyslanyZnak = new JLabel("Wysłany znak: ");listOfLabels.add(etykietaWyslanyZnak);     
    JLabel etykietaWyslanyBinarny = new JLabel("Wysłany Binarny:");listOfLabels.add(etykietaWyslanyBinarny);
    JLabel etykietaBitParzystosci = new JLabel("Bit Parzystości:");listOfLabels.add(etykietaBitParzystosci);    
    JLabel etykietaBityHaminga = new JLabel("Bity Haminga:");listOfLabels.add(etykietaBityHaminga);    
    JLabel etykietaWielomianCRC16 = new JLabel("Wielomian CRC16:");listOfLabels.add(etykietaWielomianCRC16);    
    JLabel etykietaWielomianCRC32 = new JLabel("Wielomian CRC32:");listOfLabels.add(etykietaWielomianCRC32);    
    JLabel etykietaWielomianCRCITU = new JLabel("Wielomian CRC-ITU:");listOfLabels.add(etykietaWielomianCRCITU);    
    JLabel etykietaWielomianSDLC = new JLabel("Wielomian SDLC:");listOfLabels.add(etykietaWielomianSDLC);    
    JLabel etykietaPrzesylanyCiag = new JLabel("Przesyłany Ciąg:");listOfLabels.add(etykietaPrzesylanyCiag);    
    JLabel etykietaDoZaklocenia = new JLabel("Do Zakłócenia:");listOfLabels.add(etykietaDoZaklocenia);    
    JLabel etykietaOdebranyCiag = new JLabel("Odebrany Ciąg:");listOfLabels.add(etykietaOdebranyCiag);    
    JLabel etykietaWykryteBledy = new JLabel("Wykryte Błędy:");listOfLabels.add(etykietaWykryteBledy);   
    JLabel etykietaPoprawionyBinarny = new JLabel("Poprawiony Binarny:");listOfLabels.add(etykietaPoprawionyBinarny);    
    JLabel etykietaOdebranyCiagZnakow = new JLabel("Odebrany Znak:");listOfLabels.add(etykietaOdebranyCiagZnakow);      
    {int distanceFromNorthOfTheWindow = 10;
        for(JLabel element : listOfLabels){
            layoutSprezynowy.putConstraint(SpringLayout.WEST, element, 10, SpringLayout.WEST, okno);
            layoutSprezynowy.putConstraint(SpringLayout.NORTH, element, distanceFromNorthOfTheWindow, SpringLayout.NORTH, okno);
            okno.add(element);
            element.setPreferredSize(new Dimension(400, 20)); 

            distanceFromNorthOfTheWindow += 25;
        } 
    }
    //--------------------------------------------------------------------------------------------------------
    ArrayList<JTextField> listOfDataControlTextFields = new ArrayList();
    ArrayList<JTextField> listOfTextFields = new ArrayList();
    JTextField poleTekstoweWyslanyZnak = new JTextField();listOfTextFields.add(poleTekstoweWyslanyZnak); poleTekstoweWyslanyZnak.setText(null); poleTekstoweWyslanyZnak.setDocument(new LengthRestrictedDocument(1));
    JTextField poleTekstoweWyslanyBinarny = new JTextField();listOfTextFields.add(poleTekstoweWyslanyBinarny);  
    JTextField poleTekstoweBitParzystosci = new JTextField();listOfTextFields.add(poleTekstoweBitParzystosci);         listOfDataControlTextFields.add(poleTekstoweBitParzystosci);    
    JTextField poleTekstoweBityHaminga = new JTextField();listOfTextFields.add(poleTekstoweBityHaminga);               listOfDataControlTextFields.add(poleTekstoweBityHaminga);
    JTextField poleTekstoweWielomianCRC16 = new JTextField();listOfTextFields.add(poleTekstoweWielomianCRC16);         listOfDataControlTextFields.add(poleTekstoweWielomianCRC16);
    JTextField poleTekstoweWielomianCRC32 = new JTextField();listOfTextFields.add(poleTekstoweWielomianCRC32);         listOfDataControlTextFields.add(poleTekstoweWielomianCRC32);
    JTextField poleTekstoweWielomianCRCITU = new JTextField();listOfTextFields.add(poleTekstoweWielomianCRCITU);       listOfDataControlTextFields.add(poleTekstoweWielomianCRCITU);
    JTextField poleTekstoweWielomianSDLC = new JTextField();listOfTextFields.add(poleTekstoweWielomianSDLC );          listOfDataControlTextFields.add(poleTekstoweWielomianSDLC);
    JTextField poleTekstowePrzesylanyCiag = new JTextField();listOfTextFields.add(poleTekstowePrzesylanyCiag); 
    JTextField poleTekstoweDoZaklocenia = new JTextField();listOfTextFields.add(poleTekstoweDoZaklocenia);             //BinaryDigitsInputVerifier binInputVerifier = new BinaryDigitsInputVerifier(); poleTekstoweDoZaklocenia.setInputVerifier(binInputVerifier);
    JTextField poleTekstoweOdebranyCiag = new JTextField();listOfTextFields.add(poleTekstoweOdebranyCiag); 
    JTextField poleTekstoweWykryteBledy = new JTextField();listOfTextFields.add(poleTekstoweWykryteBledy);   
    JTextField poleTekstowePoprawionyBinarny = new JTextField();listOfTextFields.add(poleTekstowePoprawionyBinarny); 
    JTextField poleTekstoweOdebranyZnak = new JTextField(); listOfTextFields.add(poleTekstoweOdebranyZnak);  
    {int distanceFromNorthOfTheWindow = 10;
        for(JTextField element : listOfTextFields){
            layoutSprezynowy.putConstraint(SpringLayout.WEST, element, 150, SpringLayout.WEST, okno);
            layoutSprezynowy.putConstraint(SpringLayout.NORTH, element, distanceFromNorthOfTheWindow, SpringLayout.NORTH, okno);
            okno.add(element);
            element.setPreferredSize(new Dimension(400, 20)); 
            element.setEditable(false);

            distanceFromNorthOfTheWindow += 25;
        }
    }
    //--------------------------------------------------------------------------------------------------------
    ArrayList<JRadioButton> listOfJRadioButtons = new ArrayList();
    ButtonGroup grupaRadiowa = new ButtonGroup();
    for(Integer i = 0;  i<6; i++){
        JRadioButton roboczyRadioButton = new JRadioButton();
        grupaRadiowa.add(roboczyRadioButton);
        listOfJRadioButtons.add(roboczyRadioButton);
        
        layoutSprezynowy.putConstraint(SpringLayout.EAST, roboczyRadioButton, -5, SpringLayout.WEST, listOfDataControlTextFields.get(i) );
        layoutSprezynowy.putConstraint(SpringLayout.NORTH, roboczyRadioButton, 0, SpringLayout.NORTH, listOfDataControlTextFields.get(i) );
        
        roboczyRadioButton.setName(i.toString());
        roboczyRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Integer textFieldIndex = Integer.parseInt(roboczyRadioButton.getName() );
                    JTextField fieldToChangeState = (JTextField)listOfDataControlTextFields.get(textFieldIndex);
                if(roboczyRadioButton.isSelected())
                    fieldToChangeState.setEditable(true);//Każde z tych pol nasluchuje zmiany stanu na "editable".
                                                         //Taka zmiana stanu wywoluje skrypt wygenerowania danych i wypełnienia nimi pola//mergetest
                else{
                   //fieldToChangeState.setEditable(false);  //TODO obecnie zbędne zabezpieczenie, rozważyć usunięcie linijki
                    fieldToChangeState.setText("");
                }
                poleTekstoweOdebranyCiag.setText("");
                poleTekstoweWykryteBledy.setText("");
                poleTekstowePoprawionyBinarny.setText("");
                poleTekstoweOdebranyZnak.setText("");
            }
        });
        if(i<2)
        roboczyRadioButton.setEnabled(false);
        okno.add(roboczyRadioButton);
    }    
    //--------------------------------------------------------------------------------------------------------
    poleTekstoweWyslanyZnak.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            for(int i = 2; i<listOfTextFields.size(); i++)
                listOfTextFields.get(i).setText("");
            poleTekstoweDoZaklocenia.setEditable(false);
            grupaRadiowa.clearSelection();
            
            String inString = poleTekstoweWyslanyZnak.getText();
            String binaryString = "";
            binaryString = ParityControllerPositive.convertStringToBinaryString(inString);                                
            poleTekstoweWyslanyBinarny.setText(binaryString);
            
            if(! poleTekstoweWyslanyZnak.getText().isEmpty()){
                int i = 0;
                for(; i<2; i++)
                    listOfJRadioButtons.get(i).setEnabled(true);         
                for(; i<6; i++)
                    listOfJRadioButtons.get(i).setEnabled(false); 
            }
            else{
                int i = 0;
                for(; i<2; i++)
                    listOfJRadioButtons.get(i).setEnabled(false);         
                for(; i<6; i++)
                    listOfJRadioButtons.get(i).setEnabled(true); 
            }
        }
    });
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweDoZaklocenia.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String receivedString = poleTekstoweDoZaklocenia.getText();
            boolean poprawne = isStringBinaryDigitsOnly(receivedString);
            if(poprawne){
                poleTekstoweOdebranyCiag.setText( receivedString );

                boolean computingPolynomial = false;
                String polynomial = "";
                PolynomialData data;
                PolynomialData checksum = null;
                if(selectedRadioButtonTag>=2)
                    computingPolynomial = true;

                switch (selectedRadioButtonTag){
                    case 0:
                        try{
                            poleTekstoweOdebranyZnak.setText("");
                        ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString); 
                        String text = (new StringBuilder().append(ParityControllerPositive.convertParityProtectedBinaryStringToChar(receivedString))).toString();
                        poleTekstoweOdebranyZnak.setText( text );
                        poleTekstoweWykryteBledy.setText("");
                        }catch(Exception e){poleTekstoweWykryteBledy.setText(e.getMessage());}
                        break;
                    case 1:
                        if( HammingController.isHammingProtectedWordCorrect(receivedString) ){
                            String hammingBitsDeletedWord = HammingController.deleteHammingBitsFromWord(receivedString);
                            Integer integerChar = Integer.parseInt(hammingBitsDeletedWord, 2);
                            char convertedChar = (char) integerChar.intValue();
                            StringBuilder finalChar = new StringBuilder("");
                            finalChar.append(convertedChar);
                            poleTekstoweOdebranyZnak.setText(finalChar.toString());
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


                            //wykryte bledy = jakie 
                            //jesli pojedynczy blad, 
                                                        //ktory bit zostal poprawiony
                                                        //wtedy poprawiony binarny = poprawiony binarny
                                                        //odebrany znak = konwersja
                            //jesli wiecej niz jeden blad to
                                                        //wykryte bledy = wiecej niz jeden bit przeklamany, powtorz przesylanie
                                                        //poprawiony binarny = null
                                                        //odebrany znak = null
                        }
                        break;
                    case 2:
                        polynomial = "11000000000000101";//crc16
                        data = new PolynomialData(przesylanyCiag,1,13,2,1);//crc16
                        checksum = new PolynomialData(data.getParts());
                        break;
                    case 3:
                        polynomial = "100000100110000010001110110110111";//crc32
                        data = new PolynomialData(przesylanyCiag,6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
                        checksum = new PolynomialData(data.getParts());
                        break;
                    case 4:
                        polynomial = "10001000000100001"; //crcItu
                        data = new PolynomialData(przesylanyCiag,4,7,4,1,1);
                        checksum = new PolynomialData(data.getParts());
                        break;
                    case 5:
                        polynomial = "10001000000100101"; //SDLC
                        data = new PolynomialData(przesylanyCiag,4,7,4,1,1);
                        checksum = new PolynomialData(data.getParts());
                        break;

                }

                if(computingPolynomial){
                    checksum.computeData(polynomial);
                    String receivedData = poleTekstoweDoZaklocenia.getText();
                    poleTekstoweOdebranyCiag.setText( receivedData );

                    int[] polynomialFormat;
                    switch (polynomial){
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

                    PolynomialData dataToCheck = new PolynomialData(receivedData,polynomialFormat);
                    dataToCheck.computeData(checksum.returnAsString());
                    String validationMessage = "";
                    if(dataToCheck.returnAsString().equals(polynomial))
                        validationMessage = " - Dane prawidłowe";
                    else
                        validationMessage = " - Dane nie prawidłowe";
                    poleTekstoweWykryteBledy.setText(dataToCheck.returnAsString()+ "" +validationMessage);
                }
            }    
        }
//--------------------------------------------------------------------------------------------------------
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
        
    });
//--------------------------------------------------------------------------------------------------------
    poleTekstoweBitParzystosci.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 0;
                poleTekstoweBitParzystosci.setText(ParityControllerPositive.calculateParityBitForBinaryString(poleTekstoweWyslanyBinarny.getText()) );
                poleTekstowePrzesylanyCiag.setText(ParityControllerPositive.convertStringToParityProtectedBinaryString(poleTekstoweWyslanyZnak.getText()));
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(poleTekstowePrzesylanyCiag.getText());
                poleTekstoweBitParzystosci.setEditable(false);
            }
        });
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweBityHaminga.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 1;
                poleTekstowePrzesylanyCiag.setText(HammingController.addHammingControlToString(poleTekstoweWyslanyBinarny.getText()));
                poleTekstoweBityHaminga.setText( HammingController.getParityBitsFromHammingWord(poleTekstowePrzesylanyCiag.getText()) );
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(poleTekstowePrzesylanyCiag.getText());
                poleTekstoweBityHaminga.setEditable(false);
            }
        });
//--------------------------------------------------------------------------------------------------------
    poleTekstoweWielomianCRC16.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 2;
                PolynomialData data2 = new PolynomialData(1,13,2,1);
                //poleTekstowePrzesylanyCiag.setText(data.returnAsString());
                przesylanyCiag = data2.returnAsString();
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(data2.returnAsString());
                poleTekstoweWielomianCRC16.setText("11000000000000101");
                poleTekstoweWielomianCRC16.setEditable(false);
            }
        });
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianCRC32.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 3;
                PolynomialData data2 = new PolynomialData(6,3,1,6,4,1,1,2,1,2,1,2,1,1,1);
                //poleTekstowePrzesylanyCiag.setText(data.returnAsString());
                przesylanyCiag = data2.returnAsString();
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(data2.returnAsString());
                poleTekstoweWielomianCRC32.setText("100000100110000010001110110110111");
                poleTekstoweWielomianCRC32.setEditable(false);
            }
        });
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianCRCITU.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 4;
                PolynomialData data2 = new PolynomialData(4,7,4,1,1);
                //poleTekstowePrzesylanyCiag.setText(data.returnAsString());
                przesylanyCiag = data2.returnAsString();
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(data2.returnAsString());
                poleTekstoweWielomianCRCITU.setText("10001000000100001");
                poleTekstoweWielomianCRCITU.setEditable(false);
            }
        });
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianSDLC.addPropertyChangeListener("editable", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedRadioButtonTag = 5;
                PolynomialData data2 = new PolynomialData(4,7,4,1,1);
                //poleTekstowePrzesylanyCiag.setText(data.returnAsString());
                przesylanyCiag = data2.returnAsString();
                poleTekstoweDoZaklocenia.setEditable(true);
                poleTekstoweDoZaklocenia.setText(data2.returnAsString());
                poleTekstoweWielomianSDLC.setText("10001000000100101");
                poleTekstoweWielomianSDLC.setEditable(false);
            }
        });            
    //--------------------------------------------------------------------------------------------------------     
    poleTekstoweWyslanyZnak.setEditable(true);
    this.setSize(600, 500);
    }
}
