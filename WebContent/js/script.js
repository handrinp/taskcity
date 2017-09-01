'use strict';
/*******************************************************************************
 * Startup
 ******************************************************************************/
$(document).ready(function() {
	closePopUp();
});

/*******************************************************************************
 * GUI FUNCTIONS
 ******************************************************************************/

function showPopUp() {
	$('#popUnder').css('display', 'block');
	$('#popUp').css('display', 'block');
}

function closePopUp() {
	$('#popUnder').css('display', 'none');
	$('#popUp').css('display', 'none');
}

function submitPopUp() {
	var validated = true;

	validated &= validate('formMonth');
	validated &= validate('formDay');
	validated &= validate('formTime');

	if (validated) {
		var dateString = document.getElementById('formMonth').value + " "
				+ document.getElementById('formDay').value + ", "
				+ document.getElementById('formYear').value + " "
				+ document.getElementById('formTime').value;

		var dateObj = new Date(dateString);
		var numDays = (dateObj.getTime() - new Date().getTime()) / ONE_DAY;
		currentDueValue = numDays;
		closePopUp();
	}
}

function validate(formId) {
	var dom = document.getElementById(formId);

	if (dom.value == 0) {
		dom.style.border = '1px solid #f00';
		return false;
	}

	dom.style.border = '';
	return true;
}

/*******************************************************************************
 * HELPER FUNCTIONS
 ******************************************************************************/

function clearForms() {
	document.getElementById('formYear').selectedIndex = 0;
	document.getElementById('formMonth').selectedIndex = 0;
	document.getElementById('formDay').selectedIndex = 0;
	document.getElementById('formTime').selectedIndex = 0;
	document.getElementById('subject').selectedIndex = 0;
	document.getElementById('task').value = '';
	currentDueValue = '';
}

function getTimeString(diff) {
	// calculate the hours, days, weeks
	var diffHours = Math.floor(diff / ONE_HOUR) % 24;
	var diffDays = Math.floor(diff / ONE_DAY) % 7;
	var diffWeeks = Math.floor(diff / ONE_WEEK);

	// build the time string
	var timeString = "";
	if (diffWeeks > 0)
		timeString += diffWeeks + 'w ';
	if (diffDays > 0)
		timeString += diffDays + 'd ';
	if (diffHours > 0)
		timeString += diffHours + 'h';

	// put line breaks in between time units
	return timeString.trim().replace(/ /g, '<br>');
}
