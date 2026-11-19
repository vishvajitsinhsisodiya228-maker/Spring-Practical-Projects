<%@ page import="gym.model.Member" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Search Result</title></head><body>
<h2>Member Search</h2>
<% String message=(String)request.getAttribute("message"); Member m=(Member)request.getAttribute("member");
if(message!=null){ %><p><%=message%></p><% } else if(m!=null){ %>
<p>ID: <%=m.getMemberId()%></p><p>Name: <%=m.getName()%></p><p>Phone: <%=m.getPhone()%></p>
<p>Plan: <%=m.getPlan()%></p><p>Months: <%=m.getMonths()%></p><p>Fee: ₹<%=String.format("%.2f",m.getFee())%></p>
<% } %>
<a href="members">Back to members</a>
</body></html>
