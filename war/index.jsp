<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aol.gaeBase.*" %>
<%@ page import="com.aol.gaeBase.model.*" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
  </head>

  <body>
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
      </tr>
      <tr>
        <td><a href="gaebase">GAEBase</a></td>
      </tr>
    </table>
  
	
	<table border = "1">
	<%
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Thing> things;
		try {
			Query q = pm.newQuery(Thing.class);
			things = (List<Thing>) q.execute();
		} finally {
			pm.close();
		}
		for(Thing t:things){
		%>
			<tr>
			<td>id: <%out.print(t.id);%></td>
			<td><form action="/deleteThing" method="post">
			      <input type="hidden" name="id" value='<%=t.id%>'/>
			      <div><input type="submit" value="X" /></div>
			   	</form>
			</td>
		<%
	}
	%>
	</table>
  
  </body>
</html>
