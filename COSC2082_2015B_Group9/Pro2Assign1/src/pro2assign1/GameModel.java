package pro2assign1;

import java.util.*;
import javax.swing.*;

public class GameModel {
    private Set<Integer> solution = new LinkedHashSet<Integer>();
    
    private int [] userGuesses = {-1,-1,-1,-1,-1,-1};
    private int [] answerKey = new int[6]; 
    
    private int attempt;
    private int score;
    private int selectedIconNumber = -1;
    
    private boolean acceptButtonEnableFlag = true;

    int [] getUserGuesses (){
        return userGuesses;
    }
    int [] getAnswerKey(){
        return answerKey;
    }
    int getAttempt(){
        return attempt;
    }
    int getScore(){
        return score;
    }
    int getSelectedIconNumber(){
        return selectedIconNumber;
    }
    boolean getAcceptButtonEnableFlag(){
        return acceptButtonEnableFlag;
    }
    Set<Integer> getSolutionSet(){
        return solution;
    }
}
