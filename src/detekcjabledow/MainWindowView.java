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
    Container okno = getContentPane();
    okno.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2,3,2,3);
//--------------------------------------------------------------------------------------------------------
    ArrayList<JLabel> listOfLabels = new ArrayList<>(); //DODAWANIE ETYKIET
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

    c.fill = GridBagConstraints.HORIZONTAL;
    int index = 0;
    for(JLabel element : listOfLabels){
        if(index==0)
            c.anchor = GridBagConstraints.WEST;
        c.gridy = index++;
        okno.add(element,c);
    }
//--------------------------------------------------------------------------------------------------------
    ArrayList<JTextField> listOfDataControlTextFields = new ArrayList();//DODAWANIE PÓL TEKSTOWYCH
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
    JTextField poleTekstoweDoZaklocenia = new JTextField();listOfTextFields.add(poleTekstoweDoZaklocenia);
    JTextField poleTekstoweOdebranyCiag = new JTextField();listOfTextFields.add(poleTekstoweOdebranyCiag);
    JTextField poleTekstoweWykryteBledy = new JTextField();listOfTextFields.add(poleTekstoweWykryteBledy);
    JTextField poleTekstowePoprawionyBinarny = new JTextField();listOfTextFields.add(poleTekstowePoprawionyBinarny);
    JTextField poleTekstoweOdebranyZnak = new JTextField(); listOfTextFields.add(poleTekstoweOdebranyZnak);

    index = 0;
    c.gridx = 2;
    c.weightx = 1;
    for(JTextField element : listOfTextFields){
        c.gridy = index++;
        okno.add(element,c);
        element.setPreferredSize(new Dimension(400, 20));
        element.setEditable(false);
    }
//--------------------------------------------------------------------------------------------------------
    ArrayList<JRadioButton> listOfJRadioButtons = new ArrayList();//DODAWANIE RADIO BUTTONOW....
    ButtonGroup grupaRadiowa = new ButtonGroup();
    index = 2;
    c.gridx = 1;
    c.weightx = 0;
    for(Integer i = 0;  i<6; i++){
        JRadioButton roboczyRadioButton = new JRadioButton();
        grupaRadiowa.add(roboczyRadioButton);
        listOfJRadioButtons.add(roboczyRadioButton);
        roboczyRadioButton.setName(i.toString());
        if(i<2) {
            roboczyRadioButton.setEnabled(false);
        }
        c.gridy = index++;
        okno.add(roboczyRadioButton,c);
    }

controller = new MainWindowController(listOfTextFields, grupaRadiowa, listOfJRadioButtons, okno);

    for(JRadioButton roboczy : listOfJRadioButtons){
        roboczy.addItemListener(e -> controller.radioButtonItemStateChanged(roboczy));
    }
//--------------------------------------------------------------------------------------------------------
    poleTekstoweWyslanyZnak.addActionListener(ae -> controller.wyslanyZnakEnterPressed());
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweBitParzystosci.addPropertyChangeListener("editable", evt -> controller.bitParzystosciSelected());
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweBityHaminga.addPropertyChangeListener("editable", evt -> controller.bityHammingaSelected());
//--------------------------------------------------------------------------------------------------------
    poleTekstoweWielomianCRC16.addPropertyChangeListener("editable", evt -> controller.wielomianCRC16Selected());
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianCRC32.addPropertyChangeListener("editable", evt -> controller.wielomianCRC32Selected());
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianCRCITU.addPropertyChangeListener("editable", evt -> controller.wielomianCRCITUSelected());
//--------------------------------------------------------------------------------------------------------    
    poleTekstoweWielomianSDLC.addPropertyChangeListener("editable", evt -> controller.wielomianSDLCSelected());
//--------------------------------------------------------------------------------------------------------    
//-------------------------------------------------------------------------------------------------------- 
//-------------------------------------------------------------------------------------------------------- 
    //DO TEGO POLA PRZYPISANA JEST GŁÓWNA LOGIKA APLIKACJI
    poleTekstoweDoZaklocenia.addActionListener(ae -> controller.doZakloceniaActionPerformed());
//--------------------------------------------------------------------------------------------------------

    JLabel bottomStretchBlocker = new JLabel();
    c.weighty = 1;
    c.weightx = 0;
    c.gridy = 14;
    c.gridx=0;
    c.fill = GridBagConstraints.NONE;
    okno.add(bottomStretchBlocker, c);

    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //this.setResizable(false);
    poleTekstoweWyslanyZnak.setEditable(true);
    this.setSize(600, 430);
    this.setLocationRelativeTo(null);

    }

}
