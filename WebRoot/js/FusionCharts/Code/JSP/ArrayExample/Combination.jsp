<%
	/*
	We've included ../Includes/FusionCharts.jsp, which contains functions
	to help us easily create the charts that can be embedded.
	*/
%>
<%@ include file="../Includes/FusionCharts.jsp"%>

<HTML>
<HEAD>
<TITLE>FusionCharts - Array Example using Combination Column 3D
Line Chart</TITLE>
<%
	/*You need to include the following JS file, if you intend to embed the chart using JavaScript.
	Embedding using JavaScripts avoids the "Click to Activate..." issue in Internet Explorer
	When you make your own charts, make sure that the path to this JS file is correct. Else, you would get JavaScript errors.
	*/
	%>
<SCRIPT LANGUAGE="Javascript" SRC="../../FusionCharts/FusionCharts.js"></SCRIPT>
<style type="text/css">
	<!--
	body {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
	}
	-->
	</style>
</HEAD>

<BODY>

<CENTER>
<h2>FusionCharts Examples</h2>
<h4>Plotting Combination chart from data contained in Array.</h4>
<%
	/*In this example, we plot a Combination chart from data contained
	 in an array. The array will have three columns - first one for Quarter Name
	 second one for sales figure and third one for quantity. 
	 */
		String[][] arrData = new String[4][3];
		//Store Quarter Name
		arrData[0][0] = "Quarter 1";
		arrData[1][0] = "Quarter 2";
		arrData[2][0] = "Quarter 3";
		arrData[3][0] = "Quarter 4";


		//Store revenue data
		arrData[0][1] = "576000";
		arrData[1][1] = "448000";
		arrData[2][1] = "956000";
		arrData[3][1]= "734000";


		//Store Quantity
		arrData[0][2]= "576";
		arrData[1][2] = "448";
		arrData[2][2]= "956";
		arrData[3][2]= "734";

	
		String strXML="";
	    /*Now, we need to convert this data into combination XML. 
	    We convert using string concatenation.
	    strXML - Stores the entire XML
	    strCategories - Stores XML for the <categories> and child <category> elements
	    strDataRev - Stores XML for current year's sales
	    strDataQty - Stores XML for previous year's sales*/
	    
	    
	    //Initialize <chart> element
	    strXML = "<chart palette='4' caption='Product A - Sales Details' PYAxisName='Revenue' SYAxisName='Quantity (in Units)' numberPrefix='$' formatNumberScale='0' showValues='0' decimals='0' >";
	    
	    //Initialize <categories> element - necessary to generate a multi-series chart
	    String strCategories = "<categories>";
	    
	    //Initiate <dataset> elements
	    String strDataRev = "<dataset seriesName='Revenue'>";
	    String strDataQty = "<dataset seriesName='Quantity' parentYAxis='S'>";
	    
	    //Iterate through the data	
	        for(int i=0;i<arrData.length;i++){
	    	//Append <category name='...' /> to strCategories
	    	strCategories += "<category name='" + arrData[i][0] + "' />";
	    	//Add <set value='...' /> to both the datasets
	    	strDataRev += "<set value='" + arrData[i][1] + "' />";
	    	strDataQty = strDataQty +"<set value='" + arrData[i][2] + "' />";		
	        }
	    
	    //Close <categories> element
	    strCategories += "</categories>";
	    
	    //Close <dataset> elements
	    strDataRev += "</dataset>";
	    strDataQty += "</dataset>";
	    
	    //Assemble the entire XML now
    	strXML += strCategories + strDataRev + strDataQty + "</chart>";
	
	//Create the chart - MS Column 3D Line Combination Chart with data contained in strXML
	String chartCode= createChart("../../FusionCharts/MSColumn3DLineDY.swf", "", strXML, "productSales", 600, 300, false, false);
%> <%=chartCode%> <BR>
<BR>
<a href='../NoChart.html' target="_blank">Unable to see the chart
above?</a></CENTER>
</BODY>
</HTML>
