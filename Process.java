package CreatingProcesses;

public class Process implements Comparable<Process> {
	
	private int pid;
	private int arrivaltime;
	private int servicetime;
	private int starttime;
	private int endtime;
	private int waittime;
	private int servicetimeneeded;
	private int tat;
	private int priority;
	private double normalisedtat;
	private double responseRatio;
	
	public Process() {
	}

	public Process(int pid, int arrivaltime, int servicetime) {
		this.pid = pid;
		this.arrivaltime = arrivaltime;
		this.servicetime = servicetime;
	}
	
	public Process(Process p) {
		pid = p.pid;
		arrivaltime = p.arrivaltime;
		servicetime = p.servicetime;
		starttime = p.starttime;
		endtime = p.endtime;
		waittime = p.waittime;
		servicetimeneeded = p.servicetimeneeded;
		tat = p.tat;
		priority = p.priority;
		normalisedtat = p.normalisedtat;
		responseRatio = p.responseRatio;
	}

	@Override
	public String toString() {
		return "Process:: pid=" + pid + ", arrivaltime=" + arrivaltime + ", servicetime=" + servicetime + ", starttime="
				+ starttime + ", endtime=" + endtime + ", waittime=" + waittime + ", servicetimeneeded="
				+ servicetimeneeded + ", tat=" + tat + ", priority=" + priority + ", normalisedtat=" + normalisedtat
				+ ", responseRatio=" + responseRatio +'\n';
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(int arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public int getServicetime() {
		return servicetime;
	}

	public void setServicetime(int servicetime) {
		this.servicetime = servicetime;
	}

	public int getStarttime() {
		return starttime;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public int getEndtime() {
		return endtime;
	}

	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}

	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	public int getServicetimeneeded() {
		return servicetimeneeded;
	}

	public void setServicetimeneeded(int servicetimeneeded) {
		this.servicetimeneeded = servicetimeneeded;
	}

	public int getTat() {
		return tat;
	}

	public void setTat(int tat) {
		this.tat = tat;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getNormalisedtat() {
		return normalisedtat;
	}

	public void setNormalisedtat(double normalisedtat) {
		this.normalisedtat = normalisedtat;
	}

	public double getResponseRatio() {
		return responseRatio;
	}

	public void setResponseRatio(double responseRatio) {
		this.responseRatio = responseRatio;
	}
	
	public void berekenRest() {
		this.tat=(endtime - arrivaltime);
		this.normalisedtat=(double) this.tat / servicetimeneeded;
		this.waittime = endtime -arrivaltime - servicetimeneeded;
	}


	@Override
	public int compareTo(Process o) {
		// TODO Auto-generated method stub
		return this.responseRatio < o.responseRatio ? -1 : 1;
	}
	
	
	}
