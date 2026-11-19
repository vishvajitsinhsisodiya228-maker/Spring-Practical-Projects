<%@ page import="java.util.List,gym.model.Member" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Members</title></head><body>
<h2>Gym Members</h2>
<% String msg=request.getParameter("msg"); if("deleted".equals(msg)){ %><p>Member deleted</p><% } else if("notfound".equals(msg)){ %><p>Member not found</p><% } %>
<p><a href="addMember">Add Member</a></p>
<form method="get" action="searchMember">
<label>Search by phone: <input name="phone" maxlength="10"></label><button type="submit">Search</button>
</form>
<table border="1" cellpadding="6">
<tr><th>ID</th><th>Name</th><th>Phone</th><th>Plan</th><th>Months</th><th>Fee</th><th>Actions</th></tr>
<%
List<Member> members=(List<Member>)request.getAttribute("members");
if(members!=null) for(Member m:members){
%>
<tr>
<td><%=m.getMemberId()%></td><td><%=m.getName()%></td><td><%=m.getPhone()%></td>
<td><%=m.getPlan()%></td><td><%=m.getMonths()%></td><td>₹<%=String.format("%.2f",m.getFee())%></td>
<td><a href="editMember?id=<%=m.getMemberId()%>">Edit</a>
<form method="post" action="deleteMember" style="display:inline">
<input type="hidden" name="id" value="<%=m.getMemberId()%>"><button type="submit">Delete</button></form></td>
</tr>
<% } %>
</table>
</body></html>
