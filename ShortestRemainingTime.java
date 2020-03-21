package Algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import CreatingProcesses.Process;

public class ShortestRemainingTime extends CPUSchedular {
	//in principe wel preemtive maar ik gebruik geen timeslice dus doe ik hier :p 
	@Override
	public Queue<Process> NonPreemptive(Queue<Process> q) {
		System.out.println("kom ik hier?");
		// ik ga de werking van shortestProcessNext grotendeels overnemen, deze 2 
		// methodes lijken goed op elkaar, maar aangezien deze preeemtive is,
		// zal ik dus wel nog aanpassingen doen en met een extra queue moeten werken
		// 
		Queue <Process> q1 = new LinkedList<>();
		int currentTime = 0;
		Process huidigP;

		for (Process process : q) {
			q1.add(new Process(process));
		}
		
		PriorityQueue<Process> test = new PriorityQueue<>();
		PriorityQueue<Process> readyProcesses = new PriorityQueue<Process>(10,(a,b)->a.getServicetime()-b.getServicetime());
		Queue<Process> currentProcess = new PriorityQueue<>();

		// tot hier ongeveer identiek
		while(test.size() != q.size()) {
			//controleren of er momenteel een process bezig is
			if (!currentProcess.isEmpty()) {
				
				huidigP = currentProcess.peek();
				huidigP.setServicetime(huidigP.getServicetime()-1);
				// controleren of dit process klaar is met runnen
				
				if (huidigP.getServicetime() <= 0) {
					
					huidigP = currentProcess.poll();
					
					huidigP.setEndtime(currentTime);
					//parameters berekenen en andere updaten (idem aan fcfs)
					huidigP.setServicetime(0);
					huidigP.berekenRest();
					test.add(huidigP);
					gemwaittime = gemwaittime +huidigP.getWaittime();
					gemtat = gemtat+ huidigP.getTat();
					gemNtat = gemNtat +huidigP.getNormalisedtat();					
				}
			}
			// als we hier zitten wil het zeggen dat we kunnen kijken om een nieuwe toe te voegen
            while (q1.peek() != null && q1.peek().getArrivaltime()<=currentTime) {
            	readyProcesses.add(q1.poll());
            }
            //kijken of er niets bezig is en we er hebben in de ready queue
            if (currentProcess.isEmpty() && !readyProcesses.isEmpty()) {
				// indien deze voorwaarde voldaan is gaan we de bovenste van de ready queue aan de processor laten
            	huidigP = readyProcesses.poll();	// niet 100% zeker of het hier peek of pop moet zijn
				huidigP.setStarttime(currentTime);
				currentProcess.add(huidigP);
				
            } else if(!currentProcess.isEmpty() && !readyProcesses.isEmpty()) {
            	//als we hier zitten moeten we kijken of er in ready een kortere is dan in current
            	huidigP = currentProcess.peek();
            	Process vergelijkenMet = readyProcesses.peek();
            	
            	if (huidigP.getServicetime() > vergelijkenMet.getServicetime()) {
					huidigP = currentProcess.poll();
					currentProcess.add(vergelijkenMet);
					readyProcesses.add(huidigP);
					huidigP = currentProcess.peek();
					if (huidigP.getStarttime() == -1) {
						huidigP.setStarttime(currentTime);
					}
				}
            }
        	currentTime++;            
		}
        gemNtat =gemNtat/q.size();
		gemtat = gemtat/q.size();
		gemwaittime = gemwaittime/q.size();
		
		StringBuilder sb = new StringBuilder();
		sb.append("gemNTAT:" +gemNtat+ " , gemTAT: " +gemtat+ " ,gemWaittime : " +gemwaittime);
		//System.out.println(sb.toString());
		
		return test;

	}

	@Override
	public Queue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		// TODO Auto-generated method stub
		return null;
	}

}
