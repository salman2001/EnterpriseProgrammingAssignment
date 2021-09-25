$(function(){

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
            $data.append('<li>ID: ' + parsedData[i].id + ' Title: ' + parsedData[i].title + ' Year: ' + parsedData[i].year + ' Director: ' + parsedData[i].director + ' Stars: ' + parsedData[i].stars + ' Reviews: ' + parsedData[i].review);
            }
    }
});

});


            $('#deleteFilm').on('click', function(){
    var id = document.getElementById("inputFormatID").value;
    $.ajax({
type: 'DELETE',
url: '/rest/allFilmList/'+id, 
success: function(){
    console.log('success, id: ${id} deleted u fucken baincho');
    }
});

        });

});