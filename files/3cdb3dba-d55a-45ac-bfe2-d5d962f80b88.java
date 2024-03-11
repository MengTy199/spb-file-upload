package org.mt.addition;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


//1. Lim Mengty (submit)
//2. Heng MengSrong
//3. Luch Livannni
//4. Lim Ranith
public class Addition extends JFrame {
    public  Addition(){
        add(new DrawingPanel());
        setBounds(300,200,400,500);
        setVisible(true);
    }

    public static void main(String[] arg){
        new Addition();
    }
}

class DrawingPanel extends JPanel{
    int nColumns = 5;
    int nRows= 5;

    int num[]= new int[nColumns*nRows];
    Font font = new Font("Arial", Font.ITALIC, 23);

    public DrawingPanel(){
        Random random = new Random();
        for(int i=0; i<num.length; i++){
            num[i]=random.nextInt(26)+5;
        }
    }

    public void paint(Graphics graphics){
        int w= getWidth(), h = getHeight();
        int sw= w/nColumns;
        int sh= h/nRows;
        int numIndex = 0;
        graphics.setFont(font);
        graphics.setColor(Color.black);
        for (int j =0; j<nRows; j++){
            for (int i =  0; i<nColumns; i++){
                graphics.drawRect(i*sw+4, j*sh + 4,sw +4,sh-4 );
                graphics.drawString(""+ num[numIndex++], i*sw+2+sw/2 , j*sh+2 + sh/2);
            }
        }
    }
}
