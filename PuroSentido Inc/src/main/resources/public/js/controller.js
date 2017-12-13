var url = 'http://localhost:8080/user';
window.addEventListener("load",comenzar,false);
function comenzar(){
	var archivos = document.getElementById("archivos");
	archivos.addEventListener("change",procesar,false);
}
function procesar(e){
	var files = e.target.files;
	var archive = files[0];
	var lector = new FileReader();
	lector.readAsText(archive);
	lector.addEventListener("load",importCSV,false);
}
function importCSV(e){
	var resultado = e.target.result;
	var data = resultado.split(/\r\n|\n/);
	var concat = url.concat("/csv?line=");
	alert(concat);
	for(var i = 0 ; i < data.length ; i++){
		//Consumir servicio
		$.ajax({
			url : concat+data[i]
		}).then(
			function (data){
				alert(data);
			}
		);
	}
	redirectCSV();
}
function submit(){
	var name = document.getElementById('name').value;
	var lastname = document.getElementById('lastname').value;
	var address = document.getElementById('address').value;
	var zipcode = document.getElementById('zipcode').value;
	var phone = document.getElementById('phone').value;
	var color = document.getElementById('color').value;
	var concat = url.concat('/add?name=',name,'&lastname=',lastname,'&address=',address,'&zipcode=',zipcode,'&phone=',phone,'&color=',color);
	alert(concat);
	$.ajax({
		url : concat
	}).then(
		function (data){
			alert(data);
		}
	)
}
function updateTableCount(){
	var concat = url.concat('/list/one');
	$.ajax({
		url : concat
	}).then(
		function (data){
			var obj = JSON.parse(data);
			var s = obj.status;
			if(s == 'sucesful'){
				var array = obj.users;
				var total = "<tr><th><center>Color</center></th><th><center>Count</center></th></tr>";
				for(var i = 0 ; i < array.length; i++){
					var obj2 = array[i];
					var component = "<tr><th><center>"+obj2.color+"</center></th><th><center>"+obj2.count+"</center></th></tr>"
					total = total.concat(component);
				}
				$('#table').html(total);
			}
		}
	)
}
function updateTablePeople(){
	var concat = url.concat('/list/two');
	$.ajax({
		url : concat
	}).then(
		function (data){
			var obj = JSON.parse(data);
			var s = obj.status;
			if(s == 'sucesful'){
				var array = obj.users;
				var total = "<tr><th><center>Color</center></th><th><center>Count</center></th><th><center>Names</center></th><</tr>";
				for(var i = 0 ; i < array.length; i++){
					var obj2 = array[i];
					var component = "<tr><th><center>"+obj2.color+"</center></th><th><center>"+obj2.count+"</center></th>";
					var names = obj2.names;
					total = total.concat(component,"<th><center>",names,"</center></th>");
				}
				$('#table').html(total);
			}
		}
	)
}
//JSON
function updateTableCountJson(){
	var concat = url.concat('/list/one');
	$.ajax({
		url : concat
	}).then(
		function (data){
			var obj = JSON.parse(data);
			var s = obj.status;
			if(s == 'sucesful'){
				var array = obj.users;
				var total = "<br>[{";
				for(var i = 0 ; i < array.length; i++){
					var obj2 = array[i];
					if(i!=0)total = total.concat('{');
					total = total.concat('<br>"color":',array[i].color,'<br>"count":',array[i].count,'<br>');
					if(i!=array.length-1)total = total.concat('},<br>');
				}
				$('#content').html(total+"}]<br><br>");
			}
		}
	)
}
function updateTablePeopleJson(){
	var concat = url.concat('/list/two');
	$.ajax({
		url : concat
	}).then(
		function (data){
			var obj = JSON.parse(data);
			var s = obj.status;
			if(s == 'sucesful'){
				var array = obj.users;
				var total = "<br>[{";
				for(var i = 0 ; i < array.length; i++){
					var obj2 = array[i];
					if(i!=0)total = total.concat('{');
					total = total.concat('<br>"color":',array[i].color,'<br>"count":',array[i].count,'<br>"names":',array[i].names,'<br>');
					if(i!=array.length-1)total = total.concat('},<br>');
				}
				$('#content').html(total+"}]<br><br>");
			}
		}
	)
}
function redirectAdd(){
	window.location.replace("http://localhost:8080/Principal.html");
}
function redirectCount(){
	window.location.replace("http://localhost:8080/ListCount.html");
}
function redirectPerson(){
	window.location.replace("http://localhost:8080/ListPeople.html");
}
function redirectCountJson(){
	window.location.replace("http://localhost:8080/ListCountJson.html");
}
function redirectPersonJson(){
	window.location.replace("http://localhost:8080/ListPeopleJson.html");
}
function redirectCSV(){
	window.location.replace("http://localhost:8080/ImportCSV.html");
}
function submitPart2(){
	var lat = document.getElementById('lat').value;
	var lon = document.getElementById('lon').value;
	var query = document.getElementById('query').value;
	var day = document.getElementById('day').value;
	var mount = document.getElementById('mount').value;
	var year = document.getElementById('year').value;
	var urlFoursquare = "https://api.foursquare.com/v2/venues/search?ll="+(lat+","+lon)+"&query="+query+"&client_id=1QUTXICXACI0CTVGVROEPYEDKFPA5WAZ1OWQYR1NBS2T0TVJ&client_secret=GLH12U1BTPNNS2DDJ35L4AROM3YUY5FZJEEGMVKDTBRZ2DAS&v="+(year+""+mount+""+day)+"";
	
	$.ajax({
		url : urlFoursquare
	}).then(
		function(data){
			var obj = (data['response']);
			var array = (obj['venues']);
			var places = "{<br>\"places\":[<br>";
			for(var i = 0 ; i < array.length;i++){
				var json = array[i];
				places = places+"\""+json.name+"\",<br>";
			}
			places = places+']<br>}<br>';
			var oo = document.getElementById('json');
			oo.innerHTML = places;
		}
	);
}