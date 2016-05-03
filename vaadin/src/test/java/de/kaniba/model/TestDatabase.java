package de.kaniba.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import kaniba.test.Utils;

public class TestDatabase {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Utils.prepareDatabaseForTests();
	}

	@Test
	public void testVerbindung() throws SQLException {
		Connection con = Database.verbindung();
		assertNotNull("No connection created", con);
	}

	@Test
	public void testReadBar() throws SQLException {
		Bar bar = Database.readBar(1);
		assertEquals("Descriptions differ", bar.getDescription(), "Eine hübsche kleine Bar im Herzen Karlsruhes.");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReadBarNegative() throws SQLException {
		Bar bar = Database.readBar(-1);
	}
	
	@Test
	public void testReadBarTooHigh() throws SQLException {
		Bar bar = Database.readBar(100000);
		assertNull("Invalid bar read", bar);
	}
}