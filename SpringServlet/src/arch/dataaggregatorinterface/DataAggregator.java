package arch.dataaggregatorinterface;

import java.util.ArrayList;
import java.util.List;

import arch.datasourceinterface.IDataSource;

public abstract class DataAggregator implements IDataAggregator2 {

    protected List<IDataSource> dataSources = new ArrayList<IDataSource>();

    @Override
    public void addDataSource(IDataSource inf) {
        dataSources.add(inf);
    }

    @Override
    public void addDataSource(List<IDataSource> infs) {
    	dataSources = infs;
    }
    
	@Override
	public List<String> getColumnHeaders() {
		List<String> headers = new ArrayList<String>();
		
		headers.add("Company");
		headers.add("Area");
		headers.add("Revenue");
		
		return headers;
	}
}
