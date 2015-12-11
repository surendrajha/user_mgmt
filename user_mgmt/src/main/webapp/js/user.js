function ConfirmDelete(url) {
	var val = confirm("Are you sure you want to delete?");
	if (val) {
		location.href = url;
	} else {
		return;
	}
}

function retunToUrl(url) {
	location.href = url;
}
