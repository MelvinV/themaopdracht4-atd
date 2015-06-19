<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="domain.Article"%>
<%@page import="java.util.List"%>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Voorraadbeheer" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Artikel</th>
            <th>Verkoop prijs p/s</th>
            <th class="center">Voorraad</th>
            <th>Acties</th>
        </tr>
        
        <% List<Article> articles = (List<Article>) request.getAttribute("articles"); %>
        <% for(Article a : articles) { %>
        <tr>
            <td><%= a.getId() %></td>
            <td><%= a.getName() %></td>
            <td>
            <%
                double amount = a.getPrice();
                Locale locale = new Locale("nl", "NL");      
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                out.println(currencyFormatter.format(amount));
            %>
            </td>
            <td class="center"><%= a.getStock()%></td>
            <td class="right">
                <button onclick="location.href='/cms/voorraad/form?bewerken=<%= a.getId() %>'">Aanpassen</button>
                <button onclick="location.href='/cms/voorraad?verwijderen=true&id=<%= a.getId() %>'">Verwijderen</button>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="/cms/voorraad/form" title="">Artikel Toevoegen</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>