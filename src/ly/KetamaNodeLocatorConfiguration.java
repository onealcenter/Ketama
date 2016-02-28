package ly;

public interface KetamaNodeLocatorConfiguration {

	String getKeyForNode(KetamaNode node, int repetition);

	int getNodeRepetitions();
}
