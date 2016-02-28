package ly;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implements an Iterator which the KetamaNodeLoctaor may return to a client for
 * iterating through alternate nodes for a given key.
 */
class KetamaIterator implements Iterator<KetamaNode> {

	private final String key;
	private long hashVal;
	private int remainingTries;
	private int numTries = 0;
	private final HashAlgorithm hashAlg;
	private final TreeMap<Long, KetamaNode> ketamaNodes;

	/**
	 * Create a new KetamaIterator to be used by a client for an operation.
	 *
	 * @param k
	 *            the key to iterate for
	 * @param t
	 *            the number of tries until giving up
	 * @param ketamaNodes
	 *            the continuum in the form of a TreeMap to be used when
	 *            selecting a node
	 * @param hashAlg
	 *            the hash algorithm to use when selecting within the continue
	 */
	protected KetamaIterator(final String k, final int t, TreeMap<Long, KetamaNode> ketamaNodes,
			final HashAlgorithm hashAlg) {
		this.ketamaNodes = ketamaNodes;
		this.hashAlg = hashAlg;
		hashVal = hashAlg.hash(k);
		remainingTries = t;
		key = k;
	}

	private void nextHash() {
		long tmpKey = hashAlg.hash((numTries++) + key);
		hashVal += (int) (tmpKey ^ (tmpKey >>> 32));
		hashVal &= 0xffffffffL; /* truncate to 32-bits */
		remainingTries--;
	}

	public boolean hasNext() {
		return remainingTries > 0;
	}

	public KetamaNode next() {
		try {
			return getNodeForKey(hashVal);
		} finally {
			nextHash();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException("remove not supported");
	}

	private KetamaNode getNodeForKey(long hash) {

		if (!ketamaNodes.containsKey(hash)) {
			// Java 1.6 adds a ceilingKey method, but I'm still stuck in 1.5
			// in a lot of places, so I'm doing this myself.
			// 得到大于当前key的子Map，然后从中取出第一个key，就是大于且离它最近的那个key
			SortedMap<Long, KetamaNode> tailMap = ketamaNodes.tailMap(hash);
			if (tailMap.isEmpty()) {
				hash = ketamaNodes.firstKey();
			} else {
				hash = tailMap.firstKey();
			}

			// ceilingKey方法可以返回大于且离它最近的那个key
			// hash = ketamaNodes.ceilingKey(hash);
			// if (hash == null) {
			// hash = ketamaNodes.firstKey();
			// }

		}
		final KetamaNode rv = ketamaNodes.get(hash);
		return rv;
	}
}
