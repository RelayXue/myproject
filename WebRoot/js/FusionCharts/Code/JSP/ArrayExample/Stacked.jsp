
<%
	/*We've included ../Includes/FusionCharts.jsp, which contains functions
	to help us easily create the charts that can be embedded.*/
	%>
<%@ include file="../Includes/FusionCharts.jsp"%>

<HTML>
<HEAD>
<TITLE>FusionCharts - Array Example using Stacked Column 3D
Chart</TITLE>
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
<h4>Plotting Stacked Chart from data contained in Array.</h4>
<%
	/*
	In this example, we plot a Stacked chart from data contained
	in an array. The array will have three columns - first one for Quarter Name
	and the next two for data values. The first data value column would store sales information
	for Product A and the second one for Product B.
	*/
		
	
	String[][] arrData = new String[4][3];
			//Store Quarter Name
			arrData[0][0] = "Quarter 1";
			arrData[1][0] = "Quarter 2";
			arrData[2][0] = "Quarter 3";
			arrData[3][0] = "Quarter 4";
			//Sales data for Product A
			arrData[0][1] = "567500";
			arrData[1][1] = "815300";
			arrData[2][1] = "556800";
			arrData[3][1]= "734500";

			//Sales data for Product B
			arrData[0][2]= "547300";
			arrData[1][2] = "594500";
			arrData[2][2]= "754000";
			arrData[3][2]= "456300";

	    String strXML;
	    /*
	    Now, we need to convert this data into multi-series XML. 
	    We convert using string concatenation.
	    strXML - Stores the entire XML
	    strCategories - Stores XML for the <categories> and child <category> elements
	    strDataProdA - Stores XML for current year's sales
	    strDataProdB - Stores XML for previous year's sales
	    */
	    
	    
	    //Initialize <chart> element
	    strXML = "<chart caption='Sales' numberPrefix='$' formatNumberScale='0'>";
	    
	    //Initialize <categories> element - necessary to generate a stacked chart
	    String strCategories = "<categories>";
	    
	    //Initiate <dataset> elements
	    String strDataProdA = "<dataset seriesName='Product A'>";
	    String strDataProdB = "<dataset seriesName='Product B'>";
	    
	    //Iterate through the data	
	     for(int i=0;i<arrData.length;i++){
	    	//Append <category name='...' /> to strCategories
	    	strCategories += "<category name='" + arrData[i][0] + "' />";
	    	//Add <set value='...' /> to both the datasets
	    	strDataProdA += "<set value='" + arrData[i][1] + "' />";
	    	strDataProdB += "<set value='" + arrData[i][2] + "' />";	
	     }
	    
	    //Close <categories> element
	    strCategories += "</categories>";
	    
	    //Close <dataset> elements
	    strDataProdA += "</dataset>";
	    strDataProdB +="</dataset>";
	    
	    //Assemble the entire XML now
    	strXML += strCategories + strDataProdA + strDataProdB + "</chart>";
	
	//Create the chart - Stacked Column 3D Chart with data contained in strXML
	String chartCode= createChart("../../FusionCharts/StackedColumn3D.swf", "", strXML, "productSales", 500, 300, false, false);
%> <%=chartCode%> <BR>
<BR>
<a href='../NoChart.html' target="_blank">Unable to see the chart
above?</a></CENTER>
</BODY>
</HTML>
