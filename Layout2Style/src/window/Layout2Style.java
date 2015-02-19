package window;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSeparator;

public class Layout2Style {

	private JFrame frmAndroidLayoutstyle;
	private Checkbox checkboxParent;
	private Checkbox checkboxName;
	private TextField textName;
	private TextField textParent;
	private Button buttonCreate;
	private Button buttonClear;
	private Button buttonCopy;
	private TextArea textAreaLayout;
	private TextArea textAreaStyle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout2Style window = new Layout2Style();
					window.frmAndroidLayoutstyle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Layout2Style() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAndroidLayoutstyle = new JFrame();
		frmAndroidLayoutstyle.setResizable(false);
		frmAndroidLayoutstyle.setTitle("Android Layout \u8F6C Style");
		frmAndroidLayoutstyle.setBounds(100, 100, 750, 519);
		frmAndroidLayoutstyle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAndroidLayoutstyle.getContentPane().setLayout(
				new BoxLayout(frmAndroidLayoutstyle.getContentPane(),
						BoxLayout.X_AXIS));

		Panel panel = new Panel();
		frmAndroidLayoutstyle.getContentPane().add(panel);
		panel.setLayout(null);

		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 0, 734, 33);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textName = new TextField();
		textName.setColumns(20);
		panel_1.add(textName);

		checkboxName = new Checkbox("\u6307\u5B9Aname");

		panel_1.add(checkboxName);

		JSeparator separator = new JSeparator();
		panel_1.add(separator);

		textParent = new TextField();
		textParent.setColumns(20);
		panel_1.add(textParent);

		checkboxParent = new Checkbox("\u6307\u5B9Aparent");
		panel_1.add(checkboxParent);

		buttonCreate = new Button("\u751F\u6210");
		panel_1.add(buttonCreate);

		buttonClear = new Button("\u6E05\u7A7A");
		panel_1.add(buttonClear);

		buttonCopy = new Button(
				"\u590D\u5236Style\u5230\u7C98\u8D34\u677F");
		panel_1.add(buttonCopy);

		Panel panel_2 = new Panel();
		panel_2.setBounds(10, 66, 714, 156);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		textAreaLayout = new TextArea();
		panel_2.add(textAreaLayout, BorderLayout.CENTER);

		Panel panel_3 = new Panel();
		panel_3.setBounds(10, 257, 714, 214);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		textAreaStyle = new TextArea();
		textAreaStyle.setEditable(false);
		panel_3.add(textAreaStyle, BorderLayout.CENTER);

		Label label = new Label("Style :");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(10, 228, 69, 23);
		panel.add(label);

		Label label_1 = new Label("Layout :\uFF08\u5EFA\u8BAE\u5148\u5728IDE\u4E2D\u683C\u5F0F\u5316\u4E00\u4E0BLayout\u7684\u4EE3\u7801\uFF09");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(10, 39, 309, 23);
		panel.add(label_1);

		init();

	}

	/**
	 * 初始化
	 */
	private void init() {
		checkboxName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkboxName.setState(e.getStateChange() == ItemEvent.SELECTED);
				initName();
			}
		});

		checkboxParent.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkboxParent.setState(e.getStateChange() == ItemEvent.SELECTED);
				initParent();
			}
		});

		buttonCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textAreaStyle.setText(format(textAreaLayout.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaLayout.replaceRange("", 0, textAreaLayout.getText()
						.length());
				textAreaStyle.replaceRange("", 0, textAreaStyle.getText()
						.length());
			}
		});
		
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(textAreaStyle.getText().trim())) {
					return;
				}
				Transferable contents = new StringSelection(textAreaStyle.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contents, null);
			}
		});

		initParent();
		initName();
	}

	/**
	 * 转换算法
	 * @param text
	 * @return
	 * @throws IOException
	 */
	protected String format(String text) throws IOException {
		if (text == null || "".equals(text)) {
			return "";
		}

		String name = null;
		String parent = null;
		StringBuilder builder = new StringBuilder();
		String tab = "";
		if (checkboxName.isEnabled() && checkboxName.getState()) {
			tab = "\t";
			name = textName.getText().trim();
			builder.append("<style name=\"").append(name).append('"');
			if (checkboxParent.isEnabled() && checkboxParent.getState()) {
				parent = textParent.getText().trim();
				builder.append(" parent=\"").append(parent).append("\">");
			} else {
				builder.append('>');
			}
			builder.append('\n');
		}
		BufferedReader reader = new BufferedReader(new StringReader(text));
		String temp = null;
		while ((temp = reader.readLine()) != null) {
//			System.out.println(temp);
			try {
				temp = temp.trim();
				// 去除标签行<XX>和</XX>
				if (temp.startsWith("<") || temp.startsWith("</")
						|| temp.startsWith("xmlns:")) {
					continue;
				}
				// 处理</Hahahaha>此类标签
				if (temp.indexOf("</") > 0) {
					temp = temp.substring(0, temp.indexOf("</"));
				}
				// 跳过空字符串
				if ("".equals(temp)) {
					continue;
				}
				// 处理 /> 结尾的行
				if (temp.endsWith("/>")) {
					temp = temp.substring(0, temp.length() - 2).trim();
				}
				// 处理 > 结尾的行
				if (temp.endsWith(">")) {
					temp = temp.substring(0, temp.length() - 1).trim();
				}
				String part1 = temp.substring(0, temp.indexOf('=')).trim();
                String part2 = temp.substring(part1.length() + 2, temp.length()-1).trim();
                builder.append(tab + "<item name=\"" + part1 + "\">" + part2 + "</item>"+'\n');
			} catch (Exception e) {
				// 只要遇到异常，说明改行的格式不符合，跳过
			}
		}
		reader.close();
		if (checkboxName.isEnabled() && checkboxName.getState()) {
			builder.append("</style>");
		}
//		System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * 初始化parent
	 */
	private void initParent() {
		textParent.setEnabled(checkboxName.getState()
				&& checkboxParent.getState());
	}

	/**
	 * 初始化name
	 */
	private void initName() {
		textName.setEnabled(checkboxName.getState());
		checkboxParent.setEnabled(checkboxName.getState());
		initParent();
	}

}
