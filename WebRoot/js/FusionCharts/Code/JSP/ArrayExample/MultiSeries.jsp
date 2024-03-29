
<%
	/*We've included ../Includes/FusionCharts.jsp, which contains functions
	to help us easily create the charts that can be embedded.*/
	%>
<%@ include file="../Includes/FusionCharts.jsp"%>

<HTML>
<HEAD>
<TITLE>FusionCharts - Array Example using Multi Series Column 3D
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
<h4>Plotting multi-series chart from data contained in Array.</h4>
<%
	/*In this example, we plot a multi series chart from data contained
	in an array. The array will have three columns - first one for data label (product)
	and the next two for data values. The first data value column would store sales information
	for current year and the second one for previous year.
	
	Let's store the sales data for 6 products in our array. We also store
	the name of products. 
	*/
	String[][] arrData = new String[6][3];
	    		//Store Name of Products
	    		arrData[0][0] = "Product A";
	        	arrData[1][0] = "Product B";
	        	arrData[2][0] = "Product C";
	        	arrData[3][0] = "Product D";
	        	arrData[4][0] = "Product E";
	        	arrData[5][0] = "Product F";
	        	
	        	//Store sales data
	        	arrData[0][1] = "567500";
	        	arrData[1][1] = "815300";
	        	arrData[2][1] = "556800";
	        	arrData[3][1]= "734500";
	        	arrData[4][1] = "676800";
        		arrData[5][1] = "648500";
	
			//Store sales data for previous year
			arrData[0][2]= "547300";
			arrData[1][2] = "584500";
			arrData[2][2]= "754000";
			arrData[3][2]= "456300";
			arrData[4][2]= "754500";
			arrData[5][2]= "437600";

	String strXML = "<chart caption='Sales by Product' numberPrefix='$' formatNumberScale='1' rotateValues='1' placeValuesInside='1' decimals='0' >";
	    	
	    	//Initialize <categories> element - necessary to generate a multi-series chart
	    	String strCategories = "<categories>";
	    	
	    	//Initiate <dataset> elements
	    	String strDataCurr = "<dataset seriesName='Current Year'>";
	    	String strDataPrev = "<dataset seriesName='Previous Year'>";
	    	
	    	//Iterate through the data	
	    	for(int i=0;i<arrData.length;i++){
	    		//Append <category name='...' /> to strCategories
	    		strCategories = strCategories + "<category name='" + arrData[i][0] + "' />";
	    		//Add <set value='...' /> to both the datasets
	    		strDataCurr = strDataCurr + "<set value='" + arrData[i][1] + "' />";
	    		strDataPrev = strDataPrev + "<set value='" + arrData[i][2] + "' />";	
	    	}
	    	
	    	//Close <categories> element
	    	strCategories = strCategories + "</categories>";
	    	
	    	//Close <dataset> elements
	    	strDataCurr = strDataCurr + "</dataset>";
	    	strDataPrev = strDataPrev + "</dataset>";
	    	
	    	//Assemble the entire XML now
    		strXML = strXML + strCategories + strDataCurr + strDataPrev + "</chart>";
	
			//Create the chart - MS Column 3D Chart with data contained in strXML
			String chartCode= createChart("../../FusionCharts/MSColumn3D.swf", "", strXML, "productSales", 600, 300, false, false);
%>
		    <%=chartCode%> <BR>
<BR>
<a href='../NoChart.html' target="_blank">Unable to see the chart
above?</a></CENTER>
</BODY>
</HTML>
