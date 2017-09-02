'use strict';

var currentDueValue; // global variable for due millis

/*******************************************************************************
 * Startup
 ******************************************************************************/
$(document).ready(function() {
	closePopUp();
	clearForms();
});

/*******************************************************************************
 * SERVLET FUNCTIONS
 ******************************************************************************/
function removeTask(taskid) {
	// hack to prevent hashtag anger in the URL
	if (taskid.startsWith("#")) {
		taskid = taskid.replace("#", "HASHTAG");
	}

	window.location = "/taskcity/removeTask?taskid=" + taskid;
}

function addTask() {
	var id = Math.floor(Math.random() * 16777215).toString(16);
	var subj = document.getElementById('subject').value;
	var desc = document.getElementById('task').value;

	window.location = "/taskcity/addTask?taskID=" + id + "&taskSubject=" + subj
			+ "&taskDescription=" + desc + "&taskDue=" + currentDueValue;
}

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

		currentDueValue = new Date(dateString).getTime();
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
	currentDueValue = 0;
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
