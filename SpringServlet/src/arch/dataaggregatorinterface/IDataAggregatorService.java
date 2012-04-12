package arch.dataaggregatorinterface;

import java.util.Map;

public interface IDataAggregatorService {
	
	public void addDataAggregator(IDataAggregator dataAggregator);
	public void removeDataAggregator(IDataAggregator dataAggregator);
	public Map<String, IDataAggregator> getDataAggregators();
	public IDataAggregator getAggregator(String type);
}
