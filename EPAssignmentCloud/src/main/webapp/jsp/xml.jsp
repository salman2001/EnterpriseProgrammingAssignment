<%@ page language="java" contentType="text/xml; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<films>
<headings>
<heading>id</heading>
<heading>title</heading>
<heading>year</heading>
<heading>director</heading>	
<heading>stars</heading>	
<heading>review</heading>		
</headings>
<film>
<id>${film.id}</id>
<title>${film.title}</title>
<year>${film.year}</year>
<director>${film.director}</director>
<star>${film.stars}</star>
<review>${film.review}</review>
</film>    
</films>