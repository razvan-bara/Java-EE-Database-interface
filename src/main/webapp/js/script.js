

function openModal(){
	let modal = document.querySelector(".modal");

	if(!modal.classList.contains("active")){
		modal.classList.add("active");
	}
}

function closeModal(){
	let modal = document.querySelector(".modal");

	if(modal.classList.contains("active")){
		modal.classList.remove("active");
	}
}


function remove_notifications() {
	const notifications = document.querySelectorAll(".notification");	
	notifications.forEach( function(val,index) {
		val.classList.add("seen");
	});
}

setTimeout(remove_notifications, 4000)