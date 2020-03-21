package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

import CreatingProcesses.Process;

public class MLFBQ1 extends CPUSchedular{
	
	//geen nut in MLFB, omdat we werken met timeSlices
	@Override
	public Queue<Process> NonPreemptive(Queue<Process> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Queue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		int slice0 = 8;
		int slice1 = 8;
		int slice2 = 8;
		int slice3 = 8;
		int slice4 = 8;
		int sliceTimer = 0;
		boolean interrupt = false;
		Queue<Process> q1 = new LinkedList<>();
		int currentTime =0;
		int tmpT =0;
		int currentQueueSlice = 0;
		Process huidigP=null;
		Queue<Process> done = new LinkedList<Process>();
		Queue<Process> readyWaiting0 = new LinkedList<Process>();
		Queue<Process> readyWaiting1 = new LinkedList<Process>();
		Queue<Process> readyWaiting2 = new LinkedList<Process>();
		Queue<Process> readyWaiting3 = new LinkedList<Process>();
		Queue<Process> readyWaiting4 = new LinkedList<Process>();
		
		for (Process process : q) {
			q1.add(new Process(process));
		}
		while(done.size() != q.size()) {
			
			if(huidigP != null) {
				huidigP.setServicetime(huidigP.getServicetime() - 1);
				sliceTimer++;
			}
			if(currentQueueSlice == sliceTimer && huidigP != null) {
				interrupt = true;
			}else if(huidigP != null && huidigP.getServicetime() == 0) {
				interrupt = true;
			}
			while( !q1.isEmpty() && q1.peek().getArrivaltime()<=currentTime) {
				readyWaiting0.add(q1.poll());
				interrupt = true;
			}
			if(interrupt) {
				if(huidigP == null) {
					if(!readyWaiting0.isEmpty()) {
						huidigP = readyWaiting0.poll();
						huidigP.setStarttime(currentTime);
						huidigP.setPriority(0);
						currentQueueSlice = slice0;
						sliceTimer = 0;
					}
					else if(!readyWaiting1.isEmpty()) {
						huidigP = readyWaiting1.poll();
						huidigP.setPriority(1);
						currentQueueSlice = slice1;
						sliceTimer = 0;
					}
					else if(!readyWaiting2.isEmpty()) {
						huidigP = readyWaiting2.poll();
						huidigP.setPriority(2);
						currentQueueSlice = slice2;
						sliceTimer = 0;
					}
					else if(!readyWaiting3.isEmpty()) {
						huidigP = readyWaiting3.poll();
						huidigP.setPriority(3);
						currentQueueSlice = slice3;
						sliceTimer = 0;
					}
					else if(!readyWaiting4.isEmpty()) {
						huidigP = readyWaiting4.poll();
						huidigP.setPriority(4);
						currentQueueSlice = slice4;
						sliceTimer = 0;
					}
					interrupt = false;
				}
				else {
					if(huidigP.getServicetime()==0) {
						huidigP.setEndtime(currentTime);
						huidigP.berekenRest();
						
						
						sliceTimer=0;
						tmpT=0;
						gemNtat = gemNtat + huidigP.getNormalisedtat();
						gemtat = gemtat + huidigP.getTat();
						gemwaittime= gemwaittime +huidigP.getWaittime();
						done.add(huidigP);
						huidigP = null;
					}
					else {
						huidigP.setPriority(huidigP.getPriority() + 1);
						if(huidigP.getPriority()==1) {
							readyWaiting1.add(huidigP);
							interrupt = true;
							huidigP = null;
						}
						else if(huidigP.getPriority()==2) {
							readyWaiting2.add(huidigP);
							interrupt = true;
							huidigP = null;
						}
						else if(huidigP.getPriority()==3) {
							readyWaiting3.add(huidigP);
							interrupt = true;
							huidigP = null;
						}
						else if(huidigP.getPriority()>=4) {
							readyWaiting4.add(huidigP);
							interrupt = true;
							huidigP = null;
						}
					}
					
				}
							
			}
			currentTime++;
			
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
