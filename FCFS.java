package Algorithm;

import CreatingProcesses.Process;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FCFS extends CPUSchedular {

	@Override
	public Queue<Process> NonPreemptive(Queue<Process> q) throws NullPointerException{
		Queue<Process> q1 = new LinkedList<>();
		int currentTime =0;
		Process huidigP;
		for (Process process : q) {
			q1.add(new Process(process));
		}
		Queue<Process> test = new LinkedList<>();
		while(!q1.isEmpty()) {
			huidigP = q1.poll();
			if(currentTime < huidigP.getArrivaltime()) {									//process kan meteen starten
				currentTime = huidigP.getArrivaltime() + huidigP.getServicetimeneeded();	//huidige tijd nadat process klaar is
				huidigP.setStarttime(huidigP.getArrivaltime());								//start als toekomt
				huidigP.setEndtime(currentTime);
				
				
			}
			else { // process al een tijd in wait, kan pas starten nadat de laatste af is = currentTime
				huidigP.setStarttime(currentTime);
				currentTime = currentTime + huidigP.getServicetimeneeded();
				huidigP.setEndtime(currentTime);
			}
			huidigP.setServicetime(0);
			huidigP.berekenRest();
			test.add(huidigP);
			
			
			
			gemwaittime = gemwaittime +huidigP.getWaittime();
			gemtat = gemtat+ huidigP.getTat();
			gemNtat = gemNtat +huidigP.getNormalisedtat();		
		}
		
		gemNtat =gemNtat/q.size();
		gemtat = gemtat/q.size();
		gemwaittime = gemwaittime/q.size();
		
		StringBuilder sb = new StringBuilder();
		sb.append("gemNTAT:" +gemNtat+ " , gemTAT: " +gemtat+ " ,gemWaittime : " +gemwaittime);
		System.out.println(sb.toString());
		return test;
	}
	
	
	//geen nut in FCFS
	@Override
	public PriorityQueue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
