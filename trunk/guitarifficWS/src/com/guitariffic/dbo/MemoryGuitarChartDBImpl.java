package com.guitariffic.dbo;

import java.io.File;
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
            //map = new HashMap<String, GuitarChart>();
            map = loadFromXMLFile();
        }
    }

    private Map<String, GuitarChart> loadFromXMLFile() {
        Map<String, GuitarChart> map = new HashMap<String, GuitarChart>();
        try {
            File fXmlFile = new File("/resources/chords.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            // optional, but recommended
            // read this -
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("staff");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : "
                            + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : "
                            + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : "
                            + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : "
                            + eElement.getElementsByTagName("salary").item(0).getTextContent());

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
            map = new HashMap<String, GuitarChart>();
        }
        return instance;
    }

    @Override
    public void add(GuitarChart chart) {
        map.put(chart.getId(), chart);
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
        while (it.hasNext()) {
            Entry<String, GuitarChart> pairs = it.next();
            list.add(pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return list;
    }

    @Override
    public GuitarChart get(String id) {
        return map.get(id);
    }
}
