<%@ page import="gym.model.Member" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Edit Member</title></head><body>
<h2>Edit Member</h2>
<% String error=(String)request.getAttribute("error"); if(error!=null){ %><p><%=error%></p><% } %>
<% Member m=(Member)request.getAttribute("member"); if(m!=null){ %>
<p>Name: <%=m.getName()%> | Phone: <%=m.getPhone()%></p>
<form method="post" action="updateMember">
<input type="hidden" name="memberId" value="<%=m.getMemberId()%>">
<label>Plan: <select name="plan">
<% for(String p:new String[]{"Silver","Gold","Platinum"}){ %><option value="<%=p%>" <%=p.equals(m.getPlan())?"selected":""%>><%=p%></option><% } %>
</select></label><br>
<label>Months: <input type="number" name="months" min="1" max="12" value="<%=m.getMonths()%>"></label><br>
<button type="submit">Update</button></form>
<% } %>
<a href="members">Back</a>
</body></html>
