package com.dph.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer
{
	boolean started = false;
	ServerSocket ss = null;

	List<Client> clients = new ArrayList<Client>();

	public void start()
	{
		try
		{
			ss = new ServerSocket(8889);
			started = true;
		} catch (BindException e)
		{
			System.out.print("端口使用中。。。。。");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (started)
			{
				Socket s = ss.accept();
				Client c = new Client(s);

				System.out.println("a client connected!");
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				ss.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private class Client implements Runnable
	{
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;

		public Client(Socket s)
		{
			this.s = s;
			try
			{
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		public void send(String str)
		{
			try
			{
				dos.writeUTF(str);
			} catch (IOException e)
			{
				clients.remove(this);
				System.out.println("对方推出了，我从List被删除");
			}
		}

		public void run()
		{
			try
			{
				while (bConnected)
				{
					String str = dis.readUTF();
					System.out.println(str);
					for (int i = 0; i < clients.size(); i++)
					{
						Client c = clients.get(i);
						c.send(str);
					}

					/*
					 * for(Iterator<Client> it=clients.iterator();it.hasNext();)
					 * { Client c=clients.get(i); c.send(str); }
					 */

					/*
					 * Iterator<Client> it=clients.iterator();
					 * while(it.hasNext()) { Client c=it.next(); c.send(str); }
					 */
				}
			} catch (EOFException e)
			{
				System.out.print("Client closed");
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally
			{
				try
				{
					if (s != null)
						s.close();
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
