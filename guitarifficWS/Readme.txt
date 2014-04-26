/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

 This is the meT CS 751 Final Project, creating Axis2 REST and SOAP services for the guitariffic application.
 
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
Use the build.xml file to deploy to an Axis2 server and run generate.service to generate your aar file.

Then use run.chartclient or run.songclient to run the REST-based client tests.

Here is a sample run of the tests.

Guitar Chart Services

     [java] ==========================Running:  getCharts==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getList xmlns:ns="http://service.guitariffic.com">
     [java] </ns:getList>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getListResponse xmlns:ns="http://service.guitariffic.com" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd"><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=35</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=36</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=159</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=33</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=158</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=34</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=157</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=39</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=156</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=155</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=37</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=154</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=38</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=152</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=153</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=150</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=151</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=43</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=42</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=41</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=40</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=22</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=169</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=23</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=24</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=25</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=166</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=26</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=165</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=27</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=168</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=28</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=167</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=29</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=161</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=3</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=162</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=2</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=163</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=1</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=164</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=30</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=7</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=6</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=32</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=5</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=160</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=31</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=4</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=9</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=8</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=19</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=179</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=17</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=178</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=18</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=177</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=15</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=176</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=16</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=13</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=14</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=11</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=12</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=170</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=21</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=171</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=20</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=174</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=175</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=109</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=172</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=108</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=173</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=107</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=106</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=105</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=104</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=103</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=102</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=99</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=101</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=100</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=98</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=97</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=96</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=95</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=94</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=93</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=92</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=91</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=90</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=180</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=181</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=10</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=182</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=183</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=184</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=185</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=186</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=88</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=89</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=116</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=117</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=79</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=114</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=78</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=115</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=77</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=112</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=113</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=110</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=111</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=118</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=119</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=82</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=83</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=80</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=81</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=86</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=87</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=84</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=85</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=125</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=67</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=126</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=66</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=127</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=69</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=128</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=68</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=121</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=122</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=123</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=124</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=129</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=70</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=120</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=71</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=72</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=73</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=74</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=75</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=76</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=134</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=135</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=132</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=133</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=59</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=138</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=58</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=139</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=57</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=136</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=56</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=137</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=55</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=64</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=65</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=62</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=63</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=131</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=60</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=130</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=61</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=143</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=49</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=144</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=48</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=145</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=146</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=147</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=45</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=148</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=44</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=149</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=47</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=46</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=51</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=52</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=53</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=54</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=140</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=142</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=141</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=50</ns:return></ns:getListResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getListResponse xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=35
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=36
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=159
     [java]  </ns:return>
     [java]  <ns:return>
     
	<snip!  There's 180+ of them, I cut the rest out>  

     [java] </ns:getListResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End getList
     [java]  
     [java] **************************************Testing get
     [java] ==========================Running:  get 1==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      1
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>X 123 </ax27:chordFingering><ax27:chordFrets>  222</ax27:chordFrets><ax27:chordName>A</ax27:chordName><ax27:chordPosition>1</ax27:chordPosition><ax27:id>1</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       X 123
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       222
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       A
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       1
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       1
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  get 15==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>113121</ax27:chordFingering><ax27:chordFrets>113121</ax27:chordFrets><ax27:chordName>A#m7</ax27:chordName><ax27:chordPosition>13</ax27:chordPosition><ax27:id>15</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       113121
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       113121
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       A#m7
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       13
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       15
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End get
     [java]  
     [java] **************************************Testing add
     [java] ==========================Running:  add ==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:add xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:chart>
     [java]   <ns:chordName>
     [java]       R#
     [java]   </ns:chordName>
     [java]   <ns:chordFingering>
     [java]       123456
     [java]   </ns:chordFingering>
     [java]   <ns:chordFrets>
     [java]       654321
     [java]   </ns:chordFrets>
     [java]   <ns:chordPosition>
     [java]       3
     [java]   </ns:chordPosition>
     [java]   <ns:isLeftHandedFrets>
     [java]       false
     [java]   </ns:isLeftHandedFrets>
     [java]  </ns:chart>
     [java] </ns:add>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:addResponse xmlns:ns="http://service.guitariffic.com"><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=187</ns:return></ns:addResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:addResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=187
     [java]  </ns:return>
     [java] </ns:addResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  get  after add==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      187
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>123456</ax27:chordFingering><ax27:chordFrets>654321</ax27:chordFrets><ax27:chordName>R#</ax27:chordName><ax27:chordPosition>3</ax27:chordPosition><ax27:id>187</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       123456
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       654321
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       R#
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       3
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       187
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End add
     [java]  
     [java] **************************************Testing update
     [java] ==========================Running:  get 15 before==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>113121</ax27:chordFingering><ax27:chordFrets>113121</ax27:chordFrets><ax27:chordName>A#m7</ax27:chordName><ax27:chordPosition>13</ax27:chordPosition><ax27:id>15</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       113121
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       113121
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       A#m7
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       13
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       15
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  update==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:update xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:chart>
     [java]   <ns:id>
     [java]       15
     [java]   </ns:id>
     [java]   <ns:chordName>
     [java]       A#m7
     [java]   </ns:chordName>
     [java]   <ns:chordFingering>
     [java]       113121
     [java]   </ns:chordFingering>
     [java]   <ns:chordFrets>
     [java]       123456
     [java]   </ns:chordFrets>
     [java]   <ns:chordPosition>
     [java]       13
     [java]   </ns:chordPosition>
     [java]   <ns:isLeftHandedFrets>
     [java]       true
     [java]   </ns:isLeftHandedFrets>
     [java]  </ns:chart>
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:update>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:updateResponse xmlns:ns="http://service.guitariffic.com"><ns:return>http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=15</ns:return></ns:updateResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:updateResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/GuitarChartService/get?id=15
     [java]  </ns:return>
     [java] </ns:updateResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  get 15 after==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>113121</ax27:chordFingering><ax27:chordFrets>123456</ax27:chordFrets><ax27:chordName>A#m7</ax27:chordName><ax27:chordPosition>13</ax27:chordPosition><ax27:id>15</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       113121
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       123456
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       A#m7
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       13
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       15
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End update
     [java]  
     [java] **************************************Testing delete
     [java] ==========================Running:  get 15 before deleting it==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart"><ax27:chordFingering>113121</ax27:chordFingering><ax27:chordFrets>123456</ax27:chordFrets><ax27:chordName>A#m7</ax27:chordName><ax27:chordPosition>13</ax27:chordPosition><ax27:id>15</ax27:id><ax27:leftHanded>false</ax27:leftHanded></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax27:GuitarChart">
     [java]   <ax27:chordFingering>
     [java]       113121
     [java]   </ax27:chordFingering>
     [java]   <ax27:chordFrets>
     [java]       123456
     [java]   </ax27:chordFrets>
     [java]   <ax27:chordName>
     [java]       A#m7
     [java]   </ax27:chordName>
     [java]   <ax27:chordPosition>
     [java]       13
     [java]   </ax27:chordPosition>
     [java]   <ax27:id>
     [java]       15
     [java]   </ax27:id>
     [java]   <ax27:leftHanded>
     [java]       false
     [java]   </ax27:leftHanded>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  delete==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:delete xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:delete>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] ==========================Running:  get 15 after deleting it (should result in a GuitarChartNotFound fault)==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      15
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] Exception in thread "main" org.apache.axis2.AxisFault: <ns:GuitarChartServiceGuitarChartNotFound xmlns:ns="http://service.guitariffic.com"><GuitarChartNotFound xmlns="http://service.guitariffic.com" xmlns:ax27="http://model.guitariffic.com/xsd" xmlns:ax25="http://exception.service.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax25:GuitarChartNotFound" /></ns:GuitarChartServiceGuitarChartNotFound>
     [java] 	at org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(Utils.java:536)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.handleResponse(OutInAxisOperation.java:375)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.send(OutInAxisOperation.java:421)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.executeImpl(OutInAxisOperation.java:229)
     [java] 	at org.apache.axis2.client.OperationClient.execute(OperationClient.java:165)
     [java] 	at org.apache.axis2.client.ServiceClient.sendReceive(ServiceClient.java:555)
     [java] 	at org.apache.axis2.client.ServiceClient.sendReceive(ServiceClient.java:531)
     [java] 	at client.GuitarChartClient.testElementOMElement(GuitarChartClient.java:138)
     [java] 	at client.GuitarChartClient.main(GuitarChartClient.java:62)
     [java] Java Result: 1
BUILD SUCCESSFUL
Total time: 2 seconds


Note:  That exception at the end is CORRECT since that shows the chart has been deleted.

Song Services

     [java] ==========================Running:  getSongs==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getList xmlns:ns="http://service.guitariffic.com">
     [java] </ns:getList>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getListResponse xmlns:ns="http://service.guitariffic.com" xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd"><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=3</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=2</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=1</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=5</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=4</ns:return></ns:getListResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getListResponse xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=3
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=2
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=1
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=5
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=4
     [java]  </ns:return>
     [java] </ns:getListResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End getList
     [java]  
     [java] **************************************Testing get
     [java] ==========================Running:  get 4==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      4
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song"><ax23:artistName>Elton John</ax23:artistName><ax23:chords><ax23:array xsi:type="ax23:GuitarChart"><ax23:chordFingering>123456</ax23:chordFingering><ax23:chordFrets>654321</ax23:chordFrets><ax23:chordName>A</ax23:chordName><ax23:chordPosition>1</ax23:chordPosition><ax23:id>1</ax23:id><ax23:leftHanded>false</ax23:leftHanded></ax23:array><ax23:array xsi:type="ax23:GuitarChart"><ax23:chordFingering>234567</ax23:chordFingering><ax23:chordFrets>765432</ax23:chordFrets><ax23:chordName>B</ax23:chordName><ax23:chordPosition>2</ax23:chordPosition><ax23:id>2</ax23:id><ax23:leftHanded>true</ax23:leftHanded></ax23:array></ax23:chords><ax23:chords><ax23:array xsi:type="ax23:GuitarChart"><ax23:chordFingering>345678</ax23:chordFingering><ax23:chordFrets>876543</ax23:chordFrets><ax23:chordName>C</ax23:chordName><ax23:chordPosition>3</ax23:chordPosition><ax23:id>3</ax23:id><ax23:leftHanded>false</ax23:leftHanded></ax23:array><ax23:array xsi:type="ax23:GuitarChart"><ax23:chordFingering>456789</ax23:chordFingering><ax23:chordFrets>987654</ax23:chordFrets><ax23:chordName>D</ax23:chordName><ax23:chordPosition>4</ax23:chordPosition><ax23:id>4</ax23:id><ax23:leftHanded>true</ax23:leftHanded></ax23:array></ax23:chords><ax23:id>4</ax23:id><ax23:lyrics>It's a human sign</ax23:lyrics><ax23:lyrics>When things go wrong</ax23:lyrics><ax23:songName>Sacrifice</ax23:songName><ax23:urls>http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg</ax23:urls><ax23:urls>http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7086/13886219436_98277a7bb1_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg</ax23:urls></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song">
     [java]   <ax23:artistName>
     [java]       Elton John
     [java]   </ax23:artistName>
     [java]   <ax23:chords>
     [java]    <ax23:array xsi:type="ax23:GuitarChart">
     [java]     <ax23:chordFingering>
     [java]         123456
     [java]     </ax23:chordFingering>
     [java]     <ax23:chordFrets>
     [java]         654321
     [java]     </ax23:chordFrets>
     [java]     <ax23:chordName>
     [java]         A
     [java]     </ax23:chordName>
     [java]     <ax23:chordPosition>
     [java]         1
     [java]     </ax23:chordPosition>
     [java]     <ax23:id>
     [java]         1
     [java]     </ax23:id>
     [java]     <ax23:leftHanded>
     [java]         false
     [java]     </ax23:leftHanded>
     [java]    </ax23:array>
     [java]    <ax23:array xsi:type="ax23:GuitarChart">
     [java]     <ax23:chordFingering>
     [java]         234567
     [java]     </ax23:chordFingering>
     [java]     <ax23:chordFrets>
     [java]         765432
     [java]     </ax23:chordFrets>
     [java]     <ax23:chordName>
     [java]         B
     [java]     </ax23:chordName>
     [java]     <ax23:chordPosition>
     [java]         2
     [java]     </ax23:chordPosition>
     [java]     <ax23:id>
     [java]         2
     [java]     </ax23:id>
     [java]     <ax23:leftHanded>
     [java]         true
     [java]     </ax23:leftHanded>
     [java]    </ax23:array>
     [java]   </ax23:chords>
     [java]   <ax23:chords>
     [java]    <ax23:array xsi:type="ax23:GuitarChart">
     [java]     <ax23:chordFingering>
     [java]         345678
     [java]     </ax23:chordFingering>
     [java]     <ax23:chordFrets>
     [java]         876543
     [java]     </ax23:chordFrets>
     [java]     <ax23:chordName>
     [java]         C
     [java]     </ax23:chordName>
     [java]     <ax23:chordPosition>
     [java]         3
     [java]     </ax23:chordPosition>
     [java]     <ax23:id>
     [java]         3
     [java]     </ax23:id>
     [java]     <ax23:leftHanded>
     [java]         false
     [java]     </ax23:leftHanded>
     [java]    </ax23:array>
     [java]    <ax23:array xsi:type="ax23:GuitarChart">
     [java]     <ax23:chordFingering>
     [java]         456789
     [java]     </ax23:chordFingering>
     [java]     <ax23:chordFrets>
     [java]         987654
     [java]     </ax23:chordFrets>
     [java]     <ax23:chordName>
     [java]         D
     [java]     </ax23:chordName>
     [java]     <ax23:chordPosition>
     [java]         4
     [java]     </ax23:chordPosition>
     [java]     <ax23:id>
     [java]         4
     [java]     </ax23:id>
     [java]     <ax23:leftHanded>
     [java]         true
     [java]     </ax23:leftHanded>
     [java]    </ax23:array>
     [java]   </ax23:chords>
     [java]   <ax23:id>
     [java]       4
     [java]   </ax23:id>
     [java]   <ax23:lyrics>
     [java]       It's a human sign
     [java]   </ax23:lyrics>
     [java]   <ax23:lyrics>
     [java]       When things go wrong
     [java]   </ax23:lyrics>
     [java]   <ax23:songName>
     [java]       Sacrifice
     [java]   </ax23:songName>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7086/13886219436_98277a7bb1_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg
     [java]   </ax23:urls>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End get
     [java]  
     [java] **************************************Testing add
     [java] ==========================Running:  add ==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:add xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:song>
     [java]   <ns:songName>
     [java]       Made In England
     [java]   </ns:songName>
     [java]   <ns:artistName>
     [java]       Elton John
     [java]   </ns:artistName>
     [java]  </ns:song>
     [java] </ns:add>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:addResponse xmlns:ns="http://service.guitariffic.com"><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=6</ns:return></ns:addResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:addResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=6
     [java]  </ns:return>
     [java] </ns:addResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  get  after add==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      6
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song"><ax23:artistName>Elton John</ax23:artistName><ax23:chords xsi:nil="true" /><ax23:id>6</ax23:id><ax23:lyrics xsi:nil="true" /><ax23:songName>Made In England</ax23:songName><ax23:urls>http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg</ax23:urls><ax23:urls>http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3756/13909755684_fbb174407a_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3829/13909777294_e2af342322_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7086/13886219436_98277a7bb1_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg</ax23:urls></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song">
     [java]   <ax23:artistName>
     [java]       Elton John
     [java]   </ax23:artistName>
     [java]   <ax23:chords xsi:nil="true">
     [java]   </ax23:chords>
     [java]   <ax23:id>
     [java]       6
     [java]   </ax23:id>
     [java]   <ax23:lyrics xsi:nil="true">
     [java]   </ax23:lyrics>
     [java]   <ax23:songName>
     [java]       Made In England
     [java]   </ax23:songName>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3756/13909755684_fbb174407a_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3829/13909777294_e2af342322_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7086/13886219436_98277a7bb1_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg
     [java]   </ax23:urls>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End add
     [java]  
     [java] **************************************Testing update
     [java] ==========================Running:  get before update==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      6
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song"><ax23:artistName>Elton John</ax23:artistName><ax23:chords xsi:nil="true" /><ax23:id>6</ax23:id><ax23:lyrics xsi:nil="true" /><ax23:songName>Made In England</ax23:songName><ax23:urls>http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg</ax23:urls><ax23:urls>http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg</ax23:urls></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song">
     [java]   <ax23:artistName>
     [java]       Elton John
     [java]   </ax23:artistName>
     [java]   <ax23:chords xsi:nil="true">
     [java]   </ax23:chords>
     [java]   <ax23:id>
     [java]       6
     [java]   </ax23:id>
     [java]   <ax23:lyrics xsi:nil="true">
     [java]   </ax23:lyrics>
     [java]   <ax23:songName>
     [java]       Made In England
     [java]   </ax23:songName>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3773/13909359505_15767ec129_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg
     [java]   </ax23:urls>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  update==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:update xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:song>
     [java]   <ns:id>
     [java]       6
     [java]   </ns:id>
     [java]   <ns:songName>
     [java]       Made In England
     [java]   </ns:songName>
     [java]   <ns:artistName>
     [java]       Elton John
     [java]   </ns:artistName>
     [java]   <ns:lyrics>
     [java]       I was made in England
     [java]   </ns:lyrics>
     [java]   <ns:lyrics>
     [java]       Out of Cadillac muscle
     [java]   </ns:lyrics>
     [java]  </ns:song>
     [java]  <ns:id>
     [java]      6
     [java]  </ns:id>
     [java] </ns:update>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:updateResponse xmlns:ns="http://service.guitariffic.com"><ns:return>http://192.168.1.139:8080/axis2/services/SongService/get?id=6</ns:return></ns:updateResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:updateResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/SongService/get?id=6
     [java]  </ns:return>
     [java] </ns:updateResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  get after update==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      6
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song"><ax23:artistName>Elton John</ax23:artistName><ax23:chords xsi:nil="true" /><ax23:id>6</ax23:id><ax23:lyrics>I was made in England</ax23:lyrics><ax23:lyrics>Out of Cadillac muscle</ax23:lyrics><ax23:songName>Made In England</ax23:songName><ax23:urls>http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg</ax23:urls><ax23:urls>http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg</ax23:urls></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song">
     [java]   <ax23:artistName>
     [java]       Elton John
     [java]   </ax23:artistName>
     [java]   <ax23:chords xsi:nil="true">
     [java]   </ax23:chords>
     [java]   <ax23:id>
     [java]       6
     [java]   </ax23:id>
     [java]   <ax23:lyrics>
     [java]       I was made in England
     [java]   </ax23:lyrics>
     [java]   <ax23:lyrics>
     [java]       Out of Cadillac muscle
     [java]   </ax23:lyrics>
     [java]   <ax23:songName>
     [java]       Made In England
     [java]   </ax23:songName>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7092/13909786844_f005dd8fdc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7239/13909335925_fc744cd72f_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg
     [java]   </ax23:urls>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End update
     [java]  
     [java] **************************************Testing delete
     [java] ==========================Running:  get 2 before deleting it==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      2
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com"><ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song"><ax23:artistName>Elton John</ax23:artistName><ax23:chords xsi:nil="true" /><ax23:id>2</ax23:id><ax23:lyrics xsi:nil="true" /><ax23:songName>I Guess That's Why They Call It The Blues</ax23:songName><ax23:urls>http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg</ax23:urls><ax23:urls>http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg</ax23:urls><ax23:urls>http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg</ax23:urls><ax23:urls>http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg</ax23:urls><ax23:urls>http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg</ax23:urls></ns:return></ns:getResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getResponse xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:return xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax23:Song">
     [java]   <ax23:artistName>
     [java]       Elton John
     [java]   </ax23:artistName>
     [java]   <ax23:chords xsi:nil="true">
     [java]   </ax23:chords>
     [java]   <ax23:id>
     [java]       2
     [java]   </ax23:id>
     [java]   <ax23:lyrics xsi:nil="true">
     [java]   </ax23:lyrics>
     [java]   <ax23:songName>
     [java]       I Guess That's Why They Call It The Blues
     [java]   </ax23:songName>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2938/14005741801_eefd8bd366_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm6.staticflickr.com/5051/13967065036_f0d657de73_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7341/13967065736_dfd93587d3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7362/14010171603_98b23d64fb_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7004/14000377493_529de4f887_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3788/13908223842_39e7ef81c9_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7095/13925170615_bb623532e2_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7216/13886221966_da58281ea3_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7112/13909330075_85db1f40dc_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3767/13909772104_a6778642db_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7143/13909771004_3e3e51a0c7_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3810/13886254156_0c1006eede_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2839/13886212151_de8128abd6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7021/13886247232_9ee64c76ec_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7420/13886231382_a1a0614eaf_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2808/13909774364_5a11d4c1f6_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm3.staticflickr.com/2838/13909331875_6a74d9e633_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3789/13886212621_f9a2f41f04_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm4.staticflickr.com/3716/13886234512_10d29a257a_b.jpg
     [java]   </ax23:urls>
     [java]   <ax23:urls>
     [java]       http://farm8.staticflickr.com/7094/13909787674_2e62ac5b78_b.jpg
     [java]   </ax23:urls>
     [java]  </ns:return>
     [java] </ns:getResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  delete==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:delete xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      2
     [java]  </ns:id>
     [java] </ns:delete>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] ==========================Running:  get 2 after deleting it (should result in a SongNotFound fault)==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:get xmlns:ns="http://service.guitariffic.com">
     [java]  <ns:id>
     [java]      2
     [java]  </ns:id>
     [java] </ns:get>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] Exception in thread "main" org.apache.axis2.AxisFault: <ns:SongServiceSongNotFound xmlns:ns="http://service.guitariffic.com"><SongNotFound xmlns="http://service.guitariffic.com" xmlns:ax21="http://exception.service.guitariffic.com/xsd" xmlns:ax23="http://model.guitariffic.com/xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ax21:SongNotFound" /></ns:SongServiceSongNotFound>
     [java] 	at org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(Utils.java:536)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.handleResponse(OutInAxisOperation.java:375)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.send(OutInAxisOperation.java:421)
     [java] 	at org.apache.axis2.description.OutInAxisOperationClient.executeImpl(OutInAxisOperation.java:229)
     [java] 	at org.apache.axis2.client.OperationClient.execute(OperationClient.java:165)
     [java] 	at org.apache.axis2.client.ServiceClient.sendReceive(ServiceClient.java:555)
     [java] 	at org.apache.axis2.client.ServiceClient.sendReceive(ServiceClient.java:531)
     [java] 	at client.SongClient.testElementOMElement(SongClient.java:116)
     [java] 	at client.SongClient.main(SongClient.java:52)
     [java] Java Result: 1
BUILD SUCCESSFUL
Total time: 4 seconds

Note:  That exception at the end is CORRECT since that shows the song has been deleted.
