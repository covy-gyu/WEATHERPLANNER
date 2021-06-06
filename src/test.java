import java.io.IOException;


import org.json.simple.parser.ParseException;

import api.eqkExplorer;
import api.tpnExplorer;
import dao.earthquakeDAO;
import dao.typhoonDAO;

public class test {
    public static void main(String[] args) throws IOException, ParseException {
        // earthquakeDAO eqkDAO = new earthquakeDAO();
        // boolean result = false;
        // result = eqkDAO.insertData(eqkExplorer.getEqkAPI());
        // System.out.println(result);

        // earthquakeDAO eqkDAO1 = new earthquakeDAO();
        // System.out.println(eqkDAO1.selectData());

        typhoonDAO tpnDAO = new typhoonDAO();
        boolean result = false;
        result = tpnDAO.insertData(tpnExplorer.getTpnAPI());
        System.out.println(result);

        typhoonDAO tpnDAO1 = new typhoonDAO();
        System.out.println(tpnDAO1.selectData());
    }
}
