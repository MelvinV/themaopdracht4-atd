<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="tasks" value="${requestScope.tasks}" ></c:set>
<c:set var="single" value="${requestScope.single}" ></c:set>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Reparatie</h2>

<c:choose>
    <c:when test="${!empty tasks}">
        <p>Hieronder kunt u al uw reparaties zien, klik <a href="/klant/reparatie/form">hier</a> om een reparatie aan te melden.</p>
        
        <table>
            <tr class="top">
                <td style="width: 60px;">Taak</td>
                <td style="width: 60px;">Type</td>
                <td style="width: 60px;">Status</td>
                <td>Auto</td>
                <td>Acties</td>
            </tr>

            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${task.id}" /></td>
                    <td>
                    <c:choose>
                        <c:when test="${task.type == 'APK'}">
                            <img src="/img/apkTask.png" title="APK (Algemene Periodieke Keuring)" />
                        </c:when>
                        <c:when test="${task.type == 'REPAIR'}">
                            <img src="/img/repairTask.png" title="Reparatie" />
                        </c:when>
                    </c:choose>
                    </td>
                    <td>
                    <c:choose>
                        <c:when test="${task.status == 'REQUEST'}">Aanvraag</c:when>
                        <c:when test="${task.status == 'PLANNED'}">Ingepland</c:when>
                        <c:when test="${task.status == 'FINISHED'}">Klaar</c:when>
                        <c:when test="${task.status == 'CANCELED'}">Geannuleerd</c:when>
                    </c:choose>
                    </td>
                    <td>${task.car.licensePlate} (${task.car.brand} ${task.car.model})</td>
                    <td><a href="/klant/reparatie?id=${task.id}#details">Meer Informatie</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    
    <c:when test="${empty tasks}">
        <p>Er zijn geen reparaties gevonden, klik <a href="/klant/reparatie/form">hier</a> om een reparatie aan te melden.</p>
    </c:when>
</c:choose>
        
<c:choose>
    <c:when test="${single != null}">
        <h2 id="details" style='margin-bottom: 5px;'>T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${single.id}" /></h2>
        
        <table>
            <tr>
                <td style="width: 150px;">Type:</td>
                <td>${single.type}</td>
            </tr>
            
            <tr>
                <td>Status:</td>
                <td>${single.status}</td>
            </tr>
            
            <tr>
                <td>Auto:</td>
                <td>${single.car.licensePlate} (${single.car.brand} ${single.car.model})</td>
            </tr>
            
            <tr>
                <td>Monteur:</td>
                <td>${single.mechanic.name}</td>
            </tr>
            
            <tr>
                <td>Uw Notitie:</td>
                <td>${single.customerNote}</td>
            </tr>
            
            <tr>
                <td>Monteur's Notitie:</td>
                <td>${single.mechanicNote}</td>
            </tr>
        </table>
    </c:when>
</c:choose>

<%@include file="/WEB-INF/view/website/footer.jsp" %>