module FusionChartsHelper
  
  # Renders a chart from the swf file passed as parameter either making use of setDataURL method or
  # setDataXML method. The width and height of chart are passed as parameters to this function. If the chart is not rendered,
  # the errors can be detected by setting debugging mode to true while calling this function. The view file can be registered to include javascript statements
  # by setting registering with javascript to true while calling this function.
  # - parameter chart_swf :  pass swf file that renders the chart. 
  # - parameter str_url : pass URL path to the xml file.
  # - parameter str_xml : pass xml content.
  # - parameter chart_id : Id for the chart, using which it will be recognized in the HTML page. Each chart on the page needs to have a unique Id. Datatype: String 
  # - parameter chart_width : pass value as integer as the width of the chart.
  # - parameter chart_height : pass value as integer as the height of the chart.
  # - parameter debug_mode : pass value as true ( a boolean ) for debugging errors, if any, while rendering the chart.
  # - parameter register_with_js : pass value as true ( a boolean ) for view file to be registered to include javascript statements.
  # Can be called from html block int he view where the chart needs to be embedded.
  def render_chart(chart_swf,str_url,str_xml,chart_id,chart_width,chart_height,debug_mode,register_with_js,&block)
    chart_width=chart_width.to_s
    chart_height=chart_height.to_s
    concat("\t\t<!-- START Script Block for Chart-->\n\t\t",block.binding) 
    concat(content_tag("div","\n\t\t\t\tChart.\n\t\t",{:id=>chart_id+"Div",:align=>"center"}),block.binding)
    concat("\n\t\t<script type='text/javascript'>\n",block.binding)
    
    debug_mode_num="0";
    register_with_js_num="0";
    
    if debug_mode==true
      debug_mode_num="1"
    end
    
    if register_with_js==true
      register_with_js_num="1"
    end
    
    concat("\t\t\t\tvar chart_"+chart_id+"=new FusionCharts('"+chart_swf+"','"+chart_id+"',"+chart_width+","+chart_height+","+debug_mode_num+","+register_with_js_num+");\n",block.binding)
    
    if str_xml==""
      concat("\t\t\t\t<!-- Set the dataURL of the chart -->\n",block.binding)
      concat("\t\t\t\tchart_"+chart_id+".setDataURL(\""+str_url+"\");\n",block.binding)
      logger.info("The method used is setDataURL.The URL is " + str_url)
    else
      concat("\t\t\t\t<!-- Provide entire XML data using DataXML method -->\n",block.binding)
      #concat("\t\t\t\tchart_"+chart_id+".setDataXML(\""+str_xml+"\");\n",block.binding)
      concat("\t\t\t\t",block.binding)
      concat('chart_'+chart_id+'.setDataXML(\''+str_xml+'\');',block.binding)
      concat("\n",block.binding)
      logger.info("The method used is setDataXML.The XML is " + str_xml)
    end
    
    concat("\t\t\t\t<!-- Finally render the chart. -->\n",block.binding)
    concat("\t\t\t\tchart_"+chart_id+".render('"+chart_id+"Div');\n",block.binding)
    concat("\t\t</script>\n",block.binding)
    concat("\t\t<!-- END Script Block for Chart. -->\n",block.binding)
    
  end
  # Renders a chart from the swf file passed as parameter either making use of setDataURL method or 
  # setDataXML method. The width and height of chart are passed as parameters to this function. If the chart is not rendered,
  # the errors can be detected by setting debugging mode to true while calling this function.
  # - parameter chart_swf :  SWF file that renders the chart. 
  # - parameter str_url : URL path to the xml file.
  # - parameter str_xml : XML content.
  # - parameter chart_id :  String for identifying chart.
  # - parameter chart_width : Integer for the width of the chart.
  # - parameter chart_height : Integer for the height of the chart.
  # - parameter debug_mode : True ( a boolean ) for debugging errors, if any, while rendering the chart.
  # Can be called from html block in the view where the chart needs to be embedded.
  def render_chart_html(chart_swf,str_url,str_xml,chart_id,chart_width,chart_height,debug_mode,&block)
    chart_width=chart_width.to_s
    chart_height=chart_height.to_s
    
    debug_mode_num="0"
    if debug_mode==true
      debug_mode_num="1"
    end 
    str_flash_vars=""
    if str_xml==""
      str_flash_vars="chartWidth="+chart_width+"&chartHeight="+chart_height+"&debugmode="+debug_mode_num+"&dataURL="+str_url
      logger.info("The method used is setDataURL.The URL is " + str_url)
    else
      str_flash_vars="chartWidth="+chart_width+"&chartHeight="+chart_height+"&debugmode="+debug_mode_num+"&dataXML="+str_xml
      logger.info("The method used is setDataXML.The XML is " + str_xml)
    end
    concat("\t\t<!-- START Code Block for Chart -->\n\t\t",block.binding)
    
    object_attributes={:classid=>"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"}
    object_attributes=object_attributes.merge(:codebase=>"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0")
    object_attributes=object_attributes.merge(:width=>chart_width)
    object_attributes=object_attributes.merge(:height=>chart_height)
    object_attributes=object_attributes.merge(:id=>chart_id)
    
    param_attributes1={:name=>"allowscriptaccess",:value=>"always"}
    param_tag1=content_tag("param","",param_attributes1)
    
    param_attributes2={:name=>"movie",:value=>chart_swf}
    param_tag2=content_tag("param","",param_attributes2)
    
    param_attributes3={:name=>"FlashVars",:value=>str_flash_vars}
    param_tag3=content_tag("param","",param_attributes3)
    
    param_attributes4={:name=>"quality",:value=>"high"}
    param_tag4=content_tag("param","",param_attributes4)
    
    embed_attributes={:src=>chart_swf}
    embed_attributes=embed_attributes.merge(:FlashVars=>str_flash_vars)
    embed_attributes=embed_attributes.merge(:quality=>"high")
    embed_attributes=embed_attributes.merge(:width=>chart_width)
    embed_attributes=embed_attributes.merge(:height=>chart_height).merge(:name=>chart_id)
    embed_attributes=embed_attributes.merge(:allowScriptAccess=>"always")
    embed_attributes=embed_attributes.merge(:type=>"application/x-shockwave-flash")
    embed_attributes=embed_attributes.merge(:pluginspage=>"http://www.macromedia.com/go/getflashplayer")
    
    embed_tag=content_tag("embed","",embed_attributes)
    
    concat(content_tag("object","\n\t\t\t\t"+param_tag1+"\n\t\t\t\t"+param_tag2+"\n\t\t\t\t"+param_tag3+"\n\t\t\t\t"+param_tag4+"\n\t\t\t\t"+embed_tag+"\n\t\t",object_attributes),block.binding)
    concat("\n\t\t<!-- END Code Block for Chart -->\n",block.binding)
  end
  
  # Uses render_component.  
  # Renders a chart using the swf file passed as parameter by calling an action to get the xml for the 
  # setDataXML method. The width and height of chart are passed as parameters to this function. If the chart is not rendered,
  # the errors can be detected by setting debugging mode to true while calling this function.
  # - parameter chart_swf :  SWF file that renders the chart. 
  # - parameter controller_name : The complete name of the controller containing the action.
  # - parameter action_name : The name of the action which will provide the xml.
  # - parameter chart_id :  String for identifying chart.
  # - parameter chart_width : Integer for the width of the chart.
  # - parameter chart_height : Integer for the height of the chart.
  # - parameter debug_mode : True ( a boolean ) for debugging errors, if any, while rendering the chart.
  # Can be called from html block in the view where the chart needs to be embedded.
  def render_chart_get_xml_from_action(chart_swf,controller_name,action_name,params,chart_id,chart_width,chart_height,debug_mode,register_with_js,&block)
    logger.info("The controller to be contacted is " + controller_name)
    logger.info("The action to be performed is " + action_name)
    str_xml= render_component(:action=>action_name,:controller=>controller_name,:params=>params)
    logger.info("The xml obtained from the given action is " + str_xml)
    render_chart(chart_swf,"",str_xml,chart_id,chart_width,chart_height,debug_mode,register_with_js,&block)
  end
  
  # This function can be used when time needs to be added to the URL
  # This will help avoiding cache of the page rendered by the URL
  # Can be used for dataURL method
  def add_cache_to_data_url(str_data_url)
    cache_buster= Time.now.strftime('%d_%m_%y_%H_%M_%S')
    if(str_data_url.index('?')==nil)
      str_data_url = str_data_url + "?FCCurrTime=" + cache_buster.to_s
    else
      str_data_url = str_data_url + "&FCCurrTime=" + cache_buster.to_s
    end
    logger.info("The URL after appending time is " + str_data_url)
    return str_data_url
  end
end