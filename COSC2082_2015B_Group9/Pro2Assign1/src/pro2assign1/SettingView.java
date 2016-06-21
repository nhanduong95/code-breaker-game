package pro2assign1;
import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.io.*;

public class SettingView extends JFrame {
    private JPanel settingHeading = new JPanel();
    private JPanel settingBody = new JPanel();
    
    private GridBagConstraints headingConstraints = new GridBagConstraints();
    private GridBagConstraints bodyConstraints = new GridBagConstraints();
    private GridBagConstraints frameConstraints = new GridBagConstraints();
    
    private JComboBox<String> iconSetList = new JComboBox<String>();
    private String [] iconSetNames = {"Stuff Icon Set", "Office Icon Set", "Food Icon Set"};
    
    private JButton musicOn = new JButton("On");
    private JButton musicOff = new JButton("Off");
    
    private File musicFile = new File("game-music.wav");
    private AudioClip music;
    private int timeGiven;
    
    private void fillIconSetList(JComboBox<String> combo, String[] items){
        for(String item : items)
            combo.addItem(item);
    }
    int getTimeGiven(){
        return timeGiven;
    }
    void setTimeGiven(int newTimeGiven){
        timeGiven = newTimeGiven;
    }
    AudioClip getAudioClip(){
        return music;
    }
    JComboBox getIconSetList(){
        return iconSetList;
    }
    JButton getMusicOn(){
        return musicOn;
    }
    JButton getMusicOff(){
        return musicOff;
    }
    SettingView(){
        //Play music as default
        try{
            music = Applet.newAudioClip(musicFile.toURL());
        }
        catch(Exception e){
            System.out.print("Music File is not found.");
        }
        music.play();
        
        this.setTitle("Code Breaker Game Setting");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //Font setting
        Font settingFont = new Font("Serif", Font.BOLD, 14);
        iconSetList.setFont(settingFont);
        musicOn.setFont(settingFont);
        musicOff.setFont(settingFont);
        
        //JLabel color setting
        Color settingBackgroundColor = new Color(247, 235, 217);
        iconSetList.setBackground(settingBackgroundColor);
        musicOn.setBackground(settingBackgroundColor);
        musicOff.setBackground(settingBackgroundColor);
        
        //JPannel color setting
        Color panelBackgroundColor = new Color(247,222,183);
        settingHeading.setBackground(panelBackgroundColor);
        settingBody.setBackground(panelBackgroundColor);
        this.getContentPane().setBackground(new Color(84,57,57));
        
        //Adding item to setingHeading JPanel
        settingHeading.setLayout(new GridBagLayout());
        headingConstraints.fill = GridBagConstraints.HORIZONTAL;
        headingConstraints.insets = new Insets(30, 40, 30, 40);
        settingHeading.add(new JLabel(new ImageIcon("images/setting-label.png")), headingConstraints);
        
        //Adding items to settingBody JPanel
        settingBody.setLayout(new GridBagLayout());
        bodyConstraints.insets = new Insets(50,30,10,30);
        bodyConstraints.anchor = GridBagConstraints.EAST;
        settingBody.add(new JLabel(new ImageIcon("images/icon-selection-label.png")), bodyConstraints);
        
        this.fillIconSetList(iconSetList, iconSetNames);
        bodyConstraints.gridx = 1;
        bodyConstraints.gridwidth = 2;
        bodyConstraints.insets = new Insets(50,0,10,20);
        bodyConstraints.anchor = GridBagConstraints.WEST;
        settingBody.add(iconSetList, bodyConstraints);
        
        bodyConstraints.gridx = 0;
        bodyConstraints.gridy = 1;
        bodyConstraints.insets = new Insets(0,20,40,50);
        bodyConstraints.anchor = GridBagConstraints.EAST;
        settingBody.add(new JLabel(new ImageIcon("images/music-label.png")), bodyConstraints);
        
        bodyConstraints.gridx = 1;
        bodyConstraints.insets = new Insets(0,0,40,10);
        bodyConstraints.anchor = GridBagConstraints.WEST;
        settingBody.add(musicOn, bodyConstraints);
        
        bodyConstraints.gridx = 2;
        bodyConstraints.insets = new Insets(0,0,40,20);
        bodyConstraints.anchor = GridBagConstraints.CENTER;
        settingBody.add(musicOff, bodyConstraints);
        
        //Adding elements to the main frame
        this.setLayout(new GridBagLayout());
        frameConstraints.insets = new Insets(5,5,10,5);
        this.add(settingHeading, frameConstraints);
        
        frameConstraints.gridy = 1;
        frameConstraints.insets = new Insets(0,5,5,5);
        this.add(settingBody, frameConstraints);
        this.pack();   
    }
}
