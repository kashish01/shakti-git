<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>   
<html>
	
<head>
	
	<style>	
		
		table  {
			  border-collapse: collapse;
			  width: 100%;
			}

			table th, td {
			  text-align: left;
			  padding: 8px;
			}
			
			table th {
				background-color: black;
				text-align: center;
				color: white;
				}

			table tr:nth-child(even) {
			  background-color: #90EE90;
			}	
		
	h1 {
	  font-style: italic;
	  text-align:center;
	}
	</style>
	
</head>

<body>
	
	
	<br/>
	<h1>Shakti Embroidery </h1>
      <h3 style="margin-left:1%">Party Name : <c:out value = "${products[0].partyName}"/> <br/>
		Balance Amount : <c:out value = "${products[0].balance}"/></h3>
	  
	  <table border="2" width="70%" cellpadding="2">  
	  <tr><th>Date</th><th>Name</th><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>  
	     <c:forEach var="product" items="${products}">   
	     <tr>  
		 
	     <td>${product.actualDate}</td>  
		 <td>${product.name}</td>  
	     <td>${product.productName}</td>  
	     <td>${product.quantity}</td>  
	     <td>${product.price}</td>  
		 <td>${product.price * product.quantity}</td> 
	     </tr>  
	     </c:forEach>  
		 <tr style="font-weight: bold; background-color: pink;"> 
		 		 <td colspan="5" align ="center" style="">Grand Total</td>
		 		 <c:set var="priceTotal" value="${0}" />
		 		 				<c:forEach var="product" items="${products}"> 
		 		 					<c:set var="priceTotal" value="${priceTotal + product.price * product.quantity}" />
		 		 				</c:forEach>
		 		 <td>
		 			${priceTotal}
		 		 </td>
		 		 </tr> 
	     </table>  
  
  	<br/>
	<f:form method="post" action="print" modelAttribute="productDetails">
	<input type = "submit" value= "Print final bill" style="margin-left:30%;height:30px;width:150px;font-size:14pt;"></input>
	</f:form>
	<f:form method="post" action="newbill" modelAttribute="productDetails">
	 	<input type="submit" value="New Bill" style="margin-left:42%;margin-top:-3%;height:30px;width:150px;font-size:14pt;"/>
	 </f:form>
</body>

</html>