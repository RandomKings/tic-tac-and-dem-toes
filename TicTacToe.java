import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons;
    private JButton resetButton;
    private boolean player1Turn = true;

    public TicTacToe() {
        // Set the custom look and feel
        try {
            UIManager.setLookAndFeel(new CustomLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Tic Tac Toe");
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBackground(new Color(50, 50, 50));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBackground(new Color(80, 80, 80));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(80, 80, 80));
        resetButton.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(resetButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            if (player1Turn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            player1Turn = !player1Turn;
        }
        checkForWin();
    }

    public void checkForWin() {
        String winner = "";
        if (buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText()) && !buttons[0].getText().equals("")) {
            winner = buttons[0].getText();
        } else if (buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText()) && !buttons[3].getText().equals("")) {
            winner = buttons[3].getText();
        } else if (buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText()) && !buttons[6].getText().equals("")) {
            winner = buttons[6].getText();
        } else if (buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText()) && !buttons[0].getText().equals("")) {
            winner = buttons[0].getText();
        } else if (buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[7].getText()) && !buttons[1].getText().equals("")) {
            winner = buttons[1].getText();
        } else if (buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText()) && !buttons[2].getText().equals("")) {
            winner = buttons[2].getText();
        } else if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText()) && !buttons[0].getText().equals("")) {
            winner = buttons[0].getText();
        } else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText()) && !buttons[2].getText().equals("")) {
            winner = buttons[2].getText();
        }
        if (!winner.equals("")) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            JOptionPane.showMessageDialog(frame, winner + " wins!");
        } else if (boardIsFull()) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            JOptionPane.showMessageDialog(frame, "It's a tie!");
        }
    }

    public boolean boardIsFull() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

class CustomLookAndFeel extends BasicLookAndFeel {
    public String getName() {
        return "Custom Look and Feel";
    }
    public String getID() {
        return "CustomLookAndFeel";
    }

    public String getDescription() {
        return "A custom look and feel for the Tic Tac Toe game";
    }

    public boolean isNativeLookAndFeel() {
        return false;
    }

    public boolean isSupportedLookAndFeel() {
        return true;
    }

    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        Object[] uiDefaults = {
                "ButtonUI", CustomButtonUI.class.getName()
        };
        table.putDefaults(uiDefaults);
    }

    protected void initSystemColorDefaults(UIDefaults table) {
        super.initSystemColorDefaults(table);
        Object[] uiDefaults = {
                "Button.background", new ColorUIResource(80, 80, 80),
                "Button.foreground", Color.WHITE,
                "Button.border", BorderFactory.createLineBorder(new Color(120, 120, 120), 2),
                "Panel.background", new Color(50, 50, 50),
                "OptionPane.background", new Color(50, 50, 50),
                "OptionPane.messageForeground", Color.WHITE
        };
        table.putDefaults(uiDefaults);
    }

    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);
        Object[] uiDefaults = {
                "Button.font", new Font("Helvetica Neue", Font.PLAIN, 24),
                "Button.margin", new Insets(10, 10, 10, 10),
                "Button.background", new Color(80, 80, 80),
                "Button.foreground", Color.WHITE,
                "Button.border", BorderFactory.createLineBorder(new Color(120, 120, 120), 2),
                "Button.focus", new Color(0, 0, 0, 0),
                "Button.hoverBackground", new Color(120, 120, 120),
                "Button.pressedBackground", new Color(60, 60, 60),
                "Panel.background", new Color(50, 50, 50),
                "OptionPane.background", new Color(50, 50, 50),
                "OptionPane.messageForeground", Color.WHITE
        };
        table.putDefaults(uiDefaults);
    }
}

class CustomButtonUI extends BasicButtonUI {
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setBorderPainted(false);
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        if (model.isRollover()) {
            g.setColor(b.getBackground().darker());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        } else if (model.isPressed()) {
            g.setColor(b.getBackground().darker());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        } else {
            g.setColor(b.getBackground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }

        super.paint(g, c);
    }
}
