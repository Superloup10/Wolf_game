package fr.wolf.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LanguageManager
{
	public LanguageManager instance;
	
	private File langFile;
	private FileReader langFileReader = null;
	private BufferedReader br = null;
	
	public LanguageManager(File file)
	{
		this.langFile = new File(file, "./fr_FR.xml");
		instance = this;
		
		if(langFile.exists())
		{
			try
			{
				this.langFileReader = new FileReader(langFile.getAbsoluteFile());
				this.br = new BufferedReader(langFileReader);
				while(true)
				{
					String ligne = br.readLine();
					if(ligne == null)
					{
						break;
					}
					System.out.println(ligne);
				}
			}catch(IOException ex)
			{
				System.out.println(ex);
			}
			finally
			{
				try
				{
					this.br.close();
					this.langFileReader.close();
				}catch(IOException ex)
				{
					System.out.println(ex);
				}
			}
		}
	}
}