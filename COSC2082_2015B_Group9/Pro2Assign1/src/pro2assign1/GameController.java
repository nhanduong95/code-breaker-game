package pro2assign1;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;

public class GameController {

    private Random random = new Random();

    private MainView mainView = new MainView();
    private GameModel model = new GameModel();
    private SettingView settingView = new SettingView();
            
    private ArrayList <JLabel> iconCollection = mainView.getIconCollection();
    private ArrayList <JLabel> iconHolderCollection = mainView.getIconHolderCollection();
    private ArrayList <JLabel> guessStatusCollection = mainView.getGuessStatusCollection();
    private ArrayList <JLabel> iconAnswerCollection = mainView.getIconAnswerCollection();
    
    private int [] userGuesses = model.getUserGuesses();
    private int [] answerKey = model.getAnswerKey(); 
    
    private int attempt = model.getAttempt();
    private int score = model.getScore();
    private int selectedIconNumber = model.getSelectedIconNumber();
   
    private boolean acceptButtonEnableFlag = model.getAcceptButtonEnableFlag();
    private Set<Integer> solution = model.getSolutionSet();
    private AudioClip music = settingView.getAudioClip();
    
    private void setDefaultImageCollection(ArrayList <JLabel> collection, String defaultImageUrl){
        for(JLabel image : collection)
            image.setIcon(new ImageIcon(defaultImageUrl));
    }
    
    private void setDefaultIntegerArray(int[] array, int defaultValue){
        for(int i = 0; i < array.length; i++){
            array[i] = defaultValue;
        }
    }
    
    private void setAnswerKey(){
        while(solution.size() < 6){
            Integer next = random.nextInt(8);
            solution.add(next);
        }
        int index = 0;    
        for (int i : solution){
            answerKey[index++] = i;
        }
    }
    
    private void showAnswerKey(){
        for(JLabel iconAnswer : iconAnswerCollection){
            for(JLabel icon : iconCollection){
                if(iconCollection.indexOf(icon) == answerKey[iconAnswerCollection.indexOf(iconAnswer)]){
                    iconAnswer.setIcon(icon.getIcon());
                    iconAnswer.setBorder(mainView.new CustomBorderType2());
                    iconAnswer.setVisible(true);
                }
            }
        }
        mainView.setIconAnswerCollection(iconAnswerCollection);   
    }
    
    private void clearIconBorder(ArrayList<JLabel> icons){
        for (JLabel iconAnswer : icons){
            iconAnswer.setBorder(null);
        }
    }
    
    private void setIconCollectionImages(){
        for(JLabel icon : iconCollection){
            String iconImageUrl = "images/" + mainView.getIconSetDirectory() + "/" 
                                    + iconCollection.indexOf(icon) + ".png";
            icon.setIcon(new ImageIcon(iconImageUrl));
        }
    }
    
    private int calculateScore(){
        if (attempt <= 6)
            score = 1000;
        else if (attempt <= 12)
            score = 750;
        else if (attempt <= 36)
            score = 500;
        else if (attempt <= 42)
            score = 250;
        else if (attempt <= 60)
            score = 100;
        else if (attempt <= 72)
            score = 50;
        else
            score = 0;
        return score;
    }

    GameController(){
        setAnswerKey();
        for(JLabel icon : iconCollection)
            icon.addMouseListener(new IconListener());
        
        mainView.getAcceptButtonLabel().addMouseListener(new AcceptButtonListener());
        mainView.getQuitButtonLabel().addMouseListener(new QuitButtonListener());
        mainView.getGiveUpButtonLabel().addMouseListener(new GiveUpButtonListener());
        mainView.getNewGameButtonLabel().addMouseListener(new NewGameButtonListener());
        mainView.getSettingButtonLabel().addMouseListener(new SettingButtonListener());
        settingView.getIconSetList().addActionListener(new ChosenIconSetListener());
        settingView.getMusicOn().addActionListener(new MusicOnListener());
        settingView.getMusicOff().addActionListener(new MusicOffListener());
    }
    
    class NewGameButtonListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent newGameButtonPressed){
            setDefaultImageCollection(iconHolderCollection, "images/an-icon-holder.png");
            setDefaultImageCollection(iconAnswerCollection, "images/icon-answer-null.png");
            setDefaultImageCollection(guessStatusCollection, "images/guess-status-none.png");
            
            setDefaultIntegerArray(answerKey, 0);
            setDefaultIntegerArray(userGuesses, -1);
            
            clearIconBorder(iconAnswerCollection);
            clearIconBorder(iconHolderCollection);
            
            mainView.getCodeBreakerLabel().setVisible(false);
            mainView.setCurrentIconHolder(0);
            
            acceptButtonEnableFlag = true;
            settingView.getIconSetList().setEnabled(true);
            mainView.getAcceptButtonLabel().setEnabled(true);
            
            mainView.getAttemptDisplayLabel().setText("0");
            mainView.getScoreDisplayLabel().setText("0");
            
            attempt = model.getAttempt();
            score = model.getAttempt();
            selectedIconNumber = model.getSelectedIconNumber();
            
            solution.clear();
            solution = model.getSolutionSet();
        }
    }
    class QuitButtonListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent quitButtonClicked){
            System.exit(0);
        }
    }
    
    class GiveUpButtonListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent giveUpButtonClicked){
            settingView.getIconSetList().setEnabled(false);
            showAnswerKey();
            mainView.getAcceptButtonLabel().setEnabled(false);
            mainView.getAcceptButtonLabel().setDisabledIcon(new ImageIcon("images/accept-disabled-button.png"));
            acceptButtonEnableFlag = false;
            iconHolderCollection.get(mainView.getCurrentIconHolder()).setBorder(null);
        }
    }
    class SettingButtonListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent settingButtonClicked){
            settingView.setVisible(true);
        }
    }
    class IconListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent iconPressed){
            settingView.getIconSetList().setEnabled(false);
            for(JLabel icon : iconCollection){
                if(iconPressed.getSource() == icon){
                    for(JLabel iconHolder : iconHolderCollection){
                        if(mainView.getCurrentIconHolder() == iconHolderCollection.indexOf(iconHolder)){
                            iconHolder.setIcon(icon.getIcon());
                            userGuesses[mainView.getCurrentIconHolder()] = iconCollection.indexOf(icon);
                        }
                    }
                }
            }
        }
    }
    
    class AcceptButtonListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent acceptButtonClicked){ 
            if(acceptButtonEnableFlag){
                int correctGuesses = 0;
                int incorrectGuesses = 0;
                
                //Keeping track and calculating attempt and score
                attempt++;
                calculateScore();
                mainView.getAttemptDisplayLabel().setText(String.valueOf(attempt));
                mainView.getScoreDisplayLabel().setText(String.valueOf(score));
                
                //Guess validation: checking whether the user's gusesses 
                //are right, wrong or not in the answer
                for (int i = 0; i <= 5; i++){
                    if (answerKey[i] == userGuesses[i])                  
                        correctGuesses ++;             
                }          

                for (int i = 0; i <=5 ; i++){
                    for (int j = 0; j <= 5; j++){
                        if (userGuesses[j] == (answerKey[i])){                      
                            incorrectGuesses ++;
                            break;
                        }
                    }
                }
                
                incorrectGuesses = incorrectGuesses - correctGuesses;   
                
                //Show user guess's status on the screen
                for (int i = 0; i < correctGuesses; i++)
                    guessStatusCollection.get(i).setIcon(new ImageIcon("images/guess-status-correct.png"));

                for (int i = correctGuesses; i < (incorrectGuesses + correctGuesses); i++)
                    guessStatusCollection.get(i).setIcon(new ImageIcon("images/guess-status-incorrect.png"));             

                for (int i = correctGuesses + incorrectGuesses; i < 6; i++)
                    guessStatusCollection.get(i).setIcon(new ImageIcon("images/guess-status-none.png"));                
                
                mainView.setGuessStatusCollection(guessStatusCollection);
                
                //When user wins...
                if (correctGuesses == 6){
                    acceptButtonEnableFlag = false;
                    mainView.getAcceptButtonLabel().setEnabled(false);
                    mainView.getAcceptButtonLabel().setDisabledIcon(new ImageIcon("images/accept-disabled-button.png"));
                    iconHolderCollection.get(5).setBorder(null);
                    mainView.getCodeBreakerLabel().setIcon(new ImageIcon("images/code-breaker-noti.png"));
                    mainView.getCodeBreakerLabel().setVisible(true);
                }
                //Otherwise...
                else{
                    mainView.setNextIconHolder();
                    mainView.setCurrentIconHolderImage();
                    mainView.clearPreviousIconHoleImage();
                    System.out.println();
                }
            } 
        }
    }
    
    //Icon Selection
    class ChosenIconSetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent iconSetSelected){
            JComboBox<String> iconSetList = (JComboBox<String>) iconSetSelected.getSource();
            String selectedIconSet = (String)iconSetList.getSelectedItem();
            if (selectedIconSet.equals("Stuff Icon Set")) 
                mainView.setIconSetDirectory("icon-set-01");
            else if (selectedIconSet.equals("Office Icon Set")) 
                mainView.setIconSetDirectory("icon-set-02");
            else
                mainView.setIconSetDirectory("icon-set-03");
            setIconCollectionImages();
        }
    }
    
    //Turn music on
    class MusicOnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent musicModeSelected){
            music.loop();
        }
    }
    //Turn music off
    class MusicOffListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent musicModeSelected){
            music.stop();
        }
    }
}
