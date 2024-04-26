import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FINALPROJECT extends JFrame {
    private List<String> hardSkillsList = Arrays.asList("Programming", "Analytical Thinking", "Financial Skills", "Design Skills", "Mathematical Skills", "Science Skills", "Research Skills");
    private List<String> softSkillsList = Arrays.asList("Communication Skills", "Leadership", "Teamwork", "Problem Solving", "Creativity", "Empathy");
    private JComboBox<String> hardSkillsDropdown, softSkillsDropdown;
    private JTextArea resultArea;
    private JComboBox<String> streamComboBox;
    private JTextField physicsField, chemistryField, mathField, csField, cetRankField, comedkRankField, clatPercentileField;
    private JTextArea collegeOutputArea;

    public FINALPROJECT() {
        setTitle("College Admission Predictor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230)); // Light blue color

        JPanel skillPanel = new JPanel(new GridLayout(3, 2));
        skillPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        skillPanel.setBackground(new Color(173, 216, 230)); // Light blue color
        skillPanel.add(new JLabel("Select your hard skill:"));
        hardSkillsDropdown = new JComboBox<>(hardSkillsList.toArray(new String[0]));
        skillPanel.add(hardSkillsDropdown);
        skillPanel.add(new JLabel("Select your soft skill:"));
        softSkillsDropdown = new JComboBox<>(softSkillsList.toArray(new String[0]));
        skillPanel.add(softSkillsDropdown);
        skillPanel.add(new JLabel());
        JButton skillSubmitButton = new JButton("Submit");
        skillPanel.add(skillSubmitButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        mainPanel.add(skillPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel collegePanel = new JPanel(new GridBagLayout());
        collegePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        collegePanel.setBackground(new Color(173, 216, 230)); // Light blue color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        streamComboBox = new JComboBox<>(new String[]{"PCMC", "PCMB", "Commerce"});
        JLabel streamLabel = new JLabel("Stream:");
        collegePanel.add(streamLabel, gbc);
        gbc.gridx++;
        collegePanel.add(streamComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel physicsLabel = new JLabel("Physics Marks:");
        physicsField = new JTextField(20);
        collegePanel.add(physicsLabel, gbc);
        gbc.gridx++;
        collegePanel.add(physicsField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel chemistryLabel = new JLabel("Chemistry Marks:");
        chemistryField = new JTextField(20);
        collegePanel.add(chemistryLabel, gbc);
        gbc.gridx++;
        collegePanel.add(chemistryField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel mathLabel = new JLabel("Mathematics Marks:");
        mathField = new JTextField(20);
        collegePanel.add(mathLabel, gbc);
        gbc.gridx++;
        collegePanel.add(mathField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel csLabel = new JLabel("Computer Science Marks:");
        csField = new JTextField(20);
        collegePanel.add(csLabel, gbc);
        gbc.gridx++;
        collegePanel.add(csField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel cetRankLabel = new JLabel("KCET Rank:");
        cetRankField = new JTextField(20);
        collegePanel.add(cetRankLabel, gbc);
        gbc.gridx++;
        collegePanel.add(cetRankField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel comedkRankLabel = new JLabel("COMEDK Rank:");
        comedkRankField = new JTextField(20);
        collegePanel.add(comedkRankLabel, gbc);
        gbc.gridx++;
        collegePanel.add(comedkRankField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel clatPercentileLabel = new JLabel("CLAT Percentile:");
        clatPercentileField = new JTextField(20);
        collegePanel.add(clatPercentileLabel, gbc);
        gbc.gridx++;
        collegePanel.add(clatPercentileField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JButton collegeSubmitButton = new JButton("Submit");
        collegeOutputArea = new JTextArea();
        collegeOutputArea.setEditable(false);
        collegeOutputArea.setPreferredSize(new Dimension(200, 100));
        gbc.gridwidth = 2;
        gbc.gridx++;
        gbc.gridy++;
        collegePanel.add(collegeSubmitButton, gbc);
        gbc.gridy++;
        collegePanel.add(new JScrollPane(collegeOutputArea), gbc);

        collegeSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processStreamAndMarks();
            }
        });

        mainPanel.add(collegePanel, BorderLayout.SOUTH);
        add(mainPanel);
        skillSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hardSkill = (String) hardSkillsDropdown.getSelectedItem();
                String softSkill = (String) softSkillsDropdown.getSelectedItem();
                String allocatedDegree = allocateDegree(softSkill, hardSkill);
                resultArea.setText("Based on your skills, we recommend you pursue a degree in: " + allocatedDegree);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FINALPROJECT gui = new FINALPROJECT();
                gui.setVisible(true);
            }
        });
    }

    private String allocateDegree(String softSkill, String hardSkill) {
        String[][] degreeCriteria = {
                {"BBA", "Communication Skills", "Leadership", "Teamwork", "Financial Skills"},
                {"BCom", "Financial Skills", "Analytical Thinking", "Communication Skills"},
                {"BDes", "Creativity", "Problem Solving", "Design Skills"},
                {"B.Tech", "Analytical Thinking", "Problem Solving", "Programming", "Mathematical Skills"},
                {"BSc", "Analytical Thinking", "Problem Solving", "Mathematical Skills", "Science Skills"},
                {"Law", "Communication Skills", "Analytical Thinking", "Research Skills"}
        };

        List<String> allocatedDegrees = new ArrayList<>();
        for (String[] criteria : degreeCriteria) {
            boolean hasSoftSkill = false;
            boolean hasHardSkill = false;
            for (String skill : criteria) {
                if (skill.equalsIgnoreCase(softSkill)) {
                    hasSoftSkill = true;
                }
                if (skill.equalsIgnoreCase(hardSkill)) {
                    hasHardSkill = true;
                }
            }
            if (hasSoftSkill && hasHardSkill) {
                allocatedDegrees.add(criteria[0]);
            }
        }
        return allocatedDegrees.isEmpty() ? "No suitable degree found based on your skills." : String.join(", ", allocatedDegrees);
    }

    public void processStreamAndMarks() {
        String stream = (String) streamComboBox.getSelectedItem();
        int result = stream.equals("PCMC") ? 1 : (stream.equals("PCMB") ? 2 : 3);
        int physics = Integer.parseInt(physicsField.getText());
        int chemistry = Integer.parseInt(chemistryField.getText());
        int math = Integer.parseInt(mathField.getText());
        int cs = Integer.parseInt(csField.getText());
        int cetRank = Integer.parseInt(cetRankField.getText());
        int comedkRank = Integer.parseInt(comedkRankField.getText());
        int clatPercentile = Integer.parseInt(clatPercentileField.getText());

        Selection m = new Selection();
        m.m1 = physics;
        m.m2 = chemistry;
        m.m3 = math;
        m.m4 = cs;
        m.cetrank = cetRank;
        m.comedrk = comedkRank;
        m.clatperc = clatPercentile;

        m.suggestcollegespcmc(result, resultArea, collegeOutputArea);
    }
}

class Selection {
    int m1, m2, m3, m4;
    int cetrank;
    int comedrk;
    int clatperc;

    double calculate() {
        int res = m1 + m2 + m3 + m4;
        return (res * 100) / 400.00;
    }

    void suggestcollegespcmc(int result, JTextArea resultArea, JTextArea collegeOutputArea) {
        double percentage = calculate();
        if (result == 1) {
            if (cetrank <= 10000 && comedrk <= 5000 && percentage >= 85.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. RV College of Engineering\n" +
                        "2. PES University\n" +
                        "3. BMS College of Engineering");
            } else if (cetrank <= 20000 && comedrk <= 10000 && percentage >= 80.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. BMS College of Engineering\n" +
                        "2. Ramaiah Institute of Technology\n" +
                        "3. Bangalore Institute of Technology");
            } else {
                collegeOutputArea.setText("Your rank or percentage doesn't meet the eligibility criteria for top colleges.\n" +
                        "Consider applying to other colleges.");
            }
        } else if (result == 2) {
            if (percentage >= 85.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. All India Institute of Medical Sciences(AIIMS)\n" +
                        "2. Kasturba Medical College (KMC)\n" +
                        "3. Bangalore Medical College (BMC)");
            } else if (percentage >= 80.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. Karnataka Veterinary, Animal and Fisheries Sciences University(KVAFSU)\n" +
                        "2. Veterinary College, Hebbal\n" +
                        "3. National Institute of Animal Nutrition and Physiology");
            } else {
                collegeOutputArea.setText("Your percentage doesn't meet the eligibility criteria for top medical colleges.\n" +
                        "Consider applying to other colleges.");
            }
        } else if (result == 3) {
            if (percentage >= 90.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. Shri Ram College of Commerce (SRCC)\n" +
                        "2. Lady Shri Ram College for Women (LSR)\n" +
                        "3. St. Xavier's College, Mumbai");
            } else if (percentage >= 85.0) {
                collegeOutputArea.setText("You are eligible for admission in:\n" +
                        "1. National Law School of India University(NLSIU)\n" +
                        "2. Christ University (School of Law)\n" +
                        "3. RV University (School of Law)");
            } else {
                collegeOutputArea.setText("Your percentage doesn't meet the eligibility criteria for top commerce or law colleges.\n" +
                        "Consider applying to other colleges.");
            }
        }
    }
}