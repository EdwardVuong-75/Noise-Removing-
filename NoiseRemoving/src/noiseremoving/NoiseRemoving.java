package noiseremoving;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author vuong
 */
public class NoiseRemoving {

    /**
     * @param args the command line arguments
     */
   private JFrame frame;
    private JLabel imageLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NoiseRemoving window = new NoiseRemoving();
                window.frame.setVisible(true);
            } catch (Exception e) {
            }
        });
    }

    public NoiseRemoving() 
    {
        initialize();
    }

    private void initialize() 
    {
        //creating the main frame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE); 
        
        //creating the title
        JLabel titleLabel = new JLabel("NOISE CLEANING");
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.ITALIC | Font.ITALIC, 24)); // Increase the font size to 24
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label horizontally
        frame.getContentPane().add(titleLabel, BorderLayout.NORTH);
        
        //creating the button panels
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        //creating the select file button
        JButton selectButton = new JButton("Select File");
        selectButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) 
                {
                    File file = fileChooser.getSelectedFile();
                    ImageProcess ip = new ImageProcess(file.getAbsolutePath());
                    ip.cleanNoise();
                    ip.save("noise_removed.jpg");

                    JFrame imageFrame = new JFrame();
                    imageFrame.setSize(1500, 800);
                    imageFrame.setTitle("Chosen Image: " + file.getName());
                    imageLabel = new JLabel(new ImageIcon("noise_removed.jpg"));
                    JScrollPane scrollPane = new JScrollPane(imageLabel);
                    imageFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    imageFrame.setLocationRelativeTo(null);
                    imageFrame.setVisible(true);
                    // Show a confirmation message
                    JOptionPane.showMessageDialog(frame, "Noise removed and saved as 'noise_removed.jpg'.");
                    frame.dispose(); // closing the main frame
                }
            }
        });
        
        selectButton.setFont(selectButton.getFont().deriveFont(Font.ITALIC));
        selectButton.setBackground(Color.BLUE);
        buttonPanel.add(selectButton);
        //creating the exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0); //exit the program when user clicks it 
            }
        });
        exitButton.setFont(exitButton.getFont().deriveFont(Font.ITALIC));
        exitButton.setBackground(Color.RED);
        buttonPanel.add(exitButton);
    }
}