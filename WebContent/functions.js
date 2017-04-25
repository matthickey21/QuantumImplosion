function drawStorageChart() { 
	// var xhr = new XMLHttpRequest(); 
	// xhr.open('GET', "/data/{system}/storage", true);
	// xhr.send();
	// xhr.addEventlistener("readystatechange", processRequest, false);
	// xhr.onreadystatechange = processRequest; 

	var freestorage = 1;
	var usedstorage = 1;
	function processRequest(e) {
		if (xhr.readyState == 4 && status == 200) {
		  // var response = JSON.parse(xhr.responseText);
		  // freestorage = response.freeStorage / response.totalStorage;
		  // usedstorage = (response.totalStorage - response.freeStorage) / response.totalStorage;
		}
	}

	var ctx = document.getElementById("storageChart").getContext("2d");
	ctx.canvas.width = 40;
	ctx.canvas.height = 26;
	var storageChart = new Chart(ctx, {
		type: 'pie',
		data: {
			labels: ["Free Storage", "Used Storage"],
			datasets: [{
				label: 'Storage',
				data: [freestorage, usedstorage],
				backgroundColor: ['rgba(255, 128, 0, 0.2)', 'rgba(0, 255, 255, 0.2)'],
                borderColor: ['rgba(255, 128, 0, 1)', 'rgba(0, 255, 255, 1)'],
                borderWidth: 1
			}]
		},
		options: {
			animation: {
				animateScale: true
			}
		}
	});
}
