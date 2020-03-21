package Algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import CreatingProcesses.Process;

// mooie site waar staat hoe SPN moet werken https://www.guru99.com/shortest-job-first-sjf-scheduling.html
// spijtiggenoeg is er geen code aanwezig, maar ik ga hun werking proberen naboosten

public class ShortestProcessNext extends CPUSchedular {

	@Override
	public Queue<Process> NonPreemptive(Queue<Process> q) {
		Queue <Process> q1 = new LinkedList<>();
		int currentTime = 0;
		Process huidigP;
		
		for (Process process : q) {
			q1.add(new Process(process));
		}
		/*	Om de werking te kunnen verzekeren gaan we werken met een prioiteitsqueue,
		 *	we gaan gebruik maken van een lambda statement om een comparator na te bootsten
		 *	in deze queue gaan dus telkens de processen komen die al aangekomen zijn, en we gaan 
		 *	dan de processen hier rangschikken volgens aankomsttijd.
		 */
		PriorityQueue<Process> test = new PriorityQueue<>();
		PriorityQueue<Process> readyProcesses = new PriorityQueue<Process>(10,(a,b)->a.getArrivaltime()-b.getServicetime()); // online gevonden van arrivaltijd, maar moet dit niet servicetijd zijn?
		
		// loopen totdat deze voorwaarden niet meer gelden
		while(test.size() != q.size()) {
			// kijken of we processen aan onze readyqueue kunnen toevoegen
            while (q1.peek() != null && q1.peek().getArrivaltime()<=currentTime)
    			readyProcesses.add(q1.poll());	// processen uit q1 halen en in onze readyProcesses steken, waar ze geordend zullen zijn

			
			//indien er processen aan het wachten zijn in de readyProcesses
			if(!readyProcesses.isEmpty()) {
				// van ready naar running
				huidigP = readyProcesses.poll();
				// parameters toevoegen aan huidigP zodat we tat en ander variabelen kunnen berekenen
				huidigP.setStarttime(currentTime);
				currentTime = currentTime + huidigP.getServicetimeneeded();
				huidigP.setEndtime(currentTime);

				//parameters berekenen en andere updaten (idem aan fcfs)
				huidigP.setServicetime(0);
				huidigP.berekenRest();
				test.add(huidigP);
				gemwaittime = gemwaittime +huidigP.getWaittime();
				gemtat = gemtat+ huidigP.getTat();
				gemNtat = gemNtat +huidigP.getNormalisedtat();		
				
			}else {
				currentTime++;
			}
			
		}
		gemNtat =gemNtat/q.size();
		gemtat = gemtat/q.size();
		gemwaittime = gemwaittime/q.size();
		
		StringBuilder sb = new StringBuilder();
		sb.append("gemNTAT:" +gemNtat+ " , gemTAT: " +gemtat+ " ,gemWaittime : " +gemwaittime);
		System.out.println(sb.toString());
		
		return test;
	}

	
	
	@Override
	public Queue<Process> Preemptive(Queue<Process> q, int timeSlice) {
		// TODO Auto-generated method stub
		return null;
	}

}
