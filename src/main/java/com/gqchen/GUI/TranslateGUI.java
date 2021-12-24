package com.gqchen.GUI;

import com.gqchen.common.GetTranslateResultUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TranslateGUI extends JFrame{
	private JFrame jFrame;
	private JPanel jPanelNorth;
	private JPanel jPanelSouth;
	private JTextField jTextField; 
	private JButton jButton;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	
	{	
		// jFrame的创建
		jFrame = new JFrame("翻译小插件");
		jFrame.setSize(500, 700);
		jFrame.setLocation(400, 300);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		// 组件的创建
		jPanelNorth = new JPanel();
		jPanelSouth = new JPanel();
		jPanelNorth.setLayout(new FlowLayout());
		jPanelSouth.setLayout(new BorderLayout());
		jPanelNorth.setBackground(Color.WHITE);
		jPanelSouth.setBackground(Color.GRAY);
		
		jTextField = new JTextField(30);
		jButton = new JButton("翻译");
		jTextArea = new JTextArea();
		jScrollPane = new JScrollPane(jTextArea);
		
		// 组件的设置
		jTextArea.setFont(new Font("", 5, 20));
		// 组件的添加
		jPanelNorth.add(jTextField);
		jPanelNorth.add(jButton);
		
		jPanelSouth.add(jScrollPane,BorderLayout.CENTER);
		
		jFrame.add(jPanelNorth,BorderLayout.NORTH);
		jFrame.add(jPanelSouth,BorderLayout.CENTER);
		
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
					List<String> result = GetTranslateResultUtil.getResult(word);
					jTextArea.append(word + ":");
					for (String string : result) {
						jTextArea.append("\t" + string + "\n");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
	}

}
