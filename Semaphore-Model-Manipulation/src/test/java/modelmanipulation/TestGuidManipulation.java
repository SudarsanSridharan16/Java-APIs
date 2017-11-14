package modelmanipulation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.smartlogic.semaphoremodel.ConceptScheme;
import com.smartlogic.semaphoremodel.ModelException;
import com.smartlogic.semaphoremodel.SemaphoreModel;
import com.smartlogic.semaphoremodel.Utils;

public class TestGuidManipulation {

	private SemaphoreModel semaphoreModel;
	
	@BeforeTest
	public void loadModel() {
		semaphoreModel = new SemaphoreModel(new File("src/test/resources/TestModels/ConceptSchemeTest.ttl"));
	}
	
	@AfterTest
	public void dumpModel() throws FileNotFoundException {
		semaphoreModel.write(new File("ConceptModelDump.ttl"));
	}
	
	@Test
	public void testGetGuid() throws URISyntaxException, ModelException {
		ConceptScheme conceptScheme = semaphoreModel.getConceptScheme(new URI("http://example.com/ConceptSchemeTest#ConceptScheme/MyFirstConceptScheme"));
		assertEquals(conceptScheme.getGuid(), "a72a37e5-0873-404e-ae05-017e12950f2b", "Supplied Guid");
		
		UUID uuid1 = Utils.generateUuid("This is a seed string");
		conceptScheme.updateGuid(uuid1);
		assertEquals(conceptScheme.getGuid(), uuid1.toString(), "Updated Guid");
		
		try {
			conceptScheme.addGuid(uuid1);
			fail("This GUID addition should be rejected");
		} catch (ModelException e) {
			assertEquals(e.getMessage(), "Concept http://example.com/ConceptSchemeTest#ConceptScheme/MyFirstConceptScheme already has a GUID present");
		}
		
		UUID uuid2 = Utils.generateUuid("This is another seed string");
		conceptScheme.setGuid(uuid2);
		assertEquals(conceptScheme.getGuid(), uuid2.toString(), "Set Guid");
		
		try {
			conceptScheme.deleteGuid(uuid1);
			fail("This GUID deletion should be rejected");
		} catch (ModelException e) {
			assertEquals(e.getMessage(), "Attempting to delete non-existent identifier '2cbf34f0-58c4-3461-8569-550dd4d94699' of http://example.com/ConceptSchemeTest#ConceptScheme/MyFirstConceptScheme");
		}
		
		conceptScheme.removeGuid(uuid1);
		assertNull(conceptScheme.getGuid(), "Removed Guid");
		
		conceptScheme.addGuid(uuid1);
		assertEquals(conceptScheme.getGuid(), uuid1.toString(), "Updated Guid");

		conceptScheme.deleteGuid(uuid1);
		assertNull(conceptScheme.getGuid(), "Deleted Guid");
		
		conceptScheme.setGuid(uuid2);
		assertEquals(conceptScheme.getGuid(), uuid2.toString(), "Set Guid");

	}

}