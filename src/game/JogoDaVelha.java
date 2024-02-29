package game;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;
//"javax.swing.*" e "javax.swing.*" serve para todos os imports desse tipo, como Jframe, Jlabem, Color, Font ect...

public class JogoDaVelha extends JFrame {

    ImageIcon iconO = new ImageIcon(getClass().getResource("/res/Bola.png"));
    ImageIcon iconX = new ImageIcon(getClass().getResource("/res/x.png"));

    JPanel pTela = new JPanel(new GridLayout(3, 3, 10, 10));
    //GridLayout é um Layout em forma de tabela
    Bloco[] blocos = new Bloco[9]; //Vetor de 9 posições

    int rodadas = 0;

    final int JOGADOR_O = 1;
    final int JOGADOR_X = 2;

    int jogadorVez = JOGADOR_O;

    JLabel lInformacao = new JLabel("Jogador O");

    public JogoDaVelha() {
        configurarJanela();
        configurarTela();
    }

    public void configurarTela() {
        add(BorderLayout.CENTER,pTela);
        add(BorderLayout.NORTH,lInformacao);
        setSize(720, 720);
        pTela.setBackground(BLACK);
        lInformacao.setFont(new Font("Arial",Font.BOLD,35));
        lInformacao.setForeground(BLACK);
        lInformacao.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i=0;i<9;i++) {
            Bloco bloco = new Bloco();
            blocos[i] = bloco;
            pTela.add(bloco);
        }
    }

    public void mudarVez(){
        if(jogadorVez==1) {
            jogadorVez=2;
            lInformacao.setText("Jogador X");
        } else {
            jogadorVez=1;
            lInformacao.setText("Jogador O");
        }
    }
    //    0 | 1 | 2
    //    3 | 4 | 5
    //    6 | 7 | 8
    public boolean testeVitoria(int jog) {
        if(blocos[0].quem==jog && blocos[1].quem==jog && blocos[2].quem==jog) {
            return true;
        }
        if(blocos[3].quem==jog && blocos[4].quem==jog && blocos[5].quem==jog) {
            return true;
        }
        if(blocos[6].quem==jog && blocos[7].quem==jog && blocos[8].quem==jog) {
            return true;
        }
        if(blocos[0].quem==jog && blocos[3].quem==jog && blocos[6].quem==jog) {
            return true;
        }
        if(blocos[1].quem==jog && blocos[4].quem==jog && blocos[7].quem==jog) {
            return true;
        }
        if(blocos[2].quem==jog && blocos[5].quem==jog && blocos[8].quem==jog) {
            return true;
        }
        if(blocos[0].quem==jog && blocos[4].quem==jog && blocos[8].quem==jog) {
            return true;
        }
        if(blocos[2].quem==jog && blocos[4].quem==jog && blocos[6].quem==jog) {
            return true;
        }
        return false;
    }

    public void configurarJanela() {
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JogoDaVelha();
    }

    public class Bloco extends JButton{
        int quem = 0;
        public Bloco() {
            setBackground(WHITE);
            addActionListener(e->{ //"addActionListener" utilizado para ouvir ações realizada em determinados componentes, assim, possibilitando atribuir funcionalidades à botões ou algum outro componentes.
                if(quem==0) {
                    if(jogadorVez==JOGADOR_O) {
                        setIcon(iconO);
                        quem = JOGADOR_O;
                    } else {
                        setIcon(iconX);
                        quem = JOGADOR_X;
                    }
                    if(testeVitoria(quem)) {
                        if(quem == 1){
                            JOptionPane.showMessageDialog(null,"Jogador O Venceu!");
                            System.exit(0);
                        }
                        if(quem == 2){
                            JOptionPane.showMessageDialog(null,"Jogador X Venceu!");
                            System.exit(0);
                        }
                    }
                    rodadas++;
                    if(rodadas==9) {
                        JOptionPane.showMessageDialog(null,"Deu velha!");
                        System.exit(0);
                    }
                    mudarVez();
                }
            });
        }
    }

}
