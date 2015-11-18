import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BuildWebpages {	
	public static void build(){
		System.out.print("Building webpages ...");
		File keywordDir = new File("original_dataset" + File.separator + "webpages");
		File outputFile;
		try {
			int docNo = 0;
			for(String fileStr : keywordDir.list()){
				File file = new File(keywordDir + File.separator + fileStr);
				if(file.isFile()){
					String buffer = "";
					BufferedReader in;
					in = new BufferedReader(new FileReader(file));
					while((buffer = in.readLine()) != null){
						buffer = buffer.toLowerCase();
						String[] array = buffer.split("\t");
						if(array.length > 5){
							array[1] = Normalizer.normalize(array[1], Normalizer.Form.NFKD);
							array[5] = Normalizer.normalize(array[5], Normalizer.Form.NFKD);
							String keyword = array[1].replaceAll("[^a-zA-Z0-9 ]+", "").toLowerCase().trim();
							if(BuildKeywords.keywords.containsKey(keyword)){
								docNo++;
								DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
								DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
								Document docs = docBuilder.newDocument();
								Element rootElement = docs.createElement("add");
								docs.appendChild(rootElement);
								Element document = docs.createElement("doc");
								rootElement.appendChild(document);
								Attr[] attr = new Attr[6];
								Element[] field = new Element[6];
								for(int i=0; i<6; i++){
									field[i] = docs.createElement("field");
									attr[i] = docs.createAttribute("name");
								}
								field[0].appendChild(docs.createTextNode(docNo + ""));
								attr[0].setValue("docId");
								field[0].setAttributeNode(attr[0]);
								field[1].appendChild(docs.createTextNode(array[0]));
								attr[1].setValue("doc");
								field[1].setAttributeNode(attr[1]);
								field[2].appendChild(docs.createTextNode(keyword));
								attr[2].setValue("keyword");
								field[2].setAttributeNode(attr[2]);
								field[3].appendChild(docs.createTextNode(array[2]));
								attr[3].setValue("link");
								field[3].setAttributeNode(attr[3]);
								field[4].appendChild(docs.createTextNode(array[5].replace(",", " ")));
								attr[4].setValue("desc");
								field[4].setAttributeNode(attr[4]);
								field[5].appendChild(docs.createTextNode("0"));
								attr[5].setValue("clicks");
								field[5].setAttributeNode(attr[5]);
								for(int i=0; i<6; i++){
									document.appendChild(field[i]);
								}
								outputFile = new File("solr_dataset" + File.separator + "ad_dataset" + File.separator + "ad" + docNo + ".xml");
								TransformerFactory transformerFactory = TransformerFactory.newInstance();
								Transformer transformer = transformerFactory.newTransformer();
								DOMSource source = new DOMSource(docs);
								StreamResult result = new StreamResult(outputFile);
								transformer.transform(source, result);
							}
						}
					}
					in.close();
				}
			}
		}
		catch(Exception e) {}
		System.out.println(" done.");
	}
	
	public static String round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue() + "";
	}
}