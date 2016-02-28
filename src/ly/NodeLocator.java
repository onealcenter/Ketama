package ly;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for locating a node by hash value.
 */
public interface NodeLocator {

	/**
	 * Get an iterator over the sequence of nodes that make up the backup
	 * locations for a given key.
	 *
	 * @param k
	 *            the object key
	 * @return the sequence of backup nodes.
	 */
	Iterator<KetamaNode> getSequence(String k);

	/**
	 * Update locator status.
	 *
	 * @param nodes
	 *            New locator nodes.
	 */
	void updateLocator(final List<KetamaNode> nodes);
}
