package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.sun.java.accessibility.util.GUIInitializedListener;

import CreatingProcesses.Process;

public class RoundRobin extends CPUSchedular {
	//geen nut in RR
	@Override
	public PriorityQueue<Process> NonPreemptive(Queue<Process> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Queue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		Queue<Process> q1 = new LinkedList<>();
		int currentTime =0;
		Process huidigP=null;
		Queue<Process> readyWaiting = new LinkedList<Process>();
		Queue<Process> done = new LinkedList<Process>();

		
		for (Process process : q) {
			q1.add(new Process(process));
		}
		
		while(!q1.isEmpty() || !readyWaiting.isEmpty() || huidigP!=null) {
			while( !q1.isEmpty() && q1.peek().getArrivaltime()<=currentTime) {
				readyWaiting.add(q1.poll());
			}
			if(huidigP!=null && huidigP.getServicetime()<=timeSlice) {
				currentTime += huidigP.getServicetime();
				huidigP.setServicetime(0);
				huidigP.setEndtime(currentTime);
				huidigP.berekenRest();
				gemNtat = gemNtat + huidigP.getNormalisedtat();
				gemtat = gemtat + huidigP.getTat();
				gemwaittime= gemwaittime +huidigP.getWaittime();
				done.add(huidigP);
				huidigP = null;
			} else if(huidigP != null && huidigP.getServicetime()>timeSlice) {
				huidigP.setServicetime(huidigP.getServicetime()-timeSlice);
				currentTime += timeSlice;
				readyWaiting.add(huidigP);
				huidigP = null;				
			}
			if(huidigP == null) {
				if(readyWaiting.isEmpty()){
					currentTime++;
				}
				else if(!readyWaiting.isEmpty()) {
					huidigP = readyWaiting.poll();
					if(huidigP.getStarttime()==-1) {
						huidigP.setStarttime(currentTime);
					}					
				}
				else if(!q1.isEmpty()) {
					currentTime = currentTime + timeSlice;
					
				}
			}
			
			
			
		}
		gemNtat = gemNtat/q.size();
		gemtat = gemtat/q.size();
		gemwaittime = gemwaittime/q.size();
		StringBuilder sb = new StringBuilder();
		sb.append("gemNTAT:" +gemNtat+ " , gemTAT: " +gemtat+ " ,gemWaittime : " +gemwaittime);
		System.out.println(sb.toString());
		
		return done;
	}

}
