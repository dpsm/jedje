/**
 * Copyright (c) 2008 David Marques.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Marques - Adding EPL headers.                     
 */
package br.org.cesar.jedje.compiler.test;

import java.io.InputStream;

import jmunit.framework.cldc11.TestCase;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.parser.JEdjeParser;
import br.org.cesar.jedje.compiler.parser.JEdjeScanner;

public class JEdjeScannerTest extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public JEdjeScannerTest() {
		super(1, "JEdjeScannerTest");
	}

	/**
	 * Test for method: JEdjeToken scan().
	 * 
	 * @see br.org.cesar.jedje.compiler.parser.JEdjeScanner#scan()
	 */
	public final void scan1Test() {
		InputStream  stream  = this.getClass().getResourceAsStream("/test.edc");
		try {
			JEdjeScanner  scanner  = new JEdjeScanner(stream);
			JEdjeParser   parser   = new JEdjeParser(scanner);
			JEdjeDocument document = parser.parseDocument();
			assertNotNull(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A empty method used by the framework to initialize the tests. If there's
	 * 5 test methods, the setUp is called 5 times, one for each method. The
	 * setUp occurs before the method's execution, so the developer can use it
	 * to any necessary initialization. It's necessary to override it, however.
	 * 
	 * @throws Throwable anything that the initialization can throw.
	 */
	public void setUp() throws Throwable {
	}

	/**
	 * A empty mehod used by the framework to release resources used by the
	 * tests. If there's 5 test methods, the tearDown is called 5 times, one for
	 * each method. The tearDown occurs after the method's execution, so the
	 * developer can use it to close something used in the test, like a
	 * nputStream or the RMS. It's necessary to override it, however.
	 */
	public void tearDown() {
	}

	/**
	 * This method stores all the test methods invocation. The developer must
	 * implement this method with a switch-case. The cases must start from 0 and
	 * increase in steps of one until the number declared as the total of tests
	 * in the constructor, exclusive. For example, if the total is 3, the cases
	 * must be 0, 1 and 2. In each case, there must be a test method invocation.
	 * 
	 * @param testNumber the test to be executed.
	 * @throws Throwable anything that the executed test can throw.
	 */
	public void test(int testNumber) throws Throwable {
		switch (testNumber) {
		case 0:
			scan1Test();
			break;
		}
	}

}
