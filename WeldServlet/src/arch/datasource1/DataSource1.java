package arch.datasource1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import arch.datasourceinterface.DefaultDataItem;
import arch.datasourceinterface.IDataItem;
import arch.datasourceinterface.IDataSource;

@ApplicationScoped
@Named("datasource1")
public class DataSource1 implements IDataSource {

    private static String[] companies = { "BMW", "Mercedes", "Skoda", "Saab" };

    private List<IDataItem> itemsList = null;

    public List<IDataItem> getData() {
        if (this.itemsList != null)
            return this.itemsList;
        this.itemsList = new ArrayList<IDataItem>();

        Random r = new Random();

        for (int i = 0; i < companies.length; i++) {
            IDataItem di = new DefaultDataItem(companies[i], (int) (r.nextFloat() * 100000));
            di.setArea(getArea());
            this.itemsList.add(di);
        }

        return this.itemsList;
    }

    public String getArea() {
        return "Germany";
    }
}
