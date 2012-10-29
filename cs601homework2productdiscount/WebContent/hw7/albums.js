var dataArray = new Array();

/*
 * Form routines.
 */
function onLoadBody() {
	dataArray = initializeData();
	resetForm();
}

// Handle the album dropdown.
function selectAlbum() {
	var selectedAlbum = document.getElementById("album").value;
	var albumInfo = getAlbumInformation(selectedAlbum);

	if (albumInfo != null) {
		document.getElementById("albumImage").src = albumInfo[3];
		document.getElementById("albumName").innerHTML = albumInfo[1];
		document.getElementById("albumYear").innerHTML = albumInfo[0];
		document.getElementById("albumReview").innerHTML = albumInfo[2];
		document.getElementById("details").style.visibility = "visible";
	}
}

function resetForm() {
	var albumArray = getAlbumArray(dataArray);
	var selectElement = document.getElementById("album");
	addArrayToSelect(albumArray, selectElement);

	document.getElementById("details").style.visibility = "hidden";
}

/*
 * Data routines.
 */
function getAlbumInformation(albumValue) {
	return dataArray[albumValue];
}
function getAlbumArray(dataArray) {
	var array = new Array();
	// Create array for item
	for ( var i = 0; i < dataArray.length; i++) {
		var item = [ i, dataArray[i][1] ];
		if (array.indexOf(item) == -1) {
			array.push(item);
		}
	}
	return array;
}
function initializeData(dataArray) {
	var dataArray = new Array();

	element = [
			"1969",
			"Empty Sky",
			"Elton's first album, but considered more of a \"pre-album\" due to its immaturity.",
			"albums/empty_sky.jpg" ];
	dataArray.push(element);
	element = [
			"1970",
			"Elton John",
			"The classic album with amazing songs, this was his first huge success.",
			"albums/elton_john.jpg" ];
	dataArray.push(element);
	element = [
			"1970",
			"Tumbleweed Connection",
			"A great follow-up, this album introduces the country sound that will fascinate Elton and his writing partner Bernie.",
			"albums/tumbleweed_connection.jpg" ];
	dataArray.push(element);
	element = [ "1971", "11-17-70", "The first live album, it really rocks.",
			"albums/17-11-70.jpg" ];
	dataArray.push(element);
	element = [
			"1971",
			"Friends Soundtrack",
			"Elton's first soundtrack of mostly musical pieces; it's a classic for the fans, but didn't make a dent anywhere else.",
			"albums/friends.jpg" ];
	dataArray.push(element);
	element = [
			"1971",
			"Madman Across The Water",
			"Another brilliant album containing the instant classics Tiny Dancer and Levon.",
			"albums/madman_across_the_water.jpg" ];
	dataArray.push(element);
	element = [
			"1972",
			"Honky Chateau",
			"You thought he couldn't get better, and then we get Honky Cat and Rocket Man on this album.",
			"albums/honky_chateau.jpg" ];
	dataArray.push(element);
	element = [ "1973", "Don't Shoot Me I'm Only The Piano Player",
			"This album brought us the classics of Crocodile Rock and Daniel.",
			"albums/dont_shoot_me.jpg" ];
	dataArray.push(element);
	element = [
			"1973",
			"Goodbye Yellow Brick Road",
			"Generally considered Elton's magnum opus, this double-album is full of hit after hit.  Saturday Night's Alright for Fighting, Goodbye Yellow Brick Road, Bennie and the Jets, and of course, Candle in the Wind.  And the unknown tracks on this album are just as good.  An amazing tour de force.",
			"albums/goodbye_yellow_brick_road.jpg" ];
	dataArray.push(element);
	element = [
			"1974",
			"Caribou",
			"Impossible to follow-up the double album, so he didn't.  This album was an average album, only having average tracks like the Bitch is Back and Don't Let The Sun Go Down On Me.",
			"albums/caribou.jpg" ];
	dataArray.push(element);
	element = [
			"1975",
			"Captain Fantastic And The Brown Dirt Cowboy",
			"A quieter, introspective, and biographical album, Elton is the Captain Fantastic and Bernie Taupin is the Brown Dirt Cowboy.  This album is considered \"for the fans\" rather than for commercial success.  But it still charted highly.",
			"albums/captain_fantastic.jpg" ];
	dataArray.push(element);
	element = [ "1975", "Rock Of The Westies",
			"A solid rock album but with no memorable songs.",
			"albums/rock_of_the_westies.jpg" ];
	dataArray.push(element);
	element = [
			"1976",
			"Here And There",
			"The second live album, recorded half in the UK and half in the US.  ",
			"albums/here_and_there.jpg" ];
	dataArray.push(element);
	element = [
			"1976",
			"Blue Moves",
			"Blue moves is right, this is a downer of an album.  Even the standout tracks were re-done better in future live albums.",
			"albums/blue_moves.jpg" ];
	dataArray.push(element);
	element = [
			"1978",
			"A Single Man",
			"Elton was experimenting with other lyricists, which did not go well.  Additionally, he was experimenting with new sounds like disco and funk.",
			"albums/a_single_man.jpg" ];
	dataArray.push(element);
	element = [ "1979", "Victim Of Love",
			"His disco album.  We shall speak no more of this.",
			"albums/victim_of_love.jpg" ];
	dataArray.push(element);
	element = [
			"1980",
			"21 At 33",
			"Elton's first album of the 80's, clearly a transitional album with not much going on.  ",
			"albums/21_at_33.jpg" ];
	dataArray.push(element);
	element = [
			"1981",
			"The Fox",
			"Another album of his \"down\" period; some good sweet songs, but also didn't go anywhere.  This is part of Elton's \"put a stamp on it, send it out\" set of albums, with minimal craftsmanship or musicality.",
			"albums/the_fox.jpg" ];
	dataArray.push(element);
	element = [
			"1982",
			"Jump Up",
			"Yet another \"down period\" album, but a single stand-out of Empty Garden as an amazing song, due to John Lennon's death.",
			"albums/jump_up.jpg" ];
	dataArray.push(element);
	element = [
			"1983",
			"Too Low For Zero",
			"A great album even in his \"down period\" of the 80's, the real Elton surfaced for a little bit to give us I'm Still Standing, I Guess That's Why They Call It The Blues, and several other hummable songs.",
			"albums/too_low_for_zero.jpg" ];
	dataArray.push(element);
	element = [
			"1984",
			"Breaking Hearts",
			"Firmly in the grip of 80's pop, this album was just OK; too much electronica and drum machines, but we still got Sad Songs (Say So Much).",
			"albums/breaking_hearts.jpg" ];
	dataArray.push(element);
	element = [ "1985", "Ice On Fire",
			"A weak attempt by Elton in his coke and pot-fueled years.",
			"albums/ice_on_fire.jpg" ];
	dataArray.push(element);
	element = [ "1986", "Leather Jackets",
			"A weak attempt by Elton in his coke and pot-fueled years.",
			"albums/leather_jackets.jpg" ];
	dataArray.push(element);
	element = [
			"1987",
			"Live In Australia With The Melbourne Symphony Orchestra",
			"This album blew the doors off with live albums.  On a tourin Australia, Elton recorded an amazing set of classic songs, including THE seminal version of Candle in the Wind.  An amazing album, and a must-own.",
			"albums/live_in_australia.jpg" ];
	dataArray.push(element);
	element = [
			"1988",
			"Reg Strikes Back",
			"A transitional solid album with Elton beginning to clean up after his throat surgery, a move to a deeper register, and a mental clearing out of the 80's musical mindset.",
			"albums/reg_strikes_back.jpg" ];
	dataArray.push(element);
	element = [
			"1989",
			"Sleeping With The Past",
			"The last album of his drugged days, this was actually an amazing album inspired by gospel and southern songs.  This included Sacrifice, Elton's first number one hit worldwide.",
			"albums/sleeping_with_the_past.jpg" ];
	dataArray.push(element);
	element = [
			"1992",
			"The One",
			"His first post-recovery album, designed by Gianni Versace, it signaled a beginning renaissance for Elton.  Still relying too much on electropop, it had several great hits.",
			"albums/the_one.jpg" ];
	dataArray.push(element);
	element = [
			"1992",
			"Rare Masters",
			"As part of his time off, this absolute gem of an album came out as a collection of old demos and some great remastered old tracks.",
			"albums/rare_masters.jpg" ];
	dataArray.push(element);
	element = [
			"1993",
			"Duets",
			"As part of his time off, this great little album is a set of duets with some really great artists.",
			"albums/duets.jpg" ];
	dataArray.push(element);
	element = [
			"1994",
			"The Lion King Soundtrack",
			"What does Elton do on his time off?  Write the Grammy and Oscar winning soundtrack to one of Disney's greatest movies.",
			"albums/the_lion_king.jpg" ];
	dataArray.push(element);
	element = [
			"1995",
			"Made In England",
			"Afte a long hiatus, Elton returns in this new album with a great clean voice and sound, minimalist lyrics from Bernie, and some beautiful songs.",
			"albums/made_in_england.jpg" ];
	dataArray.push(element);
	element = [
			"1997",
			"The Big Picture",
			"The next album in Elton's 90's comeback, a slightly darker and lusher tone.",
			"albums/the_big_picture.jpg" ];
	dataArray.push(element);
	element = [
			"1999",
			"Elton John and Tim Rice's Aida",
			"His first real musical written for the screen, when Aida succeeds, it blows it out of the park.",
			"albums/aida.jpg" ];
	dataArray.push(element);
	element = [
			"1999",
			"The Muse Soundtrack",
			"Mostly piano pieces, this quirky little album is for completists and fans, but not for the general public.",
			"albums/the_muse.jpg" ];
	dataArray.push(element);
	element = [
			"2000",
			"The Road To El Dorado Soundtrack",
			"While this was ostensibly a Dreamworks movie, the album stands on its own as a wonderful addition to the Elton canon, with lyrics by Tim Rice of Lion King fame.",
			"albums/el_dorado.jpg" ];
	dataArray.push(element);
	element = [
			"2001",
			"Songs From The West Coast",
			"Elton's attempt at west-coast music, this great pop album has some wonderful moments and great lyrics.  Elton's comeback continues.",
			"albums/songs_from_the_west_coast.jpg" ];
	dataArray.push(element);
	element = [
			"2004",
			"Peachtree Road",
			"A slower album for the 2000's, Elton was clearly inspired by Georgia and the Southern style of music.",
			"albums/peachtree_road.jpg" ];
	dataArray.push(element);
	element = [
			"2006",
			"The Captain And The Kid",
			"An updated autobiographical album (bookending Captain Fantastic and the Brown Dirt Cowboy), this is an amazing set of songs with a clean and fresh sound.  Elton going back to his roots and singing about what he knows about.",
			"albums/the_captain_and_the_kid.jpg" ];
	dataArray.push(element);
	element = [
			"2010",
			"The Union",
			"Co-written with his idol Leon Russell, this is older Elton at his best.  Amazing songs, amazing performances, and clearly a big thank-you to fans.",
			"albums/the_union.jpg" ];
	dataArray.push(element);
	element = [
			"2011",
			"Gnomeo And Juliet Soundtrack",
			"A movie about garden gnomes set to Elton's music.  Let's just pretend it didn't happen.",
			"albums/gnomeo.jpg" ];
	dataArray.push(element);
	return dataArray;
}

function addArrayToSelect(arrayList, selectItem) {
	selectItem.length = 0; // Reset select item.
	addOption("", "Select One", selectItem, true); // Add first item.
	for ( var i = 0; i < arrayList.length; i++) {
		addOption(arrayList[i][0], arrayList[i][1], selectItem);
	}
}

function addOption(value, text, selectItem, selectedFlag) {
	if (typeof (selectedFlag) === 'undefined') {
		selectedFlag = false;
	}
	var opt = document.createElement("option");
	opt.text = text;
	opt.value = value;
	opt.selected = selectedFlag;
	selectItem.options.add(opt);
}