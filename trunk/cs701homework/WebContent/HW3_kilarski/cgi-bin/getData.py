#!/usr/bin/python

# Import modules for CGI handling 
import cgi, cgitb 
import urllib2

# Create instance of FieldStorage 
form = cgi.FieldStorage() 

#download data from request parameter 'url'
print "Content-type:text/xml\r\n\r\n"
url = form.getvalue("url")
req = urllib2.Request(url)
response = urllib2.urlopen(req)
data = response.read()
print data


