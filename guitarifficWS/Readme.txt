/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

 This is the meT CS 751 Final Project, creating an Axis2 REST service for the guitariffic application.
 
client
 	This is the REST-based client classes that test the services.

com.guitariffic.dbo
	This is the database abstraction layer. The StorageFactory returns an object of type SongDBHelper or GuitarChartDBHelper, depending on which is needed.
	Each of those interfaces defines add/edit/update/list functionality for the songs and guitar charts.
	
	Currently there are two schemes:  Memory-based and Sql-based.  The Sql-based scheme is currently empty, ready for future coding.
	The memory-based DBHelpers load songs and charts into memory.  The chart class loads a bunch of chords from an XML file, while the song class creates a few in-memory songs.
	 	
com.guitariffic.image
	This is the image abstraction layer.  The ImageImpl returns an object of type ImageImpl.  The interface defines the getImages functionality for images.
	
	Currently there are two schemes: Flickr and Google.  Google is currently empty, ready for future coding.
	The Flickr implementation calls the public flickr API that returns a list of public images given keywords.
 
com.guitariffic.model
 	These are my domain objects.
 
com.guitariffic.service
	These are my service definition classes.  I broke out the classes to services for songs and for guitar charts.  Each provide a service to add/update/delete/list charts and songs.

com.guitariffic.service.exception
	These are service exceptions that can be returned by the services.
	
To run:
Use the build.xml file to deploy to an Axis2 server.

Here is a sample run of the tests.
