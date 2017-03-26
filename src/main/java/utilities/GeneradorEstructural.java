
package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GeneradorEstructural {

	List<String>	a				= new ArrayList<String>();
	String			keyword			= "Finder";
	String			rutaEstructura	= "C:/Documents and Settings/Student/My Documents/Downloads/pruv/StringTo" + this.keyword + "Converter.java";
	String			rutaDestino		= "C:/Documents and Settings/Student/My Documents/Downloads/pruv";


	public static void main(final String[] args) {

		final GeneradorEstructural fr = new GeneradorEstructural();
		fr.doIt();

	}

	public void doIt() {
		this.a.add("Actor");
		this.a.add("Administrator");
		this.a.add("Application");
		this.a.add("Attachment");
		this.a.add("Banner");
		this.a.add("Comment");
		this.a.add("Commentable");
		this.a.add("Customer");
		this.a.add("Demand");
		this.a.add("Message");
		this.a.add("Offer");
		this.a.add("Place");
		this.a.add("Request");

		final File f1 = new File(this.rutaEstructura);
		final String lowKeyword = this.keyword.substring(0, 1).toLowerCase() + this.keyword.substring(1);
		System.out.println(lowKeyword);
		for (final String st : this.a) {
			final String lowst = st.substring(0, 1).toLowerCase() + st.substring(1);
			System.out.println(lowst);
			final List<String> lines = new ArrayList<String>();
			String line = null;
			try {
				final FileReader fr = new FileReader(f1);
				final BufferedReader br = new BufferedReader(fr);
				while ((line = br.readLine()) != null) {
					if (line.contains(this.keyword))
						line = line.replace(this.keyword, st);
					if (line.contains(lowKeyword))
						line = line.replace(lowKeyword, lowst);
					lines.add(line + "\r\n");
				}
				fr.close();
				br.close();
				String fileName = f1.getName().replace(this.keyword, st);
				fileName = fileName.replace(lowKeyword, lowst);
				final FileWriter fw = new FileWriter(this.rutaDestino + "/" + fileName);
				final BufferedWriter out = new BufferedWriter(fw);
				for (final String s : lines)
					out.write(s);
				out.flush();
				out.close();

			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
