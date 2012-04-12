package arch.datasourceinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSourceServiceImpl implements IDataSourceService {
	private static List<IDataSource> dataSourceList = new ArrayList<IDataSource>();
	
	public void setDataSourceList(Map<String, IDataSource> list) {
		dataSourceList.addAll(list.values());
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
