import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Calculator extends JFrame {
    public static final int BUTTON_SPACING = 5;
    public static final int PANEL_SPACING = BUTTON_SPACING*2;
    public static final Color BCKGRND_COLOUR = Color.LIGHT_GRAY;

    private JLabel screen;
    private JPanel numberPanel;
    private JPanel operationPanel;

    public Calculator() {
        initialize();
    }

    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(400,400));

        screen = new ScreenLabel();
        screen.setPreferredSize(new Dimension(200,100));
        screen.setMinimumSize(new Dimension(200,50));
        Border screenBorder = new LineBorder(BCKGRND_COLOUR, BUTTON_SPACING*2);
        Border screenPadding = new EmptyBorder(10, 10,10, 10);
        //screen.setBorder(screenPadding);
        screen.setBorder(new CompoundBorder(screenBorder, screenPadding));
        //screen.setBorder(BorderFactory.createLineBorder(BCKGRND_COLOUR, PADDING_SIZE*2));
        //screen.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //this.add(screen, BorderLayout.NORTH);
        //screen.setVisible(true);

        GridLayout numberLayout = new GridLayout(0,3, BUTTON_SPACING, BUTTON_SPACING);
        numberPanel = new JPanel();
        numberPanel.setLayout(numberLayout);
        numberPanel.setBackground(BCKGRND_COLOUR);
        numberPanel.setBorder(new EmptyBorder(BUTTON_SPACING, BUTTON_SPACING*2, BUTTON_SPACING*2, BUTTON_SPACING));


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
        customActionButtonSetup(resetButton, Color.white, numberPanel);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("0");
            }
        });

        JButton equalsButton = new JButton("=");
        customActionButtonSetup(equalsButton, Color.white, numberPanel);
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

        numberPanel.setPreferredSize(new Dimension(300,50));
        numberPanel.setMinimumSize(new Dimension(200,50));

        // add operation buttons
        operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(0,1, BUTTON_SPACING, BUTTON_SPACING));
        operationPanel.setBackground(BCKGRND_COLOUR);
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

        operationPanel.setPreferredSize(new Dimension(200,200));
        operationPanel.setMaximumSize(new Dimension(200,200));
        operationPanel.setBorder(new EmptyBorder(BUTTON_SPACING, BUTTON_SPACING, BUTTON_SPACING*2, BUTTON_SPACING*2));




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
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
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

    private void customActionButtonSetup(JButton button, Color colour, JPanel panel) {
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        //button.setBackground(Color.PINK);
        button.setBackground(colour);
        //button.setMinimumSize(new Dimension(200,200));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        //button.setBorder(new BasicBorders.ButtonBorder(Color.gray, ));
        //numberPanel.add(button);
        panel.add(button);
    }

}
