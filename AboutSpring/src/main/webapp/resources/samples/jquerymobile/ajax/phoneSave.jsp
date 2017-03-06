<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	String[] phone = {
		"",
		request.getParameter("pname"),
		request.getParameter("pimage"),
		request.getParameter("pcompany"),
		request.getParameter("pprice"),
		request.getParameter("pcolor")
	};
	
	List<String[]> phoneDb = (List<String[]>) session.getAttribute("phoneDb");
	String pno = null;
	
	for (int i = 1; i <= 20; i++) {
		
		pno = "p" + i;
		boolean exist = false;
		
		for (String[] p : phoneDb) {
			
			if (p[0].equals(pno)) {
				
				exist = true;
				break;
			
			}
		
		}
		
		if (!exist) break;
	
	}
	
	phone[0] = pno;
	
	phoneDb.add(phone);
	
	response.sendRedirect("phoneDetail.jsp?pno=" + pno);
%>
