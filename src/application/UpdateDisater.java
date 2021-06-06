package application;

import java.io.IOException;
import java.util.Objects;

import org.json.simple.parser.ParseException;

import api.EqkExplorer;
import api.TpnExplorer;
import dao.EarthquakeDAO;
import dao.TyphoonDAO;
import dto.EarthquakeDTO;
import dto.TyphoonDTO;

public class UpdateDisater extends Thread {
    private EarthquakeDTO eDto = null;
    private TyphoonDTO tDto = null;

    public void refresh() {
        try {
            eDto = EqkExplorer.getEqkAPI();
            tDto = TpnExplorer.getTpnAPI();
        } catch (IOException | ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (!Objects.equals(eDto, null)) {
            EarthquakeDAO.insertData(eDto);
        }
        if (!Objects.equals(tDto, null)) {
            TyphoonDAO.insertData(tDto);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000*60*60*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            refresh();
        }
    }
        

}
