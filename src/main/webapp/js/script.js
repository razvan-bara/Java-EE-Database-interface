

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