import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.TreeMap;

public class BuildKeywords {
	public static TreeMap<String, ArrayList<Double>> keywords = new TreeMap<String, ArrayList<Double>>();
	public static void build(){
		System.out.print("Building keywords ...");
		File keywordDir = new File("original_dataset" + File.separator + "keywords");
		try {
			for(String fileStr : keywordDir.list()){
				File file = new File(keywordDir + File.separator + fileStr);
				if(file.isFile()){
					String buffer = "";
					BufferedReader in;
					in = new BufferedReader(new FileReader(file));
					while((buffer = in.readLine()) != null){
						buffer = Normalizer.normalize(buffer, Normalizer.Form.NFKD);
						buffer = buffer.replaceAll("[^a-zA-Z0-9 ]+", "").toLowerCase();
						if(!keywords.containsKey(buffer) && !buffer.equals("")){
							ArrayList<Double> list = new ArrayList<Double>();
							list.add(0.0); list.add(0.0); list.add(0.0); list.add(0.0); list.add(0.0); list.add(0.0);
							keywords.put(buffer.trim(), list);
						}
					}
					in.close();
				}
			}
		}
		catch(Exception e) {}
		System.out.println(" done.");
	}
}