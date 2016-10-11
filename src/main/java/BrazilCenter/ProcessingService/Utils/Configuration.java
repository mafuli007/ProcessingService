package BrazilCenter.ProcessingService.Utils;

public class Configuration {

	private String softwareId;
	
	/**MQ configuration*/
	private String mqHostIp;
	private String mqUserName;
	private String mqPasswd;
	
	/**root directory used to store data*/
	private String rootDir;
	
	public String getRootDir() {
		return rootDir;
	}
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	public String getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}
	public String getMqHostIp() {
		return mqHostIp;
	}
	public void setMqHostIp(String mqHostIp) {
		this.mqHostIp = mqHostIp;
	}
	public String getMqUserName() {
		return mqUserName;
	}
	public void setMqUserName(String mqUserName) {
		this.mqUserName = mqUserName;
	}
	public String getMqPasswd() {
		return mqPasswd;
	}
	public void setMqPasswd(String mqPasswd) {
		this.mqPasswd = mqPasswd;
	}
}
