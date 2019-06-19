package com.wuzhen.gmpath.test;

import com.wuzhen.gmpath.GMOptimalPath;
import com.wuzhen.gmpath.GMPoint;
import com.wuzhen.gmpath.GMPointCollection;
import com.wuzhen.gmpath.GMSearchFactory;
import com.wuzhen.gmpath.GMUtility;

public class GMPathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	 
		
		
		// TODO Auto-generated method stub
		//GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(120.481673,30.754201, 120.513586,30.735620);
		//GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(120.470919,30.757479, 120.496080,30.722763);
		GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(120.495077,30.735702   ,120.482433,30.745123   );
		
		System.out.println("beginNo:" + path.BeginNo);
		System.out.println("endNo:" + path.EndNo);
		System.out.println("lineNoS:" + path.GetRoadPath());
		System.out.println("xyS:" + path.GetPathString()); 
		System.out.println("seconds:" + path.ExecSeconds); 
		System.out.println("des:" + path.GetPathDesString());  
		
		 
	}

}
