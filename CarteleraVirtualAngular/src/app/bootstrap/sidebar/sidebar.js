/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function menu() {
	var elem = document.getElementById("main");
	var style = window.getComputedStyle(elem); // getComputedStyle importante para ver estilos agregados por el css
	var marginLMain = style.marginLeft;

	if(marginLMain == "250px"){
		document.getElementById("mySidenav").style.width = "0";
		document.getElementById("main").style.marginLeft = "0";
		document.getElementById("sidenavContent").style.position = "absolute";
	} else{
		document.getElementById("mySidenav").style.width = "250px";
		document.getElementById("main").style.marginLeft = "250px";
		document.getElementById("sidenavContent").lastChild.style.position = "fixed";
	}
	
}
