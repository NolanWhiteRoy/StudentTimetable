/*
 * Programmer: Nolan White-Roy 
 * Date Completed: Friday, May 1, 2020
 * Description: The program will create and modify an XML File that shows a students timeable.
 */
package module16;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Nolan
 */
public class Module16 {

    /**
     * @param args the command line arguments
     */
    // DocumentBuilder is used to create a document file which is later transformed into an XML Dile.
    static DocumentBuilder docBuilder = null;

    // This string has to be static to dodge an error. 
    static String file = "courses.xml";

    public static void main(String[] args) {
        // XML objects require try and catch statements or else they error out, so try entire main to make it easy.
        try {
            // Initialize docBuilder.
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Create a document referenced as "d".
            Document d = docBuilder.newDocument();

            // Create a Semester root element.
            Element root = d.createElement("Semseter");
            root.appendChild(d.createTextNode("S1 "));
            d.appendChild(root);

            // Creates a new course element we will add elements and text to.
            Element course = d.createElement("course");
            root.appendChild(course);

            // Creates a code element, appends text to it and appends the code to the course.
            Element code = d.createElement("code");
            code.appendChild(d.createTextNode("ICS4U "));
            course.appendChild(code);

            // Same as above but with description. 
            Element description = d.createElement("description");
            description.appendChild(d.createTextNode("Computer Programming, Grade 12, College "));
            course.appendChild(description);

            // Teacher
            Element teacher = d.createElement("teacher");
            teacher.appendChild(d.createTextNode("Teacher A ")); //Generic Teacher until edit
            course.appendChild(teacher);

            // File Type
            Element fileType = d.createElement("fileType");
            fileType.appendChild(d.createTextNode("Unmodified "));
            course.appendChild(fileType);

            // Repeat for other courses.
            Element course2 = d.createElement("course");
            root.appendChild(course2);

            Element code2 = d.createElement("code");
            code2.appendChild(d.createTextNode("ENG4U "));
            course2.appendChild(code2);

            Element teacher2 = d.createElement("teacher");
            teacher2.appendChild(d.createTextNode("Teacher B ")); //Generic teacher until edit
            course2.appendChild(teacher2);

            Element course3 = d.createElement("course");
            root.appendChild(course3);

            Element code3 = d.createElement("code");
            code3.appendChild(d.createTextNode("MCV4U "));
            course3.appendChild(code3);

            Element teacher3 = d.createElement("teacher");
            teacher3.appendChild(d.createTextNode("Teacher C ")); //Generic Teacher until edit
            course3.appendChild(teacher3);

            // Transforms the document from a document to an Xml File
            Transformer t = TransformerFactory.newInstance().newTransformer();
            DOMSource ds = new DOMSource(d);
            StreamResult sr = new StreamResult(new File(file));
            t.transform(ds, sr);

            // Calls edit function. 
            documentEdit();
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public static void documentEdit() {
        try {
            Document d = docBuilder.parse(file);  // Grab the xml file, change back to a document.

            // Runs for each name this loop needs to replace.
            for (int i = 0; i < 3; i++) {
           
                Node n = d.getElementsByTagName("teacher").item(i);

                if (n.getTextContent().equals("Teacher A ")) {
                    n.setTextContent("Mr. Theodoropoulos ");
                }
                
                else if (n.getTextContent().equals("Teacher B ")) {
                    n.setTextContent("Mr. Osborne ");
                }
                
                else if (n.getTextContent().equals("Teacher C ")) {
                    n.setTextContent("Mr. Armstrong ");
                }
            }

            for (int i = 0; i < 3; i++) {
                
                // Grabs the element i of the chosen type. 
                Node n = d.getElementsByTagName("course").item(i);

                Element schoolBoardName = d.createElement("schoolBoard");
                schoolBoardName.appendChild(d.createTextNode("AMDSB "));
                n.appendChild(schoolBoardName);
            }

            // Transforms the document back to an xml. 
            Transformer t = TransformerFactory.newInstance().newTransformer();
            DOMSource ds = new DOMSource(d);
            StreamResult sr = new StreamResult(new File(file));
            t.transform(ds, sr);

            // Wow! Look at all those catches!
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }

    }

}
