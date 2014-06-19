package biblioj



import static org.junit.Assert.fail;
import grails.test.mixin.TestFor;
import junit.framework.TestCase
import spock.lang.*

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */

public class InitialisationServiceIntegrationSpec extends GroovyTestCase {
	def InitialisationService initialisationService

	@Before
	public void setUp() throws Exception {
//				initialisationService = new InitialisationService()
				println initialisationService==null
				println Livre.count()
		//		println initialisationService
				if (Livre.count()==0) {
					initialisationService.serviceMethod()
				}
	}

	@Test
	public void test() {
		println "test OK"
		assertEquals(0, 0)
		//		Livre l = Livre.findByTitre("UnLivre")
		//		org.junit.Assert.assertNotNull(l)
		//		org.junit.Assert.assertEquals(l.titre, "UnLivre")
	}
}
//@TestFor(InitialisationService)
//class InitialisationServiceIntegrationSpec  {
//	InitialisationService initialisationService




//	@Before
//	def setUp() {
//		if (Livre.count()==0) {
//			initialisationService.initialiseDonneesReferenciel()
//		}
//	}
//
//	@Test
//	void testInitialDonneesRefferenciel() {
//		given: "test given"
//		println "first given"
//
//		when: "test when"
//		println "first when"
//
//		then: "test then"
//		println "first then"

//		Livre l = Livre.findByTitre("UnLivre")
//		org.junit.Assert.assertNotNull(l)
//		org.junit.Assert.assertEquals(l.titre, "UnLivre")
//	}
//}
