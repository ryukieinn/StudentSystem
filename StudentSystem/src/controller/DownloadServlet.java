package controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.MyDao;
 
@WebServlet("/jsp/DownLoadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MyDao dao = new MyDao();
    public DownloadServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename,hid;
        hid=request.getParameter("hid");
		filename = dao.queryMygbook(hid);
		//ʵ���ļ����� ������Ӧͷ
		String fileName = this.getServletContext().getRealPath("")+"..\\"+"download\\"+filename;
		filename = new Date().getTime()+(int)(Math.random()*100)+filename;
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		System .out.print("fileName"+fileName);
		//��ʹ���ļ������� ���ļ������ڴ��� ��ʹ������� ���ļ�������û�
		File file = new File(fileName);
		FileInputStream fileIn = new FileInputStream(file);
		//׼��һ��������
		byte[] b = new byte[(int)file.length()];
		//���ļ����뻺������
		fileIn.read(b);
		//�����Ӧ�������
		ServletOutputStream sout = response.getOutputStream();
//����response.getOutputStream()�������� ServeltOutputStream ��������ͻ���д���ļ����ݡ�
		sout.write(b);
		sout.close();
	}
}