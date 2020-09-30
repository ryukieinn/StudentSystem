//package controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import Entity.*;
//import Service.SystemDao;
//import Service.CustomerDao;
//
//@SuppressWarnings("serial")
//@WebServlet("/jsp/user.action")
//
//public class UsersSrevlet extends HttpServlet {
//	private CustomerDao sdao = new CustomerDao();
//	private SystemDao dao = new SystemDao();
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String opttype=request.getParameter("opttype");
//		String bname=null, url=null, tname=null, uid=null,fileName=null;
//		switch(opttype){
//		case "add":
//			request.setCharacterEncoding("utf-8");
//			bname=request.getParameter("bname");
//			request.setCharacterEncoding("utf-8");
//			url=request.getParameter("content");
//			request.setCharacterEncoding("utf-8");
//			tname=request.getParameter("tname");
//			request.getSession().setAttribute("bname", bname);
//			request.getSession().setAttribute("url", url);
//			request.getSession().setAttribute("tname", tname);
//			uid=(String)request.getSession().getAttribute("uid");
//			System.out.println(bname+url+tname);
//			//if(dao.addBlog(new Blog(bname,url,tname,"0"),uid)>0)
//			response.sendRedirect("addimg.jsp?bname="+bname+"&url="+url+"&tname="+tname+"");
//			break;
//		case "img":
//			uid = (String)request.getSession().getAttribute("uid");
//			bname=(String)request.getSession().getAttribute("bname");
//			url=(String)request.getSession().getAttribute("url");
//			tname=(String)request.getSession().getAttribute("tname");
//			System.out.println(bname+url+tname);
//	    	//���һ���ļ��ϴ��Ĺ�����
//	        DiskFileItemFactory dfif = new DiskFileItemFactory();//DiskFileItemFactory �Ǵ��� FileItem ����Ĺ���
//	        //�����ļ��ϴ���ʱ�洢�Ĵ�С8M
//	        dfif.setSizeThreshold(1024*1024*8);
//	        //ָ����ʱ�ļ�Ŀ¼��Ĭ��ֵΪ��ǰ�û���ϵͳ��ʱ�ļ�Ŀ¼����ͨ��System.getProperty(��java.io.tmpdir��)��ӡ�鿴��
//	        dfif.setRepository(new File("java.io.tmpdir"));
//	        System.out.println(System.getProperty("java.io.tmpdir"));
//	        //ʹ��servlet�������ļ��ϴ�
//	        ServletFileUpload upload = new ServletFileUpload(dfif);
//	        //�����ļ��ϴ��Ĵ�С
//	        upload.setSizeMax(1024*1024*50);
//	        //��õ�ǰ��������·��
//	        String uploadPath = this.getServletContext().getRealPath("");
//	        System.out.println("��ǰ������·��---->"+uploadPath);
//	        uploadPath = uploadPath+"..\\"+"img";
//	        File uploadDir = new File(uploadPath);
//	        if(!uploadDir.exists()){
//	            uploadDir.mkdir();
//	        }
//	        try {
//	            List<FileItem> formItem = upload.parseRequest(request);
//	            if(formItem != null && formItem.size() >0){
//	                //ѭ������Ҫ�ϴ����ļ�
//	                for(FileItem item : formItem){
//	                    if(!item.isFormField()){
//				//���Ҫ�ϴ����ļ�������
//	                        fileName = new File(item.getName()).getName();
//	                        //fileName = new Date().getTime()+(int)(Math.random()*100)+fileName;
//	 
//	                        //Ŀ���ļ�
//	                        String filePath = uploadPath+"\\"+fileName;
//	                        File storeFile = new File(filePath);
//	                        item.write(storeFile);
//	 
//	                        System.out.println(fileName);
//	                        System.out.println(filePath);
//	                        request.setAttribute("fileName", fileName);
//	                    }
//	                }
//	            }
//	        } catch (FileUploadException e) {
//	            e.printStackTrace();
//	            request.setAttribute("message", "ͼƬ�ϴ�ʧ��:"+e.getMessage());
//	            request.getRequestDispatcher("error.jsp").forward(request, response);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//			if(dao.addBlog(new Blog(bname,url,tname,fileName),uid)>0)
//	        //if(dao.addBlogimg(fileName,uid)>0)
//				response.sendRedirect("blog.action?opttype=queryAllblog");
//		}
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			doGet(request, response);
//	}
//}