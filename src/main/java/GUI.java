import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private static final long serialVersionUID = -5309511203070524917L;

    int w = 400, h = 400, m = 30, rows = 6, columns = 2;

    String pathValue = new String(), artifactValue, groupValue, versionValue;
    public String[] options = { "Do not open", "VS code", "Atom", "Intellij IDEA" };

    JTextField artifactId, group, version;
    JComboBox<String> editor;

    GUI() {
        design();
    }

    public void add(Component c, int i, int j, int width, int height) {
        c.setBounds(i * w / columns + m / 2, j * h / rows + m / 2, width * w / columns - m, height * h / rows - m);
        add(c);
    }

    public void design() {
        setLayout(null);

        JLabel addressLabel = new JLabel("Project path :", 0);
        reDesign(addressLabel);
        add(addressLabel, 0, 0, 1, 1);

        JButton chooseAddress = new JButton("Choose path");
        reDesign(chooseAddress);
        chooseAddress.addActionListener((e) -> chooseFolder());
        add(chooseAddress, 1, 0, 1, 1);

        JLabel artifactIdLabel = new JLabel("artifact ID :", 0);
        reDesign(artifactIdLabel);
        add(artifactIdLabel, 0, 1, 1, 1);

        artifactId = new JTextField();
        reDesign(artifactId);
        add(artifactId, 1, 1, 1, 1);

        JLabel groupLabel = new JLabel("Group :", 0);
        reDesign(groupLabel);
        add(groupLabel, 0, 2, 1, 1);

        group = new JTextField();
        reDesign(group);
        add(group, 1, 2, 1, 1);

        JLabel versionLabel = new JLabel("Version :", 0);
        reDesign(versionLabel);
        add(versionLabel, 0, 3, 1, 1);

        version = new JTextField("1.0");
        reDesign(version);
        add(version, 1, 3, 1, 1);

        JLabel editorLabel = new JLabel("Editor :", 0);
        reDesign(editorLabel);
        add(editorLabel, 0, 4, 1, 1);

        editor = new JComboBox<String>(options);
        reDesign(editor);
        add(editor, 1, 4, 1, 1);

        JButton cancel = new JButton("Cancel");
        reDesign(cancel);
        cancel.addActionListener((e) -> System.exit(0));
        add(cancel, 0, 5, 1, 1);

        JButton generate = new JButton("Generate");
        reDesign(generate);
        generate.addActionListener((e) -> getData());
        add(generate, 1, 5, 1, 1);

        JLabel powered = new JLabel("powereed by mmhlego");
        powered.setForeground(new Color(240, 240, 240));
        powered.setFont(new Font("Dialog", 2, 12));
        powered.setBounds(w / 2 - 70, h - 14, 140, 14);
        add(powered);

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quick java project builder");
        setSize(w, h);
        getContentPane().setBackground(new Color(57, 62, 70));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void reDesign(JLabel l) {
        l.setForeground(new Color(240, 240, 240));
        l.setFont(new Font("Dialog", 0, 16));
    }

    public void reDesign(JTextField l) {
        l.setBackground(new Color(67, 72, 80));
        l.setForeground(new Color(240, 240, 240));
        l.setBorder(new MatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        l.setHorizontalAlignment(0);
        l.setFont(new Font("Dialog", 0, 16));
        l.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                l.setBackground(new Color(87, 92, 100));
                l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(214, 90, 49)));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                l.setBackground(new Color(67, 72, 80));
                l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
            }
        });
    }

    public void reDesign(JButton b) {
        b.setBackground(new Color(67, 72, 80));
        b.setForeground(new Color(240, 240, 240));
        b.setBorder(null);
        b.setHorizontalAlignment(0);
        b.setFont(new Font("Dialog", 0, 16));
        b.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                b.setForeground(new Color(214, 90, 49));
                b.setBackground(new Color(87, 92, 100));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                b.setForeground(new Color(240, 240, 240));
                b.setBackground(new Color(67, 72, 80));
            }
        });
    }

    public void reDesign(JComboBox<String> c) {
        c.setForeground(new Color(240, 240, 240));
        c.setFont(new Font("Dialog", 0, 16));
        ((AbstractButton) c.getComponent(0)).setBorderPainted(false);
        ((JLabel) c.getRenderer()).setHorizontalAlignment(0);
        c.setBackground(new Color(67, 72, 80));
        c.setBorder(null);
    }

    public void chooseFolder() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selector.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        selector.showSaveDialog(null);
        try {
            pathValue = selector.getSelectedFile().toString() + "\\";
        } catch (Exception e) {
            pathValue = new String();
            JOptionPane.showMessageDialog(this, "You didn't specify a path.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getData() {
        artifactValue = artifactId.getText();
        groupValue = group.getText();
        versionValue = version.getText();

        if (pathValue.equals(new String())) {
            JOptionPane.showMessageDialog(this, "You didn't specify a path.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (artifactValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "You didn't specify an artifact id.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else if (groupValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "You didn't specify a group.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (versionValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "You didn't specify a version.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            dispose();
            new ProjectCreator(pathValue, artifactValue, groupValue, versionValue, editor.getSelectedItem().toString());
        }
    }
}