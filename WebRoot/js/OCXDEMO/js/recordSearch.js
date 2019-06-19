var Array_FDID;
var lResult = 1;
var IniFDID="";
var FdVideoArray=null;
var videoArray=null;
var ds;
var fileName;
var startTime_rtn;
var endTime_rtn;
var fdId_rtn;
var grid;
var llOption;


Ext.onReady(function(){
		var tabs = new Ext.TabPanel('south_div',{
	        resizeTabs:true, // turn on tab resizing
	        minTabWidth: 15,
	        preferredTabWidth:120
	    });
        
        var videoSearchInfo = tabs.addTab('videoSearch_Div', "��ѯ�б�");
        tabs.activate('videoSearch_Div');
});

/*************************������ѯ����XML��ʽ��¼���б�**************************************/
function getRecordList(sRecords)
{
  FdVideoArray =sRecords.split("$");
  var recordListLength = FdVideoArray.length;
	GVideoArray = new IniArray(recordListLength);
	for(i = 1;i < recordListLength;i++)
	{
		videoArray=FdVideoArray[i].split("|");
		var fileName = videoArray[0];
		var size = videoArray[1];
		var startTime = videoArray[2];
		var endTime = videoArray[3];
		videoArray[4] = i;
		GVideoArray[i-1] = videoArray;
	}
	if(grid != null)
	{
		grid.destroy();
	}	
	getSearchDiv(); //����grid
	ds.load();	
	FdVideoArray = []; //�������
	videoArray =[];
}
/****************************************************************************/

function getSearchDiv()
{
    ds = new Ext.data.Store({   
        proxy: new Ext.data.MemoryProxy(GVideoArray),
        reader: new Ext.data.ArrayReader({}, [
         	   
         	   {name: 'fileName'},    
	           {name: 'fileSize'},    
	           {name: 'startTime'},    
	           {name: 'endTime'}, 
	           {name: 'id'}   
	      ])    
        });    
    
    var cm  = new Ext.grid.ColumnModel
    (
      [
        {
          header: '���',
          width: 50,
          sortable: true,
          dataIndex: 'id'
        },
        
        {
          header: '��ʼʱ��',
          width: 140,
          sortable: true,
          dataIndex: 'startTime'
        },
        {
          header: '����ʱ��',
          width: 140,
          sortable: true,
          dataIndex: 'endTime'
        },
        {
          header: '�ļ���',
          width: 300,
          sortable: true,
          dataIndex: 'fileName'
        },
        {
          header: '�ļ���С',
          width: 100,
          sortable: true,
          dataIndex: 'fileSize'
        }       
      ]
    );
//	cm.setHidden(5, true);
    
    grid = new Ext.grid.Grid
    (
      'searchgrid', 
      {
        ds: ds,
        cm: cm,
        selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
        enableColLock:false
      }
    );
    
    grid.addListener('rowdblclick',onRowClick); //˫���¼�����¼���ļ���ͬʱѡ����Ҫ���ص��ļ�
    
	 function onRowClick(grid, rowIndex, colIndex, e){  
	  	var selectionModel = grid.getSelectionModel();
	  	var record = selectionModel.getSelected();
	  	
	  	var startTime = record.data['startTime'];
	  	var endTime = record.data['endTime'];
	  	var fileName = record.data['fileName'];
	  	WebOcx.StopVideo(hwnd);
	  	WebOcx.StopPlayRecord(hwnd);
	  	//re=WebOcx.PlayRecord(hwnd, llOption, fileName);	 	
	    setTimeout("WebOcx.PlayRecord(hwnd, llOption, fileName);",100); 	  	
	 } 		

    grid.addListener('rowclick',singleClick); //�����¼���ѡȡ��Ҫ���ص��ļ�
	 function singleClick(grid, rowIndex, colIndex, e){  
	  	var selectionModel = grid.getSelectionModel();
	  	var record = selectionModel.getSelected();

	  	startTime_rtn = record.data['startTime'];
	  	endTime_rtn = record.data['endTime'];
	  	fileName = record.data['fileName'];
	
	 } 		
	 
    var rz = new Ext.Resizable
    (
      'searchgrid', 
      {
        //wrap:true,
        minHeight:100,
        pinned:true,
        handles: 's'
      }
    );
    rz.on('resize', grid.autoSize, grid); 
    grid.render();
//	ds.load();	
}

//�ط�¼��
function PlayRecord()
{ 	
   WebOcx.StopPlayRecord(hwnd);
//   WebOcx.PlayRecord(hwnd,llOption,fileName);
	 setTimeout("WebOcx.PlayRecord(hwnd, llOption, fileName);",100);
}

//��ͣ����¼��
function PausePlayRecord()
{
   WebOcx.PausePlayRecord(hwnd);
}
//ֹͣ����¼��
function StopPlayRecord()
{
   WebOcx.StopPlayRecord(hwnd);
}
//��ʼ¼��
function StartRecord()
{
   WebOcx.StartRecord(sCameraID, sChannelID, sFileName);
}
//ֹͣ¼��
function StopRecord()
{
   WebOcx.StopRecord(sCameraID, sChannelID);
}

//¼���ļ�����
function QueryRecord()
{ 
        var sStartTime = Ext.get('BeginTime_1').dom.value;
        var sEndTime = Ext.get('EndTime_1').dom.value;
        var lOption = Ext.get('recordType').dom.value;
        var fdname = Ext.get('fdNameSelect').dom.value;
        if(fdname == null || fdname == '')
        {
        	Ext.MessageBox.alert("��ʾ","���ȴ�����豸�б���ѡ������ͷ!");
        	return;
        }
        if(sStartTime != '' && sEndTime != '')
        {
        	WebOcx.QueryRecord(sCameraID,sChannelID,lOption,sStartTime,sEndTime); //lOption: 0 ��ʾǰ��¼�� 1��ʾ����¼��
        }
        else
        {
        	Ext.MessageBox.alert("��ʾ","����д����ʱ���!");
        	return;
        }
        llOption = parseInt(lOption);   
}

//����¼��
function DownloadRecord()
{
	lResult=WebOcx.DownloadRecord(fileName, sDstFilePath);	
}

