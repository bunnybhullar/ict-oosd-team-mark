package ca.sait.oosd.travel.agent;

import ca.sait.oosd.business.TEObject;

/**
 * All properties encapsulated and could be access only via the getters and setters
 * @author Duminda
 *
 */
public class Agent extends TEObject{
	
	private int agentId;
	private String agentFistName;
	private String agentMiddleInitial;
	private String agentLastName;
	private String phone;
	private String email;
	private String position;
	private int agencyId;
	
	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	
	public String getAgentFistName() {
		return agentFistName;
	}

	public void setAgentFistName(String agentFistName) {
		this.agentFistName = agentFistName;
	}

	public String getAgentMiddleInitial() {
		return agentMiddleInitial;
	}

	public void setAgentMiddleInitial(String agentMiddleInitial) {
		this.agentMiddleInitial = agentMiddleInitial;
	}

	public String getAgentLastName() {
		return agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	@Override
	public String toString() {
		return "";
	}
}
