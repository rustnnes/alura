<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Caelum Ensino e Inovação</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<script>
   var StyleFile = "theme1.css";
   document.writeln('<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/' + StyleFile + '">');
</script>
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="css/ie-sucks.css" />
<![endif]-->
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>Sistema de Leilões</h2>
    <div id="topmenu">
            	<ul>
                	<li class="current"><a href="<c:url value="/" />">Home</a></li>
                    <li><a href="<c:url value="/usuarios" />">Usuários</a></li>
                	<li><a href="<c:url value="/leiloes" />">Leilões</a></li>
              </ul>
          </div>
      </div>
        <div id="wrapper">
            <div id="content">
            
            	<decorator:body/>
            	
            </div>
            <div id="sidebar">
  				<ul>
                	<li><h3><a href="#" class="house">Caelum Online</a></h3>
                        <ul>
                        	<li><a href="http://www.caelum.com.br/online" class="report">Veja nossos cursos!</a></li>
                        </ul>
                    </li>
                </ul>
          </div>
      </div>
        <div id="footer">
        <div id="credits">
   		Template feito por <a href="http://www.bloganje.com">Bloganje</a>
        </div>

        </div>
</div>
</body>
</html>