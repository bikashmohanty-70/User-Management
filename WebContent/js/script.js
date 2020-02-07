//Get the modal
var modal = document.getElementById("myModal");

//Get the button that opens the modal
var btn = document.getElementById("myBtn");

//Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

//When the user clicks the button, open the modal 
btn.onclick = function() {
	modal.style.display = "block";
}

//When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
}

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

//When user clicks on show password icon, show password in the password field
function showPassword() {
	var new_password = document.getElementById("new");
	var confirm_password = document.getElementById("confirm");
	if(new_password === confirm_password)
	{
		if (new_password.type === "password" &&  confirm_password.type === "password") {
			new_password.type = "text";
			confirm_password.type = "text";
		} else {
			new_password.type = "password";
			confirm_password.type = "password";
		}
	}
	else
	{
		document.getElementById("error").innerHTML = "Password Mismatch";
		if (new_password.type === "password" &&  confirm_password.type === "password") {
			new_password.type = "text";
			confirm_password.type = "text";
		} else {
			new_password.type = "password";
			confirm_password.type = "password";
		}
	}
}