<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Danh sách sản phẩm</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  	$(document).ready(function(e){
  	});
  	
  	function buy(obj) {
  		var content = obj.getAttribute("content");
  		var amount = obj.getAttribute("amount");
  		var country = '<%=(request.getParameter("country") == null ? "" : request.getParameter("country"))%>';
  		$.ajax({
  			type: 'POST',
  		    url: 'http://localhost:8081/pay',
  		    data : 'content='+content+'&amount='+amount+"&country="+country,
  		  	dataType: 'json',
  		    success: function(result){
  		    	console.log(result);
  		    	renderForm(result);
  		    }
  		});
  	}
  </script>
</head>
<body>

<div class="container">
  <br/>
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
    <% if("VN".equals(request.getParameter("country"))){ out.write("Việt Nam"); } else {%> 
    <% if("ID".equals(request.getParameter("country"))) { out.write("Indo");} else {%> 
    <% out.write("Chọn thị trường"); }}%> 
    <span class="caret"></span></button>
    <ul class="dropdown-menu">
      <li><a href="<%=request.getContextPath()%>?country=VN">Việt Nam</a></li>
      <li><a href="<%=request.getContextPath()%>?country=ID">Indo</a></li>
    </ul>
  </div>
  
  <h2>Danh sách sản phẩm</h2>
  <div class="list-group">
	  <a href="#" onclick="buy(this); return false;" class="list-group-item active firstItem" content="First item" amount="1000" data-toggle="modal" data-target="#myModal">First item, 1000 IDR</a>
	  <a href="#" onclick="buy(this); return false;" class="list-group-item secondItem" content="Second item" amount="2000" data-toggle="modal" data-target="#myModal">Second item, 2000 IDR</a>
	  <a href="#" onclick="buy(this); return false;" class="list-group-item thirdItem" content="Third item" amount="3000" data-toggle="modal" data-target="#myModal">Third item, 3000 IDR</a>
  </div>
  
  
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Chọn phương thức thanh toán</h4>
        </div>
        <div class="modal-body">
          <p id="paymentMethodContent">...</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</div>

<script type="text/javascript">
function renderForm(responseText){
	var response = responseText; //JSON.parse(responseText);
	//if(response["code"]==0){
	//	alert('completed')
	//} else {
	{
		if(response.checkoutUrl){
			window.location= response.checkoutUrl;
		} else {
			var groupsElement = document.getElementById('paymentMethodContent');
			//Clear UI
			groupsElement.innerHTML = "";
			var groups = response.groups;
			for(var i = 0;i<groups.length;i++){
				var group = groups[i];
				var form = document.createElement('FORM');
				form.action = group["target"];
				form.method = "POST";
				var fieldset = document.createElement("fieldset");
				var legend = document.createElement('legend');
				legend.appendChild(document.createTextNode(group["label"]));
				fieldset.appendChild(legend);
				
				/*
					add hidden transation field
				*/
				var input = document.createElement('input');
				input.type = 'hidden';
				input.name="transaction";
				input.value = response["transaction"];
				fieldset.appendChild(input);
				
				var params=group.params;
				if(params!=null){
					for(var j = 0;j<params.length;j++){
						var param = params[j];
						var type = param["type"];
						if(type=="number" || type == "text"||type=='email'){
							var input = document.createElement('input');
							input.type = type;
							input.placeholder = param["label"];
							input.name = param["name"];
							fieldset.appendChild(input);
							fieldset.appendChild(document.createElement('br'));
						} else if(type=='submit'){
							var input = document.createElement('input');
							input.type = 'submit';
							input.value = param["label"];
							input.name = param["name"];
							fieldset.appendChild(input);
							fieldset.appendChild(document.createElement('br'));
						} else if(type=='select'){
							fieldset.appendChild(document.createTextNode(param["label"]));
							fieldset.appendChild(document.createElement('br'));
							
							var select = document.createElement('select');
							select.name = param["name"];
							var options = param["options"];
							for(var k = 0;k<options.length;k++){
								var option = options[k];
								var optionElement = document.createElement('option');
								optionElement.value = option["value"];
								optionElement.appendChild(document.createTextNode(option["label"]))
								select.appendChild(optionElement)
							}
							fieldset.appendChild(select);
							fieldset.appendChild(document.createElement('br'));
						} else if(type=='link'){
							var anchor = document.createElement('a');
							anchor.href = option["name"];
							anchor.target="_blank";
							anchor.appendChild(document.createTextNode(option["label"]))
							fieldset.appendChild(anchor);
							fieldset.appendChild(document.createElement('br'));
						} else {
							var msg = document.createElement('div');
							msg.appendChild(document.createTextNode(option["label"]))
							fieldset.appendChild(msg);
						}
					}
				}
				form.appendChild(fieldset);
				groupsElement.appendChild(form);
				
			}
			$('#paymentMethodContent form').submit(function(event) {
			    event.preventDefault();
			    console.log($(this).serialize());
			    $.ajax({
			        type: 'POST',
			        url: $(this).attr('action'),
			        data: $(this).serialize(),
			        success: function(result){
			        	//console.log(result);
				    	renderForm(result);
				    },
				    failure: function(response, opts) {
				    	console.log("failure " + response);
				    }
			    })/* .done(renderForm).fail(function(data) {
			    	alert(data.responseText);
			    }) */;
			});
		}
		//document.getElementById("debug").innerHTML = responseText;
	}
}
</script>
</body>
</html>
