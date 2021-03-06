package com.smartlogic.ontologyeditor.examples;

import java.io.IOException;

import com.smartlogic.ontologyeditor.OEClientException;
import com.smartlogic.ontologyeditor.OEClientReadWrite;
import com.smartlogic.ontologyeditor.beans.Concept;

public class DeleteConcept extends ModelManipulation {

	public static void main(String[] args) throws OEClientException, IOException {
		OEClientReadWrite oeClient = getOEClient(false);

		Concept concept = oeClient.getConcept("http://example.com/TestModel#MyThirdConcept");

		oeClient.deleteConcept(concept);
	}
}
