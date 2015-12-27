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
  		$.ajax({
  			type: 'POST',
  		    url: 'http://localhost:8081/pay',
  		    data : 'content='+content+'&amount='+amount,
  		    success: function(result){
  		    	console.log(result);
  		    }
  		});
  	}
  </script>
</head>
<body>

<div class="container">
  <h2>List Group With Linked Items</h2>
  <div class="list-group">
	  <a href="#" onclick="buy(this); return false;" class="list-group-item active firstItem" content="First item" amount="1000">First item, 1000 IDR</a>
	  <a href="#" onclick="buy(this); return false;" class="list-group-item secondItem" content="Second item" amount="2000">Second item, 2000 IDR</a>
	  <a href="#" onclick="buy(this); return false;" class="list-group-item thirdItem" content="Third item" amount="3000">Third item, 3000 IDR</a>
  </div>
</div>

</body>
</html>
