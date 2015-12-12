package com.dph.chat;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatClient extends Frame
{
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	private boolean bConnected = false;
	private StringBuffer sb = null;

	public ChatClient(StringBuffer sb)
	{
		this.sb = sb;
		connect();
		new Thread(new RecVThread()).start();
	}

	public ChatClient()
	{
		connect();
		new Thread(new RecVThread()).start();
	}

	TextField tfText = new TextField();
	TextArea taContent = new TextArea();

	public static void main(String[] args)
	{
		// new ChatClient().launchFrame();
	}

	/*
	 * public void launchFrame() { setLocation(400, 300); this.setSize(300,
	 * 300);
	 * 
	 * add(tfText, BorderLayout.SOUTH); add(taContent, BorderLayout.NORTH);
	 * pack();
	 * 
	 * this.addWindowListener(new WindowAdapter() {
	 * 
	 * @Override public void windowClosing(WindowEvent e) { disConnection();
	 * System.exit(0); }
	 * 
	 * });
	 * 
	 * tfText.addActionListener(new TFListener());
	 * 
	 * setVisible(true); connect(); new Thread(new RecVThread()).start(); }
	 */

	// 连接服务器
	public void connect()
	{
		try
		{
			s = new Socket("localhost", 8889);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			System.out.print("connected!");

			bConnected = true;
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 与服务器断开连接
	public void disConnection()
	{
		try
		{
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//

	public void sendmsg(String str)
	{
		try
		{
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	private class TFListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String str = tfText.getText().trim();
			// System.out.print(str);
			// taContent.setText("ahdfjkada");
			// taContent.setText(str);
			tfText.setText("");

			try
			{
				// DataOutputStream dos=new
				// DataOutputStream(s.getOutputStream());
				dos.writeUTF(str);

				dos.flush();
				// dos.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

		}

	}
	// 接收线程
	private class RecVThread implements Runnable
	{

		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			try
			{
				while (bConnected)
				{
					String str = dis.readUTF();
					// System.out.println(str);
					taContent.setText(taContent.getText() + str + '\n');
					ChatClient.this.sb.append(str + "-");
				}
			} catch (SocketException e)
			{
				System.out.println("请稍后，系统正在关闭。。。。。。 ");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
