<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门</title>
<link href=" bootstrap/css/bootstrap.css" rel="stylesheet" />
<style type="text/css">
#main {
	width: 550px;
	margin: 20px auto;
}

#pro, #noPro {
	width: 700px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 3px;
	background: #ccc;
}

#brn {
	width: 120px;
	margin: 20px auto;
}

#add {
	margin-right: 50px;
}

.pro {
	background: #337ab7;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-left: 5px;
	color: white;
	padding: 0 20px;
	margin-top: 10px;
	border-radius: 3px;
}

.select {
	background: #d9534f;
}
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
	$(".pro").click(function(){
		
		$(this).toggleClass("select")
		
	})
	$("#add").click(function(){
	
		if($("#noPro").find(".select").length>0){
	
			var proId="";	
		    $("#noPro").find(".select").each(function(){
		    proId+=$(this).data("id")+",";		
			})
		proId= proId.substring(0,proId.length-1);
		
			
			
			
			
			$.ajax({
				url:"d2p",
				type:"post",
				data:{ type:"addBatch",depId:${dep.id},proId:proId},
				dataType:"text",
				success:function(data){
					if(data=="true"){
			 			var pro=$("#noPro").find(".select");
						pro.removeClass("select");
						$("#pro").append(pro);
					}
					
				}
				
			})
			
			
			
		}else{
			alert("请选择数据");
		}
	})
		$("#delete").click(function(){
			
			if($("#pro").find(".select").length>0){
		
				var proId="";
				
				
			    $("#pro").find(".select").each(function(){
			    proId+=$(this).data("id")+",";		
				})
				proId= proId.substring(0,proId.length-1);
			//	alert(proId);
			
				$.ajax({
					url:"d2p",
					type:"post",
					data:{ type:"deleteBatch",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							var pro=$("#pro").find(".select");
							pro.removeClass("select");
							$("#noPro").append(pro);
						}
						
					}
					
				})
				
				
				
			}else{
				alert("请选择数据");
			}
			
		
	})
	  var noProLeft=$("#noPro").offset().left;
	  var noProTop=$("#noPro").offset().top;
	  var noProWidth=parseFloat($("#noPro").css("width"));
	  var noProHeight=parseFloat($("#noPro").css("height"));
	  
	  var proLeft=$("#pro").offset().left;
	  var proTop=$("#pro").offset().top;
	  var proWidth=parseFloat($("#pro").css("width"));
	  var proHeight=parseFloat($("#pro").css("height"));
	  var startLeft;
	  var startTop;
	 $( ".pro" ).draggable({
	      start: function() {
	    	  startLeft=$(this).offset().left;
		      startTop=$(this).offset().top;
		      startId=$(this).parent().attr('id');
	        },	        
	        stop: function() {
	       var stopLeft=$(this).offset().left;
	       var stopTop=$(this).offset().top;
	    
	     if("noPro"==(startId)){
	
	       if(stopLeft>=proLeft&&stopLeft<=proLeft+proWidth&&stopTop>=proTop&&stopTop<=proTop+proHeight){
		   var pro=$(this);
		   var proId=$(this).data("id");
			$.ajax({
				url:"d2p",
				type:"post",
				data:{ type:"add2",depId:${dep.id},proId:proId},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						pro.css("position","static");
						$("#pro").append(pro);
						pro.css("position","relative");
						pro.css("left","0");
						pro.css("top","0");
					}	
				}	
			})  
	  }else {
	         $(this).offset({left:startLeft,top:startTop})
	  } 
	       
	       
	 }else{
		 
		 if(stopLeft>=noProLeft&&stopLeft<=noProLeft+noProWidth&&stopTop>=400&&stopTop<=650){
			  var pro=$(this);
			  var proId=$(this).data("id");
				$.ajax({
						url:"d2p",
						type:"post",
						data:{ type:"delete2",depId:${dep.id},proId:proId},
						dataType:"text",
						success:function(data){
							if(data=="true"){
						
								pro.css("position","static");
								$("#noPro").append(pro);
								pro.css("position","relative");
								pro.css("left","0");
								pro.css("top","0");
								
							}
							
						}
							
				})  
		  }else {
		         $(this).offset({left:startLeft,top:startTop})
		     
		  }  
	 }
	       
	       
	       
	       }
	      });
	 
	
	 	
	     
	 
	 
	 
	 
	 
	})
</script>
</head>
<body>


	<div id="main">
		<h2>${dep.name }</h2>
		<div id="pro">

			<c:forEach items="${list }" var="pro">
				<div class="pro" data-id="${pro.id }">${pro.name}</div>
			</c:forEach>

		</div>
		<div id="btn">
			<button id="add" type="button" class="btn btn-primary">增加</button>
			<button id="delete" type="button" class="btn btn-danger">删除</button>
		</div>

		<div id="noPro">
			<c:forEach items="${noList }" var="pro">
				<div class="pro" data-id="${pro.id }">${pro.name}</div>
			</c:forEach>
		</div>
	</div>



</body>
</html>









































