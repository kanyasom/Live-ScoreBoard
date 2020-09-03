<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>league</title>
<style>
    *{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    background: #0b0980d0;
}
body{
    font-family: Helvetica;
    -webkit-font-smoothing: antialiased;
    background: rgb(165, 206, 243);
}
h2{
    text-align: center;
    font-size: 18px;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: rgb(250, 18, 18);
    padding: 30px 0;
}

/* Table Styles */

.table-wrapper{
    /*margin: 10px 70px 70px;*/
    box-shadow: 0px 35px 50px rgba(131, 118, 118, 0.356);
}

.fl-table {
    border-radius: 5px;
    font-size: 12px;
    font-weight: normal;
    border: none;
    border-collapse: collapse;
    width: 100%;
    max-width: 100%;
    white-space: nowrap;
    background-color:#b0b7be;

}

.fl-table td, .fl-table th {
    text-align: center;
    padding: 8px;

    
}

.fl-table td {
    border-right: 1px solid #93a0ad;
    font-size: 12px;
    background-color:#34495E;
    border-bottom: 1px solid #6e6d6d;
}

.fl-table thead th {
    color: #ffffff;
    background: #4FC3A1;
}


.fl-table thead th:nth-child(odd) {
    color: #ffffff;
    background:#39f3bb7c;
}

.fl-table tr:nth-child(even) {
    background:white;
}

/* Responsive */

@media (max-width: 767px) {
    .fl-table {
        display: block;
        width: 100%;
    }
    .table-wrapper:before{
        content: "Scroll horizontally >";
        display: block;
        text-align: right;
        font-size: 11px;
        color: white;
        padding: 0 0 10px;
    }
    .fl-table thead, .fl-table tbody, .fl-table thead th {
        display: block;
    }
    .fl-table thead th:last-child{
        border-bottom: none;
    }
    .fl-table thead {
        float: left;
    }
    .fl-table tbody {
        width: auto;
        position: relative;
        overflow-x: auto;
    }
    .fl-table td, .fl-table th {
        padding: 20px .625em .625em .625em;
        height: 60px;
        vertical-align: middle;
        box-sizing: border-box;
        overflow-x: hidden;
        overflow-y: auto;
        width: 120px;
        font-size: 13px;
        text-overflow: ellipsis;
    }
    .fl-table thead th {
        text-align: left;
        border-bottom: 1px solid #f7f7f9;
    }
    .fl-table tbody tr {
        display: table-cell;
    }
    .fl-table tbody tr:nth-child(odd) {
        background: none;
    }
    .fl-table tr:nth-child(even) {
        background: transparent;
    }
    .fl-table tr td:nth-child(odd) {
        background: #F8F8F8;
        border-right: 1px solid  #E6E4E4;
    }
    .fl-table tr td:nth-child(even) {
        border-right: 1px solid #E6E4E4;
    }
    .fl-table tbody td {
        display: block;
        text-align: center;
        border-right: 1px solid #E6E4E4;
    }
}
    </style>


<%          int current = 0; 
            Connection connect = null;
		    String connectionURL = "jdbc:mysql://localhost:3306/sports";
            ResultSet rs = null;
%>
	<FORM NAME="form1" ACTION="match.jsp" METHOD="get"> 
	<%
		            Statement statement = null;
		            try {
		                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		                connect = DriverManager.getConnection(connectionURL, "root", "KILLU");
		                statement = connect.createStatement(); 
		                rs = statement.executeQuery("select * from matches");
		               
		                	
						%>

    
 <div class="table-wrapper">
<FONT color="black"></b>
 
    <table class="fl-table">
        <thead>
        <tr>
            <th>Match ID</th>
            <th>Info</th>
            <th>Teams</th>
            <th>Season</th>
        </tr>
        </thead>
        <tbody>
        <%while(rs.next())
        {
            %>
            <tr>
                <td><%=rs.getInt("idevent") %></td>
                <td><%=rs.getString("type") %></td>
                <td><%=rs.getString("team") %></td>
                <td><%=rs.getInt("season") %></td>
               
            </tr>
            <%}%>
        <tbody>
    </table>            	   
   </div>
		   <%
		   
		    
		}
		            catch (Exception ex) {
		    %>
          	<FONT size="+3" color="black"><b></b>
          	 <%
            	                out.println("Unable to connect to database.");
            	            }        	            
		            %>  
		            </FONT>
</FORM>
</head>
</html>