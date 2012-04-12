package arch.dataaggregatorinterface;

import java.util.HashMap;
import java.util.Map;

public class DataAggregatorServiceImpl implements IDataAggregatorService {
	
	private static Map<String, IDataAggregator> map = new HashMap<String, IDataAggregator>();
	
	public void setDataAggregatorMap(Map<String, IDataAggregator> m) {
		map.putAll(m);
	}

	@Override
	public void addDataAggregator(IDataAggregator dataAggregator) {
		map.put(dataAggregator.getType(), dataAggregator);
	}

	@Override
	public void removeDataAggregator(IDataAggregator dataAggregator) {
		map.remove(dataAggregator.getType());
	}

	@Override
	public Map<String, IDataAggregator> getDataAggregators() {
		return map;
	}

	@Override
	public IDataAggregator getAggregator(String type) {
		return map.get(type);
	}
}
