package qktest;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Scanner;

import qktest.file.SimpleFileFilter;

public class Main
{
	public static void main(String[] args)
	{		
		if (args.length < 1)
		{
			throw new IllegalArgumentException("No directory given to index.");
		}
		
		// Search just one directory
		// 
		// With a list of directories as input, d1, d2, d3, ...,
		// one directory may be a sub-directory of another.
		final File fRoot = new File(args[0]);
		if (!fRoot.isDirectory())
		{
			throw new IllegalArgumentException("Not a directory.");
		}
		
		FileFilter filter = SimpleFileFilter.getInstance();
		File[] files = fRoot.listFiles(filter);
		
		SearchEngine se = new SearchEngine(filter);
		se.gatherFiles(Arrays.asList(files));
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		while (true)
		{
			System.out.println("search> ");
			final String line = keyboard.nextLine();
			if (line.startsWith("exit"))
				break;
			se.searchWords(line);
		}
		System.out.println("Session ended");
	}

}
