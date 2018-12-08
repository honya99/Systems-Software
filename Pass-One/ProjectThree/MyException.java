 	/* Honya Elfayoumy
   Systems Software COP3404 - Reddivari
   November 3, 2018 */


class MyException extends Exception{
	private String string;
	
	public MyException(String string){
		this.string = string;
	}
	
	public String toString(){
		return string;
	}
}