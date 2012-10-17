<?php 
if(session_id() == '') {
	session_set_cookie_params(31536000,'/');
	session_start();
}
include("include/homeheader.php") ?>
<div id="dhonis">
	<div
		class="dhonishow effect_blind duration_2 autoplay_5 hide_navigation">
		<img src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
			src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
			src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" /> <img
			src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" />
	</div>
	<div
		class="dhonishow middle effect_appear duration_2 autoplay_5 hide_navigation">
		<img src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" /> <img
			src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" /> <img
			src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
			src="img/bg/bg-02.jpg" width="492" height="330" alt="" />
	</div>
	<div
		class="dhonishow effect_horizontal duration_2 autoplay_5 hide_navigation">
		<img src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
			src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" /> <img
			src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" /> <img
			src="img/bg/bg-01.jpg" width="550" height="352" alt="" />
	</div>
	<div
		class="dhonishow effect_horizontal duration_2 autoplay_5 hide_navigation">
		<img src="img/bg/bg-04.jpg" width="3008" height="2000" alt="" /> <img
			src="img/bg/bg-02.jpg" width="492" height="330" alt="" /> <img
			src="img/bg/bg-01.jpg" width="550" height="352" alt="" /> <img
			src="img/bg/bg-03.jpg" width="2592" height="1936" alt="" />
	</div>
</div>
<script>
				window.onload = function() {
					var a = new SearchDhonishow();
				}
			</script>
</div>
<form name="mailinglist" action="" method="post">
	<div id="mailinglist">
		<label for="emailaddress">joinourmailinglist:</label> <input
			type="email" name="emailaddress" placeholder="emailaddress" size="50">
		<input type="submit" value="Submit">
	</div>
</form>
<?php include("include/footer.php") ?>
