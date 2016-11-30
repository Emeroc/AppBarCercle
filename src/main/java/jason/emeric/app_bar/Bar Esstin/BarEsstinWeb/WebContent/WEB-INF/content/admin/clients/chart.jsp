<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="clients.new.pagetitle" /> &gt; <s:text
		name="clients.new.chooseclient.chart" /></title>
<link href="${pageContext.request.contextPath}/css/orders.css"
	rel="stylesheet" media="screen">

</head>
<body>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<span>Le chiffre d'affaire du <fmt:formatDate value="${date}"
			pattern="dd/MM/yyyy" /> est de <fmt:formatNumber value="${ca}"
			type="currency" currencySymbol="&euro;" /></span>
	<div id="container"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<div id="container2"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>


	<script type="text/javascript">
$(function () {
    var chart;
    var chart2;
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column'
            },
            title: {
                text: 'Consommation annuelle'
            },
            xAxis: {
                categories: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre']
            },
            yAxis: {
                title: {
                    text: 'Total (€)'
                },
                min: 0
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.x +': '+ this.y +' €';
                }
            },
            series: [{
                name: '<c:out value="${year}"></c:out>',
                data: [<c:forEach items="${l}" var="ca"><c:out value="${ca.ca}"></c:out><c:if test="${ca.month != 11 }">,</c:if></c:forEach>]
            }]
        });
        
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container2',
                type: 'column'
            },
            title: {
                text: 'Bonus Cotisant Annuel'
            },
            xAxis: {
                categories: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre']
            },
            yAxis: {
                title: {
                    text: 'Total (€)'
                },
                min: 0
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.x +': '+ this.y +' €';
                }
            },
            series: [{
                name: '<c:out value="${year}"></c:out>',
                data: [<c:forEach items="${l2}" var="ca"><c:out value="${ca.ca}"></c:out><c:if test="${ca.month != 11 }">,</c:if></c:forEach>]
            }]
        });
    });
    
});
</script>
</body>
</html>
