  //Honya Elfayoumy

public class SICInstruction extends HashableObject{
	private String mnemonic = "", OPCode = "", miscellaneous = "";
	private int intByteSize = 0;

	public SICInstruction(String mnemonic, String OPCode, String byteSize, String miscellaneous){
		this.mnemonic = mnemonic;
		this.OPCode = OPCode;
		this.miscellaneous = miscellaneous;
		
		try{
			intByteSize = Integer.parseInt(byteSize);
		}
		catch(NumberFormatException e){}
		finally{
			setHashableString(mnemonic);
		}
	}
	
	public String getMnemonic(){
		return mnemonic;
	}
	
	public String getOPCode(){
		return OPCode;
	}
	
	public int getByteSize(){
		return intByteSize;
	}
	
	public String getMiscellaneous(){
		return miscellaneous;
	}
	
	public String toString(){
		return mnemonic + " " + OPCode + " " + intByteSize;
	}
}