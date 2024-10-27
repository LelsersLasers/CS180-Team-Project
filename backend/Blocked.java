package backend;

public class Blocked implements BlockedInterface {
	private int id;
	private int blockerId;
	private int blockedId;

	public Blocked(int blockerId, int blockedId) {
		this.id = -1;
		this.blockerId = blockerId;
		this.blockedId = blockedId;
	}

	public Blocked(int id, int blockerId, int blockedId) {
		this.id = id;
		this.blockerId = blockerId;
		this.blockedId = blockedId;
	}

	public int getId() {
		return id;
	}

	public int getBlockerId() {
		return blockerId;
	}

	public int getBlockedId() {
		return blockedId;
	}

	public void setId(int id) {
		this.id = id;
	}
}
