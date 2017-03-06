<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	List<String[]> phoneDb = (List<String[]>) session.getAttribute("phoneDb");
	if (phoneDb == null) {
		
		phoneDb = new ArrayList<String[]>();
		phoneDb.add(new String[] {
			"p1", "갤럭시 A", "phone01.png", "삼성", "600000", "검정"
		});
		phoneDb.add(new String[] {
			"p2", "옵티머스 원", "phone02.png", "엘지", "500000", "흰색"
		});
		phoneDb.add(new String[] {
			"p3", "갤럭시 S", "phone03.png", "삼성", "700000", "검정"
		});
		phoneDb.add(new String[] {
			"p4", "갤럭시 S2", "phone04.png", "삼성", "800000", "흰색"
		});
		
		session.setAttribute("phoneDb", phoneDb);
	
	}
%>

<div id="phoneList" data-role="page">
	<div data-role="header">
		<h1>Phone List</h1>
	</div>  
	
	<div data-role="content">
		<ul data-role="listview">
			<%for(String[] phone : phoneDb) {%>
					<li>
						<a href="phoneDetail.jsp?pno=<%=phone[0]%>">
							<table>
								<tr>
									<td style="padding-right: 5px">
										<img src="../../../images/<%=phone[2]%>"  style="width:30px; height:50px"/>
									</td>
									<td style="padding-left: 5px"><%=phone[1]%></td>
								</tr>
							</table>
						</a>
					</li>
			<%}%>
		</ul>
    </div>

	<div data-role="footer" data-position="fixed">
    	<div data-role="navbar">
    		<ul>
				<li><a href="#home" data-icon="home"  data-transition="slide" data-direction="reverse">Home</a></li>
				<li><a href="phoneSaveForm.jsp" data-icon="plus"  data-transition="slide">Register Phone</a></li>
			</ul>
		</div>
    </div>
</div>