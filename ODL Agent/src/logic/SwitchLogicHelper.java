package logic;

import java.util.ArrayList;

import connectSwitch.DumpFlowsTOR;
import connectSwitch.DumpFlowsPOD1;
import connectSwitch.DumpFlowsPOD2;
import msgModel.FlowTablePOD1;
import msgModel.FlowTablePOD2;
import msgModel.FlowTableTOR;
import msgModel.FpgaParam;

public class SwitchLogicHelper {
	public void switchLogicTOR(int[][] tmp , FpgaParam param){
		// logic to store the data on the table 
				if(FlowTableTOR.flowsTOR.containsKey(param.getScheduleID())){
					FlowTableTOR.flowsTOR.get(param.getScheduleID()).add(tmp);
				} else {
					ArrayList<int[][]> val = new ArrayList<int[][]>();
					FlowTableTOR.flowsTOR.put(param.getScheduleID(), val);
					FlowTableTOR.flowsTOR.get(param.getScheduleID()).add(tmp);
				}
				//displaying flow table data
				FlowTableTOR.displayData();
				
				// check and flush the flow tables
				if (FlowTableTOR.flowsTOR.get(param.getScheduleID()).size() == param.getFlowCounter()){
				DumpFlowsTOR.flush(param.getScheduleID());
				}
	}
	
	public void switchLogicPOD1(int[][] tmp , FpgaParam param){
		// logic to store the data on the table 
				if(FlowTablePOD1.flowsPOD1.containsKey(param.getScheduleID())){
					FlowTablePOD1.flowsPOD1.get(param.getScheduleID()).add(tmp);
				} else {
					ArrayList<int[][]> val = new ArrayList<int[][]>();
					FlowTablePOD1.flowsPOD1.put(param.getScheduleID(), val);
					FlowTablePOD1.flowsPOD1.get(param.getScheduleID()).add(tmp);
				}
				//displaying flow table data
				FlowTablePOD1.displayData();
				
				// check and flush the flow tables
				if (FlowTablePOD1.flowsPOD1.get(param.getScheduleID()).size() == param.getFlowCounter()){
				DumpFlowsPOD1.flush(param.getScheduleID());
				}
	}
	
	public void switchLogicPOD2(int[][] tmp , FpgaParam param){
		// logic to store the data on the table 
				if(FlowTablePOD2.flowsPOD2.containsKey(param.getScheduleID())){
					FlowTablePOD2.flowsPOD2.get(param.getScheduleID()).add(tmp);
				} else {
					ArrayList<int[][]> val = new ArrayList<int[][]>();
					FlowTablePOD2.flowsPOD2.put(param.getScheduleID(), val);
					FlowTablePOD2.flowsPOD2.get(param.getScheduleID()).add(tmp);
				}
				//displaying flow table data
				FlowTablePOD2.displayData();
				
				// check and flush the flow tables
				if (FlowTablePOD2.flowsPOD2.get(param.getScheduleID()).size() == param.getFlowCounter()){
				DumpFlowsPOD2.flush(param.getScheduleID());
				}
	}
}
