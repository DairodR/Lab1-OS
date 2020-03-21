package Algorithm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import CreatingProcesses.Process;

public class HRRN extends CPUSchedular {

	@Override
	public Queue<Process> NonPreemptive(Queue<Process> q) {
		// TODO Auto-generated method stub
		Queue<Process> q1 = new LinkedList<>();
		int currentTime =0;
		Process huidigP=null;
		Queue<Process> done = new LinkedList<Process>();
		PriorityQueue<Process> readyWaiting = new PriorityQueue<Process>(Comparator.comparing(Process::getResponseRatio).reversed());
		Queue<Process> tmp = new LinkedList();
		for (Process process : q) {
			q1.add(new Process(process));
		}
		while(!q1.isEmpty() || !readyWaiting.isEmpty() || huidigP!=null) {
			while( !q1.isEmpty() && q1.peek().getArrivaltime()<=currentTime) {
				readyWaiting.add(q1.poll());
			}
			if(!readyWaiting.isEmpty()) {
				while(!readyWaiting.isEmpty()) {
					tmp.add(readyWaiting.poll());
				}
				for (Process process : tmp) {
					double responseRatio = (double)(process.getServicetimeneeded() + (currentTime - process.getArrivaltime()))/process.getServicetimeneeded();
					process.setResponseRatio(responseRatio);	
				}
				while(!tmp.isEmpty()) {
					readyWaiting.add(tmp.poll());
				}
				huidigP = readyWaiting.poll();
				huidigP.setStarttime(currentTime);
				huidigP.setServicetime(0);
				currentTime= currentTime+ huidigP.getServicetimeneeded();
				huidigP.setEndtime(currentTime);
				huidigP.berekenRest();
				gemNtat = gemNtat + huidigP.getNormalisedtat();
				gemtat = gemtat + huidigP.getTat();
				gemwaittime= gemwaittime +huidigP.getWaittime();
				done.add(huidigP);
				huidigP = null;

			}
			else currentTime++;
		}
		
		gemNtat = gemNtat/q.size();
		gemtat = gemtat/q.size();
		gemwaittime = gemwaittime/q.size();
		StringBuilder sb = new StringBuilder();
		sb.append("gemNTAT:" +gemNtat+ " , gemTAT: " +gemtat+ " ,gemWaittime : " +gemwaittime);
		System.out.println(sb.toString());
		
		return done;
	}
	
	
	//HRRN is non Preemptive
	@Override
	public Queue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		// TODO Auto-generated method stub
		return null;
	}

}
