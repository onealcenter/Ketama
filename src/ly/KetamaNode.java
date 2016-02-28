package ly;

public class KetamaNode {
	private String socketAddress;

	public KetamaNode(String sa) {
		this.socketAddress = sa;
	}

	public String getSocketAddress() {
		return socketAddress;
	}

	public void setSocketAddress(String socketAddress) {
		this.socketAddress = socketAddress;
	}
}
