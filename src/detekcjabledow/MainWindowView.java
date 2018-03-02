package detekcjabledow;

import businesslogic.LengthRestrictedDocument;

import java.awt.*;
import java.util.*;
import javax.swing.*;

class MainWindowView extends JFrame{
    private MainWindowController controller;
//--------------------------------------------------------------------------------------------------------
MainWindowView(){  //OPERACJE INICJALIZACJI OKNA
    super("Detekcja Błędów");
    Container contentPane = getContentPane();
    contentPane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2,3,2,3);
//--------------------------------------------------------------------------------------------------------
    ArrayList<JLabel> listOfLabels = new ArrayList<>(); //DODAWANIE ETYKIET
    JLabel labelCharToSend = new JLabel("Wysłany znak: ");listOfLabels.add(labelCharToSend);
    JLabel labelCharToSendInBinaryAscii = new JLabel("Wysłany Binarny:");listOfLabels.add(labelCharToSendInBinaryAscii);
    JLabel labelParityBit = new JLabel("Bit Parzystości:");listOfLabels.add(labelParityBit);
    JLabel labelHammingsBits = new JLabel("Bity Haminga:");listOfLabels.add(labelHammingsBits);
    JLabel labelPolynomialCrc16 = new JLabel("Wielomian CRC16:");listOfLabels.add(labelPolynomialCrc16);
    JLabel labelPolynomialCrc32 = new JLabel("Wielomian CRC32:");listOfLabels.add(labelPolynomialCrc32);
    JLabel labelPolynomialCrcItu = new JLabel("Wielomian CRC-ITU:");listOfLabels.add(labelPolynomialCrcItu);
    JLabel labelPolynomialSdlc = new JLabel("Wielomian SDLC:");listOfLabels.add(labelPolynomialSdlc);
    JLabel labelSendingSequence = new JLabel("Przesyłany Ciąg:");listOfLabels.add(labelSendingSequence);
    JLabel labelSequenceToDisrupt = new JLabel("Do Zakłócenia:");listOfLabels.add(labelSequenceToDisrupt);
    JLabel labelReceivedSequence = new JLabel("Odebrany Ciąg:");listOfLabels.add(labelReceivedSequence);
    JLabel labelDetectedErrors = new JLabel("Wykryte Błędy:");listOfLabels.add(labelDetectedErrors);
    JLabel labelCorrectedBinary = new JLabel("Poprawiony Binarny:");listOfLabels.add(labelCorrectedBinary);
    JLabel labelReceivedChar = new JLabel("Odebrany Znak:");listOfLabels.add(labelReceivedChar);

    c.fill = GridBagConstraints.HORIZONTAL;
    int index = 0;
    for(JLabel element : listOfLabels){
        if(index==0)
            c.anchor = GridBagConstraints.WEST;
        c.gridy = index++;
        contentPane.add(element,c);
    }
//--------------------------------------------------------------------------------------------------------
    ArrayList<JTextField> listOfDataControlTextFields = new ArrayList();//DODAWANIE PÓL TEKSTOWYCH
    ArrayList<JTextField> listOfTextFields = new ArrayList();
    JTextField textFieldCharToSend = new JTextField();listOfTextFields.add(textFieldCharToSend); textFieldCharToSend.setText(null); textFieldCharToSend.setDocument(new LengthRestrictedDocument(1));
    JTextField textFieldCharToSendInBinaryAscii = new JTextField();listOfTextFields.add(textFieldCharToSendInBinaryAscii);
    JTextField textFieldParityBit = new JTextField();listOfTextFields.add(textFieldParityBit);         listOfDataControlTextFields.add(textFieldParityBit);
    JTextField textFieldHammingsBits = new JTextField();listOfTextFields.add(textFieldHammingsBits);               listOfDataControlTextFields.add(textFieldHammingsBits);
    JTextField textFieldPolynomialCrc16 = new JTextField();listOfTextFields.add(textFieldPolynomialCrc16);         listOfDataControlTextFields.add(textFieldPolynomialCrc16);
    JTextField textFieldPolynomialCrc32 = new JTextField();listOfTextFields.add(textFieldPolynomialCrc32);         listOfDataControlTextFields.add(textFieldPolynomialCrc32);
    JTextField textFieldPolynomialCrcItu = new JTextField();listOfTextFields.add(textFieldPolynomialCrcItu);       listOfDataControlTextFields.add(textFieldPolynomialCrcItu);
    JTextField textFieldPolynomialSdlc = new JTextField();listOfTextFields.add(textFieldPolynomialSdlc );          listOfDataControlTextFields.add(textFieldPolynomialSdlc);
    JTextField textFieldSendingSequence = new JTextField();listOfTextFields.add(textFieldSendingSequence);
    JTextField textFieldSequenceToDisrupt = new JTextField();listOfTextFields.add(textFieldSequenceToDisrupt);
    JTextField textFieldReceivedSequence = new JTextField();listOfTextFields.add(textFieldReceivedSequence);
    JTextField textFieldDetectedErrors = new JTextField();listOfTextFields.add(textFieldDetectedErrors);
    JTextField textFieldCorrectedBinary = new JTextField();listOfTextFields.add(textFieldCorrectedBinary);
    JTextField textFieldReceivedChar = new JTextField(); listOfTextFields.add(textFieldReceivedChar);

    index = 0;
    c.gridx = 2;
    c.weightx = 1;
    for(JTextField element : listOfTextFields){
        c.gridy = index++;
        contentPane.add(element,c);
        element.setPreferredSize(new Dimension(400, 20));
        element.setEditable(false);
    }
//--------------------------------------------------------------------------------------------------------
    ArrayList<JRadioButton> listOfJRadioButtons = new ArrayList();//DODAWANIE RADIO BUTTONOW....
    ButtonGroup radioButtonsGroup = new ButtonGroup();
    index = 2;
    c.gridx = 1;
    c.weightx = 0;
    for(Integer i = 0;  i<6; i++){
        JRadioButton temporaryRadioButton = new JRadioButton();
        radioButtonsGroup.add(temporaryRadioButton);
        listOfJRadioButtons.add(temporaryRadioButton);
        temporaryRadioButton.setName(i.toString());
        if(i<2) {
            temporaryRadioButton.setEnabled(false);
        }
        c.gridy = index++;
        contentPane.add(temporaryRadioButton,c);
    }

controller = new MainWindowController(listOfTextFields, radioButtonsGroup, listOfJRadioButtons, contentPane);

    for(JRadioButton radioButton : listOfJRadioButtons){
        radioButton.addItemListener(e -> controller.radioButtonItemStateChanged(radioButton));
    }
//--------------------------------------------------------------------------------------------------------
    textFieldCharToSend.addActionListener(ae -> controller.textFieldCharToSendActionPerformed());
//--------------------------------------------------------------------------------------------------------    
    textFieldParityBit.addPropertyChangeListener("editable", evt -> controller.textFieldParityBitPropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------    
    textFieldHammingsBits.addPropertyChangeListener("editable", evt -> controller.textFieldHammingsBitsPropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------
    textFieldPolynomialCrc16.addPropertyChangeListener("editable", evt -> controller.textFieldPolynomialCrc16PropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------    
    textFieldPolynomialCrc32.addPropertyChangeListener("editable", evt -> controller.textFieldPolynomialCrc32PropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------    
    textFieldPolynomialCrcItu.addPropertyChangeListener("editable", evt -> controller.textFieldPolynomialCrcItuPropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------    
    textFieldPolynomialSdlc.addPropertyChangeListener("editable", evt -> controller.textFieldPolynomialSdlcPropertyEditableChanged());
//--------------------------------------------------------------------------------------------------------    
//-------------------------------------------------------------------------------------------------------- 
//-------------------------------------------------------------------------------------------------------- 
    //DO TEGO POLA PRZYPISANA JEST GŁÓWNA LOGIKA APLIKACJI
    textFieldSequenceToDisrupt.addActionListener(ae -> controller.textFieldSequenceToDisruptActionPerformed());
//--------------------------------------------------------------------------------------------------------

    JLabel bottomStretchBlocker = new JLabel();
    c.weighty = 1;
    c.weightx = 0;
    c.gridy = 14;
    c.gridx=0;
    c.fill = GridBagConstraints.NONE;
    contentPane.add(bottomStretchBlocker, c);

    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //this.setResizable(false);
    textFieldCharToSend.setEditable(true);
    this.setSize(600, 430);
    this.setLocationRelativeTo(null);

    }

}
