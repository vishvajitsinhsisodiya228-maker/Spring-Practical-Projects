<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Add Member</title></head><body>
<h2>Add Gym Member</h2>
<% String error=(String)request.getAttribute("error"); if(error!=null){ %>
<p style="color:red"><%= error %></p><% } %>
<form method="post" action="addMember">
<label>Name: <input name="name" value="<%= request.getAttribute("name")==null?"":request.getAttribute("name") %>" required></label><br>
<label>Phone: <input name="phone" maxlength="10" value="<%= request.getAttribute("phone")==null?"":request.getAttribute("phone") %>" required></label><br>
<label>Plan: <select name="plan">
<% String selected=(String)request.getAttribute("plan"); String[] plans={"Silver","Gold","Platinum"}; for(String p:plans){ %>
<option value="<%=p%>" <%=p.equals(selected)?"selected":""%>><%=p%></option>
<% } %></select></label><br>
<label>Months: <input type="number" name="months" min="1" max="12" value="<%=request.getAttribute("months")==null?"1":request.getAttribute("months")%>" required></label><br>
<button type="submit">Save Member</button></form>
<p><a href="members">Back to members</a></p>
</body></html>
