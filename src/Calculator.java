import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Calculator extends JFrame {
    JLabel screen;
    JPanel numberPanel;
    JPanel operationPanel;

    public Calculator() {
        initialize();
    }

    private void initialize() {
        this.setLayout(new BorderLayout());

        screen = new ScreenLabel();
        //this.add(screen, BorderLayout.NORTH);
        //screen.setVisible(true);

        GridLayout numberLayout = new GridLayout(0,3);
        numberPanel = new JPanel();
        numberPanel.setLayout(numberLayout);

        ArrayList<JButton> buttonList = new ArrayList<>();


        for(int i =0; i<=9; i++) {
             buttonList.add(new JButton(String.valueOf(i)));

        }
//        JButton zeroButton = new JButton("0");
//        buttonList.add(zeroButton);
//        JButton oneButton = new JButton("1");
//        buttonList.add(oneButton);
//        JButton twoButton = new JButton("2");
//        buttonList.add(twoButton);
//        JButton threeButton = new JButton("3");
//        buttonList.add(threeButton);
//        JButton fourButton = new JButton("4");
//        buttonList.add(fourButton);
//        JButton fiveButton = new JButton("5");
//        buttonList.add(fiveButton);
//        JButton sixButton = new JButton("6");
//        buttonList.add(sixButton);
//        JButton sevenButton = new JButton("7");
//        buttonList.add(sevenButton);
//        JButton eightButton = new JButton("8");
//        buttonList.add(eightButton);
//        JButton nineButton = new JButton("9");
//        buttonList.add(nineButton);
        initButtons(buttonList, Color.PINK, numberPanel);

        JButton resetButton = new JButton("C");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("0");
            }
        });
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentExpression = screen.getText();

                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("JavaScript");
                try {
                    screen.setText(engine.eval(currentExpression).toString());
                } catch (ScriptException scriptException) {
                    System.out.println("scriptException found");
                    scriptException.printStackTrace();
                }

            }
        });

        numberPanel.add(resetButton);
        numberPanel.add(equalsButton);

        // add operation buttons
        operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(0,1));
        ArrayList<JButton> operationButtonList = new ArrayList<>();
        JButton plusButton = new JButton("+");
        operationButtonList.add(plusButton);
        JButton minusButton = new JButton("-");
        operationButtonList.add(minusButton);
        JButton timesButton = new JButton("*");
        operationButtonList.add(timesButton);
        JButton divideButton = new JButton("/");
        operationButtonList.add(divideButton);
        initButtons(operationButtonList, new Color(243, 114, 181, 255), operationPanel);




        this.add(screen, BorderLayout.NORTH);
        this.add(numberPanel, BorderLayout.CENTER);
        this.add(operationPanel, BorderLayout.EAST);


        this.pack();
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class ScreenLabel extends JLabel {
        public ScreenLabel() {
            this.setBackground(Color.white);
            this.setText("0");
        }
    }

    private void initButtons(ArrayList buttonList, Color colour, JPanel panel) {

        Iterator<JButton> buttonIterator = buttonList.iterator();

        while(buttonIterator.hasNext()) {
            JButton button = buttonIterator.next();
            defaultButtonSetup(button, colour, panel);
        }
    }

    private void defaultButtonSetup(JButton button, Color colour, JPanel panel) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        //button.setBackground(Color.PINK);
        button.setBackground(colour);
        //button.setMinimumSize(new Dimension(200,200));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        //button.setBorder(new BasicBorders.ButtonBorder(Color.gray, ));
        //numberPanel.add(button);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screen.getText().equals("0")) {
                    screen.setText("");
                }
                screen.setText(screen.getText() + button.getText());
            }
        });

    }

}
