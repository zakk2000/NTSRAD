<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="phoneList" data-role="page">
	<div data-role="header" data-position="fixed">
		<h1>Save Phone</h1>
		<a href="#" data-rel="back" data-icon="arrow-l">Back</a>
	</div>
	
	<div data-role="content">
		
		<form id="phonesaveform" method="post" action="phoneSave.jsp">
			
			<div data-role="fieldcontain">
				<label for="pname">상품명:</label>
				<input id="pname" type="text" name="pname" value="갤럭시 S3"/> 
			</div>
			
			<div data-role="fieldcontain">
				<label for="pcompany">제조사:</label>
				<input id="pcompany" type="text" name="pcompany" value="삼성"/> 
			</div>
			
			<div data-role="fieldcontain">
				<label for="pprice">상품가:</label>
				<input id="pprice" type="number" name="pprice" value="300000"/> 
			</div>
			
			<div data-role="fieldcontain">
				<label for="pimage">이미지:</label>
				<select id="pimage" name="pimage">
					<option selected>phone05.png</option>
					<option>phone06.png</option>
					<option>phone07.png</option>
				</select>
			</div>
			
			<div data-role="fieldcontain">
				<fieldset data-role="controlgroup" data-type="horizontal">
					<legend>색상:</legend>
					<input id="radio1" type="radio" name="pcolor" value="black" checked="checked"/>
					<label for="radio1">Black</label>
					<input id="radio2"  type="radio" name="pcolor" value="white"/>
					<label for="radio2">White</label>
				</fieldset>
			</div>
			
			<input type="submit" value="등록"/>
		
		</form>
	
    </div>
	
	<div data-role="footer" data-position="fixed">
    	<div data-role="navbar">
    		<ul>
				<li><a href="#home" data-icon="home" data-transition="slide" data-direction="reverse">Home</a></li>
			</ul>
		</div>
    </div>
</div>
