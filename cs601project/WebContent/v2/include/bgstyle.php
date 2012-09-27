<?php
  $bg = array('bg-01.jpg', 'bg-02.jpg', 'bg-03.jpg', 'bg-04.jpg'); // array of filenames

  $i = rand(0, count($bg)-1); // generate random number size of the array
  $selectedBg = "$bg[$i]"; // set variable equal to which random filename was chosen
?>
<style type="text/css">
	body{
		background: url("img/bg/<?php echo $selectedBg;?>") no-repeat fixed center center;
		/*For IE8*/
		filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("img/bg/<?php echo $selectedBg;?>")",sizingMethod="scale" );
		/*For IE8?
		-ms-filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="url("img/bg/<?php echo $selectedBg;?>")", sizingMethod="scale");*/
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
		/*Background image end*/
	}
</style>