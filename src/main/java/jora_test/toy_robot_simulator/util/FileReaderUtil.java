package jora_test.toy_robot_simulator.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
	
	public interface ILineProcessor {
		Object processLine(String line, int lineNumber);
	}
	
	public static List<String> processLines(String filePath) throws IOException {
		List<String> result = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				result.add(line.trim());
			}
		}
		return result;
	}
}
