<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include('../include/header.php');
?>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC8vY8Gf7DQ32NxxSqrclMlUZZ-nZTVInI&sensor=false">
</script>
<script type="text/javascript">
        var mapOptions = {
          center: new google.maps.LatLng(42.351231,-71.117575),
          zoom: 8,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
</script>
<?php
include('../include/body.php');
?>
<h2>do you feel like chicken tonight?</h2>
<fieldset id="contactinfo">
<legend>
contact information
</legend>
telephone.617.353.5000<br>
fax.617.353.5000<br>
email: <a href="mailto:chickenrice@gmail.com">chickenriceyum@gmail.com</a><br>
<div id="map_canvas"
	style="width: 600; height: 400"></div>
	<iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=928+Commonwealth+Avenue,+Boston,+MA&amp;aq=0&amp;oq=928+Commonwealth+ave,+Boston,+MA&amp;sll=42.367337,-71.05793&amp;sspn=0.01132,0.021415&amp;t=m&amp;g=928+Commercial+Street,+Boston,+MA&amp;ie=UTF8&amp;hq=&amp;hnear=928+Commonwealth+Ave,+Brookline,+Massachusetts+02446&amp;ll=42.359876,-71.116219&amp;spn=0.022198,0.036478&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=928+Commonwealth+Avenue,+Boston,+MA&amp;aq=0&amp;oq=928+Commonwealth+ave,+Boston,+MA&amp;sll=42.367337,-71.05793&amp;sspn=0.01132,0.021415&amp;t=m&amp;g=928+Commercial+Street,+Boston,+MA&amp;ie=UTF8&amp;hq=&amp;hnear=928+Commonwealth+Ave,+Brookline,+Massachusetts+02446&amp;ll=42.359876,-71.116219&amp;spn=0.022198,0.036478&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">View Larger Map</a></small>
	</fieldset>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent
	ligula tortor, facilisis a rutrum a, ullamcorper quis urna. Praesent
	faucibus imperdiet nibh a luctus. Phasellus ac sem nisl, in imperdiet
	augue. In turpis erat, pharetra ut aliquet vitae, suscipit vitae massa.
	Integer mattis congue mi vel sollicitudin. Praesent feugiat, dui at
	laoreet sodales, ante libero faucibus augue, in gravida libero nulla et
	lectus. Fusce ultricies eleifend commodo. Vivamus a elit justo, ut
	vulputate massa. Etiam quis nibh ut eros vulputate lobortis. Proin
	sapien urna, auctor et fringilla vitae, commodo et nisi. Proin sed
	mattis nisi.</p>

<p>Donec venenatis ante eu sem imperdiet feugiat. Lorem ipsum dolor sit
	amet, consectetur adipiscing elit. Donec diam augue, rhoncus vitae
	dignissim sit amet, suscipit a libero. Phasellus ultrices turpis nec
	lacus sagittis dictum. Vivamus sodales tincidunt congue. Fusce mattis
	nibh eget dolor hendrerit porttitor. Nunc ligula mauris, tempus id
	scelerisque et, convallis nec diam. Vestibulum nibh nunc, pharetra eget
	bibendum laoreet, ultricies ut neque.</p>

<p>In vestibulum, risus vitae ornare tristique, quam augue posuere est,
	nec volutpat magna erat ut tellus. Vestibulum imperdiet dolor id massa
	iaculis nec posuere diam semper. Praesent vel tortor non turpis aliquet
	fringilla eu rutrum ligula. Nam placerat mi sem, sit amet auctor augue.
	Cras quis nunc arcu, eget ullamcorper leo. Integer euismod, felis vel
	mattis iaculis, leo dui laoreet sapien, ut vehicula nisl quam non quam.
	Nam rutrum diam eu est sagittis ac viverra justo pharetra. Nam ac odio
	ante, at tincidunt eros. Ut gravida, nibh tincidunt ultricies
	vestibulum, nibh velit pretium nisi, ac hendrerit odio eros eget nulla.
	In hac habitasse platea dictumst.</p>

<p>Donec in leo elit. Nam velit tortor, lobortis nec aliquet a, pharetra
	id lectus. Pellentesque vehicula orci metus, dictum porta lorem. Sed
	nec massa nisi, sit amet tristique erat. Cum sociis natoque penatibus
	et magnis dis parturient montes, nascetur ridiculus mus. Maecenas vel
	ante arcu. Donec nec est urna, et cursus velit. Vivamus interdum congue
	urna eu ornare. Etiam eget dolor turpis, a eleifend ligula. Vestibulum
	fringilla elementum leo, at sagittis elit pellentesque sed. Nullam
	bibendum fringilla enim, eget pellentesque mi vestibulum in. Cras
	molestie orci eu ante ultrices laoreet. In ipsum enim, placerat eget
	pulvinar sit amet, tincidunt sed turpis. Nunc auctor faucibus ligula,
	sit amet bibendum lorem porta at.</p>

<p>Aliquam pulvinar, leo sed pretium laoreet, eros urna rhoncus augue,
	id pretium dui urna sit amet libero. Nullam consectetur nisi lorem.
	Cras lacus neque, tincidunt ut accumsan nec, bibendum quis dolor.
	Integer luctus, tellus in auctor suscipit, nisi neque sollicitudin dui,
	malesuada consequat ipsum elit sed mi. Proin risus nisi, fermentum nec
	convallis in, imperdiet elementum sapien. Ut convallis, metus vitae
	laoreet dapibus, lacus dolor blandit lorem, id gravida odio magna
	laoreet dui. Morbi vitae vestibulum dui. Etiam bibendum nisi ac diam
	luctus luctus. Cum sociis natoque penatibus et magnis dis parturient
	montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis
	dis parturient montes, nascetur ridiculus mus. Mauris eu justo ut felis
	imperdiet aliquet eget et tellus.</p>
	<fieldset id="createdby">
	<legend>
	created by
	</legend>
	created by: ryszard kilarski<br>
	email: <a href="mailto:emrys@bu.edu">emrys@bu.edu</a>
	</fieldset>
<?php include('../include/footer.php'); ?>