package com.company.controller;

import com.company.model.PetInformation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    private static List<PetInformation> infoes;
    private final List<PetInformation> tableInfo = new ArrayList<PetInformation>();
    String pathFile;
    long timeForDateOfBirth;

    public static List<PetInformation> getInfoes() {
        return infoes;
    }

    private Node createBookElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private Node createBook(Document document, String tournament, String date, String sport, String winner, String fund, String benefits) {
        Element note = document.createElement("note");
        note.appendChild(createBookElement(document, "tournament", tournament));
        note.appendChild(createBookElement(document, "date", date));
        note.appendChild(createBookElement(document, "sport", sport));
        note.appendChild(createBookElement(document, "winner", winner));
        note.appendChild(createBookElement(document, "fund", fund));
        note.appendChild(createBookElement(document, "benefits", benefits));

        return note;
    }

    public void setBooks() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElementNS("", "table");
            document.appendChild(root);
            for (PetInformation info : infoes) {
                timeForDateOfBirth = info.getDate().getTime();

                String stringForDate = Long.toString(timeForDateOfBirth);

                root.appendChild(createBook(document, info.getTournament(), stringForDate, info.getSport(),
                        info.getWinner(), info.getFund(), info.getBenefits()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                DOMSource source = new DOMSource(document);
                File file = new File(pathFile);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(source, streamResult);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setInfoes(List<PetInformation> infs, String path) {
        infoes = infs;
        pathFile = path;
    }
}
