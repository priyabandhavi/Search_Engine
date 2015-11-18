import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BuildArticles {

	public static void build(){
		System.out.print("Building articles ...");
		File keywordDir = new File("original_dataset" + File.separator + "articles");
		File outputFile;
		try {
			int docNo = 0;
			for(String fileStr : keywordDir.list()){
				File file = new File(keywordDir + File.separator + fileStr);
				if(file.isFile()){
					docNo++;
					String title = null;
					String author = null;
					String fileId = fileStr;
					String place = null;
					String date = null;
					String content = null;
					boolean isTitle = false;
					boolean isAuthor = false;
					boolean timestamp = false;
					String buffer = "";
					BufferedReader in = new BufferedReader(new FileReader(file));
					while((buffer = in.readLine()) != null){
						buffer = buffer.replace("\r\n","").replace("\r","").replace("\n","");
						Pattern pat = Pattern.compile(".*[,].*\\s[-]{1}\\s.*");
						Matcher mat = pat.matcher(buffer);
						if(isTitle == false){
							if(!buffer.isEmpty()){
							  	title = buffer;
							  	isTitle = true;
							}
						}
						else if(isAuthor == false || timestamp == false){
							if(!buffer.isEmpty()){
								if(buffer.contains("<AUTHOR>")){
									Pattern pattern = Pattern.compile("<AUTHOR>(.+?)</AUTHOR>");
									Matcher matcher = pattern.matcher(buffer);
									matcher.find();
									buffer = matcher.group(1);
									buffer.trim();
									buffer = buffer.replace("By", "").replace("by", "").replace("BY", "");
									author = buffer.trim();
									isAuthor = true;
								}
								else if(mat.matches()){
									String[] array1 = buffer.split(" - ");
									buffer = array1[0].trim();
									String[] array2 = buffer.split(",");
									date = array2[array2.length-1].trim();
									place = "";
									for(int i=0;i<array2.length-2;i++){
										place += array2[i].trim() + ", ";
									}
									place += array2[array2.length-2].trim();
									content = "";
									for(int i=1;i<array1.length;i++){
										content += array1[i] + " ";
									}
									isAuthor = true;
									timestamp = true;
								}
								else{
									isAuthor = true;
									timestamp = true;
									content += buffer.trim() + " ";
								}
							}
						}
						else{
							content += buffer.trim();
							content += " ";
						}
					}
					in.close();
					if(content != null) {
						content = content.trim();
						content = content.replace("null", "");
						content = content.replace("Null", "");
						content = content.replace("NULL", "");
					}
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document docs = docBuilder.newDocument();
					Element rootElement = docs.createElement("add");
					docs.appendChild(rootElement);
					Element document = docs.createElement("doc");
					rootElement.appendChild(document);
					Attr[] attr = new Attr[11];
					Element[] field = new Element[11];
					for(int i=0; i<8; i++){
						field[i] = docs.createElement("field");
						attr[i] = docs.createAttribute("name");
					}
					field[0].appendChild(docs.createTextNode(docNo + ""));
					attr[0].setValue("docId");
					field[0].setAttributeNode(attr[0]);
					document.appendChild(field[0]);
					field[1].appendChild(docs.createTextNode(fileId));
					attr[1].setValue("doc");
					field[1].setAttributeNode(attr[1]);
					document.appendChild(field[1]);
					if(title != null){
						field[2].appendChild(docs.createTextNode(title));
						attr[2].setValue("title");
						field[2].setAttributeNode(attr[2]);
						document.appendChild(field[2]);
					}
					if(author != null){
						field[3].appendChild(docs.createTextNode(author));
						attr[3].setValue("author");
						field[3].setAttributeNode(attr[3]);
						document.appendChild(field[3]);
					}
					if(date != null){
						field[4].appendChild(docs.createTextNode(date));
						attr[4].setValue("date");
						field[4].setAttributeNode(attr[4]);
						document.appendChild(field[4]);
					}
					if(place != null){
						field[5].appendChild(docs.createTextNode(place));
						attr[5].setValue("place");
						field[5].setAttributeNode(attr[5]);
						document.appendChild(field[5]);
					}
					if(content != null){
						field[6].appendChild(docs.createTextNode(content));
						attr[6].setValue("content");
						field[6].setAttributeNode(attr[6]);
						document.appendChild(field[6]);
					}
					field[7].appendChild(docs.createTextNode("0"));
					attr[7].setValue("clicks");
					field[7].setAttributeNode(attr[7]);
					document.appendChild(field[7]);
					outputFile = new File("solr_dataset" + File.separator + "news_dataset" + File.separator + "news" + docNo + ".xml");
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(docs);
					StreamResult result = new StreamResult(outputFile);
					transformer.transform(source, result);
				}
			}
		}
		catch(Exception e) {}
		System.out.println(" done.");
	}
}