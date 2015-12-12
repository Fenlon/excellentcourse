package com.dph.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 此类为过滤器，专门压缩服务器打给浏览器的页面文件，有效的控制了流量
 */
public class GzipFilter implements Filter
{

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		MyResponse myresponse = new MyResponse(response);

		chain.doFilter(request, myresponse);
		// response.getwriter
		// response.getOutputStream
		byte out[] = myresponse.getBuffer();
		// System.out.println("压缩前" + out.length);

		byte gzipout[] = gzip(out);
		// System.out.println("压缩后" + gzipout.length);

		response.setHeader("content-encoding", "gzip");
		response.setHeader("content-length", gzipout.length + "");
		response.getOutputStream().write(gzipout);
	}

	public byte[] gzip(byte b[]) throws IOException
	{

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(b);
		gout.close();
		return bout.toByteArray();
	}

	class MyResponse extends HttpServletResponseWrapper
	{
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;

		private HttpServletResponse response;

		public MyResponse(HttpServletResponse response)
		{
			super(response);
			this.response = response;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException
		{
			return new MyServletOutputStream(bout); // myresponse.getOutputStream().write("hahah");
		}

		@Override
		public PrintWriter getWriter() throws IOException
		{
			pw = new PrintWriter(new OutputStreamWriter(bout,
					response.getCharacterEncoding()));
			return pw; // MyResponse.getWriter().write(bout);乱码错误
		}

		public byte[] getBuffer()
		{
			if (pw != null)
			{
				pw.close();
			}
			return bout.toByteArray();
		}
	}

	class MyServletOutputStream extends ServletOutputStream
	{

		private ByteArrayOutputStream bout;

		public MyServletOutputStream(ByteArrayOutputStream bout)
		{
			this.bout = bout;
		}

		@Override
		public void write(int b) throws IOException
		{
			bout.write(b);
		}

	}

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
