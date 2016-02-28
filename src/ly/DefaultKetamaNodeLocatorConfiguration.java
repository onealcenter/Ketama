package ly;

import java.util.HashMap;
import java.util.Map;

public class DefaultKetamaNodeLocatorConfiguration implements KetamaNodeLocatorConfiguration {
	private final int numReps = 160;

	public int getNodeRepetitions() {
		return numReps;
	}

	protected Map<KetamaNode, String> socketAddresses = new HashMap<KetamaNode, String>();

	protected String getSocketAddressForNode(KetamaNode node) {
		String result = socketAddresses.get(node);
		if (result == null) {
			result = String.valueOf(node.getSocketAddress());
			if (result.startsWith("/")) {
				result = result.substring(1);
			}
			socketAddresses.put(node, result);
		}
		return result;
	}

	public String getKeyForNode(KetamaNode node, int repetition) {
		return getSocketAddressForNode(node) + "-" + repetition;
	}
}
