package arch.datadisplay.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arch.dataaggregate.IDataAggregate;
import arch.datadisplay.ui.internal.TableObject;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 590808281763644925L;
    private static final String ANALYZED_DATA = "Analyzed Data";
    private static final String ORIGINAL_DATA = "Original Data";
    
    private static IDataAggregate dataAggregateService = null;
    
    public void setDataAggregate(IDataAggregate da) {
    	dataAggregateService = da;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	StringBuilder buffer = new StringBuilder(200);
        PrintWriter writer = resp.getWriter();
        
        renderHeader(buffer);

        List<List<String>> list1 = dataAggregateService.getData(req.getParameter("type"));
        TableObject analyzedTable = new TableObject(list1, DisplayServlet.ANALYZED_DATA, buffer);
        renderTable(analyzedTable);

        List<List<String>> list2 = dataAggregateService.getData("identity");
        TableObject originalTable = new TableObject(list2, ORIGINAL_DATA, buffer);
        renderTable(originalTable);

        renderFooter(buffer);
        writer.write(buffer.toString());
    }

    private void renderFooter(StringBuilder buffer) {
        buffer.append("</body></html>");
    }

    private void renderTable(TableObject tableObject) {
        renderTableHeader(tableObject, tableObject.getTitle(), tableObject.getBuffer());

        for (int rowIdx = 1; rowIdx < tableObject.getRows(); rowIdx++) {
            renderTableRow(tableObject.getBuffer(), tableObject.getItem(rowIdx), rowIdx);
        }

        renderTableFooter(tableObject.getBuffer());
    }

    private void renderTableFooter(StringBuilder buffer) {
        buffer.append("</table>");
    }

    private void renderTableRow(StringBuilder buffer, List<String> list1, int i) {
        buffer.append("<tr><th scope=\"row\" class=\"spec\">" + list1.get(0) + "</th>");
        
        for(int j = 1; j < list1.size(); j++) {
        	buffer.append("<td>" + list1.get(j) + "</td>");
        }
        
        buffer.append("</tr>");
    }

    private void renderTableHeader(TableObject tableObject, String title, StringBuilder buffer) {
    	List<String> headers = tableObject.getItem(0);
    	
        buffer.append("<br><div class=title>" + title + "</div><br>");
        buffer.append("<table cellspacing=\"0\"><tr><th width=\"300px\" scope=\"col\" class=\"nobg\">" + headers.get(0) + "</th>");
        
        for(int i = 1; i < headers.size(); i++) {
        	buffer.append("<th width=\"200px\" scope=\"col\">" + headers.get(i) + "</th>");
        }
        buffer.append("</tr>");
    }

    private void renderHeader(StringBuilder buffer) {
    	buffer.append("<html><head>");
    	buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:8080/WeldServlet/styles.css\"/>");
    	buffer.append("</head><body>");
    	buffer.append("<img src=\"http://localhost:8080/WeldServlet/annual-revenues.jpg\"/><br>");
    }
}
