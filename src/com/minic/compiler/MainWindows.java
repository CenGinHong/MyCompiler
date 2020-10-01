package src.com.minic.compiler;

import src.com.minic.node.SimpleNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: �½���
 * @description: ����������
 * @since: 2020/3/30 12:58
 * @version: 1.0
 */
@SuppressWarnings("ALL")
public class MainWindows {

    /**
     * ���ļ�
     */
    private JButton openFileBtn;

    /**
     * ���Ϊ�ļ�
     */
    private JButton saveAsButton;

    /**
     * �ʷ�����
     */
    private JButton lexButton;

    /**
     * �﷨����
     */
    private JButton parseButton;

    /**
     * �﷨�����Ϊ
     */
    private JButton saveSyntaxTreeButton;
    /**
     * �������
     */
    private JButton yuYiFenXiButton;
    /**
     * �м��������
     */
    private JButton zhongJianDaiMaButton;
    /**
     * �м�������Ϊ
     */
    private JButton saveSzhongJianDaiMaButton;

    /**
     * Դ����
     */
    private JTextArea sourceTextArea;

    /**
     * �ʷ���
     */
    private JTextArea lexingTextArea;

    /**
     * �﷨����
     */
    private JTextArea parsingTextArea;

    /**
     * �﷨����
     */
    private JTextArea yuYiFenXiTextArea;

    /**
     * �м������
     */
    private JTextArea zhongJianDaiMaTextArea;

    private JFrame frame;

    public static void main(String[] args) {
        MainWindows mainWindows = new MainWindows();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindows.createAndShowGUI();
            }
        });
    }

    /**
     * ��������
     */
    private void createAndShowGUI() {
        // ���� JFrame ʵ��
        frame = new JFrame("MyCompiler");
        // Setting the width and height of frame
        frame.setSize(1000, 600);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //���
        JPanel panel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        panel.add(centerPanel, BorderLayout.CENTER);
        frame.setContentPane(panel);
        //ѡ������
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(400, 20, 500, 500);
        centerPanel.add(tabbedPane);
        //������
        JToolBar toolBar = getJToolbar();
        toolBar.setMargin(new Insets(0, 10, 0, 20));
        panel.add(toolBar, BorderLayout.PAGE_START);

        //Դ��༭��
        sourceTextArea = new JTextArea();
        sourceTextArea.setEditable(true);
        sourceTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane sourceTextAreaScrollPane = new JScrollPane(sourceTextArea);
        sourceTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sourceTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sourceTextAreaScrollPane.setBounds(20, 20, 350, 500);
        centerPanel.add(sourceTextAreaScrollPane);

        //�ʷ�������
        lexingTextArea = new JTextArea();
        lexingTextArea.setEditable(false);
        lexingTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane lexingTextAreaScrollPane = new JScrollPane(lexingTextArea);
        lexingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lexingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("�ʷ�����", lexingTextAreaScrollPane);

        //�﷨����
        parsingTextArea = new JTextArea();
        parsingTextArea.setEditable(false);
        parsingTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane parsingTextAreaScrollPane = new JScrollPane(parsingTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("�﷨����", parsingTextAreaScrollPane);
        frame.setVisible(true);

        //���������
        yuYiFenXiTextArea = new JTextArea();
        yuYiFenXiTextArea.setEditable(false);
        yuYiFenXiTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane yuYiFenXiAreaScrollPane = new JScrollPane(yuYiFenXiTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("�������", yuYiFenXiAreaScrollPane);
        frame.setVisible(true);

        zhongJianDaiMaTextArea = new JTextArea();
        zhongJianDaiMaTextArea.setEditable(false);
        zhongJianDaiMaTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane zhongJianDaiMaAreaScrollPane = new JScrollPane(zhongJianDaiMaTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("�м����", zhongJianDaiMaAreaScrollPane);
        frame.setVisible(true);
    }

    /**
     * ������
     *
     * @return
     */
    private JToolBar getJToolbar() {
        JToolBar toolBar = new JToolBar();
        //���ļ�
        openFileBtn = new JButton("���ļ�");
        saveAsButton = new JButton("�����ļ�");
        lexButton = new JButton("�ʷ�����");
        parseButton = new JButton("�﷨����");
        saveSyntaxTreeButton = new JButton("�﷨�����Ϊ");
        yuYiFenXiButton = new JButton("�������");
        zhongJianDaiMaButton = new JButton("�����м����");
        saveSzhongJianDaiMaButton = new JButton("�м�������Ϊ");


        initAction();
        toolBar.add(openFileBtn);
        toolBar.add(saveAsButton);
        toolBar.add(lexButton);
        toolBar.add(parseButton);
        toolBar.add(saveSyntaxTreeButton);
        toolBar.add(yuYiFenXiButton);
        toolBar.add(zhongJianDaiMaButton);
        toolBar.add(saveSzhongJianDaiMaButton);
        toolBar.setFloatable(false);
        return toolBar;
    }

    private void initAction() {
        //���ļ�����ʾ���ļ���
        openFileBtn.addActionListener(e -> {
            //�õ��ļ�
            File file = showFileOpenDialog(frame);
            //�ļ�Ϊ�գ�ֱ��ȡ���ļ�ѡ���
            if (file == null) {
                return;
            }
            //��ȡ�ļ�
            try {
                String content = getContent(file);
                //��ʾ��Դ����
                sourceTextArea.setText(content);
            } catch (FileNotFoundException fileNotFoundException) {
                //�ļ�δ�ҵ��쳣���׳������
                JOptionPane.showMessageDialog(frame, "�ļ�δ�ҵ�.", "����", JOptionPane.ERROR_MESSAGE);
            }
        });

        //�ʷ�����
        lexButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Դ��Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                lexingTextArea.setText(myCompiler.myLex.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "δͨ���ʷ�������", "����", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //�﷨����
        parseButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Դ��Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                JOptionPane.showMessageDialog(frame, "��ͨ���﷨������", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                lexingTextArea.setText(myCompiler.myLex.toString());
                SimpleNode root = (SimpleNode) myCompiler.jjtree.rootNode();
                StringBuilder stringBuilder = new StringBuilder();
                root.dump("", stringBuilder);
                parsingTextArea.setText(stringBuilder.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "δͨ���﷨������", "����", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //�﷨�����Ϊ
        saveSyntaxTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (parsingTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "δ�����﷨����", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(parsingTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Դ�����Ϊ
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (sourceTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "δ�����﷨����", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(sourceTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //�������
        yuYiFenXiButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Դ��Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                //������������б�
                StringBuilder error = new StringBuilder();
                for (String s : myCompiler.errorList.getErrorList()) {
                    error.append(s + "\n");
                }
                if (error.toString().isEmpty()) {
                    error.append("�޷����������");
                }
                System.out.println(error);
                yuYiFenXiTextArea.setText(error.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "δͨ���ʷ����﷨������", "����", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //�м����
        zhongJianDaiMaButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "Դ��Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                //������������б�
                List<String> qt = myCompiler.codeGen.getQt();
                if (!qt.isEmpty()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String s : qt) {
                        stringBuilder.append(s + "\n");
                    }
                    zhongJianDaiMaTextArea.setText(stringBuilder.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "δͨ�����������", "����", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "δͨ���ʷ����﷨������", "����", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //Դ�����Ϊ
        saveSzhongJianDaiMaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (zhongJianDaiMaTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "δ�����м���룡", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(zhongJianDaiMaTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "�����ļ��쳣��", "����", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * ��ȡ�ļ�����
     *
     * @return
     */
    private String getContent(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        for (; ; ) {
            int rsz = 0;
            try {
                rsz = in.read(buffer, 0, buffer.length);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "��ȡ����.", "����", JOptionPane.ERROR_MESSAGE);
            }
            if (rsz < 0) {
                break;
            }
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }


    /**
     * ���ļ�
     *
     * @param parent frame
     * @return file
     */
    private File showFileOpenDialog(Component parent) {
        // ����һ��Ĭ�ϵ��ļ�ѡȡ��
        JFileChooser fileChooser = new JFileChooser();

        // ����Ĭ����ʾ���ļ���Ϊ��ǰ�ļ���
        fileChooser.setCurrentDirectory(new File("."));

        // �����ļ�ѡ���ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // �����Ƿ������ѡ
        fileChooser.setMultiSelectionEnabled(false);

        // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // ��������"ȷ��", ���ȡѡ����ļ�·��
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private File showFileSaveDialog(Component parent) {
        // ����һ��Ĭ�ϵ��ļ�ѡȡ��
        JFileChooser fileChooser = new JFileChooser();


        // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // ��������"����", ���ȡѡ��ı���·��
            File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }
}




