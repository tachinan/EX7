
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.lang.Math"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perimeter</title>
    </head>
    <body>
         <%
            String radiusStr = request.getParameter("radius");
            double radius;

            if (radiusStr == null || radiusStr.isEmpty()) {
                radius = 0;
            } else {
                try {
                    radius = Double.parseDouble(radiusStr);
                } catch (NumberFormatException e) {
                    radius = 0;
                }
            }
            
            double perimeter = 2 * Math.PI * radius;
            out.println("<h1>For the circle with radius = " + radius + "</h1>");
            out.println("<h2>Perimeter is " + perimeter + "</h2>");
        %>
    </body>
</html>
