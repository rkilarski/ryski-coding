<?php
  $bg = array('bg-01.jpg', 'bg-02.jpg', 'bg-03.jpg', 'bg-04.jpg', 'bg-05.jpg', 'bg-06.jpg', 'bg-07.jpg', 'bg-08.jpg', 'bg-09.jpg', 'bg-10.jpg', 'bg-11.jpg', 'bg-12.jpg', 'bg-13.jpg', 'bg-14.jpg', 'bg-15.jpg', 'bg-16.jpg', 'bg-17.jpg', 'bg-18.jpg', 'bg-19.jpg'); // array of filenames

  $i = rand(0, count($bg)-1); // generate random number size of the array
  $selectedBg = "$bg[$i]"; // set variable equal to which random filename was chosen
?>
<style type="text/css">
	body{
		background: url("../img/bg/<?php echo $selectedBg;?>") no-repeat fixed center center;
		/*For IE8*/
		filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("../img/bg/<?php echo $selectedBg;?>")",sizingMethod="scale" );
		/*For IE8?
		-ms-filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("../img/bg/<?php echo $selectedBg;?>")", sizingMethod="scale");*/
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
		/*Background image end*/
	}
</style>