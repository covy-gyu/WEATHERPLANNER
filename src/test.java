import java.io.IOException;


import org.json.simple.parser.ParseException;

import api.eqkExplorer;
import api.tpnExplorer;
import dao.EarthquakeDAO;
import dao.TyphoonDAO;

public class test {
    public static void main(String[] args) throws IOException, ParseException {
        // earthquakeDAO eqkDAO = new earthquakeDAO();
        // boolean result = false;
        // result = eqkDAO.insertData(eqkExplorer.getEqkAPI());
        // System.out.println(result);

        // earthquakeDAO eqkDAO1 = new earthquakeDAO();
        // System.out.println(eqkDAO1.selectData());

        TyphoonDAO tpnDAO = new TyphoonDAO();
        boolean result = false;
        result = tpnDAO.insertData(tpnExplorer.getTpnAPI());
        System.out.println(result);

        TyphoonDAO tpnDAO1 = new TyphoonDAO();
        System.out.println(tpnDAO1.selectData());
    }
}
