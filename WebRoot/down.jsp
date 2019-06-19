<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
     String filePath =request.getParameter("filePath");    
     String fileName = request.getParameter("fileName");   
    if(fileName!=null&&filePath!=null){    
         response.setContentType("application/x-download");  
         response.addHeader("Content-Disposition","attachment;filename=" + java.net.URLEncoder.encode(fileName,"UTF-8"));    
         try{  
             out.clear();  
             out=pageContext.pushBody();  
         }catch(Throwable e){ 
              e.printStackTrace();  
       }  
      try{  
         RequestDispatcher dis = application.getRequestDispatcher(filePath); 
              dis.forward(request,response);  
   }catch(Throwable e){  
    e.printStackTrace();  
     }finally{  
    response.flushBuffer();  
   }  
   }  
%> 