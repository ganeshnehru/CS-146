
public class Process {

	int priority;

	int processID;

	//Constructor 
	public Process(int processID, int priority)

	{
		this.priority = priority;
		this.processID = processID;
	}

	//Returns priority.
	public int getPriority() 
	{
		return priority;
	}

	//Sets priority.
	public void setPriority(int priority) 
	{
		this.priority = priority;
	}
	
	//Gets process ID.
	public int getPid() 
	{
		return processID;
	}
	
	//Sets process ID.
	public void setPid(int pid) 
	{
		this.processID = pid;
	}

}