package pro2assign1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

class MainView extends JFrame{
    
    private JLabel newGameButtonLabel = new JLabel(new ImageIcon("images/new-game-button.png"));
    private JLabel giveUpButtonLabel = new JLabel(new ImageIcon("images/give-up-button.png"));
    private JLabel quitButtonLabel = new JLabel(new ImageIcon("images/quit-game-button.png"));
    private JLabel settingButtonLabel = new JLabel(new ImageIcon("images/setting-button.png"));
    private JLabel acceptButtonLabel = new JLabel(new ImageIcon("images/accept-button.png"));
    
    private JLabel gameNameAndInfoLabel = new JLabel(new ImageIcon("images/game-name.png"));
    private JLabel scoreLabel = new JLabel(new ImageIcon("images/score.png"));
    private JLabel scoreDisplayLabel = new JLabel("0");
    private JLabel attemptLabel = new JLabel(new ImageIcon("images/attempt.png"));
    private JLabel attemptDisplayLabel = new JLabel("0");
    private JLabel codeBreakerLabel = new JLabel(new ImageIcon("images/code-breaker-noti.png"));
    
    private JPanel controllingPanel01 = new JPanel(new GridBagLayout());
    private JPanel iconPanel = new JPanel();
    private JPanel ringPanel = new JPanel(new GridBagLayout());
    private JPanel guessStatusPanel = new JPanel(new GridBagLayout());
    private JPanel controllingPanel02 = new JPanel(new GridBagLayout());
    private JPanel gamePlayingCanvas = new JPanel(new BorderLayout(5,5));
    
    private GridBagConstraints frameConstraint = new GridBagConstraints ();
    private GridBagConstraints controlButtonConstraints01 = new GridBagConstraints ();
    private GridBagConstraints controlButtonConstraints02 = new GridBagConstraints ();
    private GridBagConstraints ringComponentConstraint = new GridBagConstraints ();
    private GridBagConstraints guessStatusConstraint = new GridBagConstraints ();
    
    private int currentIconHolder = 0;
    private String iconSetDirectory = "icon-set-01";
    
    private ArrayList<JLabel> iconCollection = new ArrayList<>();
    private ArrayList<JLabel> iconHolderCollection = new ArrayList<>();
    private ArrayList<JLabel> iconAnswerCollection = new ArrayList<>();
    private ArrayList<JLabel> guessStatusCollection = new ArrayList<>();
    
    /*Getters and Setters for GameView primitive data*/
    int getCurrentIconHolder(){
        return currentIconHolder;
    }
    void setCurrentIconHolder(int currentIconHolder){
        this.currentIconHolder = currentIconHolder;
    }
    void setNextIconHolder(){
        if(currentIconHolder == 5)
            currentIconHolder = 0;
        else
            currentIconHolder++;
    }
    void setIconSetDirectory(String newIconDirectory){
        iconSetDirectory = newIconDirectory;
    }
    String getIconSetDirectory(){
        return iconSetDirectory;
    }
    
    /*Getters and Setters for GUI Components*/
    ArrayList getIconCollection(){
        return iconCollection;
    }
    ArrayList getIconHolderCollection(){
        return iconHolderCollection;
    }
    ArrayList getGuessStatusCollection(){
        return guessStatusCollection;
    }
    void setGuessStatusCollection(ArrayList <JLabel> guessStatusCollection){
        this.guessStatusCollection = guessStatusCollection;
    }
    ArrayList getIconAnswerCollection(){
        return iconAnswerCollection;
    }
    void setIconAnswerCollection(ArrayList <JLabel> iconAnswerKeyCollection){
        this.iconAnswerCollection = iconAnswerKeyCollection;
    }
    JLabel getNewGameButtonLabel(){
        return newGameButtonLabel;
    }
    JLabel getGiveUpButtonLabel(){
        return giveUpButtonLabel;
    }
    JLabel getQuitButtonLabel(){
        return quitButtonLabel;
    }
    JLabel getSettingButtonLabel(){
        return settingButtonLabel;
    }
    JLabel getAcceptButtonLabel(){
        return acceptButtonLabel;
    }
    JLabel getCodeBreakerLabel(){
        return codeBreakerLabel;
    }
    JLabel getScoreDisplayLabel (){
        return scoreDisplayLabel;
    }
    JLabel getAttemptDisplayLabel (){
        return attemptDisplayLabel;
    }
    
    /*Other methods*/
    void fillIconHolderCollection(){
        for(int i = 0; i < 6; i++)
            iconHolderCollection.add(new JLabel(new ImageIcon("images/an-icon-holder.png")));
    }
    void setCurrentIconHolderImage(){
        for(int i = 0; i < 6; i++){
            if(currentIconHolder == i)
                iconHolderCollection.get(i).setBorder(new CustomBorderType1());
        }
    }
    void clearPreviousIconHoleImage(){
        for(int i = 0; i < 6; i++){
            if(currentIconHolder == 0)
                iconHolderCollection.get(5).setBorder(null);
            else if(currentIconHolder - 1 == i)
                iconHolderCollection.get(i).setBorder(null);
        }
    }
    void fillIconCollection(){
        for(int i = 0; i < 8; i++){
            String iconImageUrl = "images/" + iconSetDirectory + "/" + i + ".png";
            iconCollection.add(new JLabel(new ImageIcon(iconImageUrl)));
            iconPanel.add(iconCollection.get(i));
        }
    }
    void fillGuessStatusCollection(){
        for(int i = 0; i < 6; i++){
            guessStatusCollection.add(new JLabel(new ImageIcon("images/guess-status-none.png")));
        }
    }
    void fillIconAnswerCollection(){
        for(int i = 0; i < 6; i++){
            iconAnswerCollection.add(new JLabel(new ImageIcon("images/icon-answer-null.png")));
        }
    }
    MainView(){
        this.setTitle("Code Breaker Game");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Foreground setting for JLabels
        Color gameFontColor = new Color(77,42,55);
        scoreDisplayLabel.setForeground(gameFontColor);
        attemptDisplayLabel.setForeground(gameFontColor);
        
        //Font setting for JLabels
        Font gameFont = new Font("Serif",  Font.BOLD, 20);
        scoreDisplayLabel.setFont(gameFont);
        attemptDisplayLabel.setFont(gameFont);
        
        //Background setting for pannels and the main frame
        Color panelBackgroundColor = new Color(247,222,183);
        guessStatusPanel.setBackground(panelBackgroundColor);
        ringPanel.setBackground(panelBackgroundColor);
        controllingPanel01.setBackground(panelBackgroundColor);
        controllingPanel02.setBackground(panelBackgroundColor);
        iconPanel.setBackground(panelBackgroundColor);
        gamePlayingCanvas.setBackground(new Color(84,57,57));
        this.getContentPane().setBackground(new Color(84,57,57));
        
        //Size setting for scoreDisplayLabel and gamePlayingCanvas
        scoreDisplayLabel.setPreferredSize(new Dimension(60, 50));
        controllingPanel02.setPreferredSize(new Dimension(100,83));
        gamePlayingCanvas.setPreferredSize(new Dimension(750, 620));
        
        //Position setting for newGameButtonLabel
        controlButtonConstraints01.gridx = 0;
        controlButtonConstraints01.gridy = 0;
        controlButtonConstraints01.insets = new Insets(10,20,10,0);
        controlButtonConstraints01.anchor = GridBagConstraints.SOUTHWEST;
        newGameButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        controllingPanel01.add(newGameButtonLabel, controlButtonConstraints01);
        
        //Position setting for quitButtonLabel
        controlButtonConstraints01.gridx = 1;
        controlButtonConstraints01.insets = new Insets(10,7,10,20);
        controlButtonConstraints01.anchor = GridBagConstraints.SOUTHEAST;
        quitButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        controllingPanel01.add(quitButtonLabel, controlButtonConstraints01);
        
        //Position setting for giveUpButtonLabel
        controlButtonConstraints01.gridx = 0;
        controlButtonConstraints01.gridy = 1;
        controlButtonConstraints01.insets = new Insets(0,20,10,0);
        controlButtonConstraints01.anchor = GridBagConstraints.NORTHWEST;
        giveUpButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        controllingPanel01.add(giveUpButtonLabel, controlButtonConstraints01);
        
        //Position setting for settingButtonLabel
        controlButtonConstraints01.gridx = 1;
        controlButtonConstraints01.insets = new Insets(0,7,10,20);
        controlButtonConstraints01.anchor = GridBagConstraints.NORTHEAST;
        settingButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        controllingPanel01.add(settingButtonLabel, controlButtonConstraints01);
        
        //Adding items to iconPanel
        this.fillIconCollection();
        acceptButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconPanel.add(acceptButtonLabel);
        
        //Adding guess-status images to guessStatusPanel
        this.fillGuessStatusCollection();
        for(int i = 0; i < 6; i++){
            guessStatusConstraint.gridx = i;
            guessStatusConstraint.gridy = 0;
            guessStatusConstraint.insets = new Insets(10,20,10,20);
            guessStatusPanel.add((guessStatusCollection.get(i)), guessStatusConstraint);
        }
        
        //filling JLabel collections
        this.fillIconHolderCollection();
        this.fillIconAnswerCollection();
        this.setCurrentIconHolderImage();
                
        //Adding iconAnswer0 image to ringPanel
        ringComponentConstraint.gridx = 3;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(20,10,0,0);
        ringPanel.add(iconAnswerCollection.get(0), ringComponentConstraint);
        
        //Adding iconHole0 image to ringPanel
        ringComponentConstraint.gridx = 2;
        ringComponentConstraint.gridy = 0;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(20,100,0,0);
        ringPanel.add(iconHolderCollection.get(0), ringComponentConstraint);
        
        
        //Adding iconHole1 image to ringPanel
        ringComponentConstraint.gridx = 5;
        ringComponentConstraint.gridy = 1;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(45,10,0,0);
        ringPanel.add(iconHolderCollection.get(1), ringComponentConstraint);
        
        //Adding iconAnswer1 image to ringPanel
        ringComponentConstraint.gridx = 4;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(45,40,0,0);
        ringPanel.add(iconAnswerCollection.get(1), ringComponentConstraint);
        
        //Adding iconHole2 image to ringPanel
        ringComponentConstraint.gridx = 5;
        ringComponentConstraint.gridy = 2;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(70,10,0,20);
        ringPanel.add(iconHolderCollection.get(2), ringComponentConstraint);
        
        //Adding iconAnswer2 image to ringPanel
        ringComponentConstraint.gridx = 4;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(70,0,0,0);
        ringPanel.add(iconAnswerCollection.get(2), ringComponentConstraint);
        
        //Adding iconHole3 image to ringPanel
        ringComponentConstraint.gridx = 2;
        ringComponentConstraint.gridy = 3;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(40,100,20,0);
        ringPanel.add(iconHolderCollection.get(3), ringComponentConstraint);
        
        //Adding iconAnswer3 image to ringPanel
        ringComponentConstraint.gridx = 3;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(40,10,20,0);
        ringPanel.add(iconAnswerCollection.get(3), ringComponentConstraint);
        
        //Adding iconHole4 image to ringPanel
        ringComponentConstraint.gridx = 0;
        ringComponentConstraint.gridy = 2;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(65,20,0,0);
        ringPanel.add(iconHolderCollection.get(4), ringComponentConstraint);
        
        //Adding iconAnswer4 image to ringPanel
        ringComponentConstraint.gridx = 1;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(65,10,0,0);
        ringPanel.add(iconAnswerCollection.get(4), ringComponentConstraint);
        
        //Adding iconHole5 image to ringPanel
        ringComponentConstraint.gridx = 0;
        ringComponentConstraint.gridy = 1;
        ringComponentConstraint.anchor = GridBagConstraints.EAST;
        ringComponentConstraint.insets = new Insets(45,20,0,0);
        ringPanel.add(iconHolderCollection.get(5), ringComponentConstraint);
        
        //Adding iconAnswer5 image to ringPanel
        ringComponentConstraint.gridx = 1;
        ringComponentConstraint.anchor = GridBagConstraints.WEST;
        ringComponentConstraint.insets = new Insets(45,10,0,0);
        ringPanel.add(iconAnswerCollection.get(5), ringComponentConstraint);
        
        //Adding codeBreakerLabel to ringPanel 
        ringComponentConstraint.gridx = 2;
        ringComponentConstraint.gridy = 1;
        ringComponentConstraint.gridwidth = 2;
        ringComponentConstraint.gridheight = 2;
        ringPanel.add(codeBreakerLabel, ringComponentConstraint);
        
        codeBreakerLabel.setVisible(false);
        
        //Layout Setting in gamePlayingCanvas
        gamePlayingCanvas.add(iconPanel, BorderLayout.SOUTH);
        gamePlayingCanvas.add(ringPanel, BorderLayout.CENTER);
        gamePlayingCanvas.add(guessStatusPanel, BorderLayout.NORTH);
        
        //Adding scoreLabel to controllingPanel02
        controlButtonConstraints02.gridx = 0;
        controlButtonConstraints02.gridy = 0;
        controlButtonConstraints02.anchor = GridBagConstraints.WEST;
        controlButtonConstraints02.insets = new Insets(10,0,10,10);
        controllingPanel02.add(scoreLabel, controlButtonConstraints02);
        
        //Adding scoreDisplayLabel to controllingPanel02
        controlButtonConstraints02.gridx = 1;
        controlButtonConstraints02.insets = new Insets(10,0,10,10);
        controllingPanel02.add(scoreDisplayLabel, controlButtonConstraints02);
        
        //Adding attemptLabel to controllingPanel02
        controlButtonConstraints02.gridx = 2;
        controlButtonConstraints02.insets = new Insets(10,0,10,10);
        controllingPanel02.add(attemptLabel, controlButtonConstraints02);
        
        //Adding attemptDisplayLabel to controllingPanel02
        controlButtonConstraints02.gridx = 3;
        controlButtonConstraints02.insets = new Insets(10,0,10,0);
        controllingPanel02.add(attemptDisplayLabel, controlButtonConstraints02);
        
        //Position setting for controllingPanel01
        this.setLayout(new GridBagLayout());
        frameConstraint.gridx = 0;
        frameConstraint.gridy = 0;
        frameConstraint.fill = GridBagConstraints.HORIZONTAL;
        frameConstraint.insets = new Insets(5,10,5,0);
        frameConstraint.anchor = GridBagConstraints.PAGE_START;
        this.add(controllingPanel01, frameConstraint);
        
        //Position setting for gameNameLabel
        frameConstraint.gridy = 1;
        frameConstraint.fill = GridBagConstraints.HORIZONTAL;
        frameConstraint.insets = new Insets(0,10,5,0);
        this.add(gameNameAndInfoLabel, frameConstraint);
        
        //Position setting for controllingPanel02
        frameConstraint.gridy = 2;
        frameConstraint.insets = new Insets(0,10,5,0);
        this.add(controllingPanel02, frameConstraint);
        
        //Position setting for gamePlayingCanvas
        frameConstraint.gridx = 2;
        frameConstraint.gridy = 0;
        frameConstraint.gridheight = 3;
        frameConstraint.insets = new Insets(5,10,5,10);
        this.add(gamePlayingCanvas, frameConstraint);
        
        this.pack();
    }
    
    class CustomBorderType1 extends AbstractBorder{
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            BasicStroke bs = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2d.setStroke(bs);
            g2d.setColor(Color.red);
            g2d.drawRect(0, 0, width, height);
        } 
    }
    
    class CustomBorderType2 extends AbstractBorder{
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            BasicStroke bs = new BasicStroke(15, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE);
            g2d.setStroke(bs);
            g2d.setColor(new Color(128, 44, 42));
            g2d.drawOval(0, 0, width, height);
        } 
    }
}
