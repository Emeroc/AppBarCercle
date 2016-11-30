<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="orders.new.pagetitle" /> &gt; <s:text name="orders.new.chooseproducts.pagetitle" /></title>
	<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript">
	var selectLi = null;
	var newOrder = {	
			products: {},
			idProduct: 0,
			
			addCartLine: function(id, name, menuId, price) {
				var currentId = this.idProduct;
				
				this.products[currentId] = {id: id, name: name, menuId: menuId, price: format.toFloat(price.replace(',', '.'))};
					
				var remove = $('<i class="icon-remove">').click(function() {
					delete newOrder.products[currentId];								
					$('#product' + currentId).remove();
					if (id == -1) {
						delete newOrder.products[currentId];
						$('#product' + currentId).remove();
						currentId++;
						// Delete menuparts if menu is deleted
						while(currentId in newOrder.products){
								//si !=0 cela signifie qu'on est plus dans le menu
								if(newOrder.products[currentId].price!=0)
									break;
								delete newOrder.products[currentId];
								$('#product' + currentId).remove();
								currentId++;
							
						}
					}
					newOrder.calculateTotalPrice();		
				});
			
				if (price == 0) {
					$('#cart').append(
						$(document.createElement('tr')).attr('id', 'product' + currentId)
							.append($(document.createElement('td')).html(name))
					);
				}
				else {
					$('#cart').append(
						$(document.createElement('tr')).attr('id', 'product' + currentId)
							.append($(document.createElement('td')).html(name))
							.append($(document.createElement('td')).html(format.toEuros(price.replace(',', '.')) + ' &euro;'))
							.append($(document.createElement('td')).append(remove).css('cursor', 'pointer')
						)
					);
				}
				
				this.idProduct++;
				this.calculateTotalPrice();
			},
			
			calculateTotalPrice: function() {
				var total = 0.0;
				for (var p in this.products) {
					total += this.products[p].price;
				}			
				$('.total-price').html(format.toEuros(total));
			}
		};
		
		$(document).ready(function() {
			$('#okButton').click(function() {				
				var menuid = selectLi.attr('data-menuid');
				var menuname = selectLi.find('h2').html();
				var menuprice = selectLi.find('h3').html();
				var ok = true;
				$('#menuSelection .products-list').each(function(i) {
					if (!($(this).find('input:nth-child(2)').val() > 0)) {
						alert('<s:text name="orders.new.chooseproducts.choosemenu.notcompleted" />');
						ok = false;
					}
				});
				
				if (ok) {
					newOrder.addCartLine(-1, "Menu "+menuname, menuid, menuprice);
					$('#menuModal').modal('hide');
					$('#menuSelection .products-list').each(function(i) {
						newOrder.addCartLine($(this).find('input:nth-child(2)').val(), $(this).find('input:nth-child(3)').val()+" (Menu "+menuname+")", menuid, '0');
					});
				}
			});
			$('#nav .list-viewer li').click(function() {
				if ($(this).attr('data-categoryid') != -1) {
					$(document).scrollTop($('#category' + $(this).attr('data-categoryid')).offset().top - 180);
				} else {
					// Show modal for menu selection
					$('#menuModal').modal('show');
				}
			}).mouseover(function() {
				$(this).addClass('list-viewer-hover');
			}).mouseout(function() {
				$(this).removeClass('list-viewer-hover');			
			});
			
			$('#form-order').submit(function() {
				var count = 0;
				$('#formData').html('');
				$.each(newOrder.products, function(index) {
					$('#formData').append($('\
							<input type="hidden" name="order[' + count + '].id" value="' + this.id + '">\
							<input type="hidden" name="order[' + count + '].name" value="' + this.name + '">\
							<input type="hidden" name="order[' + count + '].price" value="' + this.price +'">\
							<input type="hidden" name="order[' + count + '].menuId" value="' + this.menuId +'">\
					'));
					count++;
				});
			});
		
			$('.products-list li').attr('unselectable','on').css('MozUserSelect','none')
				.click(function() {
					newOrder.addCartLine($(this).attr('data-productid'), $(this).find('h2').html(), -1, $(this).find('p').html());
				}).mouseover(function() {
					$(this).addClass('list-viewer-hover');
				}).mouseout(function() {
					$(this).removeClass('list-viewer-hover');			
				});
			
			

			$('#nav-wrapper').height(140);
			$('#nav').width($("#nav").width());
			$('#nav').affix({ offset: $('#nav').offset().top - 40  });
			$('#cart-wrapper').height(140);
			$('#cart-wrapper').width($("#cart-wrapper").width());
			$('#cart-wrapper').affix({ offset: $('#cart-wrapper').offset().top-180});
			
			// Action on menu selection
			$('#menuModal .list-viewer li').each(function(i) {
				
				$(this).click(function() {
					selectLi=$(this);
					var menuid = $(this).attr('data-menuid');
					//var menuname = $(this).find('h2').html();
					//var menuprice = $(this).find('h3').html();
						
					$('#menuSelection').load('${pageContext.request.contextPath}/admin/orders/choosemenu?menuId=' + menuid, function() {
						$(this).find('ul').each(function(i) {
							var menupartid = $(this).parent().find('input:nth-child(1)');
							var menupartproduct = $(this).parent().find('input:nth-child(2)');
							var menupartname = $(this).parent().find('input:nth-child(3)');
							
							$(this).find('li').each(function(i) {
								$(this).click(function() {
									var productid = $(this).attr('data-productid');
									menupartproduct.val(productid);
									menupartname.val($(this).find('h2').html());
									
									// Highlights selected product
									$(this).parent().find('li').each(function() {
										if ($(this).attr('data-productid') != productid) {
											$(this).removeClass('products-list-active');
										} else {
											$(this).addClass('products-list-active');
										}
									});
								});
							});
						});
						
					
					});
				});
				
			});
		//Session
			<c:forEach items="${order}" var="item">
			${item.id}; ${item.menuId};
			<c:choose>
			<c:when test="${item.menuId == 0}">
			newOrder.addCartLine(<c:out value="${item.id}"/>, "<c:out value="${item.name}"/>", 0, '<c:out value="${item.price}"/>');
			</c:when>
			<c:when test="${item.price == 0}">
			newOrder.addCartLine(<c:out value="${item.id}"/>, "<c:out value="${item.name}"/>", 0, 0);
			</c:when>
			<c:otherwise>
				//item is a menu
				newOrder.addCartLine(-1, "Menu "+"<c:out value="${item.name}"/>", <c:out value="${item.menuId}"/>, '<c:out value="${item.price}"/>');
			</c:otherwise>
		</c:choose>
			
			</c:forEach>
		});
		
	</script>
	<div class="modal hide fade" id="menuModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3><s:text name="menu" /></h3>
		</div>
		<div class="modal-body">
			<h4><s:text name="orders.new.chooseproducts.choosemenu" /></h4>
			<div class="list-viewer">
				<ul>
					<c:forEach items="${menus}" var="menu">
						<li data-menuid="<c:out value="${menu.id}" />" class="list-viewer-menu">
							<h2><c:out value="${menu.name}" /></h2>
							<h3><fmt:formatNumber value="${menu.price}" type="currency" currencySymbol="&euro;" /></h3>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="menuSelection"></div>
		</div>
		<div class="modal-footer">
			<a href="#" data-dismiss="modal" class="btn">Annuler</a> <a href="#" class="btn btn-primary" id="okButton">OK</a>
		</div>
	</div>
	
	<div class="box padded">
	<div class="row-fluid">
	 <div class="span12">
		<ul class="breadcrumb">
		    <li><a href="${pageContext.request.contextPath}/admin/orders/chooseclient">1. <s:text name="orders.new.chooseclient.pagetitle" /> (<c:out value="${client.name}" />)</a> <span class="divider">&gt;</span></li>
		    <li class="active">2. <s:text name="orders.new.chooseproducts.pagetitle" /> <span class="divider">&gt;</span></li>
		    <li>3. <s:text name="orders.new.confirm.pagetitle" /></li>
		</ul>
		<form action="${pageContext.request.contextPath}/admin/orders/saveproducts" method="POST" class="form-horizontal" id="form-order">
		<div id="nav-wrapper">
			<div id="nav">
				<div class="list-viewer">
					<ul>
						<c:forEach items="${products}" var="category">
							<li data-categoryid="<c:out value="${category.key.id}" />" class="list-viewer-item"><c:out value="${category.key.name}" /></li>
						</c:forEach>
						<li data-categoryid="-1" class="list-viewer-item"><c:out value="Menu" /></li>
					</ul>
				</div>
				<div class="row-fluid">
					<div class="span8"><h3><s:text name="products" /></h3></div>
					<div class="span4">					
						<div class="price">
							<span class="total-price">0,00</span> &euro;
							<div class="pull-right"><button type="submit" class="btn btn-primary btn-large"><s:text name="submit" /></button></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<c:if test="${actionErrors.size() > 0}">
		<div class="alert alert-error">
			<p><strong><s:text name="errors" /></strong></p>
			<s:actionerror />
		</div>
		</c:if>
		
		
		<fieldset>
			<div class="row-fluid" style="width=100%;">
				<div class="span8">
					<c:forEach items="${products}" var="category">
						<div class="category-item" id="category<c:out value="${category.key.id}"></c:out>">
							<h1><c:out value="${category.key.name}"></c:out></h1>
							<div class="products-list">
								<ul>
									<c:forEach items="${category.value}" var="product">
									<c:if test="${product.available==true }">
										<li data-productid="<c:out value="${product.id}"></c:out>">	<img src=
<%--<c:choose>
	<c:when test="${fn:length(product.picture)>0}"><%--"data:image/jpg;base64,<c:out value='${product.getPictureS()}'/>"--%>
		"${pageContext.request.contextPath}/image/img?id=<c:out value="${product.id}" />"
<%--	</c:when>
	<c:otherwise>
		"${pageContext.request.contextPath}/img/carte/<c:out value="${product.category.imagePath}" />"
	</c:otherwise>
</c:choose> --%>

							
								class="img-polaroid cat" style="background-image:url('${pageContext.request.contextPath}/img/carte/<c:out value="${product.category.imagePath}" />');">
							
											<h2><c:out value="${product.name}"></c:out></h2>
											<p><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="&euro;" /></p>
										</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="span4">
					<div id="cart-wrapper">
						<div class="box">
							<div class="title">
								<i class="icon-white icon-shopping-cart"></i> <s:text name="cart" />
							</div>
							<div style="max-height:250px !important;overflow-y: auto;">
							<table id="cart">
								<thead>
									<tr>
										<th width="50%"><s:text name="name" /></th>
										<th width="25%"><s:text name="price" /></th>
										<th width="5%"></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							</div>
						</div>
						<div class="price">
							<span class="total-price">0,00</span> &euro;
							<div class="pull-right"><button type="submit" class="btn btn-primary btn-large"><s:text name="submit" /></button></div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<div id="formData">
		</div>
		</form>
	</div>
	</div>
	</div>
</body>
</html>