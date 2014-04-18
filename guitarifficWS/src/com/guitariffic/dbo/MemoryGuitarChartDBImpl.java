/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.guitariffic.model.GuitarChart;

public class MemoryGuitarChartDBImpl implements GuitarChartDBHelper {
    private static Map<String, GuitarChart> map = null;
    private static GuitarChartDBHelper instance;

    public MemoryGuitarChartDBImpl() {
        if (map == null) {
            map = loadFromXMLFile();
        }
    }

    /**
     * This method reads in a file that looks like this:
     * 
     * @formatter:off
     *                <chords>
     *                <chord>
     *                <chordName>A</chordName>
     *                <chordPosition>1</chordPosition>
     *                <chordFingering>X 123 </chordFingering>
     *                <chordFrets> 222</chordFrets>
     *                <isLeftHanded>FALSE</isLeftHanded>
     *                </chord>
     *                </chords>
     * @formatter:on
     *               and returns a map of the guitar charts it finds therein.
     */
    private Map<String, GuitarChart> loadFromXMLFile() {
        Map<String, GuitarChart> map = new HashMap<String, GuitarChart>();
        try {
            // URL fileUrl = this.getClass().getResource("/chords.xml");
            InputStream stream = ClassLoader.class.getResourceAsStream("/chords.xml");
            // File file = new File(fileUrl);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

            // optional, but recommended
            // read this -
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("chord");
            int id = 1;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String chordName =
                            eElement.getElementsByTagName("chordName").item(0).getTextContent();
                    String chordPosition =
                            eElement.getElementsByTagName("chordPosition").item(0)
                                    .getTextContent();
                    String chordFingering =
                            eElement.getElementsByTagName("chordFingering").item(0)
                                    .getTextContent();
                    String chordFrets =
                            eElement.getElementsByTagName("chordFrets").item(0).getTextContent();
                    boolean isLeftHanded =
                            eElement.getElementsByTagName("isLeftHanded").item(0).getTextContent()
                                    .equals("TRUE");
                    GuitarChart chart =
                            new GuitarChart(Integer.toString(id), chordName, chordPosition,
                                    chordFingering, chordFrets, isLeftHanded);
                    map.put(chart.getId(), chart);
                    id++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static GuitarChartDBHelper getInstance() {
        if (instance == null) {
            instance = new MemoryGuitarChartDBImpl();
        }
        return instance;
    }

    @Override
    public String add(GuitarChart chart) {
        String id = Integer.toString(map.size() + 1);
        chart.setId(id);
        map.put(id, chart);
        return id;
    }

    @Override
    public void update(GuitarChart chart, String id) {
        map.put(id, chart);
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public List<GuitarChart> getList(String search) {
        List<GuitarChart> list = new ArrayList<GuitarChart>();
        Iterator<Entry<String, GuitarChart>> it = map.entrySet().iterator();
        String searchUpper = search.toUpperCase();
        while (it.hasNext()) {
            Entry<String, GuitarChart> pairs = it.next();
            // Either get all chords or the ones that match the search criteria.
            String upperName = pairs.getValue().getChordName().toUpperCase();
            if (search.equals("") || search.isEmpty() || (upperName.contains(searchUpper))) {
                list.add(pairs.getValue());
            }
        }
        return list;
    }

    @Override
    public GuitarChart get(String id) {
        return map.get(id);
    }
}
