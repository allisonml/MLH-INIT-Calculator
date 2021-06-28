import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JFrame {
    Label resultLabel;

    public Calculator() {
        initialize();
    }

    private void initialize() {
        resultLabel = new ResultLabel();
        add(resultLabel, BorderFactory.);

        GridLayout buttonLayout = new GridLayout(0,3)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(buttonLayout);

        ArrayList<JButton> buttonList = new ArrayList<>();


//        for(int i =0; i<=9; i++) {
//             Jbutton
//
//        }
        JButton zeroButton = new JButton("0");
        buttonList.add(zeroButton);
        JButton oneButton = new JButton("1");
        buttonList.add(oneButton);
        JButton twoButton = new JButton("2");
        buttonList.add(twoButton);
        JButton threeButton = new JButton("3");
        buttonList.add(threeButton);
        JButton fourButton = new JButton("4");
        buttonList.add(fourButton);
        JButton fiveButton = new JButton("5");
        buttonList.add(fiveButton);
        JButton sixButton = new JButton("6");
        buttonList.add(sixButton);
        JButton sevenButton = new JButton("7");
        buttonList.add(sevenButton);
        JButton eightButton = new JButton("8");
        buttonList.add(eightButton);
        JButton nineButton = new JButton("9");
        buttonList.add(nineButton);
        initNumbers()
    }

    public class ResultLabel extends JLabel {
        public ResultLabel() {
            this.setBackground(Color.white);
            this.setText("0");
        }
    }

    private void initNumbers(ArrayList buttonList) {
        for(Object button : buttonList) {
             button = (JButton) button;
             //add button onto button jpanel
            
             ((JButton) button).addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // want to be able to show number and operations in label when pressed
                     resultLabel.setText(resultLabel.getText() + ((JButton) button).getText());
                     // then, nee to do operation depending on if it's the first or second number given..?

                 }

             });

        }

    }

}
