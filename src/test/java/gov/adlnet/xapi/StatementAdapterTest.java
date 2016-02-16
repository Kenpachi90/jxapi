package gov.adlnet.xapi;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gov.adlnet.xapi.model.Actor;
import gov.adlnet.xapi.model.Agent;
import gov.adlnet.xapi.model.IStatementObject;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.adapters.ActorAdapter;
import gov.adlnet.xapi.model.adapters.StatementObjectAdapter;
import junit.framework.TestCase;
import xapi.Import;

public class StatementAdapterTest extends TestCase {
	private String testString = "{\"actor\":{\"mbox\":\"mailto:tom@example.com\"}, \"verb\":{\"id\":\"http://adlnet.gov/expapi/verbs/asked\"}, \"object\":{\"id\":\"q://do/you/like/green-eggs-n-ham\"}}";
	
	private Gson gson;
	public StatementAdapterTest(){
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Actor.class, new ActorAdapter());
		builder.registerTypeAdapter(IStatementObject.class, new StatementObjectAdapter());
		gson = builder.create();
	}
	public void testDeserializeStatement() {
		FileReader read;
		try {
			read = new FileReader("C:\\Users\\Kenpachi\\Downloads\\test.json");
			Statement a = gson.fromJson(read, Statement.class);
			assert a.getActor().getClass() == Agent.class;
			assert a.getActor().getMbox().equals("mailto:tom@example.com");
			assert a.getVerb().getId().equals("http://adlnet.gov/expapi/verbs/asked");
			System.out.println("yahoo");
			xapi.Import.ImportStatement(a);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
