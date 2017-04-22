package com.oberthur.fileRenamer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRenamer {

	public static void main(String[] args) 
	{
		// parameter arguments: path <oldName> <newName> [isIncludeSubFolder]
		if(args.length < 3 || args.length > 4)
		{
			return;
		}
		
		File dir = new File(args[0]);
		
		String regexToRename = args[1];
		String regexNewName = args[2];
		
		boolean isIncludeSubFolder = false;
		
		rename(dir, regexToRename, regexNewName, isIncludeSubFolder);
	}
	
	private static final void rename(File dir, String regexToRename, String regexNewName, boolean isIncludeSubFolder)
	{
		if(dir.isDirectory())
		{
			for(final File file : dir.listFiles())
			{
				if(file.isFile())
				{
					renameFile(file, regexToRename, regexNewName);
				}
				else if(isIncludeSubFolder)
				{
					rename(file, regexToRename, regexNewName, isIncludeSubFolder);
			
				}
			}
		}
		else
		{
			renameFile(dir, regexToRename, regexNewName);
		}
	}
	
	private static final void renameFile(File file, String regexToRename, String regexNewName)
	{
		try
		{
			System.out.println(file.getName());
			
			System.out.println(regexToRename);
			
			Pattern pattern = Pattern.compile(regexToRename);
			Matcher matcher = pattern.matcher(file.getName());
			
			if(matcher.find())
			{
				//Rename the file
				File newFile = new File(file.getParentFile() + "/" + regexNewName);
				file.renameTo(newFile);
				System.out.println("Renamed: " + file.getName() + " -> " + newFile.getName());
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
