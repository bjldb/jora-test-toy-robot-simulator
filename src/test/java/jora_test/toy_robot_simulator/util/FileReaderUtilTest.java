package jora_test.toy_robot_simulator.util;

import java.util.List;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Assert.*;

public class FileReaderUtilTest {

	@Test
	public void test() throws IOException {
		List<String> lines = FileReaderUtil.processLines("testdata/singleMove.txt");
		String[] exp = { "PLACE 0,0,NORTH", "MOVE", "REPORT" };
		for(int i = 0; i < exp.length; i++) {
			assertEquals(exp[i], lines.get(i));
		}
	}

	@Test(expected=IOException.class)
	public void testFileNotFound() throws IOException {
		List<String> lines = FileReaderUtil.processLines("filenotfound");
	}
}