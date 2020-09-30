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
//import Service.MyDao;
// 
//@WebServlet("/jsp/UploadServlet")
//public class UploadServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//	MyDao dao = new MyDao();
//	String hid,uid;
//    public UploadServlet() {
//        super();
//    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request,response);
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        uid = (String)request.getSession().getAttribute("uid");
//        hid=request.getParameter("hid");
//    	//���һ���ļ��ϴ��Ĺ�����
//        DiskFileItemFactory dfif = new DiskFileItemFactory();//DiskFileItemFactory �Ǵ��� FileItem ����Ĺ���
//        //�����ļ��ϴ���ʱ�洢�Ĵ�С8M
//        dfif.setSizeThreshold(1024*1024*8);
//        //ָ����ʱ�ļ�Ŀ¼��Ĭ��ֵΪ��ǰ�û���ϵͳ��ʱ�ļ�Ŀ¼����ͨ��System.getProperty(��java.io.tmpdir��)��ӡ�鿴��
//        dfif.setRepository(new File("java.io.tmpdir"));
//        System.out.println(System.getProperty("java.io.tmpdir"));
//        //ʹ��servlet�������ļ��ϴ�
//        ServletFileUpload upload = new ServletFileUpload(dfif);
//        //�����ļ��ϴ��Ĵ�С
//        upload.setSizeMax(1024*1024*50);
//        //��õ�ǰ��������·��
//        String uploadPath = this.getServletContext().getRealPath("");
//        System.out.println("��ǰ������·��---->"+uploadPath);
//        uploadPath = uploadPath+"..\\"+"upload";
//        File uploadDir = new File(uploadPath);
//        if(!uploadDir.exists()){
//            uploadDir.mkdir();
//        }
//        try {
//            List<FileItem> formItem = upload.parseRequest(request);
//            if(formItem != null && formItem.size() >0){
//                //ѭ������Ҫ�ϴ����ļ�
//                for(FileItem item : formItem){
//                    if(!item.isFormField()){
//			//���Ҫ�ϴ����ļ�������
//                        String fileName = new File(item.getName()).getName();
//                        //fileName = new Date().getTime()+(int)(Math.random()*100)+fileName;
// 
//                        //Ŀ���ļ�
//                        String filePath = uploadPath+"\\"+fileName;
//                        File storeFile = new File(filePath);
//                        item.write(storeFile);
// 
//                        System.out.println(fileName);
//                        System.out.println(filePath);
//                        request.setAttribute("fileName", fileName);
//                        dao.addMyhomework(uid,hid,fileName);
//                    }
//                }
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//            request.setAttribute("message", "�ļ��ϴ�ʧ��:"+e.getMessage());
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        request.getRequestDispatcher("upload.jsp").forward(request, response);
//    }
//}
// 