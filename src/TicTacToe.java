import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    Random random=new Random();
    JFrame frame=new JFrame();
    JPanel title_panel=new JPanel();
    JPanel button_panel=new JPanel();
    JLabel textfield=new JLabel();
    JButton[][] buttons=new JButton[3][3];
    boolean player1_turn;
    int turns=0;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Verdana",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int r=0; r<3; r++) {
            for (int c = 0; c < 3; c++) {

                buttons[r][c] = new JButton();
                button_panel.add(buttons[r][c]);
                buttons[r][c].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[r][c].setFocusable(false);
                buttons[r][c].addActionListener(this);
            }
        }
        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        firstTurn();


    }

    public void actionPerformed(ActionEvent e){

        for(int r=0; r<3; r++){
            for(int c=0; c<3;c++){
                if(e.getSource()==buttons[r][c]){
                    turns++;
                    if(player1_turn){
                        if(buttons[r][c].getText()==""){
                            buttons[r][c].setForeground(new Color(255,0,0));
                            buttons[r][c].setText("X");
                            player1_turn=false;
                            textfield.setText("0 turn");
                            check();
                        }
                    }
                    else{
                        if(buttons[r][c].getText()==""){
                            buttons[r][c].setForeground(new Color(0,0,255));
                            buttons[r][c].setText("O");
                            player1_turn=true;
                            textfield.setText("X turn");
                            check();
                        }
                    }

                }
            }
        }
    }

    public void firstTurn(){

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(random.nextInt(2)==0){
            player1_turn=true;
            textfield.setText("X turn");
        }
        else{
            player1_turn=false;
            textfield.setText("O turn");
        }
    }

    public void check(){
        //Horizontal
        for(int r=0; r<3; r++){
            if(buttons[r][0].getText()=="")continue;

            if(buttons[r][0].getText()==buttons[r][1].getText() && buttons[r][1].getText()==buttons[r][2].getText()){
                for(int i=0; i<3; i++){
                    if(buttons[r][0].getText()=="X"){
                        xWins(buttons[r][i] );
                    }else{
                        oWins(buttons[r][i]);
                    }
                }
            }
        }


        //  vertical
        for(int c=0; c<3;c++){
            if(buttons[0][c].getText()=="")continue;

            if(buttons[0][c].getText()==buttons[1][c].getText() && buttons[1][c].getText()==buttons[2][c].getText()){
                for(int i=0; i<3; i++){
                    if(buttons[0][c].getText()=="X"){
                        xWins(buttons[i][c] );
                    }else{
                        oWins(buttons[i][c]);
                    }
                }
            }
        }

        //diagonally
        if(buttons[0][0].getText() == buttons[1][1].getText() && buttons[1][1].getText()==buttons[2][2].getText() && buttons[0][0].getText() != ""){
            for(int i=0; i<3; i++) {
                if (buttons[0][0].getText() == "X") {
                    xWins(buttons[i][i]);
                } else {
                    oWins(buttons[i][i]);
                }
            }
        }

        //anti-diagonally
        if(buttons[0][2].getText() == buttons[1][1].getText() && buttons[1][1].getText() == buttons[2][0].getText() && buttons[0][2].getText() != ""){
            for(int i=0; i<3; i++){
                if(buttons[0][2].getText()=="X"){
                    xWins(buttons[0][2]);
                    xWins(buttons[1][1]);
                    xWins(buttons[2][0]);
                }else{
                    oWins(buttons[0][2]);
                    oWins(buttons[1][1]);
                    oWins(buttons[2][0]);
                }
            }

        }

        if(turns==9){
            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    setTie(buttons[r][c]);
                }
            }
        }
    }


    public void xWins(JButton win){
        win.setBackground(Color.green);
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                buttons[r][c].setEnabled(false);
            }
        }
        textfield.setText("X wins");
    }

    public void oWins(JButton win){
        win.setBackground(Color.green);
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                buttons[r][c].setEnabled(false);
            }
        }
        textfield.setText("O wins");
    }

    void setTie(JButton tile){
        tile.setForeground(Color.ORANGE);
        tile.setBackground(Color.gray);
        textfield.setText(" Tie!");
    }
}

