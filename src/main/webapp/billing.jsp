<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>   
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
	
<head>
	
	<style>
	table.table2  {
	  border-collapse: collapse;
	  width: 100%;
	}

	table.table2 th, td {
	  text-align: left;
	  padding: 8px;
	}
	
	table.table2 th {
		background-color: black;
		text-align: center;
		color: white;
		}

	table.table2 tr:nth-child(even) {
	  background-color: #90EE90;
	}
	
	 h1 {
	  font-style: italic;
	  text-align:center;
	}
	

	</style>
	
	<script type="text/javascript">

	      function checkParty() {
	        if(document.getElementById("partyName").value === ""){
			  alert('Please enter Party Name');
			  event.preventDefault();
			  }
			  if(document.getElementById("balance").value === ""){
			  		  alert('Please enter Balance Amount');
			  		  event.preventDefault();
			  		  }
	      }

	    </script>
	
</head>

<body>
	
	  <h1>Shakti Embroidery </h1>
	 


	<h3 style="margin-left:140px;">Enter billing details</h3>
	<f:form method="post" action="save" onsubmit="checkParty()" modelAttribute="productDetails">
		<div style= "margin-left:40%;margin-top: -3%;font-weight: bold;font-size: 20px;background-color: #f3e6ff; width:820px">
			&nbsp;Party Name :  <f:input path="partyName" style="height:30px;width:300px;" type="search" />&nbsp;&nbsp;    
			Balance Amount  : <f:input path="balance" style="height:30px;width:200px;" type="search" />
		</div>
		<table style="margin-left:50px;background-color: #f3e6ff; filter: alpha(opacity=40); opacity: 0.95;border:1px black solid; border-radius:6px;"  >
						<tr>
							<td>Bill Date:</td>
							<!--<fmt:formatDate value="${yourObject.date}" var="dateString" pattern="dd-MM-yyyy" />-->
							<td><f:input  path="date" type = "date" required="required" style="height:30px;width:300px"/></td>
						</tr>
						
						<tr>
							<td>Name:</td>
							<td><f:input path="name" placeholder = "School/Company name" style="height:30px;width:300px"  type="search"/></td>
						</tr>
						
			<tr>
				<td>Product :</td>
				<td>	<f:input path="productName" style="height:30px;width:300px" type="search"/>			
				     <!--
						<select>
						s<c:forEach items="${allProducts}"  var="product">
				         <option value="${product}">${product.name}</option>
				     </c:forEach>
				 </select>-->
					 <!--<form:select id="docNo" path="allProducts">
					            <form:option value="NONE" label="--- Select ---" />
					            <form:options items="${product}" itemValue="product" itemLabel="product"/>
					          </form:select>-->
							  
				 </td>
			</tr>
			
			<tr>
				<td>Quantity:</td>
				<td><f:input path="quantity" style="height:30px;width:300px" type="number"/></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><f:input path="price" style="height:30px;width:300px" type="number"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" onSubmit="checkParty()" style="margin-left:90px;height:30px;width:150px;font-size:14pt;"value="Save Changes"/>
				</td>
			</tr>
		</table>
	</f:form>
	<c:if test="${products.size() > 0}">
	<table border="1" width="70%" cellpadding="1" class="table2">  
		  <tr><th>Date</th><th>Name</th><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>  
		     <c:forEach var="product" items="${products}">   
		     <tr>  
			 
		     <td>${product.actualDate}</td>  
			 <td>${product.name}</td>  
		     <td>${product.productName}</td>  
		     <td>${product.quantity}</td>  
		     <td>${product.price}</td>  
			 <td>${product.price * product.quantity}</td> 
			 <td>	<f:form method="get" action="delete" modelAttribute="productDetails">
					 <input type = "hidden" name="sno" value=${product.sno}></input>
			 		 <input type="submit" value="Delete"/>
			 		 </f:form></td>
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
			 <f:form method="post" action="savefinal" modelAttribute="productDetails">
			 	<input type="submit" value="Submit Bill" style="margin-left:30%;height:30px;width:150px;font-size:14pt;"/>
			 </f:form><f:form method="post" action="newbill" modelAttribute="productDetails">
			 			 	<input type="submit" value="New Bill" style="margin-left:42%;margin-top:-3%;height:30px;width:150px;font-size:14pt;"/>
			 			 </f:form>
					 </c:if>
</body>

</html>