/**
 *
 */
$(document).ready(function() {
	var chart = new GuitarChart("C", 3, "x12345", "123455", false);
	var chartSVG = chart.getSVG();
	chartSVG.setAttribute("class","guitarchart");
	$("#chordtray").append(chartSVG);

	var chart = new GuitarChart("D", 3, "x12345", "123455", false);
	var chartSVG = chart.getSVG();
	chartSVG.setAttribute("class","guitarchart");
	$("#chordtray").append(chartSVG);
});
