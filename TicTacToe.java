import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel name_panel = new JPanel();
    JLabel textfield = new JLabel();
    JLabel name = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    boolean bot;
    boolean bot_turn;
    String letter = "";

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(240,128,128));
        textfield.setForeground(new Color(128,0,0));
        textfield.setFont(new Font("Times", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        name.setBackground(new Color(255,160,122));
        name.setForeground(new Color(128,0,0));
        name.setFont(new Font("Times", Font.BOLD, 40));
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setText( "Name: " + someNames.get(random.nextInt(23)));
        name.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        name_panel.setLayout(new BorderLayout());
        name_panel.setBounds(0, 800, 300, 100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150, 150, 150));

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Times", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        name_panel.add(name);
        frame.add(name_panel, BorderLayout.SOUTH);
        
        frame.setVisible(true);

        start();
    }

    ArrayList<String> someNames = new ArrayList<String>() {
      {
         add("Dulcify");
         add("Whirlaway");
         add("Gumshoe");
         add("Skipteaser");
         add("Kissed by a fish");
         add("Rakatack");
         add("Viscosity");
         add("Flattermeforever");
         add("Sonneteer");
         add("Cigar starter");
         add("Flat out fast");
         add("Gothic soldier");
         add("Sexy librarian");
         add("Fishy advice");
         add("Motel princess");
         add("Lunatique");
         add("Panicking petunia");
         add("Wingspan");
         add("Southern missle");
         add("Excessive moves");
         add("Paul bunyans axe");
         add("A storm it is");
         add("Dawn of war");
      }
   };
    ArrayList<Integer> num = new ArrayList<Integer>() {    
      {
        add(0);
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
      }
    };
   

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        for (int i = 0; i < 9; i++){
            if(e.getSource() == buttons[i]){
                if(!bot){
                    if(player1_turn){
                        if(buttons[i].getText() == ""){
                            buttons[i].setForeground(new Color(255, 0, 0));
                            buttons[i].setText("X");
                            player1_turn = false;
                            textfield.setText("O Turn");
                            check();
                        }
                    } else {
                            if(buttons[i].getText() == ""){
                                buttons[i].setForeground(new Color(0, 0, 255));
                                buttons[i].setText("O");
                                player1_turn = true;
                                textfield.setText("X Turn");
                                check();
                            }
                    }
                } else {
                    if(bot_turn){
                        runBot();
                    } else {
                            if(buttons[i].getText() == ""){
                                buttons[i].setForeground(new Color(0, 0, 255));
                                buttons[i].setText("O");
                                num.remove(e.getSource());
                                bot_turn = true;
                                textfield.setText("X Turn");
                                
                                check();
                            }
                    }

                }
            }
        }
        
    }
    public void runBot(){
        int choice = num.get(random.nextInt(num.size())); 
        if(buttons[choice].getText() == ""){
            buttons[choice].setForeground(new Color(255, 0, 0));
            buttons[choice].setText("X");
            bot_turn = false;
            textfield.setText("O Turn");
            num.remove(choice);
            check();
        } else if(buttons[choice].getText() == "X" || buttons[choice].getText() == "O"){
            runBot();
        }
    }


    public void check(){
        if((buttons[0].getText() == "X") &&
        (buttons[1].getText() == "X") && 
        (buttons[2].getText() == "X")){
            xWins(0, 1, 2);
        }
        if((buttons[3].getText() == "X") &&
        (buttons[4].getText() == "X") && 
        (buttons[5].getText() == "X")){
            xWins(3, 4, 5);
        }
        if((buttons[6].getText() == "X") &&
        (buttons[7].getText() == "X") && 
        (buttons[8].getText() == "X")){
            xWins(6, 7, 8);
        }
        if((buttons[0].getText() == "X") &&
        (buttons[3].getText() == "X") && 
        (buttons[6].getText() == "X")){
            xWins(0, 3, 6);
        }
        if((buttons[1].getText() == "X") &&
        (buttons[4].getText() == "X") && 
        (buttons[7].getText() == "X")){
            xWins(1, 4, 7);
        }
        if((buttons[2].getText() == "X") &&
        (buttons[5].getText() == "X") && 
        (buttons[8].getText() == "X")){
            xWins(2, 5, 8);
        }
        if((buttons[0].getText() == "X") &&
        (buttons[4].getText() == "X") && 
        (buttons[8].getText() == "X")){
            xWins(0, 4, 8);
        }
        if((buttons[2].getText() == "X") &&
        (buttons[4].getText() == "X") && 
        (buttons[6].getText() == "X")){
            xWins(2, 4, 6);
        }

        if((buttons[0].getText() == "O") &&
        (buttons[1].getText() == "O") && 
        (buttons[2].getText() == "O")){
            oWins(0, 1, 2);
        }
        if((buttons[3].getText() == "O") &&
        (buttons[4].getText() == "O") && 
        (buttons[5].getText() == "O")){
            oWins(3, 4, 5);
        }
        if((buttons[6].getText() == "O") &&
        (buttons[7].getText() == "O") && 
        (buttons[8].getText() == "O")){
            oWins(6, 7, 8);
        }
        if((buttons[0].getText() == "O") &&
        (buttons[3].getText() == "O") && 
        (buttons[6].getText() == "O")){
            oWins(0, 3, 6);
        }
        if((buttons[1].getText() == "O") &&
        (buttons[4].getText() == "O") && 
        (buttons[7].getText() == "O")){
            oWins(1, 4, 7);
        }
        if((buttons[2].getText() == "O") &&
        (buttons[5].getText() == "O") && 
        (buttons[8].getText() == "O")){
            oWins(2, 5, 8);
        }
        if((buttons[0].getText() == "O") &&
        (buttons[4].getText() == "O") && 
        (buttons[8].getText() == "O")){
            oWins(0, 4, 8);
        }
        if((buttons[2].getText() == "O") &&
        (buttons[4].getText() == "O") && 
        (buttons[6].getText() == "O")){
            oWins(2, 4, 6);
        }

    }

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins");
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins");

        
    }

    public void start() {
        String s = (String)JOptionPane.showInputDialog(new JFrame(),
               "O / X?",
               "Pick Your Team",
               JOptionPane.PLAIN_MESSAGE
             );

            if (s.equals("o") || s.equals("O")){
                letter = "O";
                textfield.setText("O Turn");
                player1_turn = true;
            } else if (s.equals("x") || s.equals("X")){
                textfield.setText("X Turn");
                player1_turn = true;
                letter = "X";
            } else {
                ErrorDialog();
                start();
            }

        
        String t = (String)JOptionPane.showInputDialog(new JFrame(),
             "Bot or Player?",
             JOptionPane.PLAIN_MESSAGE
           );
        
        if (t.equals("Player") || t.equals("player")){
            bot = false;
        } else if (t.equals("Bot") || t.equals("bot")){
            bot = true;

        } else {
            ErrorDialog();
            start();
        }

    }

    public void ErrorDialog() {
        String message = "\"Wrong User Input\"\n"
            + "Please try and reload\n"
            + "Thank You!";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
            JOptionPane.ERROR_MESSAGE);
      }



}