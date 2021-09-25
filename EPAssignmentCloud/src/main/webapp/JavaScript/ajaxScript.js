$(function(){
/*ajax for getting all films */
$('#getFilms').on('click', function(){
var data_format = document.getElementById("inputFormatDataType").value;
var $data = $('.data').on('click', function(){

$('#data').toggle();

});	

$.ajax({
type: 'GET',
url: '/getAllFilms?data-format='+data_format, 
success: function(data){
    let parsedData = data;
        for(let i=0; i < parsedData.length; i++){
            $data.append('<table><li>ID: ' + parsedData[i].id + ' Title: ' + parsedData[i].title + ' Year: ' + parsedData[i].year + ' Director: ' + parsedData[i].director + ' Stars: ' + parsedData[i].stars + ' Reviews: ' + parsedData[i].review);
            }
    }
});

});

/*ajax for the delete button */
            $('#deleteFilm').on('click', function(){
    var id = document.getElementById("inputFormatID").value;
    $.ajax({
type: 'DELETE',
url: '/rest/allFilmList/'+id, 
success: function(){
    console.log('success, id: ${id} film is deleted');
    }
});

        });
/*ajax for the add button */
$('#addFilm').on('click', function(event){
	event.preventDefault();
	var dataformat = $("input[name ='data-format']:checked").val();

if(dataformat = 'json')	
var filmList = {
	id : $('#byID').val(),
	title : $('#byTitle').val(),
	year : $('#byYear').val(),
	director : $('#byDirector').val(),
	stars : $('#byStars').val(),
	review : $('#byReview').val()
}
else if (dataformat == 'xml'){
	
	var idf = $('#byID').val();
	var titlef = $('#byTitle').val();
	var yearf = $('#byYear').val();
	var directorf = $('#byDirector').val();
	var starsf = $('#byStars').val();
	var reviewf = $('#byReview').val();
	
	var xmlfilm = `<film>
	                <id>${idf}</id>
					<title>${titlef}</title>
					<year>${yearf}</year>
					<director>${directorf}</director>
					<stars>${starsf}</stars>
					<review> ${reviewf}</review>  `				
}
    $.ajax({
type: 'POST',
url: '/rest/allFilmList', 
 headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
data:JSON.stringify(filmList),
dataType:'json',
success: function(data){
     console.log('movie added' + data);
    }
});
});
/*ajax for updating film in database */
$('#updateFilm').on('click', function(){
	var filmUpdate = {
	id : $('#bID').val(),
	title : $('#bTitle').val(),
	year : $('#bYear').val(),
	director : $('#bDirector').val(),
	stars : $('#bStars').val(),
	review : $('#bReview').val()
	}
  $.ajax({
type: 'PUT',
url: '/rest/allFilmList', 
 headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
data:JSON.stringify(filmUpdate),
dataType:'json',
success: function(data){
     console.log('movie updated' + data);
    }
});
});
});