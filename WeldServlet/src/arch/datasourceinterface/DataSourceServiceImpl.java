package arch.datasourceinterface;

import java.util.List;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Default
public class DataSourceServiceImpl implements IDataSourceService {
	private List<IDataSource> dataSourceList = new ArrayList<IDataSource>();
	
	@Inject 
	void initDataSources(@Any Instance<IDataSource> services) { 
	   for (IDataSource service: services) {
	      addDataSource(service);
	   }
	}
	
	@Override
	public void addDataSource(IDataSource dataSource) {
		if ( !dataSourceList.contains(dataSource) )
			dataSourceList.add(dataSource);
	}
	
	@Override
	public List<IDataSource> getDataSources() {
		return dataSourceList;
	}

	@Override
	public void removeDataSource(IDataSource dataSource) {
		if ( dataSourceList.contains(dataSource) )
			dataSourceList.remove(dataSource);
	}
}
