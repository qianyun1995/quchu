package quchu;

public class Clean {

	private int uid;
	private String content;

	Clean()
	{
		
	}
	Clean(int uid, String content)
	{
		this.uid=uid;
		this.content=content;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
