package arch.dataaggregatorinterface;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Default
public class DataAggregatorServiceImpl implements IDataAggregatorService {
	
	Map<String, IDataAggregator> map = new HashMap<String, IDataAggregator>();
	
	@Inject 
	void initAggregators(@Any Instance<IDataAggregator> services) { 
	   for (IDataAggregator service: services) {
	      addDataAggregator(service);
	   }
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
