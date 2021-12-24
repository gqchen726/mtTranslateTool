package com.gqchen.GUI;

import com.gqchen.bean.Trans_Result;
import com.gqchen.common.BaiduTranslateResultUtil;
import com.gqchen.common.BaiduTranslateResultUtil1;
import com.gqchen.common.GetTranslateResultUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class BaiduTranslateGUI extends JFrame{

    private JFrame jFrame;
    private JPanel jPanelNorth;
    private JPanel jPanelSouth;
    private JTextField jTextField;
    private JButton jButton;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JComboBox jComboBoxFrom;
    private JComboBox jComboBoxTo;
    private JLabel jLabelFrom;
    private JLabel jLabelTo;
    private Trans_Result result;
    private String[] froms = {"-","en","zh","jp","kor"};


    {
        // jFrame的创建
        jFrame = new JFrame("百度接口翻译小插件");
        jFrame.setSize(600, 300);
        jFrame.setLocation(1400, 700);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // 组件的创建
        jPanelNorth = new JPanel();
        jPanelSouth = new JPanel();
        jPanelNorth.setLayout(new FlowLayout());
        jPanelSouth.setLayout(new BorderLayout());
        jPanelNorth.setBackground(Color.WHITE);
        jPanelSouth.setBackground(Color.GRAY);




        jTextField = new JTextField(10);
        jButton = new JButton("翻译");
        jTextArea = new JTextArea();
        jScrollPane = new JScrollPane(jTextArea);

        jLabelFrom = new JLabel("from:");
        jLabelTo = new JLabel("to:");
        jComboBoxFrom = new JComboBox(froms);
        jComboBoxTo = new JComboBox(froms);
        // 组件的设置
        jTextArea.setFont(new Font("", 5, 20));


        // 组件的添加
        jPanelNorth.add(jLabelFrom);
        jPanelNorth.add(jComboBoxFrom);
        jPanelNorth.add(jLabelTo);
        jPanelNorth.add(jComboBoxTo);
        jPanelNorth.add(jTextField);
        jPanelNorth.add(jButton);

        jPanelSouth.add(jScrollPane, BorderLayout.CENTER);

        jFrame.add(jPanelNorth, BorderLayout.NORTH);
        jFrame.add(jPanelSouth, BorderLayout.CENTER);

        jFrame.setVisible(true);

        // 添加事件监听器
        jButton.addActionListener(new ActionListener() {
            private String word;

            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空文本域
                jTextArea.setText("");

                // 获取输入框的文本内容
                word = jTextField.getText();
                jTextArea.setText("");
                try {
                    final String from = jComboBoxFrom.getSelectedItem().toString();
                    final String to = jComboBoxTo.getSelectedItem().toString();
                    if (from != "-" && to != "-") {
                        result =  BaiduTranslateResultUtil1.btru.getFinalResult(word,from,to);
                    } else {
                        // 默认选项auto->zh
                        result =  BaiduTranslateResultUtil1.btru.getFinalResult(word);
                    }

                    jTextArea.append("\n\nsrc : "+result.getSrc());
                    jTextArea.append("\n"+"dst : "+result.getDst());

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });



    }

}
